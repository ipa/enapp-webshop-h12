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
    @NamedQuery(name = "PurchaseitemEntity.findAll", query = "SELECT p FROM PurchaseitemEntity p"),
    @NamedQuery(name = "PurchaseitemEntity.findById", query = "SELECT p FROM PurchaseitemEntity p WHERE p.id = :id"),
    @NamedQuery(name = "PurchaseitemEntity.findByProductno", query = "SELECT p FROM PurchaseitemEntity p WHERE p.productno = :productno"),
    @NamedQuery(name = "PurchaseitemEntity.findByQuantity", query = "SELECT p FROM PurchaseitemEntity p WHERE p.quantity = :quantity"),
    @NamedQuery(name = "PurchaseitemEntity.findByUnitprice", query = "SELECT p FROM PurchaseitemEntity p WHERE p.unitprice = :unitprice"),
    @NamedQuery(name = "PurchaseitemEntity.findByLineamount", query = "SELECT p FROM PurchaseitemEntity p WHERE p.lineamount = :lineamount"),
    @NamedQuery(name = "PurchaseitemEntity.findByDescription", query = "SELECT p FROM PurchaseitemEntity p WHERE p.description = :description")})
public class PurchaseitemEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 50)
    @Column(name = "productno")
    private String productno;
    @Column(name = "quantity")
    private Long quantity;
    @Column(name = "unitprice")
    private Long unitprice;
    @Column(name = "lineamount")
    private Long lineamount;
    @Size(max = 90)
    @Column(name = "description")
    private String description;
    @JoinColumn(name = "purchaseid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private PurchaseEntity purchaseid;

    public PurchaseitemEntity() {
    }

    public PurchaseitemEntity(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductno() {
        return productno;
    }

    public void setProductno(String productno) {
        this.productno = productno;
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

    public PurchaseEntity getPurchaseid() {
        return purchaseid;
    }

    public void setPurchaseid(PurchaseEntity purchaseid) {
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
        if (!(object instanceof PurchaseitemEntity)) {
            return false;
        }
        PurchaseitemEntity other = (PurchaseitemEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ch.hslu.enapp.webshop.entity.entities.PurchaseitemEntity[ id=" + id + " ]";
    }
    
}
