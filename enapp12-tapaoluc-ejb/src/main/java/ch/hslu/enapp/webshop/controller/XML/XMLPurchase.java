/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.enapp.webshop.controller.XML;

import java.io.StringWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlList;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Admin
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "purchaseMessage")
public class XMLPurchase {
    @XmlElement(name="payId")
    private String payId;
    @XmlElement(name="purchaseId")
    private String purchaseId;
    @XmlElement(name="student")
    private String student;
    @XmlElement(name="totalPrice")
    private String totalPrice;
    @XmlElement(name="date")
    private String date;
    
    @XmlElement(name="customer")
    private XMLCustomer customer;
    
    @XmlElementWrapper(name="lines")
    @XmlElementRef()
    private List<XMLPurchaseLine> lines;

    public XMLPurchase() {
        this.lines = new LinkedList<XMLPurchaseLine>();
        this.customer = new XMLCustomer();
        this.date = "";
        this.payId = "";
        this.purchaseId = "";
        this.student = "";
        this.totalPrice = "";
    }
    
    public String getXml(){
        StringWriter sw = new StringWriter();
        try {
            final JAXBContext context = JAXBContext.newInstance(XMLPurchase.class);
            final Marshaller marshall = context.createMarshaller();
            marshall.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshall.marshal(this, sw);
            return sw.toString();
        } catch (JAXBException ex) {
            Logger.getLogger(XMLPurchase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sw.toString();
    }

    public String getPayId() {
        return payId;
    }

    public void setPayId(String payId) {
        this.payId = payId;
    }

    public String getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(String purchaseId) {
        this.purchaseId = purchaseId;
    }

    public String getStudent() {
        return student;
    }

    public void setStudent(String student) {
        this.student = student;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public XMLCustomer getCustomer() {
        return customer;
    }

    public void setCustomer(XMLCustomer customer) {
        this.customer = customer;
    }

    public List<XMLPurchaseLine> getLines() {
        return lines;
    }

    public void setLines(List<XMLPurchaseLine> lines) {
        this.lines = lines;
    }
    
    
}
