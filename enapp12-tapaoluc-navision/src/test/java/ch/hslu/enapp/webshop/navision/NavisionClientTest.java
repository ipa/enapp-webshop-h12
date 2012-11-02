/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.enapp.webshop.navision;

import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import schemas.dynamics.microsoft.page.item.Item;
import schemas.dynamics.microsoft.page.item.ItemList;

/**
 * not actually unittests 
 * @author Admin
 */
public class NavisionClientTest {
    
    public NavisionClientTest() {
    }

    @Test
    public void testConnect() {
        NavisionClient client = new NavisionClient();
        boolean connect = client.connect();
        assertTrue(connect);
    }

    @Test
    public void testGetAllItems() {
        NavisionClient client = new NavisionClient();
        ItemList list = client.getAllItems();
        assertTrue(!list.getItem().isEmpty());
    }

    @Test
    public void testGetAllMP3() {
        NavisionClient client = new NavisionClient();
        ItemList list = client.getAllMP3();
        assertTrue(!list.getItem().isEmpty());
        for(Item i : list.getItem()){
            assertEquals("MP3", i.getProductGroupCode());
        }
    }
    
    @Test
    public void testGellItems(){
        NavisionClient client = new NavisionClient();
        List<ShopItem> items = client.getItems();
        for(ShopItem i : items){
            assertNotNull(i.getName());
            assertFalse(i.getDescription().isEmpty());
            assertNotNull(i.getUnitprice());
            assertTrue(i.getUnitprice() >= 0);
            assertFalse(i.getNumber().isEmpty());
        }
    }
    
    @Test
    public void testGetItmesSize(){
        NavisionClient client = new NavisionClient();
        long sizeMp3 = client.getAllMP3().getItem().size();
        long sizeShopitems = client.getItems().size();
        
        assertEquals(sizeMp3, sizeShopitems);
    }
}
