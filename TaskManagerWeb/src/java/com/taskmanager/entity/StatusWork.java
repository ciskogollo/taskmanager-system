/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.taskmanager.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
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
@Table(name = "STATUS_WORK")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StatusWork.findAll", query = "SELECT s FROM StatusWork s")
    , @NamedQuery(name = "StatusWork.findByIdStatus", query = "SELECT s FROM StatusWork s WHERE s.idStatus = :idStatus")
    , @NamedQuery(name = "StatusWork.findByTipoStatus", query = "SELECT s FROM StatusWork s WHERE s.tipoStatus = :tipoStatus")})
public class StatusWork implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_STATUS")
    private BigDecimal idStatus;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "TIPO_STATUS")
    private String tipoStatus;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "statusWorkIdStatus")
    private List<Tarea> tareaList;

    public StatusWork() {
    }

    public StatusWork(BigDecimal idStatus) {
        this.idStatus = idStatus;
    }

    public StatusWork(BigDecimal idStatus, String tipoStatus) {
        this.idStatus = idStatus;
        this.tipoStatus = tipoStatus;
    }

    public BigDecimal getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(BigDecimal idStatus) {
        this.idStatus = idStatus;
    }

    public String getTipoStatus() {
        return tipoStatus;
    }

    public void setTipoStatus(String tipoStatus) {
        this.tipoStatus = tipoStatus;
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
        hash += (idStatus != null ? idStatus.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StatusWork)) {
            return false;
        }
        StatusWork other = (StatusWork) object;
        if ((this.idStatus == null && other.idStatus != null) || (this.idStatus != null && !this.idStatus.equals(other.idStatus))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "StatusWork[ idStatus=" + idStatus + " ]";
    }
    
}
