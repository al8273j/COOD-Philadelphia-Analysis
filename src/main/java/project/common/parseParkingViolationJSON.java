package project.common;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class parseParkingViolationJSON implements parkingViolationStrategy {

    @Override
    public List<parkingViolationDataEntry> parseParkingViolationFile(String parkingViolationsFile) throws IOException, ParseException {
        List<parkingViolationDataEntry> parkingViolationEntries = new ArrayList<>();

        JSONArray parkingViolationsJSON = (JSONArray) new JSONParser().parse(new FileReader(parkingViolationsFile));

        parkingViolationsJSON.forEach((o) -> parkingViolationEntries.add(new parkingViolationFromJSON((JSONObject) o)));

        return parkingViolationEntries;
    }


}
