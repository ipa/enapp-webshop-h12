/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.enapp.webshop.entity.facade;

import ch.hslu.enapp.webshop.entity.entities.CustomerEntity;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Admin
 */
@Local
public interface CustomerFacadeLocal extends AbstractFacadeLocal<CustomerEntity> {

    void create(CustomerEntity customer);

    void edit(CustomerEntity customer);

    void remove(CustomerEntity customer);

    CustomerEntity find(Object id);

    List<CustomerEntity> findAll();

    List<CustomerEntity> findRange(int[] range);

    int count();
    
}
