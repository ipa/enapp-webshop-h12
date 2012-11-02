/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.enapp.webshop.lib.boundary;

import ch.hslu.enapp.webshop.lib.dataaccess.Product;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Admin
 */
@Local
public interface ProductManagerLocal {

    public List<Product> getAllProducts();

    public Product getProductById(String productId);
    
}
