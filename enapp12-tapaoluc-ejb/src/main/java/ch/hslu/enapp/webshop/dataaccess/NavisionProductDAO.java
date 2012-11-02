/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.enapp.webshop.dataaccess;

import ch.hslu.enapp.webshop.lib.dataaccess.Product;
import ch.hslu.enapp.webshop.lib.dataaccess.ProductDAOLocal;
import ch.hslu.enapp.webshop.lib.quailiers.WebServiceProcuctDAO;
import ch.hslu.enapp.webshop.navision.NavisionClient;
import ch.hslu.enapp.webshop.navision.NavisionClientLocal;
import ch.hslu.enapp.webshop.navision.NavisionImpl;
import ch.hslu.enapp.webshop.navision.ShopItem;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

/**
 *
 * @author Admin
 */
@Stateless
@WebServiceProcuctDAO
public class NavisionProductDAO implements ProductDAOLocal {
    
    //@Inject
    //@NavisionImpl
    //private NavisionClientLocal navisionClient;
    
    @Override
    public List<Product> getProducts() {
        NavisionClientLocal navisionClient = new NavisionClient();
        List<Product> products = new LinkedList<Product>();
        List<ShopItem> items = navisionClient.getItems();
        
        for(ShopItem si : items){
            Product p = new Product();
            p.setDescription(si.getDescription());
            p.setMediapath(si.getMediafilename());
            p.setNumber(si.getNumber());
            p.setUnitprice(si.getUnitprice());
            p.setName(si.getName());
            products.add(p);
        }
        
        return products;
    }

    @Override
    public Product getProductById(String productid) {
       List<Product> products = this.getProducts();
       for(Product p : products){
           if(p.getNumber().equals(productid)){
               return p;
           }
       }
       return null;
    }
}
