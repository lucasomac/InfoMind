/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.infomind2.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Lucas
 */
@Entity
@Table(name = "responsabilidade")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Responsabilidade.findAll", query = "SELECT r FROM Responsabilidade r"),
    @NamedQuery(name = "Responsabilidade.findByIdIgrejaResp", query = "SELECT r FROM Responsabilidade r WHERE r.responsabilidadePK.idIgrejaResp = :idIgrejaResp"),
    @NamedQuery(name = "Responsabilidade.findByIdMembroResp", query = "SELECT r FROM Responsabilidade r WHERE r.responsabilidadePK.idMembroResp = :idMembroResp"),
    @NamedQuery(name = "Responsabilidade.findByDataInicioResp", query = "SELECT r FROM Responsabilidade r WHERE r.responsabilidadePK.dataInicioResp = :dataInicioResp"),
    @NamedQuery(name = "Responsabilidade.findByDataFimResp", query = "SELECT r FROM Responsabilidade r WHERE r.dataFimResp = :dataFimResp")})
public class Responsabilidade implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ResponsabilidadePK responsabilidadePK;
    @Column(name = "dataFimResp")
    @Temporal(TemporalType.DATE)
    private Date dataFimResp;
    @JoinColumn(name = "idIgrejaResp", referencedColumnName = "idIgreja", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Igreja igreja;
    @JoinColumn(name = "idMembroResp", referencedColumnName = "idMembro", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Membro membro;

    public Responsabilidade() {
    }

    public Responsabilidade(ResponsabilidadePK responsabilidadePK) {
        this.responsabilidadePK = responsabilidadePK;
    }

    public Responsabilidade(int idIgrejaResp, int idMembroResp, Date dataInicioResp) {
        this.responsabilidadePK = new ResponsabilidadePK(idIgrejaResp, idMembroResp, dataInicioResp);
    }

    public ResponsabilidadePK getResponsabilidadePK() {
        return responsabilidadePK;
    }

    public void setResponsabilidadePK(ResponsabilidadePK responsabilidadePK) {
        this.responsabilidadePK = responsabilidadePK;
    }

    public Date getDataFimResp() {
        return dataFimResp;
    }

    public void setDataFimResp(Date dataFimResp) {
        this.dataFimResp = dataFimResp;
    }

    public Igreja getIgreja() {
        return igreja;
    }

    public void setIgreja(Igreja igreja) {
        this.igreja = igreja;
    }

    public Membro getMembro() {
        return membro;
    }

    public void setMembro(Membro membro) {
        this.membro = membro;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (responsabilidadePK != null ? responsabilidadePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Responsabilidade)) {
            return false;
        }
        Responsabilidade other = (Responsabilidade) object;
        if ((this.responsabilidadePK == null && other.responsabilidadePK != null) || (this.responsabilidadePK != null && !this.responsabilidadePK.equals(other.responsabilidadePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.infomind2.model.Responsabilidade[ responsabilidadePK=" + responsabilidadePK + " ]";
    }
    
}
