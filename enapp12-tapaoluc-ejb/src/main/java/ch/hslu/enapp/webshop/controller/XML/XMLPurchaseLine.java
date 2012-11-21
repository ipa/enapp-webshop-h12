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
@XmlRootElement(name="line")
public class XMLPurchaseLine {
    @XmlElement
    private String msDynNAVItemNo;
    @XmlElement
    private String description;
    @XmlElement
    private String quantity;
    @XmlElement
    private String totalLinePrice;

    public String getMsDynNAVItemNo() {
        return msDynNAVItemNo;
    }

    public void setMsDynNAVItemNo(String msDynNAVItemNo) {
        this.msDynNAVItemNo = msDynNAVItemNo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getTotalLinePrice() {
        return totalLinePrice;
    }

    public void setTotalLinePrice(String totalLinePrice) {
        this.totalLinePrice = totalLinePrice;
    }
    
    
}
