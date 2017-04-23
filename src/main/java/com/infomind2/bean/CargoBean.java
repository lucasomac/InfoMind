package com.infomind2.bean;

import com.infomind2.cotrol.CargoJpaController;
import com.infomind2.cotrol.exceptions.NonexistentEntityException;
import com.infomind2.model.Cargo;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class CargoBean implements Serializable {

    private CargoJpaController cargoContr = new CargoJpaController();
    private List<Cargo> cargos;
    private Cargo novoCargo;
    private Cargo cargoSelect;
    private String nome;

    @PostConstruct
    public void init() {
        this.cargos = new ArrayList<>(cargoContr.findCargoEntities());
        this.novoCargo = new Cargo();
        this.cargoSelect = new Cargo();
    }

    public List<Cargo> getCargos() {
        return cargos;
    }

    public void setCargos(List<Cargo> cargos) {
        this.cargos = cargos;
    }

    public Cargo getNovoCargo() {
        return novoCargo;
    }

    public void setNovoCargo(Cargo novoCargo) {
        this.novoCargo = novoCargo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void adicionar() throws SQLException {
        System.out.println("Chamando o cadastrar.");
        this.cargoContr.create(novoCargo);
        this.novoCargo = new Cargo();
    }

    public void consultar() {
        this.cargos = cargoContr.buscaNome(this.nome);
    }

    public void editar() throws NonexistentEntityException, Exception {
        System.out.println("Chamei metodo editar");
        System.out.printf("id-%d....  nome-%s", cargoSelect.getIdCargo(), cargoSelect.getNome());
        this.cargoContr.edit(cargoSelect);
        System.out.println("Acabou o editar");
    }

    public CargoJpaController getCargoContr() {
        return cargoContr;
    }

    public void setCargoContr(CargoJpaController cargoContr) {
        this.cargoContr = cargoContr;
    }

    public Cargo getCargoSelect() {
        return cargoSelect;
    }

    public void setCargoSelect(Cargo cargoSelect) {
        this.cargoSelect = cargoSelect;
    }
}
