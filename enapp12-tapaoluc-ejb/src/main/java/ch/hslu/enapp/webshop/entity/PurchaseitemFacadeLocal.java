/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.enapp.webshop.entity;

import ch.hslu.enapp.webshop.entity.entities.Purchaseitem;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Admin
 */
@Local
public interface PurchaseitemFacadeLocal {

    void create(Purchaseitem purchaseitem);

    void edit(Purchaseitem purchaseitem);

    void remove(Purchaseitem purchaseitem);

    Purchaseitem find(Object id);

    List<Purchaseitem> findAll();

    List<Purchaseitem> findRange(int[] range);

    int count();
    
}
