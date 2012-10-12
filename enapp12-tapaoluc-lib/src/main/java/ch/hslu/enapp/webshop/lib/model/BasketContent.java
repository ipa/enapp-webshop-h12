/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.enapp.webshop.lib.model;

import ch.hslu.enapp.webshop.lib.dataaccess.Product;
import java.util.Collections;
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
        if(this.items.contains(item)){
            item = this.items.get(this.items.indexOf(item));
            item.setAmount(item.getAmount() + amount);
        } else {
            items.add(item);
        }
    }
    
    public List<BasketContentItem> getItems(){
        return this.items;
    }
    
    public int count(){
        return this.items.size();
    }
}
