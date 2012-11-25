/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.enapp.webshop.webapp.beans;

import ch.hslu.enapp.webshop.lib.boundary.CustomerManagerLocal;
import ch.hslu.enapp.webshop.lib.dataaccess.Customer;
import javax.inject.Named;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author Admin
 */
@Named(value = "newcustomer")
@ConversationScoped
public class NewCustomer implements Serializable {

    @Inject
    private Conversation conversation;
   
    @Inject
    private CustomerManagerLocal cml;
    
    private String address;
    private String email;
    private String name;
    private String pwd;
    private String username;
    
   
    /**
     * Creates a new instance of NewCustomer
     */
    public NewCustomer() {
    }
    
    @PostConstruct
    private void init(){
        this.conversation.begin();
    }
    
    public void save(){
        Customer c = new Customer();
        c.setAddress(address);
        c.setEmail(email);
        c.setName(name);
        c.setPassword(pwd);
        c.setUsername(username);
        try{
            this.cml.saveCustomer(c);
            this.conversation.end();
            FacesContext.getCurrentInstance().getExternalContext().redirect("secure/account.xhtml");
        }catch(Exception ex){
            Logger.getGlobal().log(Level.WARNING, ex.getMessage());
        }
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    
}
