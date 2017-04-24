package com.infomind2.model;

import com.infomind2.model.Igreja;
import com.infomind2.model.MembresiaPK;
import com.infomind2.model.Membro;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.3.v20160218-rNA", date="2017-04-24T10:51:57")
@StaticMetamodel(Membresia.class)
public class Membresia_ { 

    public static volatile SingularAttribute<Membresia, MembresiaPK> membresiaPK;
    public static volatile SingularAttribute<Membresia, Igreja> igreja;
    public static volatile SingularAttribute<Membresia, Date> dataFimMembresia;
    public static volatile SingularAttribute<Membresia, Membro> membro;

}