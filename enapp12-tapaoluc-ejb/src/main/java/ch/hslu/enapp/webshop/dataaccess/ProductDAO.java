/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.enapp.webshop.dataaccess;

import ch.hslu.enapp.webshop.entity.entities.ProductEntity;
import ch.hslu.enapp.webshop.entity.facade.ProductFacadeLocal;
import com.sun.media.sound.ModelMappedInstrument;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import org.modelmapper.ModelMapper;

/**
 *
 * @author Admin
 */
@Stateless
public class ProductDAO implements ProductDAOLocal {

    @Inject
    private ProductFacadeLocal pf;
    
    @Override
    public List<Product> getProducts() {
        List<Product> products = new LinkedList<Product>();
        List<ProductEntity> entitites = this.pf.findAll();
        ModelMapper mapper = new ModelMapper();
        for(ProductEntity entitiy : entitites){
            products.add(mapper.map(entitiy, Product.class));
        }
        
        return products;
    }
    
    /** Just for UnitTests **/
    void setCustomerFacade(final ProductFacadeLocal facade){
        this.pf = facade;
    }
}
