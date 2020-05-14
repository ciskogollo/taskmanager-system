/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.taskmanager.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
 * @author cisko
 */
@Entity
@Table(name = "ROL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rol.findAll", query = "SELECT r FROM Rol r")
    , @NamedQuery(name = "Rol.findByIdRol", query = "SELECT r FROM Rol r WHERE r.idRol = :idRol")
    , @NamedQuery(name = "Rol.findByTipoRolIdTiporol", query = "SELECT r FROM Rol r WHERE r.tipoRolIdTiporol = :tipoRolIdTiporol")
    , @NamedQuery(name = "Rol.findByNombreRol", query = "SELECT r FROM Rol r WHERE r.nombreRol = :nombreRol")})
public class Rol implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_ROL")
    private BigDecimal idRol;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TIPO_ROL_ID_TIPOROL")
    private BigInteger tipoRolIdTiporol;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4000)
    @Column(name = "NOMBRE_ROL")
    private String nombreRol;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rolIdRol")
    private Collection<Usuario> usuarioCollection;

    public Rol() {
    }

    public Rol(BigDecimal idRol) {
        this.idRol = idRol;
    }

    public Rol(BigDecimal idRol, BigInteger tipoRolIdTiporol, String nombreRol) {
        this.idRol = idRol;
        this.tipoRolIdTiporol = tipoRolIdTiporol;
        this.nombreRol = nombreRol;
    }

    public BigDecimal getIdRol() {
        return idRol;
    }

    public void setIdRol(BigDecimal idRol) {
        this.idRol = idRol;
    }

    public BigInteger getTipoRolIdTiporol() {
        return tipoRolIdTiporol;
    }

    public void setTipoRolIdTiporol(BigInteger tipoRolIdTiporol) {
        this.tipoRolIdTiporol = tipoRolIdTiporol;
    }

    public String getNombreRol() {
        return nombreRol;
    }

    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }

    @XmlTransient
    public Collection<Usuario> getUsuarioCollection() {
        return usuarioCollection;
    }

    public void setUsuarioCollection(Collection<Usuario> usuarioCollection) {
        this.usuarioCollection = usuarioCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRol != null ? idRol.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rol)) {
            return false;
        }
        Rol other = (Rol) object;
        if ((this.idRol == null && other.idRol != null) || (this.idRol != null && !this.idRol.equals(other.idRol))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.taskmanage.entity.Rol[ idRol=" + idRol + " ]";
    }
    
}
