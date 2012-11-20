/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.enapp.webshop.boundary;

import ch.hslu.enapp.webshop.lib.boundary.PaymentManagerLocal;
import ch.hslu.enapp.webshop.payment.common.PaymentLocal;
import ch.hsu.enapp.webshop.payment.model.CreditCardPayment;
import ch.hsu.enapp.webshop.payment.model.PaymentResponse;
import java.util.Map;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Admin
 */
@Stateless
public class PaymentManager implements PaymentManagerLocal {

    @Inject
    private PaymentLocal pl;
    
    @Override
    public String pay(Map<String, String> map, long amount, String orderid) {        
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
        
        PaymentResponse response = this.pl.sendPayment(pay);
        
        if(response.isSuccess()){
            return response.getPaymentid();
        } else {
            return null;
        }
        
    }
}
