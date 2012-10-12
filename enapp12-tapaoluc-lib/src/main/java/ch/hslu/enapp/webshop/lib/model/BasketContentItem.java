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
public class BasketContentItem implements Comparable<BasketContentItem>{
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + (this.product != null ? this.product.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final BasketContentItem other = (BasketContentItem) obj;
        
        return this.compareTo(other) == 0;
    }
    
    public int compareTo(BasketContentItem o) {
        return this.product.getId() - o.product.getId();
    }
}
