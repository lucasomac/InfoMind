/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.infomind2.bean;

import com.infomind2.model.Igreja;
import com.infomind2.cotrol.IgrejaJpaController;
import com.infomind2.cotrol.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Lucas
 */
@ManagedBean
@ViewScoped
public class IgrejaBean implements Serializable {

    IgrejaJpaController igrejaContr = new IgrejaJpaController();
    private List<Igreja> igrejas;
    private Igreja novaIgreja;
    private Igreja igrejaSelect;
    
    private String cidadeBusca;
    private String estadoBusca;
    private String status;

    @PostConstruct
    public void init() {
        this.igrejas = new ArrayList<>(igrejaContr.findIgrejaEntities());
        this.novaIgreja = new Igreja();
        this.igrejaSelect = new Igreja();
    }

    public void adicionar() throws SQLException {
        System.out.println("Chamando o cadastrar.");
        this.novaIgreja.setStatus("SIM");
        this.igrejaContr.create(novaIgreja);
        this.novaIgreja = new Igreja();
    }

    public void editar() throws NonexistentEntityException, Exception {
        this.igrejaContr.edit(igrejaSelect);
    }

    public void consultar() {
        this.igrejas = igrejaContr.findConsultaGeral(this.cidadeBusca, this.estadoBusca, this.status);
    }

    public List<Igreja> getIgrejas() {
        return igrejas;
    }

    public Igreja getNovaIgreja() {
        return novaIgreja;
    }

    public void setIgrejas(List<Igreja> igrejas) {
        this.igrejas = igrejas;
    }

    public String getCidadeBusca() {
        return cidadeBusca;
    }

    public void setCidadeBusca(String cidadeBusca) {
        this.cidadeBusca = cidadeBusca;
    }

    public String getEstadoBusca() {
        return estadoBusca;
    }

    public void setEstadoBusca(String estadoBusca) {
        this.estadoBusca = estadoBusca;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Igreja getIgrejaSelect() {
        return igrejaSelect;
    }

    public void setIgrejaSelect(Igreja igrejaSelect) {
        this.igrejaSelect = igrejaSelect;
    }

}
