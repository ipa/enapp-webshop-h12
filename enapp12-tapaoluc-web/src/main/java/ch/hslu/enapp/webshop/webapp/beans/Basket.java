/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.enapp.webshop.webapp.beans;

import ch.hslu.enapp.webshop.lib.boundary.BasketManagerLocal;
import ch.hslu.enapp.webshop.lib.boundary.PaymentManagerLocal;
import ch.hslu.enapp.webshop.lib.model.BasketContent;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.HashMap;
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

    private final String PARAM_PRODUCTID = "productid";
    
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
        String productid = getParameter(PARAM_PRODUCTID);
        //int id = Integer.parseInt(productid);
        bml.addProduct(productid, 1);
    }
    
    public void removeProduct(){
        String productid = getParameter(PARAM_PRODUCTID);
        //int id = Integer.parseInt(productid);
        bml.addProduct(productid, -1);
    }
    
    public int numberOfProducts(){
        return this.bml.getNumberOfProducts();
    }
    
    public BasketContent basketContent(){
        return this.bml.getBasketContent();
    }

    public void checkout(){
        Map<String, String> map = new HashMap<String, String>();
        map.put(PaymentManagerLocal.PARAM_CARDNO, getParameter(PaymentManagerLocal.PARAM_CARDNO));
        map.put(PaymentManagerLocal.PARAM_CVC, getParameter(PaymentManagerLocal.PARAM_CVC));
        map.put(PaymentManagerLocal.PARAM_ED, getParameter(PaymentManagerLocal.PARAM_ED));
        
        this.bml.checkout(map);
    }
    
    public void clear(){
        
    }
    
    private String getParameter(String paramid) {
        Map<String, String> params = 
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String productid = params.get(paramid);
        return productid;
    }
}
