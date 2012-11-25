/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.enapp.webshop.lib.exceptions;

/**
 *
 * @author Admin
 */
public class CustomerRemoveFailedException extends BusinessException {

    /**
     * Creates a new instance of
     * <code>CustomerRemoveFailedException</code> without detail message.
     */
    public CustomerRemoveFailedException() {
        this("Customer could not be removed!");
    }

    /**
     * Constructs an instance of
     * <code>CustomerRemoveFailedException</code> with the specified detail
     * message.
     *
     * @param msg the detail message.
     */
    public CustomerRemoveFailedException(String msg) {
        super(msg);
    }
}
