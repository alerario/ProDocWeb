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
@Table(name = "swuserprocess")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserProcess.findAll", query = "SELECT u FROM UserProcess u"),
    @NamedQuery(name = "UserProcess.findBySwuser", query = "SELECT u FROM UserProcess u WHERE u.userProcessPK.swuser = :swuser"),
    @NamedQuery(name = "UserProcess.findBySwprocess", query = "SELECT u FROM UserProcess u WHERE u.userProcessPK.swprocess = :swprocess"),
    @NamedQuery(name = "UserProcess.findByWrite", query = "SELECT u FROM UserProcess u WHERE u.userProcessPK.write = :write")})
public class UserProcess implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UserProcessPK userProcessPK;
    @JoinColumn(name = "swprocess", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Process process;
    @JoinColumn(name = "swuser", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private User user;

    public UserProcess() {
    }

    public UserProcess(UserProcessPK userProcessPK) {
        this.userProcessPK = userProcessPK;
    }

    public UserProcess(int swuser, int swprocess, boolean write) {
        this.userProcessPK = new UserProcessPK(swuser, swprocess, write);
    }

    public UserProcessPK getUserProcessPK() {
        return userProcessPK;
    }

    public void setUserProcessPK(UserProcessPK userProcessPK) {
        this.userProcessPK = userProcessPK;
    }

    public Process getProcess() {
        return process;
    }

    public void setProcess(Process process) {
        this.process = process;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userProcessPK != null ? userProcessPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserProcess)) {
            return false;
        }
        UserProcess other = (UserProcess) object;
        if ((this.userProcessPK == null && other.userProcessPK != null) || (this.userProcessPK != null && !this.userProcessPK.equals(other.userProcessPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pdw.data.model.UserProcess[ userProcessPK=" + userProcessPK + " ]";
    }
    
}
