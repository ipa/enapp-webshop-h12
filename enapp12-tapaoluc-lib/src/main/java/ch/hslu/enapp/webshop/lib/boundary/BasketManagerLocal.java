/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.enapp.webshop.lib.boundary;

import ch.hslu.enapp.webshop.lib.dataaccess.Product;
import ch.hslu.enapp.webshop.lib.model.BasketContent;
import javax.ejb.Local;

/**
 *
 * @author Admin
 */
@Local
public interface BasketManagerLocal {

    void addProduct(final Product product, int amaount);

    void addProduct(int productId, int amount);

    int getNumberOfProducts();

    public BasketContent getBasketContent();
}
