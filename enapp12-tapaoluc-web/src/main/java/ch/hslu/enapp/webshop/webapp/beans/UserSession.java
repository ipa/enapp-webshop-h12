/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.enapp.webshop.webapp.beans;

import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.security.Principal;
import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Admin
 */
@Named(value = "usersession")
@SessionScoped
public class UserSession implements Serializable {

    private String username;

    private String iusr;
    private String ipwd;
    
    /**
     * Creates a new instance of UserSession
     */
    public UserSession() {
    }

    @PostConstruct
    public void init() {
        try {
            this.username = FacesContext.getCurrentInstance().getExternalContext()
                    .getUserPrincipal().getName();
        } catch (Exception ex) {
            // TODO log
        }

//        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Hallo " + this.username, "");
//        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void logout() throws IOException {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.invalidateSession();
        ec.redirect("./index.xhtml");
    }

    public boolean isLoggedIn() {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        Principal up = ec.getUserPrincipal();
        if(up != null && !up.getName().isEmpty()){
            this.username = up.getName();
            return true;
        }
        return false;
    }
    
    public void login(){
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest)context.getExternalContext();
        try
        {
            request.login(iusr, ipwd);
        }catch(ServletException se){
            
        }
    }
    
    public boolean isNotLoggedIn() {
        return !this.isLoggedIn();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIusr() {
        return iusr;
    }

    public void setIusr(String iusr) {
        this.iusr = iusr;
    }

    public String getIpwd() {
        return ipwd;
    }

    public void setIpwd(String ipwd) {
        this.ipwd = ipwd;
    }
}
