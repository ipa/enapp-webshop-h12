/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.enapp.webshop.payment.common;

import ch.hsu.enapp.webshop.payment.model.CreditCardPayment;
import ch.hsu.enapp.webshop.payment.model.PaymentResponse;

/**
 *
 * @author Admin
 */
public interface PaymentLocal {

    PaymentResponse sendPayment(CreditCardPayment pay);
    
}
