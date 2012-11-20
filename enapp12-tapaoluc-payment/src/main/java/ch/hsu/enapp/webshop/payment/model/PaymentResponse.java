/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hsu.enapp.webshop.payment.model;

/**
 *
 * @author Admin
 */
public class PaymentResponse {
    private boolean success;
    private String message;
    private String paymentid;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPaymentid() {
        return paymentid;
    }

    public void setPaymentid(String paymentid) {
        this.paymentid = paymentid;
    }
    
}
