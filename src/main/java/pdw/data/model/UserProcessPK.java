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
public class UserProcessPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "swuser")
    private int swuser;
    @Basic(optional = false)
    @NotNull
    @Column(name = "swprocess")
    private int swprocess;
    @Basic(optional = false)
    @NotNull
    @Column(name = "write")
    private boolean write;

    public UserProcessPK() {
    }

    public UserProcessPK(int swuser, int swprocess, boolean write) {
        this.swuser = swuser;
        this.swprocess = swprocess;
        this.write = write;
    }

    public int getSwuser() {
        return swuser;
    }

    public void setSwuser(int swuser) {
        this.swuser = swuser;
    }

    public int getSwprocess() {
        return swprocess;
    }

    public void setSwprocess(int swprocess) {
        this.swprocess = swprocess;
    }

    public boolean getWrite() {
        return write;
    }

    public void setWrite(boolean write) {
        this.write = write;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) swuser;
        hash += (int) swprocess;
        hash += (write ? 1 : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserProcessPK)) {
            return false;
        }
        UserProcessPK other = (UserProcessPK) object;
        if (this.swuser != other.swuser) {
            return false;
        }
        if (this.swprocess != other.swprocess) {
            return false;
        }
        if (this.write != other.write) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pdw.data.model.UserProcessPK[ swuser=" + swuser + ", swprocess=" + swprocess + ", write=" + write + " ]";
    }
    
}
