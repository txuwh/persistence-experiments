package com.adcubum.persistence;

import com.adcubum.persistence.entity.PartnerState;
import com.adcubum.persistence.entity.State;

import java.util.Date;
import java.util.Optional;

public interface BusinessHistoryObjectCRUDRepository<T extends State> {

    void create(T state);

    Optional<T> findById(String id, Date keyDate);

    void update(T state);

    void delete(T state);

}
