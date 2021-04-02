/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdw.data.model;

import java.io.Serializable;
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
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author alexandrelerario
 */
@Entity
@Table(name = "swsequence")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sequence.findAll", query = "SELECT s FROM Sequence s"),
    @NamedQuery(name = "Sequence.findByAnnotation", query = "SELECT s FROM Sequence s WHERE s.annotation = :annotation"),
    @NamedQuery(name = "Sequence.findById", query = "SELECT s FROM Sequence s WHERE s.id = :id")})
public class Sequence implements Serializable {

    private static final long serialVersionUID = 1L;
    @Size(max = 40)
    @Column(name = "annotation")
    private String annotation;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "sourceactivity", referencedColumnName = "id")
    @ManyToOne
    private Activity sourceactivity;
    @JoinColumn(name = "targetactivity", referencedColumnName = "id")
    @ManyToOne
    private Activity targetactivity;
    @JoinColumn(name = "targetgateway", referencedColumnName = "id")
    @ManyToOne
    private Gateway targetgateway;
    @JoinColumn(name = "sourcegateway", referencedColumnName = "id")
    @ManyToOne
    private Gateway sourcegateway;

    public Sequence() {
    }

    public Sequence(Integer id) {
        this.id = id;
    }

    public String getAnnotation() {
        return annotation;
    }

    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Activity getSourceactivity() {
        return sourceactivity;
    }

    public void setSourceactivity(Activity sourceactivity) {
        this.sourceactivity = sourceactivity;
    }

    public Activity getTargetactivity() {
        return targetactivity;
    }

    public void setTargetactivity(Activity targetactivity) {
        this.targetactivity = targetactivity;
    }

    public Gateway getTargetgateway() {
        return targetgateway;
    }

    public void setTargetgateway(Gateway targetgateway) {
        this.targetgateway = targetgateway;
    }

    public Gateway getSourcegateway() {
        return sourcegateway;
    }

    public void setSourcegateway(Gateway sourcegateway) {
        this.sourcegateway = sourcegateway;
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
        if (!(object instanceof Sequence)) {
            return false;
        }
        Sequence other = (Sequence) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pdw.data.model.Sequence[ id=" + id + " ]";
    }
    
}
