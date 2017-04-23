package com.infomind2.bean;

import com.infomind2.cotrol.MembroJpaController;
import com.infomind2.cotrol.exceptions.NonexistentEntityException;
import com.infomind2.model.Membro;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class MembroBean implements Serializable {

    MembroJpaController membroContr = new MembroJpaController();
    private List<Membro> membros;
    private Membro novoMembro;
    private Membro membroSelect;

    private String nomeBusca;
    private String cidadeBusca;
    private String estadoBusca;
    private String status;

    @PostConstruct
    public void init() {
        this.membros = new ArrayList<>(membroContr.findMembroEntities());
        this.novoMembro = new Membro();
        this.membroSelect = new Membro();
    }

    public void adcionar() {
        this.novoMembro.setStatus("SIM");
        this.membroContr.create(novoMembro);
        this.novoMembro = new Membro();
    }

    public void editar() throws NonexistentEntityException, Exception {
        this.membroContr.edit(membroSelect);
    }

    public void consultar() {
        nomeBusca = "%" + nomeBusca + "%";
        this.membros = membroContr.findConsultaGeral(nomeBusca, cidadeBusca, estadoBusca, status);
        
    }

    public List<Membro> getMembros() {
        return membros;
    }

    public void setMembros(List<Membro> membros) {
        this.membros = membros;
    }

    public Membro getNovoMembro() {
        return novoMembro;
    }

    public void setNovoMembro(Membro novoMembro) {
        this.novoMembro = novoMembro;
    }

    public Membro getMembroSelect() {
        return membroSelect;
    }

    public void setMembroSelect(Membro membroSelect) {
        this.membroSelect = membroSelect;
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

    public String getNomeBusca() {
        return nomeBusca;
    }

    public void setNomeBusca(String nomeBusca) {
        this.nomeBusca = nomeBusca;
    }

}
