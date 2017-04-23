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
@Table(name = "posse")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Posse.findAll", query = "SELECT p FROM Posse p"),
    @NamedQuery(name = "Posse.findByIdCargoPosse", query = "SELECT p FROM Posse p WHERE p.possePK.idCargoPosse = :idCargoPosse"),
    @NamedQuery(name = "Posse.findByIdMembroPosse", query = "SELECT p FROM Posse p WHERE p.possePK.idMembroPosse = :idMembroPosse"),
    @NamedQuery(name = "Posse.findByDataPosse", query = "SELECT p FROM Posse p WHERE p.possePK.dataPosse = :dataPosse"),
    @NamedQuery(name = "Posse.findByDataFimPosse", query = "SELECT p FROM Posse p WHERE p.dataFimPosse = :dataFimPosse")})
public class Posse implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PossePK possePK;
    @Column(name = "dataFimPosse")
    @Temporal(TemporalType.DATE)
    private Date dataFimPosse;
    @JoinColumn(name = "idCargoPosse", referencedColumnName = "idCargo", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Cargo cargo;
    @JoinColumn(name = "idIgrejaPosse", referencedColumnName = "idIgreja")
    @ManyToOne(optional = false)
    private Igreja idIgrejaPosse;
    @JoinColumn(name = "idMembroPosse", referencedColumnName = "idMembro", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Membro membro;

    public Posse() {
    }

    public Posse(PossePK possePK) {
        this.possePK = possePK;
    }

    public Posse(int idCargoPosse, int idMembroPosse, Date dataPosse) {
        this.possePK = new PossePK(idCargoPosse, idMembroPosse, dataPosse);
    }

    public PossePK getPossePK() {
        return possePK;
    }

    public void setPossePK(PossePK possePK) {
        this.possePK = possePK;
    }

    public Date getDataFimPosse() {
        return dataFimPosse;
    }

    public void setDataFimPosse(Date dataFimPosse) {
        this.dataFimPosse = dataFimPosse;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public Igreja getIdIgrejaPosse() {
        return idIgrejaPosse;
    }

    public void setIdIgrejaPosse(Igreja idIgrejaPosse) {
        this.idIgrejaPosse = idIgrejaPosse;
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
        hash += (possePK != null ? possePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Posse)) {
            return false;
        }
        Posse other = (Posse) object;
        if ((this.possePK == null && other.possePK != null) || (this.possePK != null && !this.possePK.equals(other.possePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.infomind2.model.Posse[ possePK=" + possePK + " ]";
    }
    
}
