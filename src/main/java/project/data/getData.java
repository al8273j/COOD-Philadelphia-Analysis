package project.data;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import project.common.*;


import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

        parseParkingViolationJSON parser = new parseParkingViolationJSON();
        return parser.parseParkingViolationFile(parkingViolationsFile);
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

    public List<parkingViolationDataEntry> getParkingViolationsCSVData() throws IOException, ParseException {

        parseParkingViolationCSV parser = new parseParkingViolationCSV();
        return parser.parseParkingViolationFile(parkingViolationsFile);


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
