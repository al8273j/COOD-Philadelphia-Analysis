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
import java.util.List;
import java.util.TreeMap;
import java.util.concurrent.ExecutionException;

import static junit.framework.Assert.assertEquals;


public class menuOneTest {


    //menu one test with custom population data
    @Test
    public void menuOneTestWithCSV() throws IOException, ParseException, ExecutionException, InterruptedException {

        getData d =  new getData("csv", "PhillyData-files/parking.csv", "PhillyData-files/properties.csv", "PhillyData-files/population.txt") {
            @Override
            public List<populationDataEntry> getPopulationArray() throws IOException {
                List<populationDataEntry> temp = new ArrayList<>();
                temp.add(new populationDataEntry(new String[]{"11121", "10"}));
                temp.add(new populationDataEntry(new String[]{"11122", "200"}));
                temp.add(new populationDataEntry(new String[]{"11123", "100"}));
                temp.add(new populationDataEntry(new String[]{"11124", "40"}));
                temp.add(new populationDataEntry(new String[]{"11125", "50"}));
                return temp;

            }
        };
        processData p = new processData(d);


        assertEquals(400, p.menuOne());
    }

    @Test
    public void menuOneTestWithJSON() throws IOException, ParseException, ExecutionException, InterruptedException {

        getData d =  new getData("json", "PhillyData-files/parking.json", "PhillyData-files/properties.csv", "PhillyData-files/population.txt") {
            @Override
            public List<populationDataEntry> getPopulationArray() throws IOException {
                List<populationDataEntry> temp = new ArrayList<>();
                temp.add(new populationDataEntry(new String[]{"11121", "10"}));
                temp.add(new populationDataEntry(new String[]{"11122", "200"}));
                temp.add(new populationDataEntry(new String[]{"11123", "100"}));
                return temp;

            }
        };

        processData p = new processData(d);




        assertEquals(310, p.menuOne());
    }
}
