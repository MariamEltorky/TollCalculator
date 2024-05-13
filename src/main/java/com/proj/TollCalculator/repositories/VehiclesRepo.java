package com.proj.TollCalculator.repositories;

import com.proj.TollCalculator.models.JPA.Vehicles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/***********************

 FreeVehicles Repository

 ***********************/

@Repository
public interface VehiclesRepo extends JpaRepository<Vehicles, Integer> {

}
