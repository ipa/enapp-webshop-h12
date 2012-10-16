/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.enapp.webshop.boundary;

import ch.hslu.enapp.webshop.lib.boundary.PurchaseManagerLocal;
import ch.hslu.enapp.webshop.lib.dataaccess.Purchase;
import ch.hslu.enapp.webshop.lib.dataaccess.PurchaseDAOLocal;
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
    
    @Override
    public void savePurchase(final Purchase purchase) {
        dao.savePurchase(purchase);
    }
}
