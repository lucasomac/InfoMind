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
public class PortePK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "idIgrejaPorte")
    private int idIgrejaPorte;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idCelulaPorte")
    private int idCelulaPorte;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dataInicioCelula")
    @Temporal(TemporalType.DATE)
    private Date dataInicioCelula;

    public PortePK() {
    }

    public PortePK(int idIgrejaPorte, int idCelulaPorte, Date dataInicioCelula) {
        this.idIgrejaPorte = idIgrejaPorte;
        this.idCelulaPorte = idCelulaPorte;
        this.dataInicioCelula = dataInicioCelula;
    }

    public int getIdIgrejaPorte() {
        return idIgrejaPorte;
    }

    public void setIdIgrejaPorte(int idIgrejaPorte) {
        this.idIgrejaPorte = idIgrejaPorte;
    }

    public int getIdCelulaPorte() {
        return idCelulaPorte;
    }

    public void setIdCelulaPorte(int idCelulaPorte) {
        this.idCelulaPorte = idCelulaPorte;
    }

    public Date getDataInicioCelula() {
        return dataInicioCelula;
    }

    public void setDataInicioCelula(Date dataInicioCelula) {
        this.dataInicioCelula = dataInicioCelula;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idIgrejaPorte;
        hash += (int) idCelulaPorte;
        hash += (dataInicioCelula != null ? dataInicioCelula.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PortePK)) {
            return false;
        }
        PortePK other = (PortePK) object;
        if (this.idIgrejaPorte != other.idIgrejaPorte) {
            return false;
        }
        if (this.idCelulaPorte != other.idCelulaPorte) {
            return false;
        }
        if ((this.dataInicioCelula == null && other.dataInicioCelula != null) || (this.dataInicioCelula != null && !this.dataInicioCelula.equals(other.dataInicioCelula))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.infomind2.model.PortePK[ idIgrejaPorte=" + idIgrejaPorte + ", idCelulaPorte=" + idCelulaPorte + ", dataInicioCelula=" + dataInicioCelula + " ]";
    }
    
}
