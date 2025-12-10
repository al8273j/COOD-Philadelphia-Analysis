import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;
import project.common.parkingViolationFromCSV;
import project.common.parkingViolationFromJSON;
import project.common.populationDataEntry;
import project.common.propertiesDataEntry;
import project.data.getData;
import project.processor.processData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.concurrent.ExecutionException;

import static junit.framework.Assert.assertEquals;


public class menuTwoTest {

    getData dataCSV = new getData("csv", "PhillyData-files/parking.csv", "PhillyData-files/properties.csv", "PhillyData-files/population.txt");
    getData dataJSON = new getData("json", "PhillyData-files/parking.json", "PhillyData-files/properties.csv", "PhillyData-files/population.txt");


    @Test
    public void menuTwoTest() throws IOException, ParseException, ExecutionException, InterruptedException {
        processData p = new processData(dataCSV);
        p.parkingViolations = new ArrayList<>();

        p.parkingViolations = new ArrayList<>();

        p.parkingViolations.add(new parkingViolationFromCSV(new String[]{
                "2024-09-10", "50", "Expired Meter", "A001", "PA", "U1001", "11121"
        }));

        p.parkingViolations.add(new parkingViolationFromCSV(new String[]{
                null, "80", "No Parking", "A002", "NY", "U1002", "11121"
        }));

        p.parkingViolations.add(new parkingViolationFromCSV(new String[]{
                "2024-09-15", "25", "Street Cleaning", "A003", "PA", "U1003", "11124"
        }));

        p.parkingViolations.add(new parkingViolationFromCSV(new String[]{
                "2024-09-18", "60", "Double Parking", "A004", "PA", "U1004", "11127"
        }));

        p.parkingViolations.add(new parkingViolationFromCSV(new String[]{
                "2024-09-20", "0", "Warning", "A005", "NY", "U1005", "11128"
        }));
        p.population = new ArrayList<>();
        p.population.add(new populationDataEntry(new String[]{"11121", "100"}));
        p.population.add(new populationDataEntry(new String[]{"11122", "2030"}));
        p.population.add(new populationDataEntry(new String[]{"11123", "10110"}));
        p.population.add(new populationDataEntry(new String[]{"11124", "4220"}));
        p.population.add(new populationDataEntry(new String[]{"11125", "5330"}));
        p.population.add(new populationDataEntry(new String[]{"11128", "0"}));


        TreeMap<String, String> expected = new TreeMap<>();
        expected.put("11121", "2.0000");
        expected.put("11124", "168.8000");
        assertEquals(expected, p.menuTwo());
    }
}