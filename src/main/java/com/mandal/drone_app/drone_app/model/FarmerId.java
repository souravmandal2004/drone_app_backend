package com.mandal.drone_app.drone_app.model;

import java.io.Serializable;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class FarmerId implements Serializable {

    private String farmerName;
    private String farmerMobNo;

    // Default constructor
    public FarmerId() {}

    // Parameterized constructor
    public FarmerId(String farmerName, String farmerMobNo) {
        this.farmerName = farmerName;
        this.farmerMobNo = farmerMobNo;
    }
}
