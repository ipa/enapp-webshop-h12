/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.enapp.webshop.webapp.beans;

import ch.hslu.enapp.webshop.lib.boundary.ProductManagerLocal;
import ch.hslu.enapp.webshop.lib.dataaccess.Product;
import java.util.LinkedList;
import java.util.List;
import javax.faces.bean.ApplicationScoped;
import javax.inject.Named;
import javax.inject.Inject;

/**
 *
 * @author Admin
 */
@Named(value = "productcatalog")
@ApplicationScoped
public class Productcatalog {

    @Inject
    private ProductManagerLocal pml;
    
    private List<Product> allProducts;
    
    /**
     * Creates a new instance of Productcatalog
     */
    public Productcatalog() {
        this.allProducts = new LinkedList<Product>();
        if(this.allProducts.isEmpty()){
            this.allProducts.clear();
            this.allProducts.addAll(pml.getAllProducts());
        }
    }
    
    public List<Product> getAllProducts(){
        return this.allProducts;
    }
}
