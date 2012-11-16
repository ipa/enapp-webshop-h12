/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.enapp.webshop.payment.postfinance;

import ch.hsu.enapp.webshop.payment.model.CreditCardPayment;
import static org.junit.Assert.*;
import org.junit.Ignore;
import org.junit.Test;
/**
 *
 * @author Admin
 */
public class PostinancePaymentTest {

    @Test @Ignore
    public void testSendPayment() {
        /*CreditCardPayment pay = getCreditCardPayment();
        PostfinancePayment payment = new PostinancePayment();
        NcResponse res = payment.sendPayment(pay);
        
        assertNotNull(res);   
        
        assertNull(res.getNcError() + ": " + res.getNcErrorPlus(), res.getNcErrorPlus());
        */
    }
    
    @Test
    public void testGetHash(){
        String expected = "2DDF9FDD344C9CA29092293D078390B26FF90F6E";
        PostinancePayment payment = new PostinancePayment();
        String hash = payment.getHash(getCreditCardPayment());
        
        assertEquals(expected, hash);
    }
    
    @Test
    public void testGetStringToHash(){
        PostinancePayment payment = new PostinancePayment();
        String toHash = payment.getStringToHash(getCreditCardPayment());
        
        String expected;
        expected = "AMOUNT=100hslu!comp@ny.websh0pCARDNO=4111111111111111hslu!comp@ny.websh0pCURRENCY=CHFhslu!comp@ny.websh0pOPERATION=REShslu!comp@ny.websh0pPSPID=HSLUiCompanyhslu!comp@ny.websh0p";
         
        assertEquals(expected, toHash);
    }

    private CreditCardPayment getCreditCardPayment() {
        CreditCardPayment pay = new CreditCardPayment();
        pay.setAmount(100);
        pay.setOrderid("1234");
        pay.setCardno("4111111111111111");
        pay.setCvc("123");
        pay.setExpirydate("12/16");
        return pay;
    }
}
