/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.enapp.webshop.lib.exceptions;

/**
 *
 * @author Admin
 */
public class PaymentUnsuccessfulException extends BusinessException {

    /**
     * Creates a new instance of
     * <code>PaymentUnsuccessfulException</code> without detail message.
     */
    public PaymentUnsuccessfulException() {
    }

    /**
     * Constructs an instance of
     * <code>PaymentUnsuccessfulException</code> with the specified detail
     * message.
     *
     * @param msg the detail message.
     */
    public PaymentUnsuccessfulException(String msg) {
        super(msg);
    }
}
