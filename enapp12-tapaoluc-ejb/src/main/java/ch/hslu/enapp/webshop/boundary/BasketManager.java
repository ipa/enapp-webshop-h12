/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.enapp.webshop.boundary;

import ch.hslu.enapp.webshop.lib.boundary.BasketManagerLocal;
import ch.hslu.enapp.webshop.lib.boundary.PaymentManagerLocal;
import ch.hslu.enapp.webshop.lib.boundary.ProductManagerLocal;
import ch.hslu.enapp.webshop.lib.boundary.PurchaseManagerLocal;
import ch.hslu.enapp.webshop.lib.dataaccess.Customer;
import ch.hslu.enapp.webshop.lib.dataaccess.CustomerDAOLocal;
import ch.hslu.enapp.webshop.lib.dataaccess.Product;
import ch.hslu.enapp.webshop.lib.dataaccess.Purchase;
import ch.hslu.enapp.webshop.lib.dataaccess.PurchaseItem;
import ch.hslu.enapp.webshop.lib.dataaccess.PurchaseStatus;
import ch.hslu.enapp.webshop.lib.exceptions.BusinessException;
import ch.hslu.enapp.webshop.lib.exceptions.PaymentUnsuccessfulException;
import ch.hslu.enapp.webshop.lib.model.BasketContent;
import ch.hslu.enapp.webshop.lib.model.BasketContentItem;
import ch.hsu.enapp.webshop.payment.model.PaymentResponse;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.EJBException;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;
import javax.inject.Inject;

/**
 *
 * @author Admin
 */
@Stateful
public class BasketManager implements BasketManagerLocal {

    @Inject
    private CustomerDAOLocal customerDAO;
    @Inject
    private ProductManagerLocal pml;
    @Inject
    private PurchaseManagerLocal purchaseManager;
    private BasketContent content;

    @Inject
    private PaymentManagerLocal paymgr;
    
    @Resource
    private SessionContext ejbContext;
    
    public BasketManager() {
        this.content = new BasketContent();
    }

    @Override
    public void addProduct(final Product product, int amaount) {
        this.content.insertProduct(product, amaount);
    }

    @Override
    public void addProduct(String productId, int amount) {
        Product p = pml.getProductById(productId);
        this.addProduct(p, amount);
    }

    @Override
    public int getNumberOfProducts() {
        return this.content.count();
    }

    @Override
    public BasketContent getBasketContent() {
        return this.content;
    }

    @Override
    public void checkout(Map<String, String> map) throws BusinessException{
        // only for logged in users
        String username = ejbContext.getCallerPrincipal().getName();
        Customer customer = customerDAO.getCustomerByName(username);

        Purchase purchase = convertBasketToPurchase();
        String orderid = new Date().toString();
        List<PurchaseItem> items = purchase.getPurchaseItems();
        long amount = 0;
        for(PurchaseItem pi : items){
            amount += (pi.getQuantity() + pi.getUnitprice()) * 100;
        }
        PaymentResponse payid;
        try {
            payid = (PaymentResponse) this.paymgr.pay(map, amount, orderid);
        } catch (PaymentUnsuccessfulException ex) {
            throw ex;
        }
        
        if(payid == null){
            throw new BusinessException("payment did not work");
        }
                
        purchase.setCustomer(customer);
        purchase.setPayid(payid.getPaymentid());
        
        // save purchase
        try {
            //purchase.setPurchaseItems(new LinkedList<PurchaseItem>());
            purchaseManager.savePurchase(purchase);
            
        } catch (Exception ex) {
            throw new EJBException(ex);
        }finally{
            this.content = new BasketContent();
        }
    }

    private Purchase convertBasketToPurchase() {
        Purchase purchase = new Purchase();

        purchase.setDatetime(new Date());
        purchase.setStatus(PurchaseStatus.NEW);
        // set the items
        List<PurchaseItem> pItems = new LinkedList<PurchaseItem>();
        for (BasketContentItem item : this.content.getItems()) {
            PurchaseItem pItem = new PurchaseItem();
            pItem.setDescription(item.getProduct().getName());
            pItem.setLineamount(1L);
            pItem.setProductid(item.getProduct().getId());
            pItem.setQuantity(item.getAmount());
            pItem.setUnitprice(item.getProduct().getUnitprice());
            pItem.setProductNo(item.getProduct().getNumber());
            pItems.add(pItem);
        }
        purchase.setPurchaseItems(pItems);
        return purchase;
    }
}
