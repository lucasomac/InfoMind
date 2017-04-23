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
@Table(name = "porte")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Porte.findAll", query = "SELECT p FROM Porte p"),
    @NamedQuery(name = "Porte.findByIdIgrejaPorte", query = "SELECT p FROM Porte p WHERE p.portePK.idIgrejaPorte = :idIgrejaPorte"),
    @NamedQuery(name = "Porte.findByIdCelulaPorte", query = "SELECT p FROM Porte p WHERE p.portePK.idCelulaPorte = :idCelulaPorte"),
    @NamedQuery(name = "Porte.findByDataInicioCelula", query = "SELECT p FROM Porte p WHERE p.portePK.dataInicioCelula = :dataInicioCelula"),
    @NamedQuery(name = "Porte.findByDataFimCelula", query = "SELECT p FROM Porte p WHERE p.dataFimCelula = :dataFimCelula")})
public class Porte implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PortePK portePK;
    @Column(name = "dataFimCelula")
    @Temporal(TemporalType.DATE)
    private Date dataFimCelula;
    @JoinColumn(name = "idCelulaPorte", referencedColumnName = "idCelula", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Celula celula;
    @JoinColumn(name = "idIgrejaPorte", referencedColumnName = "idIgreja", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Igreja igreja;

    public Porte() {
    }

    public Porte(PortePK portePK) {
        this.portePK = portePK;
    }

    public Porte(int idIgrejaPorte, int idCelulaPorte, Date dataInicioCelula) {
        this.portePK = new PortePK(idIgrejaPorte, idCelulaPorte, dataInicioCelula);
    }

    public PortePK getPortePK() {
        return portePK;
    }

    public void setPortePK(PortePK portePK) {
        this.portePK = portePK;
    }

    public Date getDataFimCelula() {
        return dataFimCelula;
    }

    public void setDataFimCelula(Date dataFimCelula) {
        this.dataFimCelula = dataFimCelula;
    }

    public Celula getCelula() {
        return celula;
    }

    public void setCelula(Celula celula) {
        this.celula = celula;
    }

    public Igreja getIgreja() {
        return igreja;
    }

    public void setIgreja(Igreja igreja) {
        this.igreja = igreja;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (portePK != null ? portePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Porte)) {
            return false;
        }
        Porte other = (Porte) object;
        if ((this.portePK == null && other.portePK != null) || (this.portePK != null && !this.portePK.equals(other.portePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.infomind2.model.Porte[ portePK=" + portePK + " ]";
    }
    
}
