/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.enapp.webshop.webapp.beans;

import ch.hslu.enapp.webshop.boundary.ProductManager;
import ch.hslu.enapp.webshop.dataaccess.NavisionProductDAO;
import ch.hslu.enapp.webshop.lib.dataaccess.Product;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author Admin
 */
public class ProductcatalogTest {
    
    public ProductcatalogTest() {
    }

    @Test @Ignore
    public void testGetAllProducts(){
        Productcatalog catalog = new Productcatalog();
        ProductManager pml = new ProductManager();
        NavisionProductDAO dao = new NavisionProductDAO();
        pml.setProductDAO(dao);
        catalog.setProductManager(pml);
        catalog.postConstruct();
        List<Product> list = catalog.getAllProducts();
        assertFalse(list.isEmpty());
    }
}
