/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.enapp.webshop.dataaccess;

import ch.hslu.enapp.webshop.lib.dataaccess.Customer;
import ch.hslu.enapp.webshop.lib.dataaccess.CustomerDAOLocal;
import ch.hslu.enapp.webshop.entity.entities.CustomerEntity;
import ch.hslu.enapp.webshop.entity.entities.CustomergroupsEntity;
import ch.hslu.enapp.webshop.entity.facade.CustomerFacadeLocal;
import ch.hslu.enapp.webshop.entity.facade.CustomergroupsEntityFacadeLocal;
import ch.hslu.enapp.webshop.lib.exceptions.AdministratorCannotBeRemovedException;
import ch.hslu.enapp.webshop.lib.exceptions.BusinessException;
import ch.hslu.enapp.webshop.lib.exceptions.CustomerRemoveFailedException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    @Inject
    private CustomergroupsEntityFacadeLocal cgf;
    
    private static final String GROUP_USERS =  "users";
    
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
    
    @Override
    public String getCustomerNoFromEnappDeamon(String corrid) {
        return "";
        //throw new NotImplementedException();
    }
    
    @Override
    public void saveCustomer(Customer customer) {
         Logger.getGlobal().log(Level.INFO, "save customer {0}", customer.getUsername());
         ModelMapper mapper = new ModelMapper();
         CustomerEntity ce; // = this.cf.getCustomerById(customer.getId());
         ce = mapper.map(customer, CustomerEntity.class);
         this.cf.edit(ce);
         
         Logger.getGlobal().log(Level.INFO, "add customer {0} to group users", customer.getUsername());
         CustomergroupsEntity group = new CustomergroupsEntity();
         group.setGroupname(GROUP_USERS);
         group.setUsername(ce.getUsername());
         this.cgf.edit(group);
    }
    
    @Override
    public void removeCustomer(Customer customer) throws BusinessException{
        if(customer.getUsername().equals("admin")){
            throw new AdministratorCannotBeRemovedException();
        }
        try{
            CustomerEntity ce = this.cf.getCustomerById(customer.getId());
            this.cf.remove(ce);
        } catch(Exception ex){
            Logger.getGlobal().log(Level.WARNING, "could not delete customer {0}{1}", 
                    new Object[]{customer.getId(), ex.getMessage()});
            throw new CustomerRemoveFailedException();
        }
    }
    
    /** Just for UnitTests **/
    void setCustomerFacade(final CustomerFacadeLocal facade){
        this.cf = facade;
    }
}
