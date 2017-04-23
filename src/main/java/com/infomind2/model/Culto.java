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
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Lucas
 */
@Entity
@Table(name = "culto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Culto.findAll", query = "SELECT c FROM Culto c"),
    @NamedQuery(name = "Culto.findByIdCulto", query = "SELECT c FROM Culto c WHERE c.idCulto = :idCulto"),
    @NamedQuery(name = "Culto.findByDataCelebracao", query = "SELECT c FROM Culto c WHERE c.dataCelebracao = :dataCelebracao"),
    @NamedQuery(name = "Culto.findByQtdMembro", query = "SELECT c FROM Culto c WHERE c.qtdMembro = :qtdMembro"),
    @NamedQuery(name = "Culto.findByQtdVisitante", query = "SELECT c FROM Culto c WHERE c.qtdVisitante = :qtdVisitante"),
    @NamedQuery(name = "Culto.findByQtdConversao", query = "SELECT c FROM Culto c WHERE c.qtdConversao = :qtdConversao"),
    @NamedQuery(name = "Culto.findByHorario", query = "SELECT c FROM Culto c WHERE c.horario = :horario"),
    @NamedQuery(name = "Culto.findByDescricaoCulto", query = "SELECT c FROM Culto c WHERE c.descricaoCulto = :descricaoCulto")})
public class Culto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idCulto")
    private Integer idCulto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dataCelebracao")
    @Temporal(TemporalType.DATE)
    private Date dataCelebracao;
    @Basic(optional = false)
    @NotNull
    @Column(name = "qtdMembro")
    private int qtdMembro;
    @Basic(optional = false)
    @NotNull
    @Column(name = "qtdVisitante")
    private int qtdVisitante;
    @Basic(optional = false)
    @NotNull
    @Column(name = "qtdConversao")
    private int qtdConversao;
    @Basic(optional = false)
    @NotNull
    @Column(name = "horario")
    @Temporal(TemporalType.TIME)
    private Date horario;
    @Size(max = 100)
    @Column(name = "descricaoCulto")
    private String descricaoCulto;
    @JoinColumn(name = "idIgrejaCulto", referencedColumnName = "idIgreja")
    @ManyToOne(optional = false)
    private Igreja idIgrejaCulto;

    public Culto() {
    }

    public Culto(Integer idCulto) {
        this.idCulto = idCulto;
    }

    public Culto(Integer idCulto, Date dataCelebracao, int qtdMembro, int qtdVisitante, int qtdConversao, Date horario) {
        this.idCulto = idCulto;
        this.dataCelebracao = dataCelebracao;
        this.qtdMembro = qtdMembro;
        this.qtdVisitante = qtdVisitante;
        this.qtdConversao = qtdConversao;
        this.horario = horario;
    }

    public Integer getIdCulto() {
        return idCulto;
    }

    public void setIdCulto(Integer idCulto) {
        this.idCulto = idCulto;
    }

    public Date getDataCelebracao() {
        return dataCelebracao;
    }

    public void setDataCelebracao(Date dataCelebracao) {
        this.dataCelebracao = dataCelebracao;
    }

    public int getQtdMembro() {
        return qtdMembro;
    }

    public void setQtdMembro(int qtdMembro) {
        this.qtdMembro = qtdMembro;
    }

    public int getQtdVisitante() {
        return qtdVisitante;
    }

    public void setQtdVisitante(int qtdVisitante) {
        this.qtdVisitante = qtdVisitante;
    }

    public int getQtdConversao() {
        return qtdConversao;
    }

    public void setQtdConversao(int qtdConversao) {
        this.qtdConversao = qtdConversao;
    }

    public Date getHorario() {
        return horario;
    }

    public void setHorario(Date horario) {
        this.horario = horario;
    }

    public String getDescricaoCulto() {
        return descricaoCulto;
    }

    public void setDescricaoCulto(String descricaoCulto) {
        this.descricaoCulto = descricaoCulto;
    }

    public Igreja getIdIgrejaCulto() {
        return idIgrejaCulto;
    }

    public void setIdIgrejaCulto(Igreja idIgrejaCulto) {
        this.idIgrejaCulto = idIgrejaCulto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCulto != null ? idCulto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Culto)) {
            return false;
        }
        Culto other = (Culto) object;
        if ((this.idCulto == null && other.idCulto != null) || (this.idCulto != null && !this.idCulto.equals(other.idCulto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.infomind2.model.Culto[ idCulto=" + idCulto + " ]";
    }
    
}
