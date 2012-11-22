/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.enapp.webshop.webapp.beans;

import ch.hslu.enapp.webshop.lib.boundary.BasketManagerLocal;
import ch.hslu.enapp.webshop.lib.boundary.PaymentManagerLocal;
import ch.hslu.enapp.webshop.lib.exceptions.BusinessException;
import ch.hslu.enapp.webshop.lib.model.BasketContent;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
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
    
    private String cardno = "4111111111111111";
    private String cvc = "123";
    private String ed = "12/16";
    
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
        FacesContext ctx = FacesContext.getCurrentInstance();
        Map<String, String> map = new HashMap<String, String>();
        map.put(PaymentManagerLocal.PARAM_CARDNO, this.getCardno());
        map.put(PaymentManagerLocal.PARAM_CVC, this.getCvc());
        map.put(PaymentManagerLocal.PARAM_ED, this.getEd());
        try {
            this.bml.checkout(map);
            ctx.addMessage("paysuccess", new FacesMessage("Payment Successful"));
        } catch (BusinessException ex) {
            ctx.addMessage("paymessage", new FacesMessage(ex.getMessage()));
        }
    }
    
    public boolean messageIsSet(String clientId) {
        FacesContext ctx = FacesContext.getCurrentInstance();
        return ctx.getMessages(clientId).hasNext();
    }
    
    public void clear(){
        
    }
    
    private String getParameter(String paramid) {
        Map<String, String> params = 
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String res = params.get(paramid);
        return res;
    }

    public String getCardno() {
        return cardno;
    }

    public void setCardno(String cardno) {
        this.cardno = cardno;
    }

    public String getCvc() {
        return cvc;
    }

    public void setCvc(String cvc) {
        this.cvc = cvc;
    }

    public String getEd() {
        return ed;
    }

    public void setEd(String ed) {
        this.ed = ed;
    }
    
    
}
