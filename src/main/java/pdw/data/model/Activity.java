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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
@Table(name = "swactivity")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Activity.findAll", query = "SELECT a FROM Activity a"),
    @NamedQuery(name = "Activity.findById", query = "SELECT a FROM Activity a WHERE a.id = :id"),
    @NamedQuery(name = "Activity.findByName", query = "SELECT a FROM Activity a WHERE a.name = :name"),
    @NamedQuery(name = "Activity.findByDescription", query = "SELECT a FROM Activity a WHERE a.description = :description"),
    @NamedQuery(name = "Activity.findByMandatory", query = "SELECT a FROM Activity a WHERE a.mandatory = :mandatory"),
    @NamedQuery(name = "Activity.findByExecorder", query = "SELECT a FROM Activity a WHERE a.execorder = :execorder"),
    @NamedQuery(name = "Activity.findByStart", query = "SELECT a FROM Activity a WHERE a.start = :start")})
public class Activity implements Serializable {

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
    @Column(name = "mandatory")
    private boolean mandatory;
    @Basic(optional = false)
    @NotNull
    @Column(name = "execorder")
    private int execorder;
    @Basic(optional = false)
    @NotNull
    @Column(name = "start")
    private boolean start;
    @JoinTable(name = "swactivflow", joinColumns = {
        @JoinColumn(name = "swactivity", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "nextactivity", referencedColumnName = "id")})
    @ManyToMany
    private Collection<Activity> activityCollection;
    @ManyToMany(mappedBy = "activityCollection")
    private Collection<Activity> activityCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "activity")
    private Collection<ActivityArtifact> activityArtifactCollection;
    @JoinColumn(name = "swguidance", referencedColumnName = "id")
    @ManyToOne
    private Guidance swguidance;
    @JoinColumn(name = "swphase", referencedColumnName = "id")
    @ManyToOne
    private Phase swphase;
    @JoinColumn(name = "swrole", referencedColumnName = "id")
    @ManyToOne
    private Role swrole;
    @OneToMany(mappedBy = "sourceactivity")
    private Collection<Sequence> sequenceCollection;
    @OneToMany(mappedBy = "targetactivity")
    private Collection<Sequence> sequenceCollection1;

    public Activity() {
    }

    public Activity(Integer id) {
        this.id = id;
    }

    public Activity(Integer id, boolean mandatory, int execorder, boolean start) {
        this.id = id;
        this.mandatory = mandatory;
        this.execorder = execorder;
        this.start = start;
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

    public boolean getMandatory() {
        return mandatory;
    }

    public void setMandatory(boolean mandatory) {
        this.mandatory = mandatory;
    }

    public int getExecorder() {
        return execorder;
    }

    public void setExecorder(int execorder) {
        this.execorder = execorder;
    }

    public boolean getStart() {
        return start;
    }

    public void setStart(boolean start) {
        this.start = start;
    }

    @XmlTransient
    public Collection<Activity> getActivityCollection() {
        return activityCollection;
    }

    public void setActivityCollection(Collection<Activity> activityCollection) {
        this.activityCollection = activityCollection;
    }

    @XmlTransient
    public Collection<Activity> getActivityCollection1() {
        return activityCollection1;
    }

    public void setActivityCollection1(Collection<Activity> activityCollection1) {
        this.activityCollection1 = activityCollection1;
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

    public Phase getSwphase() {
        return swphase;
    }

    public void setSwphase(Phase swphase) {
        this.swphase = swphase;
    }

    public Role getSwrole() {
        return swrole;
    }

    public void setSwrole(Role swrole) {
        this.swrole = swrole;
    }

    @XmlTransient
    public Collection<Sequence> getSequenceCollection() {
        return sequenceCollection;
    }

    public void setSequenceCollection(Collection<Sequence> sequenceCollection) {
        this.sequenceCollection = sequenceCollection;
    }

    @XmlTransient
    public Collection<Sequence> getSequenceCollection1() {
        return sequenceCollection1;
    }

    public void setSequenceCollection1(Collection<Sequence> sequenceCollection1) {
        this.sequenceCollection1 = sequenceCollection1;
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
        if (!(object instanceof Activity)) {
            return false;
        }
        Activity other = (Activity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pdw.data.model.Activity[ id=" + id + " ]";
    }
    
}
