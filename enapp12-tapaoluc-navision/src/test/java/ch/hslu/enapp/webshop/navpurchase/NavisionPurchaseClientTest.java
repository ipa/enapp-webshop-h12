/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.enapp.webshop.navpurchase;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author Admin
 */
public class NavisionPurchaseClientTest {
    
    public NavisionPurchaseClientTest() {
    }

    @Test @Ignore
    public void testGetPurchaseStatus() throws Exception {
        String corrid = "58031353594379485";
        NavisionPurchaseClient client = new NavisionPurchaseClient();
        NavisionPurchaseResponse res = client.getPurchaseStatus(corrid);
        assertNotNull(res);
        
        assertEquals(corrid, res.getCorrelationId());
        assertFalse(res.getDynNAVCustomerNo().isEmpty());
        
    }
}
