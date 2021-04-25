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
@Table(name = "swgateway")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Gateway.findAll", query = "SELECT g FROM Gateway g"),
    @NamedQuery(name = "Gateway.findById", query = "SELECT g FROM Gateway g WHERE g.id = :id"),
    @NamedQuery(name = "Gateway.findByAnnotation", query = "SELECT g FROM Gateway g WHERE g.annotation = :annotation"),
    @NamedQuery(name = "Gateway.findByType", query = "SELECT g FROM Gateway g WHERE g.type = :type")})
public class Gateway implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 40)
    @Column(name = "annotation")
    private String annotation;
    @Column(name = "type")
    private Character type;
    @OneToMany(mappedBy = "targetgateway")
    private Collection<Sequence> sequenceCollection;
    @OneToMany(mappedBy = "sourcegateway")
    private Collection<Sequence> sequenceCollection1;

    public Gateway() {
    }

    public Gateway(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAnnotation() {
        return annotation;
    }

    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }

    public Character getType() {
        return type;
    }

    public void setType(Character type) {
        this.type = type;
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
        if (!(object instanceof Gateway)) {
            return false;
        }
        Gateway other = (Gateway) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pdw.data.model.Gateway[ id=" + id + " ]";
    }
    
}
