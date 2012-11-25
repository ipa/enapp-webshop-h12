/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.enapp.webshop.boundary;

import ch.hslu.enapp.webshop.lib.boundary.CustomerManagerLocal;
import ch.hslu.enapp.webshop.lib.dataaccess.Customer;
import ch.hslu.enapp.webshop.lib.dataaccess.CustomerDAOLocal;
import ch.hslu.enapp.webshop.lib.exceptions.BusinessException;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Admin
 */
@Stateless
public class CustomerManager implements CustomerManagerLocal {

    @Inject
    private CustomerDAOLocal dao;
    
    @Override
    public void saveCustomer(Customer customer) {
        this.dao.saveCustomer(customer);
    }
    
    @Override
    public List<Customer> getCustomers() {
        return this.dao.getCustomers();
    }

    @Override
    public Customer getCustomerByUsername(String username) {
        Customer customer = dao.getCustomerByName(username);
        return customer;
    }
    
    @Override
    public void removeCustomer(Customer customer) throws BusinessException{
        dao.removeCustomer(customer);
    }
}
