/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.enapp.webshop.lib.boundary;

import ch.hslu.enapp.webshop.lib.dataaccess.Customer;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Admin
 */
@Local
public interface CustomerManagerLocal {

    void saveCustomer(Customer customer);

    List<Customer> getCustomers();

    Customer getCustomerByUsername(String username);
    
}
