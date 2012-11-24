/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.enapp.webshop.webapp.admin.beans;

import ch.hslu.enapp.webshop.boundary.CustomerManager;
import ch.hslu.enapp.webshop.lib.boundary.CustomerManagerLocal;
import ch.hslu.enapp.webshop.lib.dataaccess.Customer;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
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
    
    public void saveCustomer(String customer){
        Integer id = Integer.parseInt(customer);
        Customer search = new Customer();
        search.setId(id);
        int idx = Collections.binarySearch(customers, search, new Customer().getCustomerComparator());
        Customer found = this.customers.get(idx);
        
        this.cml.saveCustomer(found);
    }
}
