package project.common;

import java.util.List;

public class propertiesDataEntry {

    String[] properties;
    public propertiesDataEntry(String[] properties){
        this.properties = properties;
    }

    public Double getMarketValue() {
        try{
            return Double.parseDouble(properties[34]);

        } catch(Exception e){
            return -1.0;
        }
    }

    public Double getTotalLivableArea() {
        try {
            return Double.parseDouble(properties[64]);
        }
        catch(Exception e){
            return null;
        }
    }

    public String getZipCode() {
        return properties[72];
    }

}
