/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.enapp.webshop.dataaccess.util;

import ch.hslu.enapp.webshop.entity.facade.AbstractFacadeLocal;
import java.util.LinkedList;
import java.util.List;
import org.modelmapper.ModelMapper;

/**
 *
 * @author Admin
 */
public class DataAccessHelper<TEntity, TModel, TFacade extends AbstractFacadeLocal<TEntity>> {
    
    public List<TModel> getData(TFacade facade){
        List<TModel> list = new LinkedList<TModel>();
        ModelMapper mapper = new ModelMapper();
        List<TEntity> entities = facade.findAll();
        for(TEntity entity : entities){
            TModel m = null;
            mapper.map(entity, m);
            list.add(m);
        }
        return list;
    }
}
