/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.enapp.webshop.boundary;

import ch.hslu.enapp.webshop.lib.boundary.BasketManagerLocal;
import ch.hslu.enapp.webshop.lib.boundary.ProductManagerLocal;
import ch.hslu.enapp.webshop.lib.boundary.PurchaseManagerLocal;
import ch.hslu.enapp.webshop.lib.dataaccess.CustomerDAOLocal;
import ch.hslu.enapp.webshop.lib.dataaccess.Product;
import ch.hslu.enapp.webshop.lib.dataaccess.Purchase;
import ch.hslu.enapp.webshop.lib.dataaccess.PurchaseItem;
import ch.hslu.enapp.webshop.lib.dataaccess.PurchaseStatus;
import ch.hslu.enapp.webshop.lib.model.BasketContent;
import ch.hslu.enapp.webshop.lib.model.BasketContentItem;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
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

    public BasketManager() {
        this.content = new BasketContent();
    }

    @Override
    public void addProduct(final Product product, int amaount) {
        this.content.insertProduct(product, amaount);
    }

    @Override
    public void addProduct(int productId, int amount) {
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
    public void checkout() {
        Purchase purchase = convertBasketToPurchase();

        // save purchase
        try {
            purchaseManager.savePurchase(purchase);
            this.content = new BasketContent();
        } catch (Exception ex) {
        }
    }

    private Purchase convertBasketToPurchase() {
        Purchase purchase = new Purchase();

        purchase.setCustomer(customerDAO.getCustomerById(1));
        purchase.setDatetime(new Date());
        purchase.setStatus(PurchaseStatus.NEW);
        // set the items
        List<PurchaseItem> pItems = new LinkedList<PurchaseItem>();
        for (BasketContentItem item : this.content.getItems()) {
            PurchaseItem pItem = new PurchaseItem();
            pItem.setDescription(item.getProduct().getDescription());
            pItem.setLineamount(1L);
            pItem.setProductid(item.getProduct().getId());
            pItem.setQuantity(item.getAmount());
            pItem.setUnitprice(item.getProduct().getUnitprice());
            pItems.add(pItem);
        }
        purchase.setPurchaseItems(pItems);
        return purchase;
    }
}
