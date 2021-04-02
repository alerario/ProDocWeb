/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdw.data.model;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author alexandrelerario
 */
@Entity
@Table(name = "swactivityartifact")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ActivityArtifact.findAll", query = "SELECT a FROM ActivityArtifact a"),
    @NamedQuery(name = "ActivityArtifact.findBySwactivity", query = "SELECT a FROM ActivityArtifact a WHERE a.activityArtifactPK.swactivity = :swactivity"),
    @NamedQuery(name = "ActivityArtifact.findBySwartifact", query = "SELECT a FROM ActivityArtifact a WHERE a.activityArtifactPK.swartifact = :swartifact"),
    @NamedQuery(name = "ActivityArtifact.findByInput", query = "SELECT a FROM ActivityArtifact a WHERE a.activityArtifactPK.input = :input")})
public class ActivityArtifact implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ActivityArtifactPK activityArtifactPK;
    @JoinColumn(name = "swactivity", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Activity activity;
    @JoinColumn(name = "swartifact", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Arctifact arctifact;

    public ActivityArtifact() {
    }

    public ActivityArtifact(ActivityArtifactPK activityArtifactPK) {
        this.activityArtifactPK = activityArtifactPK;
    }

    public ActivityArtifact(int swactivity, int swartifact, boolean input) {
        this.activityArtifactPK = new ActivityArtifactPK(swactivity, swartifact, input);
    }

    public ActivityArtifactPK getActivityArtifactPK() {
        return activityArtifactPK;
    }

    public void setActivityArtifactPK(ActivityArtifactPK activityArtifactPK) {
        this.activityArtifactPK = activityArtifactPK;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public Arctifact getArctifact() {
        return arctifact;
    }

    public void setArctifact(Arctifact arctifact) {
        this.arctifact = arctifact;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (activityArtifactPK != null ? activityArtifactPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ActivityArtifact)) {
            return false;
        }
        ActivityArtifact other = (ActivityArtifact) object;
        if ((this.activityArtifactPK == null && other.activityArtifactPK != null) || (this.activityArtifactPK != null && !this.activityArtifactPK.equals(other.activityArtifactPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pdw.data.model.ActivityArtifact[ activityArtifactPK=" + activityArtifactPK + " ]";
    }
    
}
