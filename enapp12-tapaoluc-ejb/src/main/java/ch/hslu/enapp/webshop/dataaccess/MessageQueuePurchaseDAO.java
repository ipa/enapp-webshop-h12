/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.enapp.webshop.dataaccess;

import ch.hslu.enapp.webshop.controller.PurchaseQueue;
import ch.hslu.enapp.webshop.lib.dataaccess.Customer;
import ch.hslu.enapp.webshop.lib.dataaccess.Purchase;
import ch.hslu.enapp.webshop.lib.dataaccess.PurchaseDAOLocal;
import ch.hslu.enapp.webshop.lib.quailiers.JMSPurchaseDAO;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author Admin
 */
@JMSPurchaseDAO
public class MessageQueuePurchaseDAO implements PurchaseDAOLocal {

    @Inject
    private PurchaseQueue pq;
    
    @Override
    public void savePurchase(Purchase purchase) {
        this.pq.enqueuePurchase(purchase);
    }

    @Override
    public List<Purchase> getPurchaseByCustomer(Customer customer) {
        return this.pq.getPurchaseById(customer);
    }
    
}
