/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.enapp.webshop.lib.boundary;

import ch.hslu.enapp.webshop.lib.dataaccess.Purchase;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author iwan
 */
@Local
public interface PurchaseManagerLocal {

    
    /**
     *
     * @param purchase
     */
    public void savePurchase(final Purchase purchase);

    public List<Purchase> getPurchasesByCustomer(final String username);
    
}
