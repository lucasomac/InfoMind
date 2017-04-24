package com.infomind2.model;

import com.infomind2.model.Posse;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.3.v20160218-rNA", date="2017-04-24T10:51:57")
@StaticMetamodel(Cargo.class)
public class Cargo_ { 

    public static volatile SingularAttribute<Cargo, Integer> idCargo;
    public static volatile ListAttribute<Cargo, Posse> posseList;
    public static volatile SingularAttribute<Cargo, String> nome;

}