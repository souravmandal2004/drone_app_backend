package com.mandal.drone_app.drone_app.controller;

import com.mandal.drone_app.drone_app.model.RegisterFarmer;
import com.mandal.drone_app.drone_app.service.RegisterFarmerServiceImpl;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/farmers")
public class RegisterFarmerController {

    @Autowired
    private RegisterFarmerServiceImpl registerFarmerService;

    @PostMapping("/register")
    public ResponseEntity<String> registerFarmer(@RequestBody @NonNull RegisterFarmer farmer) {
        try {
            registerFarmerService.saveFarmer(farmer);
            return new ResponseEntity<>("Farmer registered successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/list")
    public ResponseEntity<List<RegisterFarmer>> getAllFarmers(@RequestParam String pilotCode) {
        List<RegisterFarmer> farmers = registerFarmerService.getFarmersByPilotCode(pilotCode);
        return ResponseEntity.ok(farmers);
    }

    @PutMapping("/{farmerMobNo}")
    public ResponseEntity<RegisterFarmer> updateFarmer(
            @PathVariable String farmerMobNo,
            @RequestBody RegisterFarmer updatedDetails) {

        Optional<RegisterFarmer> updatedFarmer = registerFarmerService.updateFarmerDetailsByMobileNumber(farmerMobNo, updatedDetails);

        if (updatedFarmer.isPresent()) {
            return new ResponseEntity<>(updatedFarmer.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{farmerMobNo}")
    public ResponseEntity<String> deleteFarmer(@PathVariable String farmerMobNo) {
        try {
            boolean isDeleted = registerFarmerService.deleteFarmerByMobileNumber(farmerMobNo);
            if (isDeleted) {
                return new ResponseEntity<>("Farmer deleted successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Farmer not found", HttpStatus.NOT_FOUND);
            }
        }
        catch (Exception e) {
            return new ResponseEntity<> ("More than one farmer exists with the same mobile number", HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<RegisterFarmer>> searchFarmersByName(@RequestParam String farmerName) {
        List<RegisterFarmer> farmers = registerFarmerService.getFarmersByName(farmerName);
        if (farmers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(farmers);
    }
}