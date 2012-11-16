/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hsu.enapp.webshop.payment.model;

/**
 *
 * @author Admin
 */
public class CreditCardPayment {
    private String orderid;
    private Integer amount;
    private String cardno;
    private String expirydate;
    private String cvc;

    public String getAmountAsString(){
        return amount.toString();
    }
    
    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }
        
    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getCardno() {
        return cardno;
    }

    public void setCardno(String cardno) {
        this.cardno = cardno;
    }

    public String getExpirydate() {
        return expirydate;
    }

    public void setExpirydate(String expirydate) {
        this.expirydate = expirydate;
    }

    public String getCvc() {
        return cvc;
    }

    public void setCvc(String cvc) {
        this.cvc = cvc;
    }
    
}
