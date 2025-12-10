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


public class menuOneTest {

    getData dataCSV = new getData("csv", "PhillyData-files/parking.csv", "PhillyData-files/properties.csv", "PhillyData-files/population.txt");
    getData dataJSON = new getData("json", "PhillyData-files/parking.json", "PhillyData-files/properties.csv", "PhillyData-files/population.txt");

    //menu one test with custom population data
    @Test
    public void menuOneTestWithCSV() throws IOException, ParseException, ExecutionException, InterruptedException {
        processData p = new processData(dataCSV);
        p.population = new ArrayList<>();
        p.population.add(new populationDataEntry(new String[]{"11121", "10"}));
        p.population.add(new populationDataEntry(new String[]{"11122", "200"}));
        p.population.add(new populationDataEntry(new String[]{"11123", "100"}));
        p.population.add(new populationDataEntry(new String[]{"11124", "40"}));
        p.population.add(new populationDataEntry(new String[]{"11125", "50"}));

        assertEquals(400, p.menuOne());
    }

    @Test
    public void menuOneTestWithJSON() throws IOException, ParseException, ExecutionException, InterruptedException {
        processData p = new processData(dataJSON);
        p.population = new ArrayList<>();
        p.population.add(new populationDataEntry(new String[]{"11121", "10"}));
        p.population.add(new populationDataEntry(new String[]{"11122", "200"}));
        p.population.add(new populationDataEntry(new String[]{"11123", "100"}));


        assertEquals(310, p.menuOne());
    }
}