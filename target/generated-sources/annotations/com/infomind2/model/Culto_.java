package com.infomind2.model;

import com.infomind2.model.Igreja;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.3.v20160218-rNA", date="2017-04-20T08:55:23")
@StaticMetamodel(Culto.class)
public class Culto_ { 

    public static volatile SingularAttribute<Culto, Integer> idCulto;
    public static volatile SingularAttribute<Culto, Date> horario;
    public static volatile SingularAttribute<Culto, Integer> qtdVisitante;
    public static volatile SingularAttribute<Culto, Igreja> idIgrejaCulto;
    public static volatile SingularAttribute<Culto, String> descricaoCulto;
    public static volatile SingularAttribute<Culto, Integer> qtdMembro;
    public static volatile SingularAttribute<Culto, Integer> qtdConversao;
    public static volatile SingularAttribute<Culto, Date> dataCelebracao;

}