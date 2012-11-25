/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.enapp.webshop.lib.dataaccess;

import ch.hslu.enapp.webshop.lib.exceptions.BusinessException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Admin
 */
@Local
public interface CustomerDAOLocal {

    List<Customer> getCustomers();
    
    Customer getCustomerById(int id);

    Customer getCustomerByName(final String name);

    String getCustomerNoFromEnappDeamon(String corrid);

    void saveCustomer(Customer customer);

    void removeCustomer(Customer customer) throws BusinessException;
    
}
