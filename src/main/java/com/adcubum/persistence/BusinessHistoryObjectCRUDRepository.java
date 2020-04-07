package com.adcubum.persistence;

import com.adcubum.persistence.entity.PartnerState;
import com.adcubum.persistence.entity.State;

import java.util.Date;
import java.util.Optional;

public interface BusinessHistoryObjectCRUDRepository<T extends State> {

    void create(List<T> state); //init a new state
    void overwrite(List<T> state); //overwrites all of state
    void insert(T state); //adds new time segment
    void reset(T state, LocalDate newDateFrom, LocalDate newDateTo); //re arrange time period of the state
    void shift(String id, LocalDate newDateFrom); //shift the time periods
    void delete(String id);; //Delete the whole business history
    Optional<T> findById(String id, Date keyDate);

}
