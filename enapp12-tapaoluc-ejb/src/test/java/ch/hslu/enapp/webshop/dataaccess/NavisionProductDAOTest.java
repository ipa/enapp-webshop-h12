/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.enapp.webshop.dataaccess;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Admin
 */
public class NavisionProductDAOTest {
    
    public NavisionProductDAOTest() {
    }

    @Test
    public void testGetProducts() throws Exception {
        NavisionProductDAO dao = new NavisionProductDAO();
        dao.getProducts();
    }

    @Test
    public void testGetProductById() throws Exception {
    }
}
