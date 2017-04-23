/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.infomind2.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Lucas
 */
@Embeddable
public class DiscipuladoPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "idMembroDiscipulador")
    private int idMembroDiscipulador;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idMembroDiscipulo")
    private int idMembroDiscipulo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dataInicioDiscipulado")
    @Temporal(TemporalType.DATE)
    private Date dataInicioDiscipulado;

    public DiscipuladoPK() {
    }

    public DiscipuladoPK(int idMembroDiscipulador, int idMembroDiscipulo, Date dataInicioDiscipulado) {
        this.idMembroDiscipulador = idMembroDiscipulador;
        this.idMembroDiscipulo = idMembroDiscipulo;
        this.dataInicioDiscipulado = dataInicioDiscipulado;
    }

    public int getIdMembroDiscipulador() {
        return idMembroDiscipulador;
    }

    public void setIdMembroDiscipulador(int idMembroDiscipulador) {
        this.idMembroDiscipulador = idMembroDiscipulador;
    }

    public int getIdMembroDiscipulo() {
        return idMembroDiscipulo;
    }

    public void setIdMembroDiscipulo(int idMembroDiscipulo) {
        this.idMembroDiscipulo = idMembroDiscipulo;
    }

    public Date getDataInicioDiscipulado() {
        return dataInicioDiscipulado;
    }

    public void setDataInicioDiscipulado(Date dataInicioDiscipulado) {
        this.dataInicioDiscipulado = dataInicioDiscipulado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idMembroDiscipulador;
        hash += (int) idMembroDiscipulo;
        hash += (dataInicioDiscipulado != null ? dataInicioDiscipulado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DiscipuladoPK)) {
            return false;
        }
        DiscipuladoPK other = (DiscipuladoPK) object;
        if (this.idMembroDiscipulador != other.idMembroDiscipulador) {
            return false;
        }
        if (this.idMembroDiscipulo != other.idMembroDiscipulo) {
            return false;
        }
        if ((this.dataInicioDiscipulado == null && other.dataInicioDiscipulado != null) || (this.dataInicioDiscipulado != null && !this.dataInicioDiscipulado.equals(other.dataInicioDiscipulado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.infomind2.model.DiscipuladoPK[ idMembroDiscipulador=" + idMembroDiscipulador + ", idMembroDiscipulo=" + idMembroDiscipulo + ", dataInicioDiscipulado=" + dataInicioDiscipulado + " ]";
    }
    
}
