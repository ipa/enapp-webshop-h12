/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.enapp.webshop.entity.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "purchaseitem")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Purchaseitem.findAll", query = "SELECT p FROM Purchaseitem p"),
    @NamedQuery(name = "Purchaseitem.findById", query = "SELECT p FROM Purchaseitem p WHERE p.id = :id"),
    @NamedQuery(name = "Purchaseitem.findByQuantity", query = "SELECT p FROM Purchaseitem p WHERE p.quantity = :quantity"),
    @NamedQuery(name = "Purchaseitem.findByUnitprice", query = "SELECT p FROM Purchaseitem p WHERE p.unitprice = :unitprice"),
    @NamedQuery(name = "Purchaseitem.findByLineamount", query = "SELECT p FROM Purchaseitem p WHERE p.lineamount = :lineamount"),
    @NamedQuery(name = "Purchaseitem.findByDescription", query = "SELECT p FROM Purchaseitem p WHERE p.description = :description")})
public class Purchaseitem implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "quantity")
    private Long quantity;
    @Column(name = "unitprice")
    private Long unitprice;
    @Column(name = "lineamount")
    private Long lineamount;
    @Size(max = 90)
    @Column(name = "description")
    private String description;
    @JoinColumn(name = "productid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Product productid;
    @JoinColumn(name = "purchaseid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Purchase purchaseid;

    public Purchaseitem() {
    }

    public Purchaseitem(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Long getUnitprice() {
        return unitprice;
    }

    public void setUnitprice(Long unitprice) {
        this.unitprice = unitprice;
    }

    public Long getLineamount() {
        return lineamount;
    }

    public void setLineamount(Long lineamount) {
        this.lineamount = lineamount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Product getProductid() {
        return productid;
    }

    public void setProductid(Product productid) {
        this.productid = productid;
    }

    public Purchase getPurchaseid() {
        return purchaseid;
    }

    public void setPurchaseid(Purchase purchaseid) {
        this.purchaseid = purchaseid;
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
        if (!(object instanceof Purchaseitem)) {
            return false;
        }
        Purchaseitem other = (Purchaseitem) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ch.hslu.enapp.webshop.entity.entities.Purchaseitem[ id=" + id + " ]";
    }
    
}
