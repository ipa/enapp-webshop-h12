/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.enapp.webshop.boundary;

import ch.hslu.enapp.webshop.lib.boundary.PurchaseManagerLocal;
import ch.hslu.enapp.webshop.lib.dataaccess.Purchase;
import javax.ejb.Stateless;

/**
 *
 * @author iwan
 */
@Stateless
public class PurchaseManager implements PurchaseManagerLocal {

    @Override
    public void savePurchase(final Purchase purchase) {
    }
}
