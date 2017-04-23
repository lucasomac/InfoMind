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
@Table(name = "igreja")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Igreja.findAll", query = "SELECT i FROM Igreja i"),
    @NamedQuery(name = "Igreja.findByIdIgreja", query = "SELECT i FROM Igreja i WHERE i.idIgreja = :idIgreja"),
    @NamedQuery(name = "Igreja.findByDataAbertura", query = "SELECT i FROM Igreja i WHERE i.dataAbertura = :dataAbertura"),
    @NamedQuery(name = "Igreja.findByEnderecoIgreja", query = "SELECT i FROM Igreja i WHERE i.enderecoIgreja = :enderecoIgreja"),
    @NamedQuery(name = "Igreja.findByBairroIgreja", query = "SELECT i FROM Igreja i WHERE i.bairroIgreja = :bairroIgreja"),
    @NamedQuery(name = "Igreja.findByCidadeIgreja", query = "SELECT i FROM Igreja i WHERE i.cidadeIgreja = :cidadeIgreja"),
    @NamedQuery(name = "Igreja.findByEstadoIgreja", query = "SELECT i FROM Igreja i WHERE i.estadoIgreja = :estadoIgreja"),
    @NamedQuery(name = "Igreja.findByStatus", query = "SELECT i FROM Igreja i WHERE i.status = :status")})
public class Igreja implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idIgreja")
    private Integer idIgreja;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dataAbertura")
    @Temporal(TemporalType.DATE)
    private Date dataAbertura;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 70)
    @Column(name = "enderecoIgreja")
    private String enderecoIgreja;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 70)
    @Column(name = "bairroIgreja")
    private String bairroIgreja;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "cidadeIgreja")
    private String cidadeIgreja;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "estadoIgreja")
    private String estadoIgreja;
    @Size(max = 3)
    @Column(name = "status")
    private String status;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idIgrejaPosse", fetch = FetchType.EAGER)
    private List<Posse> posseList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "igreja", fetch = FetchType.EAGER)
    private List<Membresia> membresiaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "igreja", fetch = FetchType.EAGER)
    private List<Porte> porteList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "igreja", fetch = FetchType.EAGER)
    private List<Responsabilidade> responsabilidadeList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idIgrejaCulto", fetch = FetchType.EAGER)
    private List<Culto> cultoList;

    public Igreja() {
    }

    public Igreja(Integer idIgreja) {
        this.idIgreja = idIgreja;
    }

    public Igreja(Integer idIgreja, Date dataAbertura, String enderecoIgreja, String bairroIgreja, String cidadeIgreja, String estadoIgreja) {
        this.idIgreja = idIgreja;
        this.dataAbertura = dataAbertura;
        this.enderecoIgreja = enderecoIgreja;
        this.bairroIgreja = bairroIgreja;
        this.cidadeIgreja = cidadeIgreja;
        this.estadoIgreja = estadoIgreja;
    }

    public Integer getIdIgreja() {
        return idIgreja;
    }

    public void setIdIgreja(Integer idIgreja) {
        this.idIgreja = idIgreja;
    }

    public Date getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(Date dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public String getEnderecoIgreja() {
        return enderecoIgreja;
    }

    public void setEnderecoIgreja(String enderecoIgreja) {
        this.enderecoIgreja = enderecoIgreja;
    }

    public String getBairroIgreja() {
        return bairroIgreja;
    }

    public void setBairroIgreja(String bairroIgreja) {
        this.bairroIgreja = bairroIgreja;
    }

    public String getCidadeIgreja() {
        return cidadeIgreja;
    }

    public void setCidadeIgreja(String cidadeIgreja) {
        this.cidadeIgreja = cidadeIgreja;
    }

    public String getEstadoIgreja() {
        return estadoIgreja;
    }

    public void setEstadoIgreja(String estadoIgreja) {
        this.estadoIgreja = estadoIgreja;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
    public List<Porte> getPorteList() {
        return porteList;
    }

    public void setPorteList(List<Porte> porteList) {
        this.porteList = porteList;
    }

    @XmlTransient
    public List<Responsabilidade> getResponsabilidadeList() {
        return responsabilidadeList;
    }

    public void setResponsabilidadeList(List<Responsabilidade> responsabilidadeList) {
        this.responsabilidadeList = responsabilidadeList;
    }

    @XmlTransient
    public List<Culto> getCultoList() {
        return cultoList;
    }

    public void setCultoList(List<Culto> cultoList) {
        this.cultoList = cultoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idIgreja != null ? idIgreja.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Igreja)) {
            return false;
        }
        Igreja other = (Igreja) object;
        if ((this.idIgreja == null && other.idIgreja != null) || (this.idIgreja != null && !this.idIgreja.equals(other.idIgreja))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.infomind2.model.Igreja[ idIgreja=" + idIgreja + " ]";
    }

}
