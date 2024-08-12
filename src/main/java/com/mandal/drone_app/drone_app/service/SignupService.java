package com.mandal.drone_app.drone_app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mandal.drone_app.drone_app.model.SignUp;
import com.mandal.drone_app.drone_app.repository.SignupRepo;

import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class SignupService {
    
    @Autowired
    private SignupRepo signupRepo;

    private static final Pattern EMAIL_PATTERN = Pattern.compile(
        "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");

    private static final Pattern MOBILE_NUMBER_PATTERN = Pattern.compile(
        "^\\d{10}$"); // Assuming a 10-digit mobile number

    public ResponseEntity<?> registerPilot(SignUp signUp) {
        // Validate email format
        if (!EMAIL_PATTERN.matcher(signUp.getEmail()).matches()) {
            return new ResponseEntity<>("Invalid email format", HttpStatus.BAD_REQUEST);
        }

        // Validate mobile number format
        if (!MOBILE_NUMBER_PATTERN.matcher(signUp.getMobileNumber()).matches()) {
            return new ResponseEntity<>("Invalid mobile number format", HttpStatus.BAD_REQUEST);
        }

        // Check if email or pilot code already exists
        if (signupRepo.findByEmail(signUp.getEmail()).isPresent()) {
            return new ResponseEntity<>("Email already exists", HttpStatus.NOT_ACCEPTABLE);
        }

        if (signupRepo.findByPilotCode(signUp.getPilotCode()).isPresent()) {
            return new ResponseEntity<>("Pilot code already exists", HttpStatus.NOT_ACCEPTABLE);
        }
        
        // Save the new pilot's details
        signupRepo.save(signUp);
        return new ResponseEntity<>("Sign up successful", HttpStatus.OK);
    }

    public Optional<SignUp> loginPilot(String mobileNumber, String pilotCode) {
        return signupRepo.findByMobileNumberAndPilotCode(mobileNumber, pilotCode);
    }
}
