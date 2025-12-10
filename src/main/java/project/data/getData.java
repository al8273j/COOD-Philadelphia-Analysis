package project.data;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import project.common.*;

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

    public List<parkingViolationDataEntry> getParkingViolationsJSONData() throws IOException, ParseException {

        List<parkingViolationDataEntry> parkingViolationEntries = new ArrayList<>();
        JSONArray parkingViolationsJSON = (JSONArray) new JSONParser().parse(new FileReader(parkingViolationsFile));

        parkingViolationsJSON.forEach((o) -> parkingViolationEntries.add(new parkingViolationFromJSON((JSONObject) o)));

        return parkingViolationEntries;
    }

    public List<populationDataEntry> getPopulationArray() throws IOException {
        List<populationDataEntry> populationEntries = new ArrayList<>();

        FileReader fileReader = new FileReader(populationFile);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;
        while((line = bufferedReader.readLine())!= null){
            populationEntries.add(new populationDataEntry(line.split(" ")));
        }
        return populationEntries;
    }

    public List<parkingViolationDataEntry> getParkingViolationsCSVData(){
        List<parkingViolationDataEntry> parkingViolationEntries = new ArrayList<>();
        try(BufferedReader r = new BufferedReader(new FileReader(parkingViolationsFile))) {
            String line;
            while ((line = r.readLine()) != null) {
                String[] arr = line.split(",");

                parkingViolationEntries.add(new parkingViolationFromCSV(arr));
            }


        } catch(IOException e){
            System.out.println("IOException");
        }
        return parkingViolationEntries;


    }

    public List<propertiesDataEntry> getPropertiesArray() throws IOException, ParseException {
        List<propertiesDataEntry> propertiesEntries = new ArrayList<>();
        try (BufferedReader r = new BufferedReader(new FileReader(propertyValuesFile))) {
            String line;
            int lineNumber = 0;
            while ((line = r.readLine()) != null) {
                ++lineNumber;
                if(lineNumber == 1) {
                    continue;
                }
                String[] arr = line.split(",");
                propertiesEntries.add(new propertiesDataEntry(arr));

            }


        } catch (IOException e) {
            System.out.println("IOException");
        }
        return propertiesEntries;
    }


}