/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.enapp.webshop.entity.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "purchase")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PurchaseEntity.findAll", query = "SELECT p FROM PurchaseEntity p"),
    @NamedQuery(name = "PurchaseEntity.findById", query = "SELECT p FROM PurchaseEntity p WHERE p.id = :id"),
    @NamedQuery(name = "PurchaseEntity.findByDatetime", query = "SELECT p FROM PurchaseEntity p WHERE p.datetime = :datetime"),
    @NamedQuery(name = "PurchaseEntity.findByStatus", query = "SELECT p FROM PurchaseEntity p WHERE p.status = :status"),
    @NamedQuery(name = "PurchaseEntity.findByCorrid", query = "SELECT p FROM PurchaseEntity p WHERE p.corrid = :corrid")})
public class PurchaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "datetime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datetime;
    @Size(max = 15)
    @Column(name = "status")
    private String status;
    @Size(max = 50)
    @Column(name = "corrid")
    private String corrid;
    @JoinColumn(name = "customerid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private CustomerEntity customerid;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "purchaseid")
    private Collection<PurchaseitemEntity> purchaseitemEntityCollection;

    public PurchaseEntity() {
    }

    public PurchaseEntity(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCorrid() {
        return corrid;
    }

    public void setCorrid(String corrid) {
        this.corrid = corrid;
    }

    public CustomerEntity getCustomerid() {
        return customerid;
    }

    public void setCustomerid(CustomerEntity customerid) {
        this.customerid = customerid;
    }

    @XmlTransient
    public Collection<PurchaseitemEntity> getPurchaseitemEntityCollection() {
        return purchaseitemEntityCollection;
    }

    public void setPurchaseitemEntityCollection(Collection<PurchaseitemEntity> purchaseitemEntityCollection) {
        this.purchaseitemEntityCollection = purchaseitemEntityCollection;
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
        if (!(object instanceof PurchaseEntity)) {
            return false;
        }
        PurchaseEntity other = (PurchaseEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ch.hslu.enapp.webshop.entity.entities.PurchaseEntity[ id=" + id + " ]";
    }

    public void setPurchaseitemCollection(Collection<PurchaseitemEntity> list) {
        this.purchaseitemEntityCollection = list;
    }

    public Collection<PurchaseitemEntity> getPurchaseitemCollection() {
        return this.purchaseitemEntityCollection;
    }
    
}
