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
import java.util.LinkedList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author iwan
 */
@Stateless
public class PurchaseManager implements PurchaseManagerLocal {

    @Inject
    private PurchaseDAOLocal dao;
    
    @Inject
    private CustomerDAOLocal cdao;
    
    @Override
    public void savePurchase(final Purchase purchase) {
        dao.savePurchase(purchase);
    }

    @Override
    public List<Purchase> getPurchasesByCustomer(final String username) {
        Customer customer = cdao.getCustomerByName(username);
        if(customer != null){
            return dao.getPurchaseByCustomer(customer);
        }
        return new LinkedList<Purchase>();
    }
    
    
}
