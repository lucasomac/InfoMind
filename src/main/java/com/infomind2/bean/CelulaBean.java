package com.infomind2.bean;

import com.infomind2.cotrol.CelulaJpaController;
import com.infomind2.cotrol.MembroJpaController;
import com.infomind2.cotrol.exceptions.NonexistentEntityException;
import com.infomind2.model.Celula;
import com.infomind2.model.Membro;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class CelulaBean implements Serializable {

    private CelulaJpaController celulaContr = new CelulaJpaController();
    private MembroJpaController membroContr = new MembroJpaController();

    private List<Celula> celulas;
    private Celula novaCelula;
    private Celula celulaSelect;
    private List<Membro> lideres;

    private String diaRealizacao;
    private Date horario;
    private String cidadeBusca;
    private String estadoBusca;
    private String status;

    @PostConstruct
    public void init() {
        this.celulas = new ArrayList<>(celulaContr.findCelulaEntities());
        this.lideres = new ArrayList<>(membroContr.listaLideres());
        this.novaCelula = new Celula();
        this.celulaSelect = new Celula();
    }

    public List<Celula> getCelulas() {
        return celulas;
    }

    public void setCelulas(List<Celula> celulas) {
        this.celulas = celulas;
    }

    public CelulaJpaController getCelulaContr() {
        return celulaContr;
    }

    public void setCelulaContr(CelulaJpaController celulaContr) {
        this.celulaContr = celulaContr;
    }

    public Celula getNovaCelula() {
        return novaCelula;
    }

    public void setNovaCelula(Celula novaCelula) {
        this.novaCelula = novaCelula;
    }

    public Celula getCelulaSelect() {
        return celulaSelect;
    }

    public void setCelulaSelect(Celula celulaSelect) {
        this.celulaSelect = celulaSelect;
    }

    public List<Membro> getLideres() {
        return lideres;
    }

    public void setLideres(List<Membro> lideres) {
        this.lideres = lideres;
    }

    public void adicionar() {
        this.novaCelula.setStatus("SIM");
        System.out.println("Chamei adicionar bean celula");
        this.celulaContr.create(novaCelula);
        System.out.println("Conclui adicionar bean celula");
        this.novaCelula = new Celula();
    }

    public void editar() throws NonexistentEntityException, Exception {
        this.celulaContr.edit(celulaSelect);
    }

    public void consultar() {
        this.celulas = this.celulaContr.findConsultaGeral(diaRealizacao, horario, cidadeBusca, estadoBusca, status);
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

}
