/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.enapp.webshop.lib.model;

import ch.hslu.enapp.webshop.lib.dataaccess.Product;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class BasketContent {
    private List<BasketContentItem> items;
    
    public BasketContent(){
        this.items = new LinkedList<BasketContentItem>();
    }
    
    public void insertProduct(Product product, int amount){
        BasketContentItem item = new BasketContentItem(product, amount);
        items.add(item);
    }
    
    public int count(){
        return this.items.size();
    }
}
