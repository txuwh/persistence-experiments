package com.adcubum.persistence.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.Collection;

@Entity
public class Partner implements Head {

    @Id
    public String id;

    public String birthName;

    @OneToMany
    @JoinColumn(name="PARTNER_ID")
    public Collection<PartnerState> states;

}
