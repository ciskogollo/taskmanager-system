/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.taskmanage.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author cisko
 */
@Entity
@Table(name = "TIPO_FUNCION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoFuncion.findAll", query = "SELECT t FROM TipoFuncion t")
    , @NamedQuery(name = "TipoFuncion.findByIdTipofunc", query = "SELECT t FROM TipoFuncion t WHERE t.idTipofunc = :idTipofunc")})
public class TipoFuncion implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_TIPOFUNC")
    private BigDecimal idTipofunc;
    @JoinColumn(name = "PROCESO_ID_PROCESO", referencedColumnName = "ID_PROCESO")
    @ManyToOne(optional = false)
    private Proceso procesoIdProceso;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoFuncionIdTipofunc")
    private Collection<Funcion> funcionCollection;

    public TipoFuncion() {
    }

    public TipoFuncion(BigDecimal idTipofunc) {
        this.idTipofunc = idTipofunc;
    }

    public BigDecimal getIdTipofunc() {
        return idTipofunc;
    }

    public void setIdTipofunc(BigDecimal idTipofunc) {
        this.idTipofunc = idTipofunc;
    }

    public Proceso getProcesoIdProceso() {
        return procesoIdProceso;
    }

    public void setProcesoIdProceso(Proceso procesoIdProceso) {
        this.procesoIdProceso = procesoIdProceso;
    }

    @XmlTransient
    public Collection<Funcion> getFuncionCollection() {
        return funcionCollection;
    }

    public void setFuncionCollection(Collection<Funcion> funcionCollection) {
        this.funcionCollection = funcionCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipofunc != null ? idTipofunc.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoFuncion)) {
            return false;
        }
        TipoFuncion other = (TipoFuncion) object;
        if ((this.idTipofunc == null && other.idTipofunc != null) || (this.idTipofunc != null && !this.idTipofunc.equals(other.idTipofunc))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.taskmanage.entity.TipoFuncion[ idTipofunc=" + idTipofunc + " ]";
    }
    
}
