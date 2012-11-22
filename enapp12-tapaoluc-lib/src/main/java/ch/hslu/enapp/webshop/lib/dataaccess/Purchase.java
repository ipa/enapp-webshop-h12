/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.enapp.webshop.lib.dataaccess;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Admin
 */
public class Purchase {
    private Integer id;
    private Date datetime;
    private String status;
    private Customer customer;
    private List<PurchaseItem> purchaseItems;
    private String payid;
    private String corrid;
    private String no;
    
    public Purchase() {
        this.purchaseItems = new LinkedList<PurchaseItem>();
        this.no = getPurchaseNo();
    }

    private static String getPurchaseNo(){
        Long number = ((new Random().nextInt(8999) +  1000)
                        + Calendar.getInstance().getTimeInMillis());
        return number.toString();
    }
    
    public String getTotalPriceAsString(){
        Long price = 0L;
        for(PurchaseItem item : purchaseItems){
            price += item.getQuantity() * item.getUnitprice();
        }
        return price.toString();
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<PurchaseItem> getPurchaseItems() {
        return purchaseItems;
    }

    public void setPurchaseItems(List<PurchaseItem> purchaseItems) {
        this.purchaseItems = purchaseItems;
    }
    
    public String getPayid() {
        return payid;
    }

    public void setPayid(String payid) {
        this.payid = payid;
    }

    public String getCorrid() {
        return corrid;
    }

    public void setCorrid(String corrid) {
        this.corrid = corrid;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

}
