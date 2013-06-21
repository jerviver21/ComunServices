/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vi.comun.dominio;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jerviver21
 */
@Entity
@Table(name = "festivos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Festivos.findAll", query = "SELECT f FROM Festivos f"),
    @NamedQuery(name = "Festivos.findByFecha", query = "SELECT f FROM Festivos f WHERE f.fecha = :fecha")})
public class Festivos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;

    public Festivos() {
    }

    public Festivos(Date fecha) {
        this.fecha = fecha;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (fecha != null ? fecha.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Festivos)) {
            return false;
        }
        Festivos other = (Festivos) object;
        if ((this.fecha == null && other.fecha != null) || (this.fecha != null && !this.fecha.equals(other.fecha))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.vi.comun.dominio.Festivos[ fecha=" + fecha + " ]";
    }
    
}
