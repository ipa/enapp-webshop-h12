/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.enapp.webshop.navpurchase;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

/**
 *
 * @author Admin
 */
public class NavisionPurchaseClient {

    private WebResource webResource;
    private static final String URL = "http://10.29.3.152:8080/ENAPPDaemon-war/resources/salesorder/corr/";
    private static final String CONTENT_TYPE = "application/xml";
    
    private String uri;
    
    private void init(String urn){
        ClientConfig config = new DefaultClientConfig();
        Client client = Client.create(config);
        this.uri = URL + urn;
        this.webResource = client.resource(uri);
    }

    public NavisionPurchaseResponse getPurchaseStatus(String corrid) {
        this.init(corrid);
        NavisionPurchaseResponse response = this.webResource.get(NavisionPurchaseResponse.class);
        return response;
    }
}
