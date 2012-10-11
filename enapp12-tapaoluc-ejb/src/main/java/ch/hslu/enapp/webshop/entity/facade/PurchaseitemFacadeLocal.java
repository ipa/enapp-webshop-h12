/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.enapp.webshop.entity.facade;

import ch.hslu.enapp.webshop.entity.entities.PurchaseitemEntity;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Admin
 */
@Local
public interface PurchaseitemFacadeLocal {

    void create(PurchaseitemEntity purchaseitem);

    void edit(PurchaseitemEntity purchaseitem);

    void remove(PurchaseitemEntity purchaseitem);

    PurchaseitemEntity find(Object id);

    List<PurchaseitemEntity> findAll();

    List<PurchaseitemEntity> findRange(int[] range);

    int count();
    
}
