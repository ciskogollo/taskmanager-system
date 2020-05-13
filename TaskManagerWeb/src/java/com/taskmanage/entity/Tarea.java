/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.taskmanage.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author cisko
 */
@Entity
@Table(name = "TAREA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tarea.findAll", query = "SELECT t FROM Tarea t")
    , @NamedQuery(name = "Tarea.findByIdTarea", query = "SELECT t FROM Tarea t WHERE t.idTarea = :idTarea")
    , @NamedQuery(name = "Tarea.findByDescripcion", query = "SELECT t FROM Tarea t WHERE t.descripcion = :descripcion")
    , @NamedQuery(name = "Tarea.findByFechaIngreso", query = "SELECT t FROM Tarea t WHERE t.fechaIngreso = :fechaIngreso")
    , @NamedQuery(name = "Tarea.findByFechaPlazo", query = "SELECT t FROM Tarea t WHERE t.fechaPlazo = :fechaPlazo")
    , @NamedQuery(name = "Tarea.findByFechaRecepcion", query = "SELECT t FROM Tarea t WHERE t.fechaRecepcion = :fechaRecepcion")
    , @NamedQuery(name = "Tarea.findByIdTsuperior", query = "SELECT t FROM Tarea t WHERE t.idTsuperior = :idTsuperior")
    , @NamedQuery(name = "Tarea.findByIdAntes", query = "SELECT t FROM Tarea t WHERE t.idAntes = :idAntes")
    , @NamedQuery(name = "Tarea.findByIdSuces", query = "SELECT t FROM Tarea t WHERE t.idSuces = :idSuces")})
public class Tarea implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_TAREA")
    private BigDecimal idTarea;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 420)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_INGRESO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaIngreso;
    @Column(name = "FECHA_PLAZO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaPlazo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_RECEPCION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRecepcion;
    @Column(name = "ID_TSUPERIOR")
    private BigInteger idTsuperior;
    @Column(name = "ID_ANTES")
    private BigInteger idAntes;
    @Column(name = "ID_SUCES")
    private BigInteger idSuces;
    @JoinColumn(name = "FUNCION_ID_FUNCION", referencedColumnName = "ID_FUNCION")
    @ManyToOne(optional = false)
    private Funcion funcionIdFuncion;
    @JoinColumn(name = "STATUS_WORK_ID_STATUS", referencedColumnName = "ID_STATUS")
    @ManyToOne(optional = false)
    private StatusWork statusWorkIdStatus;
    @JoinColumn(name = "USUARIO_ID_USUARIO", referencedColumnName = "ID_USUARIO")
    @ManyToOne(optional = false)
    private Usuario usuarioIdUsuario;

    public Tarea() {
    }

    public Tarea(BigDecimal idTarea) {
        this.idTarea = idTarea;
    }

    public Tarea(BigDecimal idTarea, String descripcion, Date fechaIngreso, Date fechaRecepcion) {
        this.idTarea = idTarea;
        this.descripcion = descripcion;
        this.fechaIngreso = fechaIngreso;
        this.fechaRecepcion = fechaRecepcion;
    }

    public BigDecimal getIdTarea() {
        return idTarea;
    }

    public void setIdTarea(BigDecimal idTarea) {
        this.idTarea = idTarea;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Date getFechaPlazo() {
        return fechaPlazo;
    }

    public void setFechaPlazo(Date fechaPlazo) {
        this.fechaPlazo = fechaPlazo;
    }

    public Date getFechaRecepcion() {
        return fechaRecepcion;
    }

    public void setFechaRecepcion(Date fechaRecepcion) {
        this.fechaRecepcion = fechaRecepcion;
    }

    public BigInteger getIdTsuperior() {
        return idTsuperior;
    }

    public void setIdTsuperior(BigInteger idTsuperior) {
        this.idTsuperior = idTsuperior;
    }

    public BigInteger getIdAntes() {
        return idAntes;
    }

    public void setIdAntes(BigInteger idAntes) {
        this.idAntes = idAntes;
    }

    public BigInteger getIdSuces() {
        return idSuces;
    }

    public void setIdSuces(BigInteger idSuces) {
        this.idSuces = idSuces;
    }

    public Funcion getFuncionIdFuncion() {
        return funcionIdFuncion;
    }

    public void setFuncionIdFuncion(Funcion funcionIdFuncion) {
        this.funcionIdFuncion = funcionIdFuncion;
    }

    public StatusWork getStatusWorkIdStatus() {
        return statusWorkIdStatus;
    }

    public void setStatusWorkIdStatus(StatusWork statusWorkIdStatus) {
        this.statusWorkIdStatus = statusWorkIdStatus;
    }

    public Usuario getUsuarioIdUsuario() {
        return usuarioIdUsuario;
    }

    public void setUsuarioIdUsuario(Usuario usuarioIdUsuario) {
        this.usuarioIdUsuario = usuarioIdUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTarea != null ? idTarea.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tarea)) {
            return false;
        }
        Tarea other = (Tarea) object;
        if ((this.idTarea == null && other.idTarea != null) || (this.idTarea != null && !this.idTarea.equals(other.idTarea))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.taskmanage.entity.Tarea[ idTarea=" + idTarea + " ]";
    }
    
}
