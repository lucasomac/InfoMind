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
@Table(name = "membresia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Membresia.findAll", query = "SELECT m FROM Membresia m"),
    @NamedQuery(name = "Membresia.findByIdIgrejaMembro", query = "SELECT m FROM Membresia m WHERE m.membresiaPK.idIgrejaMembro = :idIgrejaMembro"),
    @NamedQuery(name = "Membresia.findByIdMembroIgreja", query = "SELECT m FROM Membresia m WHERE m.membresiaPK.idMembroIgreja = :idMembroIgreja"),
    @NamedQuery(name = "Membresia.findByDataInicioMembresia", query = "SELECT m FROM Membresia m WHERE m.membresiaPK.dataInicioMembresia = :dataInicioMembresia"),
    @NamedQuery(name = "Membresia.findByDataFimMembresia", query = "SELECT m FROM Membresia m WHERE m.dataFimMembresia = :dataFimMembresia")})
public class Membresia implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected MembresiaPK membresiaPK;
    @Column(name = "dataFimMembresia")
    @Temporal(TemporalType.DATE)
    private Date dataFimMembresia;
    @JoinColumn(name = "idIgrejaMembro", referencedColumnName = "idIgreja", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Igreja igreja;
    @JoinColumn(name = "idMembroIgreja", referencedColumnName = "idMembro", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Membro membro;

    public Membresia() {
    }

    public Membresia(MembresiaPK membresiaPK) {
        this.membresiaPK = membresiaPK;
    }

    public Membresia(int idIgrejaMembro, int idMembroIgreja, Date dataInicioMembresia) {
        this.membresiaPK = new MembresiaPK(idIgrejaMembro, idMembroIgreja, dataInicioMembresia);
    }

    public MembresiaPK getMembresiaPK() {
        return membresiaPK;
    }

    public void setMembresiaPK(MembresiaPK membresiaPK) {
        this.membresiaPK = membresiaPK;
    }

    public Date getDataFimMembresia() {
        return dataFimMembresia;
    }

    public void setDataFimMembresia(Date dataFimMembresia) {
        this.dataFimMembresia = dataFimMembresia;
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
        hash += (membresiaPK != null ? membresiaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Membresia)) {
            return false;
        }
        Membresia other = (Membresia) object;
        if ((this.membresiaPK == null && other.membresiaPK != null) || (this.membresiaPK != null && !this.membresiaPK.equals(other.membresiaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.infomind2.model.Membresia[ membresiaPK=" + membresiaPK + " ]";
    }
    
}
