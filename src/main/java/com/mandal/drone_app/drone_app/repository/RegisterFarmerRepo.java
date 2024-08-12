package com.mandal.drone_app.drone_app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mandal.drone_app.drone_app.model.FarmerId;
import com.mandal.drone_app.drone_app.model.RegisterFarmer;

public interface RegisterFarmerRepo extends JpaRepository<RegisterFarmer, FarmerId>{
    List<RegisterFarmer> findByPilotCode(String pilotCode);
    Optional<RegisterFarmer> findByFarmerId_FarmerMobNo(String farmerMobNo);
    List<RegisterFarmer> findByFarmerId_FarmerNameContainingIgnoreCase(String farmerName);
}
