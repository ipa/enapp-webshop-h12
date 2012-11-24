/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.enapp.webshop.lib.dataaccess;

import java.util.Comparator;

/**
 *
 * @author Admin
 */
public class Customer{
    private Integer id;
    private String username;
    private String password;
    private String name;
    private String address;
    private String email;
    private String navCustomerNo;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNavCustomerNo() {
        return navCustomerNo;
    }

    public void setNavCustomerNo(String navCustomerNo) {
        this.navCustomerNo = navCustomerNo;
    }
    
    public Customer.CustomerComparator getCustomerComparator(){
        return new Customer.CustomerComparator();
    }
    
    public class CustomerComparator  implements Comparator<Customer>{
        @Override
        public int compare(Customer o1, Customer o2) {
            return o1.getId() - o2.getId();
        }
    }
}
