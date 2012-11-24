/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.enapp.webshop.boundary;

import ch.hslu.enapp.webshop.lib.boundary.PurchaseManagerLocal;
import ch.hslu.enapp.webshop.lib.dataaccess.Customer;
import ch.hslu.enapp.webshop.lib.dataaccess.CustomerDAOLocal;
import ch.hslu.enapp.webshop.lib.dataaccess.Purchase;
import ch.hslu.enapp.webshop.lib.dataaccess.PurchaseDAOLocal;
import ch.hslu.enapp.webshop.lib.quailiers.JMSPurchaseDAO;
import ch.hslu.enapp.webshop.navpurchase.NavisionPurchaseClient;
import ch.hslu.enapp.webshop.navpurchase.NavisionPurchaseResponse;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author iwan
 */
@Stateless
public class PurchaseManager implements PurchaseManagerLocal {

    @Inject
    @JMSPurchaseDAO
    private PurchaseDAOLocal dao;
    
    
    @Inject
    private CustomerDAOLocal cdao;
    
    @Override
    public void savePurchase(final Purchase purchase) {
        dao.savePurchase(purchase);
        
        CustomerUpdater updater = new CustomerUpdater(purchase.getCorrid(), purchase.getCustomer().getUsername());
        Thread t = new Thread(updater);
        t.start();
    }

    @Override
    public List<Purchase> getPurchasesByCustomer(final String username) {
        Customer customer = cdao.getCustomerByName(username);
        if(customer != null){
            return dao.getPurchaseByCustomer(customer);
        }
        return new LinkedList<Purchase>();
    }

    private class CustomerUpdater implements Runnable{
        private String corrid;
        private String cname;

        public CustomerUpdater(String corrid, String cname) {
            this.corrid = corrid;
            this.cname = cname;
        }
        
        @Override
        public void run() {
            try {
                Thread.sleep(10000);
                this.updateCustomer(this.corrid, this.cname);
            } catch (InterruptedException ex) {
                Logger.getLogger(PurchaseManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        private void updateCustomer(String corrid, String cname) {
            NavisionPurchaseClient client = new NavisionPurchaseClient();
            NavisionPurchaseResponse res = client.getPurchaseStatus(corrid);

            Customer c = cdao.getCustomerByName(cname);
            c.setNavCustomerNo(res.getDynNAVCustomerNo());
        }
    } 
}
