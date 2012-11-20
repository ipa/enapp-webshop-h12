/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.enapp.webshop.payment.postfinance;

import ch.hsu.enapp.webshop.payment.model.CreditCardPayment;
import ch.hsu.enapp.webshop.payment.model.PaymentResponse;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import javax.ws.rs.core.MultivaluedMap;
import static org.junit.Assert.*;
import org.junit.Ignore;
import org.junit.Test;
/**
 *
 * @author Admin
 */
public class PostinancePaymentTest {

    private static final String CONTENT_TYPE = "application/x-www-form-urlencoded";
    private static final String USER = "enappstudents";
    private static final String PWD = "Mb%*3Kt9BU";
    private static final String PSPID = "HSLUiCompany";
    private static final String SHA1IN = "hslu!comp@ny.websh0p";
    private static final String SHA1OUT = "09dfdc4a3805a964456d8e41731671b863dee417";
    private static final String CURENCY = "CHF";
    private static final String OPERATION = "RES";
    
    @Test @Ignore
    public void testSendPayment() {
        CreditCardPayment pay = new CreditCardPayment();
        pay.setAmount(100);
        pay.setOrderid("1234865432");
        pay.setCardno("4111111111111111");
        pay.setCvc("123");
        pay.setExpirydate("12/16");
        
        PostfinancePayment payment = new PostfinancePayment();
        PaymentResponse res = payment.sendPayment(pay);
        
        assertNotNull(res);   
        
        assertTrue(res.isSuccess() + ": " + res.getMessage(), res.isSuccess());  
    }
    
    @Test
    public void testGetHash(){
        String expected = "0D6173F2B4B96DD22643CDB493E00D38071EE6BC";
        PostfinancePayment payment = new PostfinancePayment();
        String hash = payment.getHash(getCreditCardPayment());
        
        assertEquals(expected, hash);
    }
    
    @Test
    public void testGetStringToHash(){
        PostfinancePayment payment = new PostfinancePayment();
        String toHash = payment.getStringToHash(getCreditCardPayment());
        
        String expected;
        expected = "AMOUNT=100hslu!comp@ny.websh0pCARDNO=4111111111111111hslu!comp@ny.websh0pCURRENCY=CHFhslu!comp@ny.websh0pCVC=123hslu!comp@ny.websh0pED=12/16hslu!comp@ny.websh0pOPERATION=REShslu!comp@ny.websh0pORDERID=1234865432hslu!comp@ny.websh0pPSPID=HSLUiCompanyhslu!comp@ny.websh0pPSWD=Mb%*3Kt9BUhslu!comp@ny.websh0pUSERID=enappstudentshslu!comp@ny.websh0p";
         
        assertEquals(expected, toHash);
    }

    private MultivaluedMap getCreditCardPayment() {
        CreditCardPayment pay = new CreditCardPayment();
        pay.setAmount(100);
        pay.setOrderid("1234865432");
        pay.setCardno("4111111111111111");
        pay.setCvc("123");
        pay.setExpirydate("12/16");
        
        MultivaluedMap formData = new MultivaluedMapImpl();
        formData.add("PSPID", PSPID);
        formData.add("USERID", USER);
        formData.add("PSWD", PWD);
        formData.add("OPERATION", OPERATION);
        formData.add("CURRENCY", CURENCY);

        // add payment info
        formData.add("AMOUNT", pay.getAmountAsString());
        formData.add("ORDERID", pay.getOrderid());
        formData.add("CARDNO", pay.getCardno());
        formData.add("ED", pay.getExpirydate());
        formData.add("CVC", pay.getCvc());
        
        return formData;
    }
}
