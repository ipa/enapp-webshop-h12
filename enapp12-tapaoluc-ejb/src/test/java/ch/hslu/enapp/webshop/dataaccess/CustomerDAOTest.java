/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.enapp.webshop.dataaccess;

import ch.hslu.enapp.webshop.lib.dataaccess.Customer;
import ch.hslu.enapp.webshop.entity.entities.CustomerEntity;
import ch.hslu.enapp.webshop.entity.facade.CustomerFacadeLocal;
import java.util.LinkedList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;
import static org.junit.Assert.*;

/**
 *
 * @author Admin
 */
public class CustomerDAOTest {
    
    public CustomerDAOTest() {
    }
    
    @Mock 
    private CustomerFacadeLocal cfl;
    
    private static CustomerDAO dao;
    
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        
        dao = new CustomerDAO();
        dao.setCustomerFacade(cfl);
        
        List<CustomerEntity> list = new LinkedList<CustomerEntity>();
        CustomerEntity entity = new CustomerEntity(123, "duude");
        entity.setAddress("strasse");
        entity.setEmail("duude@strasse.ch");
        entity.setName("duderino");
        entity.setPassword("secret");
        list.add(entity);
        when(cfl.findAll()).thenReturn(list);
    }

    @Test
    public void testGetCustomers() throws Exception {
        List<Customer> customers = dao.getCustomers();
        assertFalse(customers.isEmpty());
        Customer c = customers.get(0);
        assertTrue(c.getUsername().equals("duude"));
        assertTrue(c.getName().equals("duderino"));
        assertEquals(c.getAddress(), "strasse");
        assertTrue(c.getId() == 123);
        assertEquals(c.getPassword(), "secret");
    }
    
    
}
