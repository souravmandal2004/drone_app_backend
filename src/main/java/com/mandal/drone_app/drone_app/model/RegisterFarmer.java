package com.mandal.drone_app.drone_app.model;

import java.time.LocalDateTime;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "farmer_details")
@Data
public class RegisterFarmer {

    @EmbeddedId
    private FarmerId farmerId;

    private String droneCode;
    private LocalDateTime date;
    private String pilotCode;
    private String coPilotName;
    private String coPilotCode;
    private String state; // Odisha by default
    private String district;
    private String block;
    private String village;
    private String sessionNumber;
    private String flightNumber;
    private String crop;
    private String medicineName;
    private String safetyCheck;
    private String acre;
}
