package com.proj.TollCalculator.repositories;

import com.proj.TollCalculator.models.JPA.Holidays;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/********************

 Holidays Repository

 ********************/

@Repository
public interface HolidaysRepo extends JpaRepository<Holidays, Integer> {
}
