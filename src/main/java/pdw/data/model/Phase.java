/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdw.data.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author jm-marcel
 */
@Entity
@Table(name = "swphase")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Phase.findAll", query = "SELECT p FROM Phase p"),
    @NamedQuery(name = "Phase.findById", query = "SELECT p FROM Phase p WHERE p.id = :id"),
    @NamedQuery(name = "Phase.findByName", query = "SELECT p FROM Phase p WHERE p.name = :name"),
    @NamedQuery(name = "Phase.findByDescription", query = "SELECT p FROM Phase p WHERE p.description = :description")})
public class Phase implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "description")
    private String description;
    @JoinColumn(name = "swprocess", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Process swprocess;
    // @Column(name = "modified")
    // @Temporal(TemporalType.DATE)
    // private Date modified;
    // @Basic(optional = false)
    // @NotNull
    // @Column(name = "created")
    // @Temporal(TemporalType.TIMESTAMP)
    // private Date created;
    // @Basic(optional = false)
    // @NotNull
    // @Column(name = "updated")
    // @Temporal(TemporalType.TIMESTAMP)
    // private Date updated;
    @OneToMany(mappedBy = "swphase")
    private Collection<Phase> phaseCollection;
    // @JoinColumn(name = "organization", referencedColumnName = "id")
    // @ManyToOne(optional = false)
    // private Organization organization;
    // @JoinColumn(name = "createdby", referencedColumnName = "id")
    // @ManyToOne(optional = false)
    // private User createdby;

    public Phase() {
    }

    public Phase(Integer id) {
        this.id = id;
    }

    public Phase(Integer id, Date created, Date updated) {
        this.id = id;
        this.created = created;
        this.updated = updated;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Process getSwprocess() {
        return swprocess;
    }

    public void setSwprocess(Process swprocess) {
        this.swprocess = swprocess;
    }

    // public Date getModified() {
    //     return modified;
    // }

    // public void setModified(Date modified) {
    //     this.modified = modified;
    // }

    // public Date getCreated() {
    //     return created;
    // }

    // public void setCreated(Date created) {
    //     this.created = created;
    // }

    // public Date getUpdated() {
    //     return updated;
    // }

    // public void setUpdated(Date updated) {
    //     this.updated = updated;
    // }

    // @XmlTransient
    // public Collection<Role> getRoleCollection() {
    //     return roleCollection;
    // }

    // public void setRoleCollection(Collection<Role> roleCollection) {
    //     this.roleCollection = roleCollection;
    // }

    @XmlTransient
    public Collection<Phase> getPhaseCollection() {
        return phaseCollection;
    }

    public void setPhaseCollection(Collection<Phase> phaseCollection) {
        this.phaseCollection = phaseCollection;
    }

    // public Organization getOrganization() {
    //     return organization;
    // }

    // public void setOrganization(Organization organization) {
    //     this.organization = organization;
    // }

    // public User getCreatedby() {
    //     return createdby;
    // }

    // public void setCreatedby(User createdby) {
    //     this.createdby = createdby;
    // }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Phase)) {
            return false;
        }
        Phase other = (Phase) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pdw.data.model.Phase[ id=" + id + " ]";
    }
    
}
