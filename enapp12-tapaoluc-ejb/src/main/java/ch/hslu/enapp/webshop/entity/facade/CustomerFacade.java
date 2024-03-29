/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.enapp.webshop.entity.facade;

import ch.hslu.enapp.webshop.entity.entities.CustomerEntity;
import ch.hslu.enapp.webshop.inject.DefaultImplementation;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Admin
 */
@Stateless
@Default
public class CustomerFacade extends AbstractFacade<CustomerEntity> implements CustomerFacadeLocal {
    @PersistenceContext(unitName = "ch.hslu.enapp_enapp12-tapaoluc-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CustomerFacade() {
        super(CustomerEntity.class);
    }
    
    @Override
    public CustomerEntity getCustomerById(Integer id){
        Query query = this.getEntityManager().createNamedQuery("CustomerEntity.findById");
        query.setParameter("id", id);
        return (CustomerEntity)query.getResultList().get(0);
    }
}
