package com.mandal.drone_app.drone_app.service;

import com.mandal.drone_app.drone_app.model.RegisterFarmer;

import java.util.List;
import java.util.Optional;

public interface RegisterFarmerService {
    Optional<RegisterFarmer> updateFarmerDetailsByMobileNumber(String farmerMobNo, RegisterFarmer updatedDetails);
    boolean deleteFarmerByMobileNumber(String farmerMobNo);
    List<RegisterFarmer> getFarmersByName(String farmerName);
}