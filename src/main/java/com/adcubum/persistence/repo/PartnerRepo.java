package com.adcubum.persistence.repo;

import com.adcubum.persistence.entity.Partner;
import org.springframework.data.repository.CrudRepository;

public interface PartnerRepo extends CrudRepository<Partner, String> {
}
