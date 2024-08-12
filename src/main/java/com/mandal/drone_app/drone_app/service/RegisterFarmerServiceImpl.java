package com.mandal.drone_app.drone_app.service;

import com.mandal.drone_app.drone_app.model.RegisterFarmer;
import com.mandal.drone_app.drone_app.repository.RegisterFarmerRepo;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterFarmerServiceImpl implements RegisterFarmerService {

    @Autowired
    private RegisterFarmerRepo registerFarmerRepository;

    @SuppressWarnings("null")
    public RegisterFarmer saveFarmer(RegisterFarmer farmer) {
        
        return registerFarmerRepository.save(farmer);
    }

    public List<RegisterFarmer> getFarmersByPilotCode(String pilotCode) {
        return registerFarmerRepository.findByPilotCode(pilotCode);
    }

    @Override
    public Optional<RegisterFarmer> updateFarmerDetailsByMobileNumber(String farmerMobNo, RegisterFarmer updatedDetails) {
        // Find the existing farmer by mobile number
        Optional<RegisterFarmer> existingFarmerOpt = registerFarmerRepository.findByFarmerId_FarmerMobNo(farmerMobNo);

        if (existingFarmerOpt.isPresent()) {
            RegisterFarmer existingFarmer = existingFarmerOpt.get();
            // Update fields as necessary
            existingFarmer.setDroneCode(updatedDetails.getDroneCode());
            existingFarmer.setDate(updatedDetails.getDate());
            existingFarmer.setPilotCode(updatedDetails.getPilotCode());
            existingFarmer.setCoPilotName(updatedDetails.getCoPilotName());
            existingFarmer.setCoPilotCode(updatedDetails.getCoPilotCode());
            existingFarmer.setState(updatedDetails.getState());
            existingFarmer.setDistrict(updatedDetails.getDistrict());
            existingFarmer.setBlock(updatedDetails.getBlock());
            existingFarmer.setVillage(updatedDetails.getVillage());
            existingFarmer.setSessionNumber(updatedDetails.getSessionNumber());
            existingFarmer.setFlightNumber(updatedDetails.getFlightNumber());
            existingFarmer.setCrop(updatedDetails.getCrop());
            existingFarmer.setMedicineName(updatedDetails.getMedicineName());
            existingFarmer.setSafetyCheck(updatedDetails.getSafetyCheck());
            existingFarmer.setAcre(updatedDetails.getAcre());

            return Optional.of(registerFarmerRepository.save(existingFarmer));
        }
        return Optional.empty();
    }

    @SuppressWarnings("null")
    @Override
    public boolean deleteFarmerByMobileNumber(String farmerMobNo) {
        Optional<RegisterFarmer> existingFarmerOpt = registerFarmerRepository.findByFarmerId_FarmerMobNo(farmerMobNo);
        if (existingFarmerOpt.isPresent()) {
            registerFarmerRepository.delete(existingFarmerOpt.get());
            return true;
        }
        return false;
    }

    public List<RegisterFarmer> getFarmersByName(String farmerName) {
        return registerFarmerRepository.findByFarmerId_FarmerNameContainingIgnoreCase(farmerName);
    }
}
