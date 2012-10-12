/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.enapp.webshop.lib.dataaccess;

/**
 *
 * @author Admin
 */
public class PurchaseItem {
    private Integer id;
    private Long quantity;
    private Long unitprice;
    private Long lineamount;
    private String description;
    private Integer productid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Long getUnitprice() {
        return unitprice;
    }

    public void setUnitprice(Long unitprice) {
        this.unitprice = unitprice;
    }

    public Long getLineamount() {
        return lineamount;
    }

    public void setLineamount(Long lineamount) {
        this.lineamount = lineamount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getProductid() {
        return productid;
    }

    public void setProductid(Integer productid) {
        this.productid = productid;
    }
    
    
}
