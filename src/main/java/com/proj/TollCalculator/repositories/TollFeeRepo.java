package com.proj.TollCalculator.repositories;

import com.proj.TollCalculator.models.JPA.TollFee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/********************

 TollFee Repository

 ********************/

@Repository
public interface TollFeeRepo extends JpaRepository<TollFee, Integer> {
}
