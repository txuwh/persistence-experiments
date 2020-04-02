package com.adcubum.persistence;

import com.adcubum.persistence.entity.PartnerState;
import com.adcubum.persistence.entity.State;

import java.util.Date;

public interface BusinessHistoryObjectCRUDRepository<T extends State> {

    void create(T state);

    T findById(String id, Date keyDate);

    void update(T state);

    void delete(T state);

}
