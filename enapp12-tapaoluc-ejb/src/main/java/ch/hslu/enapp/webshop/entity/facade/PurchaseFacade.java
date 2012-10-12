/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.enapp.webshop.entity.facade;

import ch.hslu.enapp.webshop.entity.entities.PurchaseEntity;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Admin
 */
@Stateless
@Default
public class PurchaseFacade extends AbstractFacade<PurchaseEntity> implements PurchaseFacadeLocal {
    @PersistenceContext(unitName = "ch.hslu.enapp_enapp12-tapaoluc-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PurchaseFacade() {
        super(PurchaseEntity.class);
    }
    
}
