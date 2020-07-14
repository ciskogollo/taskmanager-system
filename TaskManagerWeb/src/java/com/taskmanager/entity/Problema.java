/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.taskmanager.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;


/**
 *
 * @author cisko
 */
@Entity
@Table(name = "PROBLEMA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Problema.findAll", query = "SELECT p FROM Problema p")
    , @NamedQuery(name = "Problema.findByIdProblema", query = "SELECT p FROM Problema p WHERE p.idProblema = :idProblema")
    , @NamedQuery(name = "Problema.findByComentario", query = "SELECT p FROM Problema p WHERE p.comentario = :comentario")})
public class Problema implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_PROBLEMA")
    private BigDecimal idProblema;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "COMENTARIO")
    private String comentario;
    @JoinColumn(name = "USUARIO_ID_USUARIO", referencedColumnName = "ID_USUARIO")
    @ManyToOne(optional = false)
    private Usuario usuarioIdUsuario;

    public Problema() {
    }

    public Problema(BigDecimal idProblema) {
        this.idProblema = idProblema;
    }

    public Problema(BigDecimal idProblema, String comentario) {
        this.idProblema = idProblema;
        this.comentario = comentario;
    }

    public BigDecimal getIdProblema() {
        return idProblema;
    }

    public void setIdProblema(BigDecimal idProblema) {
        this.idProblema = idProblema;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
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
        hash += (idProblema != null ? idProblema.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Problema)) {
            return false;
        }
        Problema other = (Problema) object;
        if ((this.idProblema == null && other.idProblema != null) || (this.idProblema != null && !this.idProblema.equals(other.idProblema))) {
            return false;
        }
        return true;
    } 

    @Override
    public String toString() {
        return "{Problema: {ID:" +idProblema+", Comentario:"+comentario+" }}";
    }
    
}
