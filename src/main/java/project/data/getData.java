package project.data;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class getData {

    String parkingViolationsFormat;
    String parkingViolationsFile;
    String propertyValuesFile;
    String populationFile;


    public getData(String parkingViolationsFormat, String parkingViolationsFile, String propertyValuesFile, String populationFile) {
        this.parkingViolationsFormat = parkingViolationsFormat;
        this.parkingViolationsFile = parkingViolationsFile;
        this.propertyValuesFile = propertyValuesFile;
        this.populationFile = populationFile;
    }

    public String getParkingViolationsFormat() {
        return parkingViolationsFormat;
    }

    public JSONArray getParkingViolationsJSON() throws IOException, ParseException {
        JSONArray parkingViolationsJSON = (JSONArray) new JSONParser().parse(new FileReader(parkingViolationsFile));
        return parkingViolationsJSON;
    }

    public ArrayList<String[]> getPopulationArray() throws IOException {
        ArrayList<String[]> populationArray = new ArrayList<>();
        FileReader fileReader = new FileReader(populationFile);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;
        while((line = bufferedReader.readLine())!= null){
            populationArray.add(line.split(" "));
        }
        return populationArray;
    }













}
