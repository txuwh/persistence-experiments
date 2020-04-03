package com.adcubum.persistence.repo;

import com.adcubum.persistence.entity.Partner;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PartnerRepo extends CrudRepository<Partner, String> {

    List<Partner> findPartnerByBirthName(String birthName);
}
