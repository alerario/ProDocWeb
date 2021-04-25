/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdw.data.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author alexandrelerario
 */
@Embeddable
public class ActivityArtifactPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "swactivity")
    private int swactivity;
    @Basic(optional = false)
    @NotNull
    @Column(name = "swartifact")
    private int swartifact;
    @Basic(optional = false)
    @NotNull
    @Column(name = "input")
    private boolean input;

    public ActivityArtifactPK() {
    }

    public ActivityArtifactPK(int swactivity, int swartifact, boolean input) {
        this.swactivity = swactivity;
        this.swartifact = swartifact;
        this.input = input;
    }

    public int getSwactivity() {
        return swactivity;
    }

    public void setSwactivity(int swactivity) {
        this.swactivity = swactivity;
    }

    public int getSwartifact() {
        return swartifact;
    }

    public void setSwartifact(int swartifact) {
        this.swartifact = swartifact;
    }

    public boolean getInput() {
        return input;
    }

    public void setInput(boolean input) {
        this.input = input;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) swactivity;
        hash += (int) swartifact;
        hash += (input ? 1 : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ActivityArtifactPK)) {
            return false;
        }
        ActivityArtifactPK other = (ActivityArtifactPK) object;
        if (this.swactivity != other.swactivity) {
            return false;
        }
        if (this.swartifact != other.swartifact) {
            return false;
        }
        if (this.input != other.input) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pdw.data.model.ActivityArtifactPK[ swactivity=" + swactivity + ", swartifact=" + swartifact + ", input=" + input + " ]";
    }
    
}
