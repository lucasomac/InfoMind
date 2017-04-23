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
public class PossePK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "idCargoPosse")
    private int idCargoPosse;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idMembroPosse")
    private int idMembroPosse;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dataPosse")
    @Temporal(TemporalType.DATE)
    private Date dataPosse;

    public PossePK() {
    }

    public PossePK(int idCargoPosse, int idMembroPosse, Date dataPosse) {
        this.idCargoPosse = idCargoPosse;
        this.idMembroPosse = idMembroPosse;
        this.dataPosse = dataPosse;
    }

    public int getIdCargoPosse() {
        return idCargoPosse;
    }

    public void setIdCargoPosse(int idCargoPosse) {
        this.idCargoPosse = idCargoPosse;
    }

    public int getIdMembroPosse() {
        return idMembroPosse;
    }

    public void setIdMembroPosse(int idMembroPosse) {
        this.idMembroPosse = idMembroPosse;
    }

    public Date getDataPosse() {
        return dataPosse;
    }

    public void setDataPosse(Date dataPosse) {
        this.dataPosse = dataPosse;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idCargoPosse;
        hash += (int) idMembroPosse;
        hash += (dataPosse != null ? dataPosse.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PossePK)) {
            return false;
        }
        PossePK other = (PossePK) object;
        if (this.idCargoPosse != other.idCargoPosse) {
            return false;
        }
        if (this.idMembroPosse != other.idMembroPosse) {
            return false;
        }
        if ((this.dataPosse == null && other.dataPosse != null) || (this.dataPosse != null && !this.dataPosse.equals(other.dataPosse))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.infomind2.model.PossePK[ idCargoPosse=" + idCargoPosse + ", idMembroPosse=" + idMembroPosse + ", dataPosse=" + dataPosse + " ]";
    }
    
}
