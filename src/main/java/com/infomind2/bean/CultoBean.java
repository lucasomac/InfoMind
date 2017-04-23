package com.infomind2.bean;

import com.infomind2.cotrol.CultoJpaController;
import com.infomind2.cotrol.IgrejaJpaController;
import com.infomind2.model.Culto;
import com.infomind2.model.Igreja;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class CultoBean implements Serializable {
    
    CultoJpaController cultoContr = new CultoJpaController();
    IgrejaJpaController igrejaContr = new IgrejaJpaController();
    
    private Culto novoCulto;
    private Culto cultoSelect;
    private List<Culto> cultos;
    private List<Igreja> igrejas;
    
    private Date dataCelebracao;
    private Date horario;
    
    @PostConstruct
    public void init() {
        this.cultos = new ArrayList<>(cultoContr.findCultoEntities());
        this.igrejas = new ArrayList<>(igrejaContr.findIgrejaEntities());
        this.novoCulto = new Culto();
        this.cultoSelect = new Culto();
        this.novoCulto.setDescricaoCulto("");
    }
    
    public void adicionar() {
        System.out.println("Chamei o adicionar culto");
        this.cultoContr.create(novoCulto);
        System.out.println("Terminei o adicionar culto");
        this.novoCulto = new Culto();
            }
    
    public void editar() throws Exception {
        this.cultoContr.edit(cultoSelect);
    }
    
    public void consultar() {
        this.cultos = cultoContr.findConsultaGeral((dataCelebracao), horario);
    }
    
    public Culto getNovoCulto() {
        return novoCulto;
    }
    
    public void setNovoCulto(Culto novoCulto) {
        this.novoCulto = novoCulto;
    }
    
    public List<Culto> getCultos() {
        return cultos;
    }
    
    public void setCultos(List<Culto> cultos) {
        this.cultos = cultos;
    }
    
    public Culto getCultoSelect() {
        return cultoSelect;
    }
    
    public void setCultoSelect(Culto cultoSelect) {
        this.cultoSelect = cultoSelect;
    }
    
    public List<Igreja> getIgrejas() {
        return igrejas;
    }
    
    public void setIgrejas(List<Igreja> igrejas) {
        this.igrejas = igrejas;
    }
    
    public Date getDataCelebracao() {
        return dataCelebracao;
    }
    
    public void setDataCelebracao(Date dataCelebracao) {
        this.dataCelebracao = dataCelebracao;
    }
    
    public Date getHorario() {
        return horario;
    }
    
    public void setHorario(Date horario) {
        this.horario = horario;
    }
    
}
