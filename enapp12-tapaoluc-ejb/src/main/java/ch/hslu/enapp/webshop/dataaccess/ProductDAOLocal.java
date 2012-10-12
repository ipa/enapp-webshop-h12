/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.enapp.webshop.dataaccess;

import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Admin
 */
@Local
public interface ProductDAOLocal {

    public List<Product> getProducts();

    public Product getProductById(Integer productid);
    
}
