/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.enapp.webshop.navpurchase;

import javax.ejb.Local;

/**
 *
 * @author Admin
 */
@Local
public interface NavisionPurchaseClientLocal {

    NavisionPurchaseResponse getPurchaseStatus(String corrid);
    
}
