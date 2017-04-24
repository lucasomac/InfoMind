package com.infomind2.model;

import com.infomind2.model.Culto;
import com.infomind2.model.Membresia;
import com.infomind2.model.Porte;
import com.infomind2.model.Posse;
import com.infomind2.model.Responsabilidade;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.3.v20160218-rNA", date="2017-04-24T10:51:57")
@StaticMetamodel(Igreja.class)
public class Igreja_ { 

    public static volatile ListAttribute<Igreja, Culto> cultoList;
    public static volatile SingularAttribute<Igreja, String> cidadeIgreja;
    public static volatile SingularAttribute<Igreja, String> bairroIgreja;
    public static volatile ListAttribute<Igreja, Posse> posseList;
    public static volatile SingularAttribute<Igreja, String> enderecoIgreja;
    public static volatile ListAttribute<Igreja, Responsabilidade> responsabilidadeList;
    public static volatile ListAttribute<Igreja, Membresia> membresiaList;
    public static volatile ListAttribute<Igreja, Porte> porteList;
    public static volatile SingularAttribute<Igreja, String> estadoIgreja;
    public static volatile SingularAttribute<Igreja, Integer> idIgreja;
    public static volatile SingularAttribute<Igreja, Date> dataAbertura;
    public static volatile SingularAttribute<Igreja, String> status;

}