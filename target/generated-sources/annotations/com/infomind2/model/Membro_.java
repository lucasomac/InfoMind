package com.infomind2.model;

import com.infomind2.model.Celula;
import com.infomind2.model.Discipulado;
import com.infomind2.model.Membresia;
import com.infomind2.model.Posse;
import com.infomind2.model.Responsabilidade;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.3.v20160218-rNA", date="2017-04-24T10:51:57")
@StaticMetamodel(Membro.class)
public class Membro_ { 

    public static volatile SingularAttribute<Membro, String> isLider;
    public static volatile SingularAttribute<Membro, String> telefone;
    public static volatile SingularAttribute<Membro, String> isDoze;
    public static volatile SingularAttribute<Membro, String> enderecoMembro;
    public static volatile SingularAttribute<Membro, Date> dataBatismo;
    public static volatile ListAttribute<Membro, Discipulado> discipuladoList;
    public static volatile SingularAttribute<Membro, String> estadoMembro;
    public static volatile SingularAttribute<Membro, String> nome;
    public static volatile ListAttribute<Membro, Responsabilidade> responsabilidadeList;
    public static volatile ListAttribute<Membro, Membresia> membresiaList;
    public static volatile SingularAttribute<Membro, String> bairroMembro;
    public static volatile SingularAttribute<Membro, Date> dataNasc;
    public static volatile ListAttribute<Membro, Posse> posseList;
    public static volatile ListAttribute<Membro, Discipulado> discipuladoList1;
    public static volatile SingularAttribute<Membro, Integer> idMembro;
    public static volatile ListAttribute<Membro, Celula> celulaList;
    public static volatile SingularAttribute<Membro, String> cidadeMembro;
    public static volatile SingularAttribute<Membro, String> status;

}