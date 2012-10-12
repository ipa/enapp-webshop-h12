/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.enapp.webshop.webapp.beans;

import ch.hslu.enapp.webshop.lib.boundary.BasketManagerLocal;
import ch.hslu.enapp.webshop.lib.dataaccess.Customer;
import ch.hslu.enapp.webshop.lib.dataaccess.Purchase;
import ch.hslu.enapp.webshop.lib.dataaccess.PurchaseDAOLocal;
import ch.hslu.enapp.webshop.lib.dataaccess.PurchaseItem;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

/**
 *
 * @author Admin
 */
@Named(value = "index")
@SessionScoped
public class Index implements Serializable {

    @Inject @Default
    private PurchaseDAOLocal dao;
    
    @Inject
    private BasketManagerLocal bml;
    
    /**
     * Creates a new instance of Index
     */
    public Index() {
    }
    
    public void addPurchase(){
        Purchase purchase = new Purchase();
        Customer c = new Customer();
        c.setId(1);
        purchase.setCustomer(c);
        purchase.setDatetime(new Date());        
        purchase.setStatus("New");
        List<PurchaseItem> pis = new LinkedList<PurchaseItem>();
        PurchaseItem item = new PurchaseItem();
        item.setDescription("");
        item.setQuantity(2L);
        item.setUnitprice(123L);
        item.setProductid(1);
        pis.add(item);
        purchase.setPurchaseItems(pis);
        
        // will throw exception when not working
        dao.savePurchase(purchase);
    }
}
