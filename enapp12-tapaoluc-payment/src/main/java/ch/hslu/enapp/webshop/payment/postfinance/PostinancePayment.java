/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.enapp.webshop.payment.postfinance;

import ch.hsu.enapp.webshop.payment.model.CreditCardPayment;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import java.io.UnsupportedEncodingException;
import java.util.Formatter;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.MultivaluedMap;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author Admin
 */
public class PostinancePayment {

    private static final String URI = "https://e-payment.postfinance.ch/ncol/test/orderdirect.asp";
    private static final String CONTENT_TYPE = "application/x-www-form-urlencoded";
    private static final String USER = "enappstudents";
    private static final String PWD = "Mb%*3Kt9BU";
    private static final String PSPID = "HSLUiCompany";
    private static final String SHA1IN = "hslu!comp@ny.websh0p";
    private static final String SHA1OUT = "09dfdc4a3805a964456d8e41731671b863dee417";
    private static final String CURENCY = "CHF";
    private static final String OPERATION = "RES";
    private WebResource webResource;
    
    public PostinancePayment(){
        this.init();
    }
    
    private void init(){
        ClientConfig config = new DefaultClientConfig();
        Client client = Client.create(config);
        this.webResource = client.resource(URI);
    }
    
    public NcResponse sendPayment(CreditCardPayment pay) {
        try {
            MultivaluedMap formData = new MultivaluedMapImpl();
            formData.add("PSPID", PSPID);
            formData.add("USERID", USER);
            //formData.add("PSWD", HashHelper.toSHA1(PWD.getBytes("UTF-8")));
            formData.add("PSWD", PWD);
            formData.add("OPERATION", OPERATION);
            formData.add("CURRENCY", CURENCY);
            
            // add payment info
            formData.add("AMOUNT", pay.getAmountAsString());
            formData.add("ORDERID", pay.getOrderid());
            formData.add("CARDNO", pay.getCardno());
            formData.add("ED", pay.getExpirydate());
            formData.add("CVC", pay.getCvc());
            
            // generate hash
            formData.add("SHASIGN", this.getHash(pay));
            
            NcResponse response = this.webResource.type(CONTENT_TYPE).post(NcResponse.class, formData);
            
            return response;
        } catch (Exception ex) {
            Logger.getLogger(PostinancePayment.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    //private static final HASH_FORMAT = "";
    
    /**
     * AMOUNT=1500Mysecretsig1875!?
     * CARDNO=4111111111111111Mysecretsig1875!? 
     * CURRENCY=EURMysecretsig1875!?
     * OPERATION=RESMysecretsig1875!? 
     * ORDERID=1234Mysecretsig1875!?
     * PSPID=MyPSPIDMysecretsig1875!?
     * @param pay
     * @return 
     */
    String getHash(CreditCardPayment pay) {
        
        String toHash = getStringToHash(pay);
        
        return DigestUtils.shaHex(toHash).toUpperCase();
    }

    String getStringToHash(CreditCardPayment pay) {
        StringBuilder sb = new StringBuilder();
        //Formatter formatter = new Formatter(sb, Locale.US);
        //formatter.format(HASH_FORMAT, args)
        sb.append("AMOUNT=").append(pay.getAmountAsString()).append(SHA1IN);
        sb.append("CARDNO=").append(pay.getCardno()).append(SHA1IN);
        sb.append("CURRENCY=").append(CURENCY).append(SHA1IN);
        sb.append("OPERATION=").append(OPERATION).append(SHA1IN);
        sb.append("PSPID=").append(PSPID).append(SHA1IN);
        String toHash = sb.toString();
        return toHash;
    }
}
