/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.enapp.webshop.controller;

import ch.hslu.enapp.webshop.controller.XML.XMLPurchase;
import ch.hslu.enapp.webshop.controller.XML.XMLPurchaseLine;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Admin
 */
public class XMLPurchaseTest {
    
    public XMLPurchaseTest() {
    }

    @Test
    public void testXml() throws JAXBException {
        XMLPurchase purchase = new XMLPurchase();
        purchase.setPayId("1123");
        XMLPurchaseLine pl = new XMLPurchaseLine();
        pl.setDescription("duude");
        purchase.getLines().add(pl);
        String xml = purchase.getXml();
        
        assertNotNull(xml);
        assertTrue(xml.contains("<purchaseMessage>"));
        assertTrue(xml.contains("<lines><line>"));
        assertTrue(xml.contains("<payId>1123</payId>"));
    }
    
    @Test
    public void testEmptyXml() throws JAXBException {
        XMLPurchase purchase = new XMLPurchase();
        
        String xml = purchase.getXml();
        System.out.println("XML: " + xml);
        String expected = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><purchaseMessage><payId></payId><purchaseId></purchaseId><student></student><totalPrice></totalPrice><date></date><customer><dynNavCustNo></dynNavCustNo><name></name><address></address><postCode></postCode><city></city><shopLoginname></shopLoginname></customer><lines/></purchaseMessage>";
        assertEquals(expected, xml);
    }
}
