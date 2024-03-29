/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.enapp.webshop.lib.boundary;

import ch.hslu.enapp.webshop.lib.dataaccess.Product;
import ch.hslu.enapp.webshop.lib.exceptions.BusinessException;
import ch.hslu.enapp.webshop.lib.model.BasketContent;
import java.util.Map;
import javax.ejb.Local;

/**
 *
 * @author Admin
 */
@Local
public interface BasketManagerLocal {

    /**
     *
     * @param product
     * @param amaount
     */
    public void addProduct(final Product product, int amaount);

    /**
     *
     * @param productId
     * @param amount
     */
    public void addProduct(String productId, int amount);

    /**
     *
     * @return
     */
    public int getNumberOfProducts();

    /**
     *
     * @return
     */
    public BasketContent getBasketContent();
    
    /**
     *
     */
    public void checkout(Map<String,String> map) throws BusinessException;
}
