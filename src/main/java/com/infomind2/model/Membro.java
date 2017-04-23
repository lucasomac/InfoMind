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
@Table(name = "membro")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Membro.findAll", query = "SELECT m FROM Membro m"),
    @NamedQuery(name = "Membro.findByIdMembro", query = "SELECT m FROM Membro m WHERE m.idMembro = :idMembro"),
    @NamedQuery(name = "Membro.findByNome", query = "SELECT m FROM Membro m WHERE m.nome = :nome"),
    @NamedQuery(name = "Membro.findByTelefone", query = "SELECT m FROM Membro m WHERE m.telefone = :telefone"),
    @NamedQuery(name = "Membro.findByDataNasc", query = "SELECT m FROM Membro m WHERE m.dataNasc = :dataNasc"),
    @NamedQuery(name = "Membro.findByIsLider", query = "SELECT m FROM Membro m WHERE m.isLider = :isLider"),
    @NamedQuery(name = "Membro.findByDataBatismo", query = "SELECT m FROM Membro m WHERE m.dataBatismo = :dataBatismo"),
    @NamedQuery(name = "Membro.findByIsDoze", query = "SELECT m FROM Membro m WHERE m.isDoze = :isDoze"),
    @NamedQuery(name = "Membro.findByEnderecoMembro", query = "SELECT m FROM Membro m WHERE m.enderecoMembro = :enderecoMembro"),
    @NamedQuery(name = "Membro.findByBairroMembro", query = "SELECT m FROM Membro m WHERE m.bairroMembro = :bairroMembro"),
    @NamedQuery(name = "Membro.findByCidadeMembro", query = "SELECT m FROM Membro m WHERE m.cidadeMembro = :cidadeMembro"),
    @NamedQuery(name = "Membro.findByEstadoMembro", query = "SELECT m FROM Membro m WHERE m.estadoMembro = :estadoMembro"),
    @NamedQuery(name = "Membro.findByStatus", query = "SELECT m FROM Membro m WHERE m.status = :status")})
public class Membro implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idMembro")
    private Integer idMembro;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 80)
    @Column(name = "nome")
    private String nome;
    @Size(max = 15)
    @Column(name = "telefone")
    private String telefone;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dataNasc")
    @Temporal(TemporalType.DATE)
    private Date dataNasc;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "isLider")
    private String isLider;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dataBatismo")
    @Temporal(TemporalType.DATE)
    private Date dataBatismo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "isDoze")
    private String isDoze;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 70)
    @Column(name = "enderecoMembro")
    private String enderecoMembro;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 70)
    @Column(name = "bairroMembro")
    private String bairroMembro;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "cidadeMembro")
    private String cidadeMembro;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "estadoMembro")
    private String estadoMembro;
    @Size(max = 3)
    @Column(name = "status")
    private String status;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMembroLider", fetch = FetchType.EAGER)
    private List<Celula> celulaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "membro", fetch = FetchType.EAGER)
    private List<Posse> posseList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "membro", fetch = FetchType.EAGER)
    private List<Membresia> membresiaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "membro", fetch = FetchType.EAGER)
    private List<Responsabilidade> responsabilidadeList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "membro", fetch = FetchType.EAGER)
    private List<Discipulado> discipuladoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "membro1", fetch = FetchType.EAGER)
    private List<Discipulado> discipuladoList1;

    public Membro() {
    }

    public Membro(Integer idMembro) {
        this.idMembro = idMembro;
    }

    public Membro(Integer idMembro, String nome, Date dataNasc, String isLider, Date dataBatismo, String isDoze, String enderecoMembro, String bairroMembro, String cidadeMembro, String estadoMembro) {
        this.idMembro = idMembro;
        this.nome = nome;
        this.dataNasc = dataNasc;
        this.isLider = isLider;
        this.dataBatismo = dataBatismo;
        this.isDoze = isDoze;
        this.enderecoMembro = enderecoMembro;
        this.bairroMembro = bairroMembro;
        this.cidadeMembro = cidadeMembro;
        this.estadoMembro = estadoMembro;
    }

    public Integer getIdMembro() {
        return idMembro;
    }

    public void setIdMembro(Integer idMembro) {
        this.idMembro = idMembro;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Date getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(Date dataNasc) {
        this.dataNasc = dataNasc;
    }

    public String getIsLider() {
        return isLider;
    }

    public void setIsLider(String isLider) {
        this.isLider = isLider;
    }

    public Date getDataBatismo() {
        return dataBatismo;
    }

    public void setDataBatismo(Date dataBatismo) {
        this.dataBatismo = dataBatismo;
    }

    public String getIsDoze() {
        return isDoze;
    }

    public void setIsDoze(String isDoze) {
        this.isDoze = isDoze;
    }

    public String getEnderecoMembro() {
        return enderecoMembro;
    }

    public void setEnderecoMembro(String enderecoMembro) {
        this.enderecoMembro = enderecoMembro;
    }

    public String getBairroMembro() {
        return bairroMembro;
    }

    public void setBairroMembro(String bairroMembro) {
        this.bairroMembro = bairroMembro;
    }

    public String getCidadeMembro() {
        return cidadeMembro;
    }

    public void setCidadeMembro(String cidadeMembro) {
        this.cidadeMembro = cidadeMembro;
    }

    public String getEstadoMembro() {
        return estadoMembro;
    }

    public void setEstadoMembro(String estadoMembro) {
        this.estadoMembro = estadoMembro;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @XmlTransient
    public List<Celula> getCelulaList() {
        return celulaList;
    }

    public void setCelulaList(List<Celula> celulaList) {
        this.celulaList = celulaList;
    }

    @XmlTransient
    public List<Posse> getPosseList() {
        return posseList;
    }

    public void setPosseList(List<Posse> posseList) {
        this.posseList = posseList;
    }

    @XmlTransient
    public List<Membresia> getMembresiaList() {
        return membresiaList;
    }

    public void setMembresiaList(List<Membresia> membresiaList) {
        this.membresiaList = membresiaList;
    }

    @XmlTransient
    public List<Responsabilidade> getResponsabilidadeList() {
        return responsabilidadeList;
    }

    public void setResponsabilidadeList(List<Responsabilidade> responsabilidadeList) {
        this.responsabilidadeList = responsabilidadeList;
    }

    @XmlTransient
    public List<Discipulado> getDiscipuladoList() {
        return discipuladoList;
    }

    public void setDiscipuladoList(List<Discipulado> discipuladoList) {
        this.discipuladoList = discipuladoList;
    }

    @XmlTransient
    public List<Discipulado> getDiscipuladoList1() {
        return discipuladoList1;
    }

    public void setDiscipuladoList1(List<Discipulado> discipuladoList1) {
        this.discipuladoList1 = discipuladoList1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMembro != null ? idMembro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Membro)) {
            return false;
        }
        Membro other = (Membro) object;
        if ((this.idMembro == null && other.idMembro != null) || (this.idMembro != null && !this.idMembro.equals(other.idMembro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.infomind2.model.Membro[ idMembro=" + idMembro + " ]";
    }

}
