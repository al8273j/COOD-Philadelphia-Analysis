package project.common;

import project.processor.processData;

import java.util.List;

public class parkingViolationFromCSV extends parkingViolationDataEntry {
    private String[] parkingViolation;

    public parkingViolationFromCSV(String[] parkingViolation) {
        this.parkingViolation = parkingViolation;
    }


    public Double getFine() {

        return Double.parseDouble(parkingViolation[1]);
    }

    public String getAnonymousID() {
        return parkingViolation[3];
    }

    public String getViolation() {
        return parkingViolation[2];
    }

    public String getState() {
        return parkingViolation[4];
    }

    public String getDate() {
        return parkingViolation[0];
    }

    public String getUniqueID() {
        return parkingViolation[5];
    }

    public String getZipCode() {
        try{
        return parkingViolation[6];}
        catch(Exception e){
            return "";
        }
    }
}
