/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdw.data.model;

import java.io.Serializable;
import java.util.Collection;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author alexandrelerario
 */
@Entity
@Table(name = "swphase")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Phase.findAll", query = "SELECT p FROM Phase p"),
    @NamedQuery(name = "Phase.findById", query = "SELECT p FROM Phase p WHERE p.id = :id"),
    @NamedQuery(name = "Phase.findByName", query = "SELECT p FROM Phase p WHERE p.name = :name"),
    @NamedQuery(name = "Phase.findByDescription", query = "SELECT p FROM Phase p WHERE p.description = :description"),
    @NamedQuery(name = "Phase.findByExecorder", query = "SELECT p FROM Phase p WHERE p.execorder = :execorder")})
public class Phase implements Serializable {

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
    @Basic(optional = false)
    @NotNull
    @Column(name = "execorder")
    private int execorder;
    @OneToMany(mappedBy = "swphase")
    private Collection<Activity> activityCollection;
    @JoinColumn(name = "swprocess", referencedColumnName = "id")
    @ManyToOne
    private Process swprocess;

    public Phase() {
    }

    public Phase(Integer id) {
        this.id = id;
    }

    public Phase(Integer id, int execorder) {
        this.id = id;
        this.execorder = execorder;
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

    public int getExecorder() {
        return execorder;
    }

    public void setExecorder(int execorder) {
        this.execorder = execorder;
    }

    @XmlTransient
    public Collection<Activity> getActivityCollection() {
        return activityCollection;
    }

    public void setActivityCollection(Collection<Activity> activityCollection) {
        this.activityCollection = activityCollection;
    }

    public Process getSwprocess() {
        return swprocess;
    }

    public void setSwprocess(Process swprocess) {
        this.swprocess = swprocess;
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
