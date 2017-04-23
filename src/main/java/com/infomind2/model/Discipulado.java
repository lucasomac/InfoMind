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
@Table(name = "discipulado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Discipulado.findAll", query = "SELECT d FROM Discipulado d"),
    @NamedQuery(name = "Discipulado.findByIdMembroDiscipulador", query = "SELECT d FROM Discipulado d WHERE d.discipuladoPK.idMembroDiscipulador = :idMembroDiscipulador"),
    @NamedQuery(name = "Discipulado.findByIdMembroDiscipulo", query = "SELECT d FROM Discipulado d WHERE d.discipuladoPK.idMembroDiscipulo = :idMembroDiscipulo"),
    @NamedQuery(name = "Discipulado.findByDataInicioDiscipulado", query = "SELECT d FROM Discipulado d WHERE d.discipuladoPK.dataInicioDiscipulado = :dataInicioDiscipulado"),
    @NamedQuery(name = "Discipulado.findByDataFimDiscipulado", query = "SELECT d FROM Discipulado d WHERE d.dataFimDiscipulado = :dataFimDiscipulado")})
public class Discipulado implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DiscipuladoPK discipuladoPK;
    @Column(name = "dataFimDiscipulado")
    @Temporal(TemporalType.DATE)
    private Date dataFimDiscipulado;
    @JoinColumn(name = "idMembroDiscipulador", referencedColumnName = "idMembro", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Membro membro;
    @JoinColumn(name = "idMembroDiscipulo", referencedColumnName = "idMembro", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Membro membro1;

    public Discipulado() {
    }

    public Discipulado(DiscipuladoPK discipuladoPK) {
        this.discipuladoPK = discipuladoPK;
    }

    public Discipulado(int idMembroDiscipulador, int idMembroDiscipulo, Date dataInicioDiscipulado) {
        this.discipuladoPK = new DiscipuladoPK(idMembroDiscipulador, idMembroDiscipulo, dataInicioDiscipulado);
    }

    public DiscipuladoPK getDiscipuladoPK() {
        return discipuladoPK;
    }

    public void setDiscipuladoPK(DiscipuladoPK discipuladoPK) {
        this.discipuladoPK = discipuladoPK;
    }

    public Date getDataFimDiscipulado() {
        return dataFimDiscipulado;
    }

    public void setDataFimDiscipulado(Date dataFimDiscipulado) {
        this.dataFimDiscipulado = dataFimDiscipulado;
    }

    public Membro getMembro() {
        return membro;
    }

    public void setMembro(Membro membro) {
        this.membro = membro;
    }

    public Membro getMembro1() {
        return membro1;
    }

    public void setMembro1(Membro membro1) {
        this.membro1 = membro1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (discipuladoPK != null ? discipuladoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Discipulado)) {
            return false;
        }
        Discipulado other = (Discipulado) object;
        if ((this.discipuladoPK == null && other.discipuladoPK != null) || (this.discipuladoPK != null && !this.discipuladoPK.equals(other.discipuladoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.infomind2.model.Discipulado[ discipuladoPK=" + discipuladoPK + " ]";
    }
    
}
