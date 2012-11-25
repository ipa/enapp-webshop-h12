/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.enapp.webshop.webapp.beans;

import ch.hslu.enapp.webshop.lib.boundary.CustomerManagerLocal;
import ch.hslu.enapp.webshop.lib.boundary.PurchaseManagerLocal;
import ch.hslu.enapp.webshop.lib.dataaccess.Customer;
import ch.hslu.enapp.webshop.lib.dataaccess.Purchase;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.PostActivate;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

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
    
    @Inject
    private CustomerManagerLocal cml;
    
    private List<Purchase> purchases;
    /**
     * Creates a new instance of Account
     */
    public Account() {
        this.purchases = new LinkedList<Purchase>();
    }
    
    @PostConstruct
    private void init(){
        if(session.isLoggedIn()){
            this.purchases.clear();
            this.purchases.addAll(this. pml.getPurchasesByCustomer(session.getUsername()));
        }
    }
    
    public List<Purchase> getPurchases(){
        return this.purchases;
    }

    public Customer getCustomer() {
        return this.cml.getCustomerByUsername(session.getUsername());
    }
    
    public void saveCustomer(Customer customer){
        this.cml.saveCustomer(customer);
    }
    
    public void refreshStates(){
        this.init();
    }
}
