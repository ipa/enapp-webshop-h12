/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.enapp.webshop.boundary;

import ch.hslu.enapp.webshop.lib.boundary.ProductManagerLocal;
import ch.hslu.enapp.webshop.lib.dataaccess.Product;
import ch.hslu.enapp.webshop.lib.dataaccess.ProductDAOLocal;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Admin
 */
@Stateless
public class ProductManager implements ProductManagerLocal {

    @Inject
    private ProductDAOLocal dao;
    
    @Override
    public List<Product> getAllProducts() {
        List<Product> list = dao.getProducts();
        
        return list;
    }
    
    @Override
    public Product getProductById(String productId){
        return dao.getProductById(productId);
    }
}
