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
public class ResponsabilidadePK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "idIgrejaResp")
    private int idIgrejaResp;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idMembroResp")
    private int idMembroResp;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dataInicioResp")
    @Temporal(TemporalType.DATE)
    private Date dataInicioResp;

    public ResponsabilidadePK() {
    }

    public ResponsabilidadePK(int idIgrejaResp, int idMembroResp, Date dataInicioResp) {
        this.idIgrejaResp = idIgrejaResp;
        this.idMembroResp = idMembroResp;
        this.dataInicioResp = dataInicioResp;
    }

    public int getIdIgrejaResp() {
        return idIgrejaResp;
    }

    public void setIdIgrejaResp(int idIgrejaResp) {
        this.idIgrejaResp = idIgrejaResp;
    }

    public int getIdMembroResp() {
        return idMembroResp;
    }

    public void setIdMembroResp(int idMembroResp) {
        this.idMembroResp = idMembroResp;
    }

    public Date getDataInicioResp() {
        return dataInicioResp;
    }

    public void setDataInicioResp(Date dataInicioResp) {
        this.dataInicioResp = dataInicioResp;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idIgrejaResp;
        hash += (int) idMembroResp;
        hash += (dataInicioResp != null ? dataInicioResp.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ResponsabilidadePK)) {
            return false;
        }
        ResponsabilidadePK other = (ResponsabilidadePK) object;
        if (this.idIgrejaResp != other.idIgrejaResp) {
            return false;
        }
        if (this.idMembroResp != other.idMembroResp) {
            return false;
        }
        if ((this.dataInicioResp == null && other.dataInicioResp != null) || (this.dataInicioResp != null && !this.dataInicioResp.equals(other.dataInicioResp))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.infomind2.model.ResponsabilidadePK[ idIgrejaResp=" + idIgrejaResp + ", idMembroResp=" + idMembroResp + ", dataInicioResp=" + dataInicioResp + " ]";
    }
    
}
