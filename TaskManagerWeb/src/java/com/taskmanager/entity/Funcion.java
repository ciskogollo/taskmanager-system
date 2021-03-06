/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.taskmanager.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
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
@Table(name = "FUNCION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Funcion.findAll", query = "SELECT f FROM Funcion f")
    , @NamedQuery(name = "Funcion.findByIdFuncion", query = "SELECT f FROM Funcion f WHERE f.idFuncion = :idFuncion")
    , @NamedQuery(name = "Funcion.findByIdTipofunc", query = "SELECT f FROM Funcion f WHERE f.nombreFunc = :nombreFunc")})
public class Funcion implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_FUNCION")
    private BigDecimal idFuncion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NOMBRE_FUNC")
    private String nombreFunc;
    @JoinColumn(name = "TIPO_FUNCION_ID_TIPOFUNC", referencedColumnName = "ID_TIPOFUNC")
    @ManyToOne(optional = false)
    private TipoFuncion tipoFuncionIdTipofunc;
    @JoinColumn(name = "UNIDAD_ID_UNIDAD", referencedColumnName = "ID_UNIDAD")
    @ManyToOne(optional = false)
    private Unidad unidadIdUnidad;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "funcionIdFuncion")
    private List<Tarea> tareaList;

    public Funcion() {
    }

    public Funcion(BigDecimal idFuncion) {
        this.idFuncion = idFuncion;
    }

    public Funcion(BigDecimal idFuncion, String nombreFunc) {
        this.idFuncion = idFuncion;
        this.nombreFunc = nombreFunc;
    }

    public BigDecimal getIdFuncion() {
        return idFuncion;
    }

    public void setIdFuncion(BigDecimal idFuncion) {
        this.idFuncion = idFuncion;
    }

    public String getIdTipofunc() {
        return nombreFunc;
    }

    public void setIdTipofunc(String nombreFunc) {
        this.nombreFunc = nombreFunc;
    }

    public TipoFuncion getTipoFuncionIdTipofunc() {
        return tipoFuncionIdTipofunc;
    }

    public void setTipoFuncionIdTipofunc(TipoFuncion tipoFuncionIdTipofunc) {
        this.tipoFuncionIdTipofunc = tipoFuncionIdTipofunc;
    }

    public Unidad getUnidadIdUnidad() {
        return unidadIdUnidad;
    }

    public void setUnidadIdUnidad(Unidad unidadIdUnidad) {
        this.unidadIdUnidad = unidadIdUnidad;
    }

    @XmlTransient
    public List<Tarea> getTareaList() {
        return tareaList;
    }

    public void setTareaList(List<Tarea> tareaList) {
        this.tareaList = tareaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFuncion != null ? idFuncion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Funcion)) {
            return false;
        }
        Funcion other = (Funcion) object;
        if ((this.idFuncion == null && other.idFuncion != null) || (this.idFuncion != null && !this.idFuncion.equals(other.idFuncion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.taskmanager.entity.Funcion[ idFuncion=" + idFuncion + " ]";
    }
    
}
