/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdw.data.model;

import java.io.Serializable;
import java.util.Collection;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author alexandrelerario
 */
@Entity
@Table(name = "swarctifact")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Arctifact.findAll", query = "SELECT a FROM Arctifact a"),
    @NamedQuery(name = "Arctifact.findById", query = "SELECT a FROM Arctifact a WHERE a.id = :id"),
    @NamedQuery(name = "Arctifact.findByName", query = "SELECT a FROM Arctifact a WHERE a.name = :name"),
    @NamedQuery(name = "Arctifact.findByDescription", query = "SELECT a FROM Arctifact a WHERE a.description = :description")})
public class Arctifact implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 50)
    @Column(name = "name")
    private String name;
    @Size(max = 400)
    @Column(name = "description")
    private String description;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "arctifact")
    private Collection<ActivityArtifact> activityArtifactCollection;
    @JoinColumn(name = "swguidance", referencedColumnName = "id")
    @ManyToOne
    private Guidance swguidance;
    @JoinColumn(name = "swtemplate", referencedColumnName = "id")
    @ManyToOne
    private Template swtemplate;

    public Arctifact() {
    }

    public Arctifact(Integer id) {
        this.id = id;
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

    @XmlTransient
    public Collection<ActivityArtifact> getActivityArtifactCollection() {
        return activityArtifactCollection;
    }

    public void setActivityArtifactCollection(Collection<ActivityArtifact> activityArtifactCollection) {
        this.activityArtifactCollection = activityArtifactCollection;
    }

    public Guidance getSwguidance() {
        return swguidance;
    }

    public void setSwguidance(Guidance swguidance) {
        this.swguidance = swguidance;
    }

    public Template getSwtemplate() {
        return swtemplate;
    }

    public void setSwtemplate(Template swtemplate) {
        this.swtemplate = swtemplate;
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
        if (!(object instanceof Arctifact)) {
            return false;
        }
        Arctifact other = (Arctifact) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pdw.data.model.Arctifact[ id=" + id + " ]";
    }
    
}
