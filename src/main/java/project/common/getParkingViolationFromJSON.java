package project.common;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class getParkingViolationFromJSON {


    private JSONObject parkingViolation;

    public getParkingViolationFromJSON(JSONObject parkingViolation) {
        this.parkingViolation = parkingViolation;
    }

    public int getTicketNumber(){
        return (int)parkingViolation.get("ticket_number");
    }

    public int getPlateId(){
        return (int)parkingViolation.get("plate_id");
    }
    public String getDate(){
        return (String)parkingViolation.get("date");
    }

    public int getZipCode(){
        return (int)parkingViolation.get("zip_code");
    }

    public String getViolation(){
        return (String)parkingViolation.get("violation");
    }

    public double getFine(){
        return (double)parkingViolation.get("fine");
    }

    public String getState(){
        return (String)parkingViolation.get("state");
    }
}
