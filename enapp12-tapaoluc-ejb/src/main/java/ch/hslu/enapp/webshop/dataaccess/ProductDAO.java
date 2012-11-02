/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.enapp.webshop.dataaccess;

import ch.hslu.enapp.webshop.lib.dataaccess.ProductDAOLocal;
import ch.hslu.enapp.webshop.lib.dataaccess.Product;
import ch.hslu.enapp.webshop.entity.entities.ProductEntity;
import ch.hslu.enapp.webshop.entity.facade.ProductFacadeLocal;
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
public class ProductDAO {

    @Inject
    private ProductFacadeLocal pf;
    
    //@Override
    public List<Product> getProducts() {
        List<Product> products = new LinkedList<Product>();
        List<ProductEntity> entitites = this.pf.findAll();
        ModelMapper mapper = new ModelMapper();
        for(ProductEntity entitiy : entitites){
            products.add(mapper.map(entitiy, Product.class));
        }
        
        return products;
    }
    
    //@Override
    public Product getProductById(String productid) {
        Integer i = Integer.parseInt(productid);
        List<Product> products = this.getProducts();
        for(Product p : products){
            if(p.getId() == i){
                return p;
            }
        }
        return null;
    }
    
    /** Just for UnitTests **/
    void setCustomerFacade(final ProductFacadeLocal facade){
        this.pf = facade;
    }
}
