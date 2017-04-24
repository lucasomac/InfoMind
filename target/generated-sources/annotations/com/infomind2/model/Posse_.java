package com.infomind2.model;

import com.infomind2.model.Cargo;
import com.infomind2.model.Igreja;
import com.infomind2.model.Membro;
import com.infomind2.model.PossePK;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.3.v20160218-rNA", date="2017-04-24T10:51:57")
@StaticMetamodel(Posse.class)
public class Posse_ { 

    public static volatile SingularAttribute<Posse, Membro> membro;
    public static volatile SingularAttribute<Posse, Date> dataFimPosse;
    public static volatile SingularAttribute<Posse, Cargo> cargo;
    public static volatile SingularAttribute<Posse, PossePK> possePK;
    public static volatile SingularAttribute<Posse, Igreja> idIgrejaPosse;

}