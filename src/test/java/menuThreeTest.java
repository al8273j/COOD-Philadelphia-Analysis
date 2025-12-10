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


public class menuThreeTest {

    getData dataCSV = new getData("csv", "PhillyData-files/parking.csv", "PhillyData-files/properties.csv", "PhillyData-files/population.txt");
    getData dataJSON = new getData("json", "PhillyData-files/parking.json", "PhillyData-files/properties.csv", "PhillyData-files/population.txt");




    //case where zipcode is in hashMap already
    @Test
    public void menuThreeTestMemo() throws IOException, ParseException, ExecutionException, InterruptedException {
        processData p = new processData(dataCSV);
        p.menuThreeMemo = new HashMap<>();
        p.menuThreeMemo.put("11216", 500000);


        assertEquals(500000,p.menuThree("11216"));
    }




}
