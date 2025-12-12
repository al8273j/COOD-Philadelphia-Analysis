package project.common;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class parseParkingViolationCSV implements parkingViolationStrategy {

    @Override
    public List<parkingViolationDataEntry> parseParkingViolationFile(String parkingViolationsFile) throws IOException, ParseException {
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


}
