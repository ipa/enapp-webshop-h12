/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.enapp.webshop.payment.postfinance;

import ch.hslu.enapp.webshop.payment.common.PaymentLocal;
import ch.hsu.enapp.webshop.payment.model.CreditCardPayment;
import ch.hsu.enapp.webshop.payment.model.PaymentResponse;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.MultivaluedMap;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author Admin
 */
public class PostfinancePayment implements PaymentLocal {

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
    
    public PostfinancePayment(){
        this.init();
    }
    
    private void init(){
        ClientConfig config = new DefaultClientConfig();
        Client client = Client.create(config);
        this.webResource = client.resource(URI);
    }
    
    @Override
    public PaymentResponse sendPayment(CreditCardPayment pay){
        PaymentResponse response = new PaymentResponse();
        try {
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
            
            // generate hash
            formData.add("SHASIGN", this.getHash(formData));
            
            NcResponse res = this.webResource.type(CONTENT_TYPE).post(NcResponse.class, formData);
            boolean success = res.getNcStatus().equals("0");
            response.setSuccess(success);
            response.setMessage(res.getNcErrorPlus());
            response.setPaymentid(res.getPayId());
            
        } catch (Exception ex) {
            Logger.getLogger(PostfinancePayment.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response;
    }

    //private static final HASH_FORMAT = "";
    
    /**
     * @param pay
     * @return 
     */
    String getHash(MultivaluedMap pay) {
        
        String toHash = getStringToHash(pay);
        
        return DigestUtils.shaHex(toHash).toUpperCase();
    }

    String getStringToHash(MultivaluedMap pay) {
        StringBuilder sb = new StringBuilder();

        Set<String> keys = pay.keySet();
        SortedSet<String> sortedKeys = new TreeSet<String>();
        for(String s : keys){
            sortedKeys.add(s);
        }
        
        for(String key : sortedKeys){
            String key1 = key.replace("[", "").replace("]", "");
            sb.append(key1).append("=");
            String val = pay.get(key).toString().replace("[", "").replace("]", "");
            sb.append(val);
            sb.append(SHA1IN);
        }
        
        String toHash = sb.toString();
        return toHash;
    }
}
