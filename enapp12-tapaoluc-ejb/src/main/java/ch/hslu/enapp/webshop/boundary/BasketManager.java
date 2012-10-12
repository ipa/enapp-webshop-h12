/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.enapp.webshop.boundary;

import ch.hslu.enapp.webshop.lib.boundary.BasketManagerLocal;
import ch.hslu.enapp.webshop.lib.boundary.ProductManagerLocal;
import ch.hslu.enapp.webshop.lib.dataaccess.Product;
import ch.hslu.enapp.webshop.lib.model.BasketContent;
import javax.ejb.Stateful;
import javax.inject.Inject;

/**
 *
 * @author Admin
 */
@Stateful
public class BasketManager implements BasketManagerLocal {
    
    @Inject
    private ProductManagerLocal pml;
    
    private BasketContent content;
    
    public BasketManager(){
        this.content = new BasketContent();
    }
    
    @Override
    public void addProduct(final Product product, int amaount) {
        this.content.insertProduct(product, amaount);
    }

    @Override
    public void addProduct(int productId, int amount) {
        Product p = pml.getProductById(productId);
        this.addProduct(p, amount);
    }

    @Override
    public int getNumberOfProducts() {
        return this.content.count();
    }
    
    @Override
    public BasketContent getBasketContent(){
        return this.content;
    }
}
