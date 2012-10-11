/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.enapp.webshop.entity.facade;

import ch.hslu.enapp.webshop.entity.entities.ProductEntity;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Admin
 */
@Local
public interface ProductFacadeLocal {

    void create(ProductEntity product);

    void edit(ProductEntity product);

    void remove(ProductEntity product);

    ProductEntity find(Object id);

    List<ProductEntity> findAll();

    List<ProductEntity> findRange(int[] range);

    int count();
    
}
