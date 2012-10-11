/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.enapp.webshop.dataaccess;

import javax.ejb.Local;

/**
 *
 * @author Admin
 */
@Local
public interface PurchaseDAOLocal {

    void savePurchase(final Purchase purchase);
    
}
