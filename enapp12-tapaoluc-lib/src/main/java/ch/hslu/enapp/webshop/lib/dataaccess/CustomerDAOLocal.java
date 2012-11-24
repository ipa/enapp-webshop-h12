/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.enapp.webshop.lib.dataaccess;

import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Admin
 */
@Local
public interface CustomerDAOLocal {

    public List<Customer> getCustomers();
    
    public Customer getCustomerById(int id);

    Customer getCustomerByName(final String name);

    String getCustomerNoFromEnappDeamon(String corrid);

    void saveCustomer(Customer customer);
    
}
