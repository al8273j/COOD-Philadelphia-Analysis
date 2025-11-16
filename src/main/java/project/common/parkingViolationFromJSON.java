/*
 * This class gets information from each entry in the parking.JSON file, providing getter methods to
 * retrieve the following information:
 * ticket number
 * plate id
 * date
 * zipcode
 * violation
 * fine
 * state
 * */

package project.common;

import org.json.simple.JSONObject;

public class parkingViolationFromJSON {


    private JSONObject parkingViolation;

    public parkingViolationFromJSON(JSONObject parkingViolation) {
        this.parkingViolation = parkingViolation;
    }

    public long getTicketNumber(){
        return (long)parkingViolation.get("ticket_number");
    }

    public String getPlateId(){
        return (String)parkingViolation.get("plate_id");
    }
    public String getDate(){
        return (String)parkingViolation.get("date");
    }

    public String getZipCode(){
        return (String)parkingViolation.get("zip_code");
    }

    public String getViolation(){
        return (String)parkingViolation.get("violation");
    }

    public long getFine(){
        return (long)parkingViolation.get("fine");
    }

    public String getState(){
        return (String)parkingViolation.get("state");
    }
}
