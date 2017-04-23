package com.infomind2.model;

import com.infomind2.model.Igreja;
import com.infomind2.model.Membro;
import com.infomind2.model.ResponsabilidadePK;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.3.v20160218-rNA", date="2017-04-20T08:55:23")
@StaticMetamodel(Responsabilidade.class)
public class Responsabilidade_ { 

    public static volatile SingularAttribute<Responsabilidade, Date> dataFimResp;
    public static volatile SingularAttribute<Responsabilidade, Igreja> igreja;
    public static volatile SingularAttribute<Responsabilidade, Membro> membro;
    public static volatile SingularAttribute<Responsabilidade, ResponsabilidadePK> responsabilidadePK;

}