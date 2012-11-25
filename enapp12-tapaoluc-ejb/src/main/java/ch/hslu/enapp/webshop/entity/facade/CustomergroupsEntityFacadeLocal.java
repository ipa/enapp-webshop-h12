/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.enapp.webshop.entity.facade;

import ch.hslu.enapp.webshop.entity.entities.CustomergroupsEntity;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Admin
 */
@Local
public interface CustomergroupsEntityFacadeLocal {

    void create(CustomergroupsEntity customergroupsEntity);

    void edit(CustomergroupsEntity customergroupsEntity);

    void remove(CustomergroupsEntity customergroupsEntity);

    CustomergroupsEntity find(Object id);

    List<CustomergroupsEntity> findAll();

    List<CustomergroupsEntity> findRange(int[] range);

    int count();
    
}
