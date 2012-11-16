/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.enapp.webshop.webapp.beans;

import ch.hslu.enapp.webshop.lib.boundary.ProductManagerLocal;
import ch.hslu.enapp.webshop.lib.dataaccess.Product;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.inject.Inject;

/**
 *
 * @author Admin
 */
@Named(value = "productcatalog")
@SessionScoped
public class Productcatalog implements Serializable {

    @Inject
    private ProductManagerLocal pml;
    
    private List<Product> allProducts;
    
    /**
     * Creates a new instance of Productcatalog
     */
    public Productcatalog() {
        this.allProducts = new LinkedList<Product>();
    }
    
    @PostConstruct
    void postConstruct(){
        this.allProducts.clear();
        this.allProducts.addAll(pml.getAllProducts());
    }
    
    public List<Product> getAllProducts(){
        return this.allProducts;
    }
    
    /* for test */
    void setProductManager(ProductManagerLocal pml){
        this.pml = pml;
    }
}
