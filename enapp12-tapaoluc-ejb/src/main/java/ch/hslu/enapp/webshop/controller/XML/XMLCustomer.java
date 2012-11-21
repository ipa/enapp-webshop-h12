/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.enapp.webshop.controller.XML;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Admin
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="customer")
public class XMLCustomer {
    @XmlElement
    private String dynNavCustNo;
    @XmlElement
    private String name;
    @XmlElement
    private String address;
    @XmlElement
    private String postCode;
    @XmlElement
    private String city;
    @XmlElement
    private String shopLoginname;

    public XMLCustomer() {
        this.dynNavCustNo = "";
        this.name = "";
        this.address = "";
        this.postCode = "";
        this.city = "";
        this.shopLoginname = "";
    }

    public String getDynNavCustNo() {
        return dynNavCustNo;
    }

    public void setDynNavCustNo(String dynNavCustNo) {
        this.dynNavCustNo = dynNavCustNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getShopLoginname() {
        return shopLoginname;
    }

    public void setShopLoginname(String shopLoginname) {
        this.shopLoginname = shopLoginname;
    }
    
    
}
