/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.enapp.webshop.boundary;

import ch.hslu.enapp.webshop.lib.boundary.PaymentManagerLocal;
import ch.hslu.enapp.webshop.lib.exceptions.PaymentUnsuccessfulException;
import ch.hslu.enapp.webshop.payment.common.PaymentLocal;
import ch.hslu.enapp.webshop.payment.postfinance.PostfinancePayment;
import ch.hsu.enapp.webshop.payment.model.CreditCardPayment;
import ch.hsu.enapp.webshop.payment.model.PaymentResponse;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJBException;
import javax.ejb.Stateless;

/**
 *
 * @author Admin
 */
@Stateless
public class PaymentManager implements PaymentManagerLocal {

    //@Inject
    private PaymentLocal pl;
    
    public PaymentManager(){
        this.pl = new PostfinancePayment();
    }
    
    @Override
    public PaymentResponse pay(Map<String, String> map, long amount, String orderid)
            throws PaymentUnsuccessfulException {        
        // check if map has the necessary stuff
        if(!map.containsKey(PARAM_CARDNO) || !map.containsKey(PARAM_CVC) || !map.containsKey(PARAM_ED)){
            throw new EJBException("not all parameters set");
        }
        
        CreditCardPayment pay = new CreditCardPayment();
        pay.setCardno(map.get(PARAM_CARDNO));
        pay.setCvc(map.get(PARAM_CVC));
        pay.setExpirydate(map.get(PARAM_ED));
        
        // set amount
        pay.setAmount((int)amount);
        pay.setOrderid(orderid);
        
        PaymentResponse response = (PaymentResponse)this.pl.sendPayment(pay);
        
        if(!response.isSuccess()){
            throw new PaymentUnsuccessfulException(response.getMessage());
        }
        return response;
    }
}
