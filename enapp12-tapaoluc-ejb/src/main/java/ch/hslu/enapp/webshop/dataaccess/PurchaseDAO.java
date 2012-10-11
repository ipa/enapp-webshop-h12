/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.enapp.webshop.dataaccess;

import ch.hslu.enapp.webshop.entity.entities.CustomerEntity;
import ch.hslu.enapp.webshop.entity.entities.PurchaseEntity;
import ch.hslu.enapp.webshop.entity.facade.CustomerFacade;
import ch.hslu.enapp.webshop.entity.facade.PurchaseFacadeLocal;
import javax.ejb.Stateless;
import javax.inject.Inject;
import org.modelmapper.ModelMapper;

/**
 *
 * @author Admin
 */
@Stateless
public class PurchaseDAO implements PurchaseDAOLocal {

    @Inject
    private PurchaseFacadeLocal pfl;
    @Inject
    private ProductDAOLocal pdl;
    @Inject
    private CustomerDAOLocal cdl;
    
    @Override
    public void savePurchase(final Purchase purchase) {
        PurchaseEntity entity;
        ModelMapper mapper = new ModelMapper();
        entity = mapper.map(purchase, PurchaseEntity.class);
        
        CustomerEntity ce = mapper.map(cdl.getCustomerById(purchase.getCustomer().getId()), CustomerEntity.class);
        entity.setCustomerid(ce);
        
        this.pfl.create(entity);
    }
    
    /** Just for UnitTests **/
    void setPurchaseFacade(final PurchaseFacadeLocal facade){
        this.pfl = facade;
    }
    
    void setProducDAO(final ProductDAOLocal dao){
        this.pdl = dao;
    }
    
    void setCustomerDAO(final CustomerDAOLocal dao){
        this.cdl = dao;
    }

}
