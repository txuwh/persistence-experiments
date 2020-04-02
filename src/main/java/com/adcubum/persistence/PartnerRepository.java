package com.adcubum.persistence;

import com.adcubum.persistence.entity.PartnerState;

import java.util.Date;

public interface PartnerRepository {

    void create(PartnerState partnerState);

    PartnerState findById(String id, Date keyDate);

    void update(PartnerState partnerState);

    void delete(PartnerState partnerState);

}
