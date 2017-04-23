/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.infomind2.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Lucas
 */
@Entity
@Table(name = "celula")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Celula.findAll", query = "SELECT c FROM Celula c"),
    @NamedQuery(name = "Celula.findByIdCelula", query = "SELECT c FROM Celula c WHERE c.idCelula = :idCelula"),
    @NamedQuery(name = "Celula.findByDiaRealizacao", query = "SELECT c FROM Celula c WHERE c.diaRealizacao = :diaRealizacao"),
    @NamedQuery(name = "Celula.findByHorario", query = "SELECT c FROM Celula c WHERE c.horario = :horario"),
    @NamedQuery(name = "Celula.findByEnderecoCelula", query = "SELECT c FROM Celula c WHERE c.enderecoCelula = :enderecoCelula"),
    @NamedQuery(name = "Celula.findByBairroCelula", query = "SELECT c FROM Celula c WHERE c.bairroCelula = :bairroCelula"),
    @NamedQuery(name = "Celula.findByCidadeCelula", query = "SELECT c FROM Celula c WHERE c.cidadeCelula = :cidadeCelula"),
    @NamedQuery(name = "Celula.findByEstadoCelula", query = "SELECT c FROM Celula c WHERE c.estadoCelula = :estadoCelula"),
    @NamedQuery(name = "Celula.findByStatus", query = "SELECT c FROM Celula c WHERE c.status = :status")})
public class Celula implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idCelula")
    private Integer idCelula;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "diaRealizacao")
    private String diaRealizacao;
    @Basic(optional = false)
    @NotNull
    @Column(name = "horario")
    @Temporal(TemporalType.TIME)
    private Date horario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 70)
    @Column(name = "enderecoCelula")
    private String enderecoCelula;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 70)
    @Column(name = "bairroCelula")
    private String bairroCelula;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "cidadeCelula")
    private String cidadeCelula;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "estadoCelula")
    private String estadoCelula;
    @Size(max = 3)
    @Column(name = "status")
    private String status;
    @JoinColumn(name = "idMembroLider", referencedColumnName = "idMembro")
    @ManyToOne(optional = false)
    private Membro idMembroLider;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "celula", fetch = FetchType.EAGER)
    private List<Porte> porteList;

    public Celula() {
    }

    public Celula(Integer idCelula) {
        this.idCelula = idCelula;
    }

    public Celula(Integer idCelula, String diaRealizacao, Date horario, String enderecoCelula, String bairroCelula, String cidadeCelula, String estadoCelula) {
        this.idCelula = idCelula;
        this.diaRealizacao = diaRealizacao;
        this.horario = horario;
        this.enderecoCelula = enderecoCelula;
        this.bairroCelula = bairroCelula;
        this.cidadeCelula = cidadeCelula;
        this.estadoCelula = estadoCelula;
    }

    public Integer getIdCelula() {
        return idCelula;
    }

    public void setIdCelula(Integer idCelula) {
        this.idCelula = idCelula;
    }

    public String getDiaRealizacao() {
        return diaRealizacao;
    }

    public void setDiaRealizacao(String diaRealizacao) {
        this.diaRealizacao = diaRealizacao;
    }

    public Date getHorario() {
        return horario;
    }

    public void setHorario(Date horario) {
        this.horario = horario;
    }

    public String getEnderecoCelula() {
        return enderecoCelula;
    }

    public void setEnderecoCelula(String enderecoCelula) {
        this.enderecoCelula = enderecoCelula;
    }

    public String getBairroCelula() {
        return bairroCelula;
    }

    public void setBairroCelula(String bairroCelula) {
        this.bairroCelula = bairroCelula;
    }

    public String getCidadeCelula() {
        return cidadeCelula;
    }

    public void setCidadeCelula(String cidadeCelula) {
        this.cidadeCelula = cidadeCelula;
    }

    public String getEstadoCelula() {
        return estadoCelula;
    }

    public void setEstadoCelula(String estadoCelula) {
        this.estadoCelula = estadoCelula;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Membro getIdMembroLider() {
        return idMembroLider;
    }

    public void setIdMembroLider(Membro idMembroLider) {
        this.idMembroLider = idMembroLider;
    }

    @XmlTransient
    public List<Porte> getPorteList() {
        return porteList;
    }

    public void setPorteList(List<Porte> porteList) {
        this.porteList = porteList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCelula != null ? idCelula.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Celula)) {
            return false;
        }
        Celula other = (Celula) object;
        if ((this.idCelula == null && other.idCelula != null) || (this.idCelula != null && !this.idCelula.equals(other.idCelula))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.infomind2.model.Celula[ idCelula=" + idCelula + " ]";
    }
    
}
