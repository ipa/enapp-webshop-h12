/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.enapp.webshop.navision;

/**
 *
 * @author Admin
 */
public class ShopItem {
    private String identifiercode;
    private String number;
    private String name;
    private String description;
    private String mediafilename;
    private Long unitprice;

    public String getIdentifiercode() {
        return identifiercode;
    }

    public void setIdentifiercode(String identifiercode) {
        this.identifiercode = identifiercode;
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

    public String getMediafilename() {
        return mediafilename;
    }

    public void setMediafilename(String mediafilename) {
        this.mediafilename = mediafilename;
    }

    public Long getUnitprice() {
        return unitprice;
    }

    public void setUnitprice(Long unitprice) {
        this.unitprice = unitprice;
    }
    
    
}
