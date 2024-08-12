package com.mandal.drone_app.drone_app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mandal.drone_app.drone_app.model.SignUp;


@Repository
public interface SignupRepo extends JpaRepository<SignUp, Long> {
    Optional<SignUp> findByEmail(String email);
    Optional<SignUp> findByPilotCode(String pilotCode);
    Optional<SignUp> findByMobileNumberAndPilotCode(String mobileNumber, String pilotCode);
}
