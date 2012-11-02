/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.enapp.webshop.navision;

import java.math.BigDecimal;
import java.net.Authenticator;
import java.net.MalformedURLException;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import schemas.dynamics.microsoft.page.item.Item;
import schemas.dynamics.microsoft.page.item.ItemFields;
import schemas.dynamics.microsoft.page.item.ItemFilter;
import schemas.dynamics.microsoft.page.item.ItemList;
import schemas.dynamics.microsoft.page.item.ItemPort;
import schemas.dynamics.microsoft.page.item.ItemService;

/**
 *
 * @author Admin
 */
public class NavisionClient implements NavisionClientLocal {
    
    final String namespaceURI = "urn:microsoft-dynamics-schemas/page/item";
    final String serviceName = "Item_Service";
    final String serviceURL = "http://icompanydb01.icompany.intern:7047/DynamicsNAVTest/WS/iCompany%20HSLU%20T%26A//Page/Item";
    //final String serviceURL = "http://icompanydb01.icompany.intern:7047/DynamicsNAVTest/WS/Michel%20Platini/Page/Item";
    
    final String user = "icDynNAVWsStudentSvc";
    final String password = "ic0mp@ny";
    final String domain = "ICOMPANY";
    
    public boolean connect(){
        ItemPort itemPort = getServicePort();
        return itemPort != null;
    }
    
    @Override
    public List<ShopItem> getItems(){
        List<ShopItem> list = new LinkedList<ShopItem>();
        ItemList items = this.getAllMP3();
        
        ModelMapper mapper = new ModelMapper();
        mapper.addMappings(new ShopItemMap());
        for(Item i : items.getItem()){
            ShopItem si = mapper.map(i, ShopItem.class);
            si.setUnitprice(i.getUnitPrice().longValue());
            // style ART0000244
            si.setNumber(i.getNo());
            si.setMediafilename(i.getMediafileName());
            si.setName(i.getMediafileName());
            si.setIdentifiercode(i.getIdentifierCode());
            list.add(si);
        }
        
        return list;
    }
    
    ItemList getAllItems(){
        ItemPort port = this.getServicePort();
        List<ItemFilter> filters = new LinkedList<ItemFilter>();
        ItemList items = port.readMultiple(filters, null, 0);
        return items;
    }
    
    ItemList getAllMP3()
    {
        ItemPort port = this.getServicePort();
        
        // set filters
        List<ItemFilter> filters = new LinkedList<ItemFilter>();
        ItemFilter filter = new ItemFilter();
        filter.setField(ItemFields.PRODUCT_GROUP_CODE);
        filter.setCriteria("MP3");
        filters.add(filter);
        
        ItemList items = port.readMultiple(filters, null, 0);
        return items;
    }

    private ItemPort getServicePort(){
        try {
            this.setAuthenticator(domain, user, password);
            QName itemPageQName = new QName(namespaceURI, serviceName);
            URL url;
            url = new URL(serviceURL);
            ItemService service;
            service = new ItemService(url, itemPageQName);
            ItemPort itemPort = service.getItemPort();
            return itemPort;
        } catch (MalformedURLException ex) {
            Logger.getLogger(NavisionClient.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    private void setAuthenticator(final String domain, final String user, final String password) {
        Authenticator.setDefault(new Authenticator() {
            @Override
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(domain + "\\" + user, password.toCharArray());
            }
        });
    }
    
    
}
