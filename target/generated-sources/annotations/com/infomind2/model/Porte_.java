package com.infomind2.model;

import com.infomind2.model.Celula;
import com.infomind2.model.Igreja;
import com.infomind2.model.PortePK;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.3.v20160218-rNA", date="2017-04-20T08:55:23")
@StaticMetamodel(Porte.class)
public class Porte_ { 

    public static volatile SingularAttribute<Porte, Celula> celula;
    public static volatile SingularAttribute<Porte, Date> dataFimCelula;
    public static volatile SingularAttribute<Porte, Igreja> igreja;
    public static volatile SingularAttribute<Porte, PortePK> portePK;

}