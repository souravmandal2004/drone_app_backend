package com.mandal.drone_app.drone_app.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mandal.drone_app.drone_app.model.SignUp;
import com.mandal.drone_app.drone_app.service.SignupService;

@RestController
@RequestMapping ("api/v1/signup")
@CrossOrigin
public class SignupController {

    @Autowired
    private SignupService signupService;
    

    @PostMapping
    public ResponseEntity<?> signUp(@RequestBody SignUp signUp) {
        return signupService.registerPilot(signUp);
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginPilot(@RequestBody SignUp pilot) {
        Optional<SignUp> existingPilot = signupService.loginPilot(pilot.getMobileNumber(), pilot.getPilotCode());
        if (existingPilot.isPresent()) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(401).body("Invalid mobile number or pilot code");
        }
    }
}
