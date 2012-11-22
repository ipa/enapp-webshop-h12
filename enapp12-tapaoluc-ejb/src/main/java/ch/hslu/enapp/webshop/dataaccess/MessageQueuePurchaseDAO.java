/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.enapp.webshop.dataaccess;

import ch.hslu.enapp.webshop.controller.PurchaseQueue;
import ch.hslu.enapp.webshop.controller.PurchaseQueueLocal;
import ch.hslu.enapp.webshop.lib.dataaccess.Customer;
import ch.hslu.enapp.webshop.lib.dataaccess.Purchase;
import ch.hslu.enapp.webshop.lib.dataaccess.PurchaseDAOLocal;
import ch.hslu.enapp.webshop.lib.dataaccess.PurchaseItem;
import ch.hslu.enapp.webshop.lib.quailiers.JMSPurchaseDAO;
import java.util.LinkedList;
import java.util.List;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

/**
 *
 * @author Admin
 */
@JMSPurchaseDAO
public class MessageQueuePurchaseDAO implements PurchaseDAOLocal {

    private PurchaseQueueLocal pq;
    
    @Inject
    @Default
    private PurchaseDAOLocal dao;
    
    public MessageQueuePurchaseDAO(){
        this.pq = new PurchaseQueue();
    }
    
    @Override
    public void savePurchase(Purchase purchase) {
        this.pq.enqueuePurchase(purchase);
        if(purchase.getCorrid() != null && !purchase.getCorrid().isEmpty()){
            //purchase.setPurchaseItems(new LinkedList<PurchaseItem>());
            this.dao.savePurchase(purchase);
        }
    }

    @Override
    public List<Purchase> getPurchaseByCustomer(Customer customer) {
        return this.dao.getPurchaseByCustomer(customer);
    }
    
}
