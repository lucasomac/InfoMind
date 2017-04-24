package com.infomind2.model;

import com.infomind2.model.Membro;
import com.infomind2.model.Porte;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.3.v20160218-rNA", date="2017-04-24T10:51:57")
@StaticMetamodel(Celula.class)
public class Celula_ { 

    public static volatile SingularAttribute<Celula, String> estadoCelula;
    public static volatile SingularAttribute<Celula, Membro> idMembroLider;
    public static volatile SingularAttribute<Celula, Date> horario;
    public static volatile SingularAttribute<Celula, String> cidadeCelula;
    public static volatile SingularAttribute<Celula, String> diaRealizacao;
    public static volatile SingularAttribute<Celula, String> enderecoCelula;
    public static volatile SingularAttribute<Celula, String> bairroCelula;
    public static volatile SingularAttribute<Celula, Integer> idCelula;
    public static volatile ListAttribute<Celula, Porte> porteList;
    public static volatile SingularAttribute<Celula, String> status;

}