/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.enapp.webshop.dataaccess;

import ch.hslu.enapp.webshop.entity.entities.CustomerEntity;
import ch.hslu.enapp.webshop.entity.entities.ProductEntity;
import ch.hslu.enapp.webshop.entity.entities.PurchaseEntity;
import ch.hslu.enapp.webshop.entity.entities.PurchaseitemEntity;
import ch.hslu.enapp.webshop.entity.facade.PurchaseFacadeLocal;
import ch.hslu.enapp.webshop.lib.dataaccess.CustomerDAOLocal;
import ch.hslu.enapp.webshop.lib.dataaccess.Product;
import ch.hslu.enapp.webshop.lib.dataaccess.ProductDAOLocal;
import ch.hslu.enapp.webshop.lib.dataaccess.Purchase;
import ch.hslu.enapp.webshop.lib.dataaccess.PurchaseDAOLocal;
import ch.hslu.enapp.webshop.lib.dataaccess.PurchaseItem;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import org.modelmapper.ModelMapper;

/**
 *
 * @author Admin
 */
@Stateless
@Default
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
        
        // TODO: remove CustomerEntity Depenency
        CustomerEntity ce = mapper.map(cdl.getCustomerById(purchase.getCustomer().getId()), CustomerEntity.class);
        entity.setCustomerid(ce);
        
        List<PurchaseitemEntity> list = new LinkedList<PurchaseitemEntity>();
        for(PurchaseItem pi : purchase.getPurchaseItems()){
            PurchaseitemEntity e = mapper.map(pi, PurchaseitemEntity.class);
            ProductEntity pe = new ProductEntity(pi.getProductid());
            e.setProductid(pe);
            e.setPurchaseid(entity);
            list.add(e);
        }
        
        entity.setPurchaseitemCollection(list);
        
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
