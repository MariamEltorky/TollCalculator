package com.proj.TollCalculator.repositories;

import com.proj.TollCalculator.models.JPA.GeneralConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GeneralConfigRepo extends JpaRepository<GeneralConfig, Integer> {

    GeneralConfig findByKey(String Key);
}
