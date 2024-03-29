/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.enapp.webshop.lib.boundary;

import ch.hslu.enapp.webshop.lib.exceptions.PaymentUnsuccessfulException;
import java.util.Map;
import javax.ejb.Local;

/**
 *
 * @author Admin
 */
@Local
public interface PaymentManagerLocal {

    public final String PARAM_CARDNO = "cardno";
    public final String PARAM_CVC = "cvc";
    public final String PARAM_ED = "ed";
    
    Object pay(Map<String, String> map, long amount, String orderid) throws PaymentUnsuccessfulException;
}
