/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.enapp.webshop.lib.dataaccess;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Admin
 */
public class PurchaseTest {
    
    public PurchaseTest() {
    }

    @Test
    public void testGetTotalPriceAsString() {
        Purchase p = new Purchase();
        PurchaseItem pi1 = new PurchaseItem();
        pi1.setQuantity(2L);
        pi1.setUnitprice(3L);
        PurchaseItem pi2 = new PurchaseItem();
        pi2.setQuantity(3L);
        pi2.setUnitprice(4L);
        p.getPurchaseItems().add(pi1);
        p.getPurchaseItems().add(pi2);
        
        String result = p.getTotalPriceAsString();
        assertEquals("18", result);
    }
}
