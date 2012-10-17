/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.enapp.webshop.webapp.beans;

import ch.hslu.enapp.webshop.lib.boundary.PurchaseManagerLocal;
import ch.hslu.enapp.webshop.lib.dataaccess.Purchase;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author Admin
 */
@Named(value = "account")
@SessionScoped
public class Account implements Serializable {

    @Inject 
    private PurchaseManagerLocal pml;
    
    @Inject
    private UserSession session;
    
    /**
     * Creates a new instance of Account
     */
    public Account() {
    }
    
    public List<Purchase> getPurchases(){
        if(session.isLoggedIn()){
            return pml.getPurchasesByCustomer(session.getUsername());
        }
        return new LinkedList<Purchase>();
    }
}
