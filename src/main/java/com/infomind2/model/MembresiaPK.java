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
public class MembresiaPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "idIgrejaMembro")
    private int idIgrejaMembro;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idMembroIgreja")
    private int idMembroIgreja;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dataInicioMembresia")
    @Temporal(TemporalType.DATE)
    private Date dataInicioMembresia;

    public MembresiaPK() {
    }

    public MembresiaPK(int idIgrejaMembro, int idMembroIgreja, Date dataInicioMembresia) {
        this.idIgrejaMembro = idIgrejaMembro;
        this.idMembroIgreja = idMembroIgreja;
        this.dataInicioMembresia = dataInicioMembresia;
    }

    public int getIdIgrejaMembro() {
        return idIgrejaMembro;
    }

    public void setIdIgrejaMembro(int idIgrejaMembro) {
        this.idIgrejaMembro = idIgrejaMembro;
    }

    public int getIdMembroIgreja() {
        return idMembroIgreja;
    }

    public void setIdMembroIgreja(int idMembroIgreja) {
        this.idMembroIgreja = idMembroIgreja;
    }

    public Date getDataInicioMembresia() {
        return dataInicioMembresia;
    }

    public void setDataInicioMembresia(Date dataInicioMembresia) {
        this.dataInicioMembresia = dataInicioMembresia;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idIgrejaMembro;
        hash += (int) idMembroIgreja;
        hash += (dataInicioMembresia != null ? dataInicioMembresia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MembresiaPK)) {
            return false;
        }
        MembresiaPK other = (MembresiaPK) object;
        if (this.idIgrejaMembro != other.idIgrejaMembro) {
            return false;
        }
        if (this.idMembroIgreja != other.idMembroIgreja) {
            return false;
        }
        if ((this.dataInicioMembresia == null && other.dataInicioMembresia != null) || (this.dataInicioMembresia != null && !this.dataInicioMembresia.equals(other.dataInicioMembresia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.infomind2.model.MembresiaPK[ idIgrejaMembro=" + idIgrejaMembro + ", idMembroIgreja=" + idMembroIgreja + ", dataInicioMembresia=" + dataInicioMembresia + " ]";
    }
    
}
