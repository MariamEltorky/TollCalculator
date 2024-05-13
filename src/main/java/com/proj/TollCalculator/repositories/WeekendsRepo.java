package com.proj.TollCalculator.repositories;

import com.proj.TollCalculator.models.JPA.Weekends;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeekendsRepo extends JpaRepository<Weekends, Integer> {

//    List<Weekends> getAllWeekends ();
//
    Weekends findWeekendsByCountry (String country);
}
