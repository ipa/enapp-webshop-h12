/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.enapp.webshop.entity.facade;

import ch.hslu.enapp.webshop.entity.entities.PurchaseEntity;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Admin
 */
@Local
public interface PurchaseFacadeLocal {

    void create(PurchaseEntity purchase);

    void edit(PurchaseEntity purchase);

    void remove(PurchaseEntity purchase);

    PurchaseEntity find(Object id);

    List<PurchaseEntity> findAll();

    List<PurchaseEntity> findRange(int[] range);

    int count();
    
}
