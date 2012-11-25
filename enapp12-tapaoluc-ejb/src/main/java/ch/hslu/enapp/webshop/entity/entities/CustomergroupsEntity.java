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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "customergroups")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CustomergroupsEntity.findAll", query = "SELECT c FROM CustomergroupsEntity c"),
    @NamedQuery(name = "CustomergroupsEntity.findById", query = "SELECT c FROM CustomergroupsEntity c WHERE c.id = :id"),
    @NamedQuery(name = "CustomergroupsEntity.findByUsername", query = "SELECT c FROM CustomergroupsEntity c WHERE c.username = :username"),
    @NamedQuery(name = "CustomergroupsEntity.findByGroupname", query = "SELECT c FROM CustomergroupsEntity c WHERE c.groupname = :groupname")})
public class CustomergroupsEntity implements Serializable {
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
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "groupname")
    private String groupname;

    public CustomergroupsEntity() {
    }

    public CustomergroupsEntity(Integer id) {
        this.id = id;
    }

    public CustomergroupsEntity(Integer id, String username, String groupname) {
        this.id = id;
        this.username = username;
        this.groupname = groupname;
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

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
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
        if (!(object instanceof CustomergroupsEntity)) {
            return false;
        }
        CustomergroupsEntity other = (CustomergroupsEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ch.hslu.enapp.webshop.entity.entities.CustomergroupsEntity[ id=" + id + " ]";
    }
    
}
