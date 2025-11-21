package project.common;

public class ParkingCSVEntry {
    public String timestamp;
    public double fine;
    public String description;
    public String anonymousID;
    public String licensePlateState;
    public String uniqueID;
    public String zipCode;

    public ParkingCSVEntry(String timestamp, double fine, String description,
                           String anonymousID, String licensePlateState,
                           String uniqueID, String zipCode){
        this.timestamp=timestamp;
        this.fine=fine;
        this.description=description;
        this.anonymousID=anonymousID;
        this.licensePlateState=licensePlateState;
        this.uniqueID=uniqueID;
        this.zipCode=zipCode;
    }

    public double getFine() {
        return fine;
    }

    public String getAnonymousID() {
        return anonymousID;
    }

    public String getDescription() {
        return description;
    }

    public String getLicensePlateState() {
        return licensePlateState;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getUniqueID() {
        return uniqueID;
    }

    public String getZipCode() {
        return zipCode;
    }
}
