/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.enapp.webshop.entity.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "customer")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CustomerEntity.findAll", query = "SELECT c FROM CustomerEntity c"),
    @NamedQuery(name = "CustomerEntity.findById", query = "SELECT c FROM CustomerEntity c WHERE c.id = :id"),
    @NamedQuery(name = "CustomerEntity.findByUsername", query = "SELECT c FROM CustomerEntity c WHERE c.username = :username"),
    @NamedQuery(name = "CustomerEntity.findByPassword", query = "SELECT c FROM CustomerEntity c WHERE c.password = :password"),
    @NamedQuery(name = "CustomerEntity.findByName", query = "SELECT c FROM CustomerEntity c WHERE c.name = :name"),
    @NamedQuery(name = "CustomerEntity.findByAddress", query = "SELECT c FROM CustomerEntity c WHERE c.address = :address"),
    @NamedQuery(name = "CustomerEntity.findByEmail", query = "SELECT c FROM CustomerEntity c WHERE c.email = :email")})
public class CustomerEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "username")
    private String username;
    @Size(max = 32)
    @Column(name = "password")
    private String password;
    @Size(max = 45)
    @Column(name = "name")
    private String name;
    @Size(max = 45)
    @Column(name = "address")
    private String address;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 90)
    @Column(name = "email")
    private String email;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customerid")
    private Collection<PurchaseEntity> purchaseEntityCollection;

    public CustomerEntity() {
    }

    public CustomerEntity(Integer id) {
        this.id = id;
    }

    public CustomerEntity(Integer id, String username) {
        this.id = id;
        this.username = username;
    }

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

    @XmlTransient
    public Collection<PurchaseEntity> getPurchaseEntityCollection() {
        return purchaseEntityCollection;
    }

    public void setPurchaseEntityCollection(Collection<PurchaseEntity> purchaseEntityCollection) {
        this.purchaseEntityCollection = purchaseEntityCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CustomerEntity)) {
            return false;
        }
        CustomerEntity other = (CustomerEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ch.hslu.enapp.webshop.entity.entities.CustomerEntity[ id=" + id + " ]";
    }
    
}
