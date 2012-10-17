/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.enapp.webshop.lib.dataaccess;

import ch.hslu.enapp.webshop.lib.dataaccess.Purchase;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Admin
 */
@Local
public interface PurchaseDAOLocal {

    public void savePurchase(final Purchase purchase);

    public List<Purchase> getPurchaseByCustomer(final Customer customer);
    
}
