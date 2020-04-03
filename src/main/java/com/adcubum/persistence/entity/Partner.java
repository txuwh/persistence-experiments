package com.adcubum.persistence.entity;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.Collection;

@Entity
@FilterDef(name="state", parameters=@ParamDef( name="keyDate", type="timestamp" ))
public class Partner implements Head {

    @Id
    public String id;

    public String birthName;

    @OneToMany
    @JoinColumn(name="PARTNER_ID")
    @Filter(name="state", condition=":keyDate BETWEEN state_begin AND state_end")
    public Collection<PartnerState> states;

}

