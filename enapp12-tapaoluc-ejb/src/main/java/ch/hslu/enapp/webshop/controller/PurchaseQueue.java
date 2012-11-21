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
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.QueueConnectionFactory;
import javax.jms.Session;
import javax.jms.TextMessage;

/**
 *
 * @author Admin
 */
@MessageDriven(mappedName = "jms/purchasequeue", activationConfig = {
    @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class PurchaseQueue implements MessageListener, PurchaseQueueLocal {
    
    
    @Resource(mappedName = "jms/purchasequeuefactory")
    private QueueConnectionFactory connectionFactory;

    @Resource(mappedName = "jms/purchasequeue")
    private Queue queue;
    
    private static final String STUDENT = "tapaoluc";
    
    public PurchaseQueue() {
    }
    
    @Override
    public void onMessage(Message message) {
    }

    @Override
    public List<Purchase> getPurchaseById(Customer customer) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void enqueuePurchase(Purchase purchase){
        Connection conn = null;
        Session s = null;
        
        try {
            String xml = this.getPurchaseAsXml(purchase);
            
            conn = connectionFactory.createConnection();
            s = conn.createSession(false, s.AUTO_ACKNOWLEDGE);
            MessageProducer mp = s.createProducer(queue);
           
            TextMessage txtMessage = s.createTextMessage(xml);
            String correlationid = this.getCorrelationId();
            purchase.setCorrid(correlationid);
            txtMessage.setJMSCorrelationID(correlationid);
            txtMessage.setStringProperty("MessageFormat", "Version 1.5");
            
            mp.send(txtMessage);
        } catch (JMSException ex) {
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
    }
    
    private String getPurchaseAsXml(Purchase purchase){
        String xml = "";
        XMLPurchase xmlObj = new XMLPurchase();
        // set root xml
        xmlObj.setDate(purchase.getDatetime().toString());
        xmlObj.setPayId(purchase.getPayid());
        xmlObj.setPurchaseId(purchase.getId().toString());
        xmlObj.setStudent(STUDENT);
        xmlObj.setTotalPrice(purchase.getTotalPriceAsString());
        
        // set customer
        XMLCustomer c = xmlObj.getCustomer();
        c.setAddress(purchase.getCustomer().getAddress());
        c.setCity("Lucerne");
        c.setDynNavCustNo(purchase.getCustomer().getId().toString());
        c.setName(purchase.getCustomer().getName());
        c.setPostCode("6003");
        c.setShopLoginname("kkk");
        
        // set lines
        List<PurchaseItem> items = purchase.getPurchaseItems();
        for(PurchaseItem item : items){
            XMLPurchaseLine line = new XMLPurchaseLine();
            line.setQuantity(item.getQuantity().toString());
            line.setTotalLinePrice(item.geTotalPriceAsString());
            line.setMsDynNAVItemNo(item.getProductNo());
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
