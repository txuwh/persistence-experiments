package com.adcubum.persistence.entity;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.Filters;
import org.hibernate.annotations.ParamDef;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
@FilterDef(name="state", parameters=@ParamDef( name="keyDate", type="integer" ) )
@Filter(name="betweenDates", condition=":keyDate BETWEEN stateBegin AND stateEnd")
public class PartnerState implements State<Partner>  {

    @Id
    public String id;

    public String name;

    @ManyToOne
    public Partner partner;

    public Date stateBegin;

    public Date stateEnd;

    @Override
    public Partner getHead() {
        return partner;
    }
}
