/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.enapp.webshop.dataaccess;

import ch.hslu.enapp.webshop.entity.entities.PurchaseEntity;
import ch.hslu.enapp.webshop.entity.entities.PurchaseitemEntity;
import ch.hslu.enapp.webshop.entity.facade.PurchaseFacadeLocal;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import static org.junit.Assert.*;

/**
 *
 * @author Admin
 */
public class PurchaseDAOTest {
    
    public PurchaseDAOTest() {
    }
    
    @Mock 
    private PurchaseFacadeLocal pfl;
    @Mock
    private ProductDAOLocal pdl;
    @Mock
    private CustomerDAOLocal cdl;
    
    private PurchaseDAO dao;
    
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        
        this.dao = new PurchaseDAO();
        this.dao.setPurchaseFacade(pfl);
        this.dao.setProducDAO(pdl);
        this.dao.setCustomerDAO(cdl);
        
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                PurchaseEntity pe = (PurchaseEntity)invocation.getArguments()[0];
                assertTrue(pe.getCustomerid().getId() == 123);
                assertFalse(pe.getPurchaseitemCollection().isEmpty());
                assertTrue(pe.getStatus().equals("New"));
                for(PurchaseitemEntity e : pe.getPurchaseitemCollection()){
                    assertTrue(e.getProductid().getId() == 100);
                }
                return null;
            }
        }).when(pfl).create(any(new PurchaseEntity().getClass()));
        
        List<Product> products = new LinkedList<Product>();
        Product p = new Product();
        p.setId(100);
        p.setName("product");
        
        when(pdl.getProducts()).thenReturn(products);
        
        Customer c = new Customer();
        c.setId(123);
        when(cdl.getCustomerById(anyInt())).thenReturn(c);
    }

    @Test
    public void testSavePurchase() throws Exception {
        Purchase purchase = new Purchase();
        Customer c = new Customer();
        c.setId(123);
        purchase.setCustomer(c);
        purchase.setDatetime(new Date());        
        purchase.setStatus("New");
        List<PurchaseItem> pis = new LinkedList<PurchaseItem>();
        PurchaseItem item = new PurchaseItem();
        item.setDescription("");
        item.setQuantity(2L);
        item.setUnitprice(123L);
        item.setProductid(100);
        pis.add(item);
        purchase.setPurchaseItems(pis);
        
        // will throw exception when not working
        dao.savePurchase(purchase);
    }
}
