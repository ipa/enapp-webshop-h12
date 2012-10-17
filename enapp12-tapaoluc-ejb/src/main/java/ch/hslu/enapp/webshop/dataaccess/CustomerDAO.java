/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.enapp.webshop.dataaccess;

import ch.hslu.enapp.webshop.lib.dataaccess.Customer;
import ch.hslu.enapp.webshop.lib.dataaccess.CustomerDAOLocal;
import ch.hslu.enapp.webshop.entity.entities.CustomerEntity;
import ch.hslu.enapp.webshop.entity.facade.CustomerFacadeLocal;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import org.modelmapper.ModelMapper;

/**
 *
 * @author Admin
 */
@Stateless
@Default
public class CustomerDAO implements CustomerDAOLocal {
    @Inject
    private CustomerFacadeLocal cf;
    
    @Override
    public List<Customer> getCustomers() {
        List<Customer> list = new LinkedList<Customer>();

        List<CustomerEntity> entityList = cf.findAll();
        ModelMapper mapper = new ModelMapper();
        for(CustomerEntity ce : entityList){
            list.add(mapper.map(ce, Customer.class));
        }
        return list;
    }
    
    @Override
    public Customer getCustomerById(int id){
        List<Customer> list = this.getCustomers();
        for(Customer c : list){
            if(c.getId() == id){
                return c;
            }
        }
        return null;
    }    
    
    @Override
    public Customer getCustomerByName(final String name) {
        List<Customer> list = this.getCustomers();
        for(Customer c : list){
            if(c.getUsername().equals(name)){
                return c;
            }
        }
        return null;
    }
    
    /** Just for UnitTests **/
    void setCustomerFacade(final CustomerFacadeLocal facade){
        this.cf = facade;
    }

}
