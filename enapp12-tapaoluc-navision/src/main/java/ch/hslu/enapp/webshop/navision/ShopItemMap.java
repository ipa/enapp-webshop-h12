/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.enapp.webshop.navision;

import org.modelmapper.PropertyMap;
import schemas.dynamics.microsoft.page.item.Item;

/**
 *
 * @author Admin
 */
class ShopItemMap extends PropertyMap<Item, ShopItem> {

    @Override
    protected void configure() {
        //if(source != null && source.getUnitPrice() != null){
        //    map().setUnitprice(source.getUnitPrice().longValue());
        //}
    }
    
}
