/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.enapp.webshop.controller;

import ch.hslu.enapp.webshop.controller.XML.XMLCustomer;
import ch.hslu.enapp.webshop.controller.XML.XMLPurchase;
import ch.hslu.enapp.webshop.controller.XML.XMLPurchaseLine;
import ch.hslu.enapp.webshop.lib.dataaccess.Customer;
import ch.hslu.enapp.webshop.lib.dataaccess.Purchase;
import ch.hslu.enapp.webshop.lib.dataaccess.PurchaseItem;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.LocalBean;
import javax.ejb.MessageDriven;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.QueueConnectionFactory;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author Admin
 */
@MessageDriven(mappedName = "jms/purchasequeue", activationConfig = {
    @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class PurchaseQueue implements MessageListener, PurchaseQueueLocal {
    private static final String STUDENT = "tapaoluc";
    
    public PurchaseQueue() {
    }
    
    @Override
    public void onMessage(Message message) {
        System.out.println("got message");
    }

    @Override
    public List<Purchase> getPurchaseById(Customer customer) {
        return new LinkedList<Purchase>();
    }

    @Override
    public void enqueuePurchase(Purchase purchase){
        
        try {
            Context c = new InitialContext();
            ConnectionFactory cf = (ConnectionFactory) c.lookup("jms/purchasequeuefactory");
            Connection conn = null;
            Session s = null;
            
            try {
                String xml = this.getPurchaseAsXml(purchase);

                conn = cf.createConnection();
                s = conn.createSession(false, s.AUTO_ACKNOWLEDGE);
                Destination destination = (Destination) c.lookup("jms/purchasequeue");
                MessageProducer mp = s.createProducer(destination);

                TextMessage txtMessage = s.createTextMessage(xml);
                String correlationid = this.getCorrelationId();
                purchase.setCorrid(correlationid);
                
                txtMessage.setJMSCorrelationID(correlationid);
                txtMessage.setStringProperty("MessageFormat", "Version 1.5");
                
                //Destination tempQueue = s.createTemporaryQueue();
                //txtMessage.setJMSReplyTo(tempQueue);
                
                conn.start();
                mp.send(txtMessage);
                
                System.out.println(xml);
            } catch (JMSException ex) {
                Logger.getLogger(PurchaseQueue.class.getName()).log(Level.SEVERE, null, ex);
            } catch(Exception ex){
                Logger.getLogger(PurchaseQueue.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                if (s != null) {
                    try {
                        s.close();
                    } catch (JMSException e) {
                        Logger.getLogger(this.getClass().getName()).log(Level.WARNING, "Cannot close session", e);
                    }
                }
                if(conn != null){
                    try {
                        conn.close();
                    } catch (JMSException ex) {
                        Logger.getLogger(PurchaseQueue.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        } catch(NamingException ex){
            Logger.getLogger(PurchaseQueue.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private String getPurchaseAsXml(Purchase purchase){
        String xml = "";
        XMLPurchase xmlObj = new XMLPurchase();
        // set root xml
        xmlObj.setDate(purchase.getDatetime().toString());
        xmlObj.setPayId(purchase.getPayid());
        xmlObj.setPurchaseId(purchase.getNo().toString());
        xmlObj.setStudent(STUDENT);
        xmlObj.setTotalPrice(purchase.getTotalPriceAsString());
        
        // set customer
        XMLCustomer c = xmlObj.getCustomer();
        c.setAddress(purchase.getCustomer().getAddress());
        c.setCity("Lucerne");
        // set dyn nav id if available
        if(purchase.getCustomer().getNavCustomerNo() != null &&
                !purchase.getCustomer().getNavCustomerNo().isEmpty()){
            c.setDynNavCustNo(purchase.getCustomer().getNavCustomerNo());
        }
        c.setName(purchase.getCustomer().getName() + " " + purchase.getCustomer().getName());
        c.setPostCode("6003");
        c.setShopLoginname(purchase.getCustomer().getUsername());
        
        // set lines
        List<PurchaseItem> items = purchase.getPurchaseItems();
        for(PurchaseItem item : items){
            XMLPurchaseLine line = new XMLPurchaseLine();
            line.setQuantity(item.getQuantity().toString());
            line.setTotalLinePrice(item.geTotalPriceAsString());
            line.setMsDynNAVItemNo(item.getProductNo());
            line.setDescription(item.getDescription());
            xmlObj.getLines().add(line);
        }
        
        return xmlObj.getXml();
    }
    
    private String getCorrelationId(){
        Long correlationId =  (new Random().nextInt(8999) +  1000) 
                        * 10000000000000l
                        + Calendar.getInstance().getTimeInMillis();
        return correlationId.toString();
    }
}
