/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.enapp.webshop.lib.dataaccess;

/**
 *
 * @author Admin
 */
public class Product {
    private Integer id;
    private String number;
    private String name;
    private String description;
    private String mediapath;
    private Long unitprice;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
        this.number = this.id.toString();
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMediapath() {
        return mediapath;
    }

    public void setMediapath(String mediapath) {
        this.mediapath = mediapath;
    }

    public Long getUnitprice() {
        return unitprice;
    }

    public void setUnitprice(Long unitprice) {
        this.unitprice = unitprice;
    }
    
    
}
