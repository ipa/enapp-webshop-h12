/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.enapp.webshop.webapp.beans;

import ch.hslu.enapp.webshop.lib.boundary.BasketManagerLocal;
import ch.hslu.enapp.webshop.lib.model.BasketContent;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Map;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author Admin
 */
@Named(value = "basket")
@SessionScoped
public class Basket implements Serializable {

    @Inject
    private BasketManagerLocal bml;
    
    @Inject 
    private UserSession session;
    
    /**
     * Creates a new instance of Basket
     */
    public Basket() {
    }
    
    public void addProduct(){
        String productid = getParameter();
        int id = Integer.parseInt(productid);
        bml.addProduct(id, 1);
    }
    
    public void removeProduct(){
        String productid = getParameter();
        int id = Integer.parseInt(productid);
        bml.addProduct(id, -1);
    }
    
    public int numberOfProducts(){
        return this.bml.getNumberOfProducts();
    }
    
    public BasketContent basketContent(){
        return this.bml.getBasketContent();
    }

    public void checkout(){
        this.bml.checkout();
    }
    
    public void clear(){
        
    }
    
    private String getParameter() {
        Map<String, String> params = 
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String productid = params.get("productid");
        return productid;
    }
}
