/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.enapp.webshop.webapp.admin.beans;

import ch.hslu.enapp.webshop.boundary.CustomerManager;
import ch.hslu.enapp.webshop.lib.boundary.CustomerManagerLocal;
import ch.hslu.enapp.webshop.lib.dataaccess.Customer;
import ch.hslu.enapp.webshop.lib.exceptions.BusinessException;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author Admin
 */
@ManagedBean
@SessionScoped
public class Customeradmin {

    @Inject
    private CustomerManagerLocal cml;
    
    private List<Customer> customers;
    
    /**
     * Creates a new instance of Customeradmin
     */
    public Customeradmin() {
        //this.cml = new CustomerManager();
        this.customers = new LinkedList<Customer>();
    }

    @PostConstruct
    void postConstruct(){
        this.customers.clear();
        this.customers.addAll(this.cml.getCustomers());
    }
    
    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }
    
    public void saveCustomer(Customer customer){
        this.cml.saveCustomer(customer);
    }
    
    public boolean messageIsSet(String clientId) {
        FacesContext ctx = FacesContext.getCurrentInstance();
        return ctx.getMessages(clientId).hasNext();
    }
    
    public void deleteCustomer(Customer customer){
        FacesContext ctx = FacesContext.getCurrentInstance();
        try{
            this.cml.removeCustomer(customer);
            this.postConstruct();
            ctx.addMessage("custsuccess", new FacesMessage("Customer successfully removed"));
        }catch(BusinessException ex){
            ctx.addMessage("custerror", new FacesMessage(ex.getMessage()));
        }
    }
}
