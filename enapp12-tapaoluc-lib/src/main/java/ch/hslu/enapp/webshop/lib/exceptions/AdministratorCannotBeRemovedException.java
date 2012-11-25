/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.enapp.webshop.lib.exceptions;

/**
 *
 * @author Admin
 */
public class AdministratorCannotBeRemovedException extends CustomerRemoveFailedException {

    /**
     * Creates a new instance of
     * <code>AdministratorCannotBeRemovedException</code> without detail
     * message.
     */
    public AdministratorCannotBeRemovedException() {
        this("The administator cannot be removed!");
    }

    /**
     * Constructs an instance of
     * <code>AdministratorCannotBeRemovedException</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public AdministratorCannotBeRemovedException(String msg) {
        super(msg);
    }
}
