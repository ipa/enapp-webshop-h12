/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.enapp.webshop.navpurchase;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Admin
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="salesorder")
public class NavisionPurchaseResponse {
    @XmlElement
    private String correlationId;
    @XmlElement
    private String dynNAVCustomerNo;
    @XmlElement
    private String dynNAVSalesOrderNo;
    @XmlElement
    private String lastUpdate;
    @XmlElement
    private String postFinancePayId;
    @XmlElement
    private String purchaseDateTime;
    @XmlElement
    private String purchaseId;
    @XmlElement
    private String purchaseTotalCost;
    @XmlElement
    private String status;
    @XmlElement
    private String studentName;

    public NavisionPurchaseResponse() {
        this.correlationId = "";
        this.dynNAVCustomerNo = "";
        this.dynNAVSalesOrderNo = "";
        this.lastUpdate = "";
        this.postFinancePayId = "";
        this.purchaseDateTime = "";
        this.purchaseId = "";
        this.purchaseTotalCost = "";
        this.status = "";
        this.studentName = "";
    }
    
    public String getCorrelationId() {
        return correlationId;
    }

    public void setCorrelationId(String correlationId) {
        this.correlationId = correlationId;
    }

    public String getDynNAVCustomerNo() {
        return dynNAVCustomerNo;
    }

    public void setDynNAVCustomerNo(String dynNAVCustomerNo) {
        this.dynNAVCustomerNo = dynNAVCustomerNo;
    }

    public String getDynNAVSalesOrderNo() {
        return dynNAVSalesOrderNo;
    }

    public void setDynNAVSalesOrderNo(String dynNAVSalesOrderNo) {
        this.dynNAVSalesOrderNo = dynNAVSalesOrderNo;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getPostFinancePayId() {
        return postFinancePayId;
    }

    public void setPostFinancePayId(String postFinancePayId) {
        this.postFinancePayId = postFinancePayId;
    }

    public String getPurchaseDateTime() {
        return purchaseDateTime;
    }

    public void setPurchaseDateTime(String purchaseDateTime) {
        this.purchaseDateTime = purchaseDateTime;
    }

    public String getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(String purchaseId) {
        this.purchaseId = purchaseId;
    }

    public String getPurchaseTotalCost() {
        return purchaseTotalCost;
    }

    public void setPurchaseTotalCost(String purchaseTotalCost) {
        this.purchaseTotalCost = purchaseTotalCost;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
    
    
   
}
