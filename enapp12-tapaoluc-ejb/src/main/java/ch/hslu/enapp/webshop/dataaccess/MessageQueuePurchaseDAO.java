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
import ch.hslu.enapp.webshop.navpurchase.NavisionPurchaseClient;
import ch.hslu.enapp.webshop.navpurchase.NavisionPurchaseResponse;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import sun.rmi.runtime.Log;

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
        final String STATUS_OK = "000";
        final String STATUS_NEWCUSTOMER = "010";
        List<Purchase> purchases = this.dao.getPurchaseByCustomer(customer);
        
        NavisionPurchaseClient client = new NavisionPurchaseClient();
        for(Purchase p : purchases){
            try{
                // when correlation id is not set go to nex 
                if(p.getCorrid() == null || p.getCorrid().isEmpty()){
                    continue;
                }
                // only update when status is not 010 or 000 or empty
                if(p.getNavStatus().isEmpty() || p.getNavStatus().equals("N/A") ||
                        p.getNavStatus().equals(STATUS_OK) || 
                        p.getNavStatus().equals(STATUS_NEWCUSTOMER)){
                    Logger.getGlobal().log(Level.INFO, "updating navstatus of corrid: {0}", p.getCorrid());
                    NavisionPurchaseResponse res = client.getPurchaseStatus(p.getCorrid());
                    p.setNavStatus(res.getStatus());
                }
            } catch(Exception ex){
                Logger.getGlobal().log(Level.WARNING, "could not get status of corrid: {0}", p.getCorrid());
            }
        }
        
        return purchases;
    }
    
}
