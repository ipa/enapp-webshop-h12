/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.enapp.webshop.controller;

import ch.hslu.enapp.webshop.lib.dataaccess.Customer;
import ch.hslu.enapp.webshop.lib.dataaccess.Purchase;
import java.util.List;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;

/**
 *
 * @author Admin
 */
@MessageDriven(mappedName = "jms/purchasequeue", activationConfig = {
    @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class PurchaseQueue implements MessageListener {
    
    public PurchaseQueue() {
    }
    
    @Override
    public void onMessage(Message message) {
    }

    public List<Purchase> getPurchaseById(Customer customer) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public void enqueuePurchase(Purchase purchase) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
