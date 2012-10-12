/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.enapp.webshop.lib.model;

import ch.hslu.enapp.webshop.lib.dataaccess.Product;

/**
 *
 * @author Admin
 */
public class BasketContentItem {
    private Product product;
    private int amount;

    public BasketContentItem(Product product, int amount) {
        this.product = product;
        this.amount = amount;
    }
    
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
    
    
}
