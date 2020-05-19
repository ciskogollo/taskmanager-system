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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author cisko
 */
@Entity
@Table(name = "PROCESO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Proceso.findAll", query = "SELECT p FROM Proceso p")
    , @NamedQuery(name = "Proceso.findByIdProceso", query = "SELECT p FROM Proceso p WHERE p.idProceso = :idProceso")
    , @NamedQuery(name = "Proceso.findByTipoProceso", query = "SELECT p FROM Proceso p WHERE p.tipoProceso = :tipoProceso")
    , @NamedQuery(name = "Proceso.findByResponsableIdResp", query = "SELECT p FROM Proceso p WHERE p.responsableIdResp = :responsableIdResp")
    , @NamedQuery(name = "Proceso.findByIdResponsable", query = "SELECT p FROM Proceso p WHERE p.idResponsable = :idResponsable")})
public class Proceso implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_PROCESO")
    private BigDecimal idProceso;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "TIPO_PROCESO")
    private String tipoProceso;
    @Basic(optional = false)
    @NotNull
    @Column(name = "RESPONSABLE_ID_RESP")
    private BigInteger responsableIdResp;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_RESPONSABLE")
    private BigInteger idResponsable;
    @JoinColumn(name = "CLIENTE_ID_CLIENTE", referencedColumnName = "ID_CLIENTE")
    @ManyToOne(optional = false)
    private Cliente clienteIdCliente;
    @JoinColumn(name = "USUARIO_ID_USUARIO", referencedColumnName = "ID_USUARIO")
    @ManyToOne(optional = false)
    private Usuario usuarioIdUsuario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "procesoIdProceso")
    private List<TipoFuncion> tipoFuncionList;

    public Proceso() {
    }

    public Proceso(BigDecimal idProceso) {
        this.idProceso = idProceso;
    }

    public Proceso(BigDecimal idProceso, String tipoProceso, BigInteger responsableIdResp, BigInteger idResponsable) {
        this.idProceso = idProceso;
        this.tipoProceso = tipoProceso;
        this.responsableIdResp = responsableIdResp;
        this.idResponsable = idResponsable;
    }

    public BigDecimal getIdProceso() {
        return idProceso;
    }

    public void setIdProceso(BigDecimal idProceso) {
        this.idProceso = idProceso;
    }

    public String getTipoProceso() {
        return tipoProceso;
    }

    public void setTipoProceso(String tipoProceso) {
        this.tipoProceso = tipoProceso;
    }

    public BigInteger getResponsableIdResp() {
        return responsableIdResp;
    }

    public void setResponsableIdResp(BigInteger responsableIdResp) {
        this.responsableIdResp = responsableIdResp;
    }

    public BigInteger getIdResponsable() {
        return idResponsable;
    }

    public void setIdResponsable(BigInteger idResponsable) {
        this.idResponsable = idResponsable;
    }

    public Cliente getClienteIdCliente() {
        return clienteIdCliente;
    }

    public void setClienteIdCliente(Cliente clienteIdCliente) {
        this.clienteIdCliente = clienteIdCliente;
    }

    public Usuario getUsuarioIdUsuario() {
        return usuarioIdUsuario;
    }

    public void setUsuarioIdUsuario(Usuario usuarioIdUsuario) {
        this.usuarioIdUsuario = usuarioIdUsuario;
    }

    @XmlTransient
    public List<TipoFuncion> getTipoFuncionList() {
        return tipoFuncionList;
    }

    public void setTipoFuncionList(List<TipoFuncion> tipoFuncionList) {
        this.tipoFuncionList = tipoFuncionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProceso != null ? idProceso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Proceso)) {
            return false;
        }
        Proceso other = (Proceso) object;
        if ((this.idProceso == null && other.idProceso != null) || (this.idProceso != null && !this.idProceso.equals(other.idProceso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.taskmanager.entity.Proceso[ idProceso=" + idProceso + " ]";
    }
    
}
