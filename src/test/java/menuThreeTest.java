import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;
import project.common.parkingViolationFromCSV;
import project.common.parkingViolationFromJSON;
import project.common.populationDataEntry;
import project.common.propertiesDataEntry;
import project.data.getData;
import project.processor.processData;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ExecutionException;

import static junit.framework.Assert.assertEquals;


public class menuThreeTest {






    //case where zipcode is in hashMap already
    @Test
    public void menuThreeTestMemo() throws IOException, ParseException, ExecutionException, InterruptedException {
        getData dataCSV = new getData("csv", "PhillyData-files/parking.csv", "PhillyData-files/properties.csv", "PhillyData-files/population.txt");
        processData p = new processData(dataCSV);
        p.menuThreeMemo = new HashMap<>();
        p.menuThreeMemo.put("11216", 500000);


        assertEquals(500000,p.menuThree("11216"));
    }


    @Test
    public void menuThreeTestNoMemo() throws IOException, ParseException, ExecutionException, InterruptedException {

        getData d =  new getData("csv", "PhillyData-files/parking.csv", "PhillyData-files/properties.csv", "PhillyData-files/population.txt") {
            @Override
            public List<propertiesDataEntry> getPropertiesArray() throws IOException, ParseException {
                List<propertiesDataEntry> temp = new ArrayList<>();
                String[] arr1 = new String[73];
                Arrays.fill(arr1, "");
                arr1[34] = "2000";
                arr1[72] = "11216";
                temp.add(new propertiesDataEntry(arr1));

                String[] arr2 = new String[73];
                Arrays.fill(arr2, "");
                arr2[34] = "1000";
                arr2[72] = "11216";
                temp.add(new propertiesDataEntry(arr2));
                return temp;
            }
        };
        processData p = new processData(d);






        assertEquals(1500,p.menuThree("11216"));
    }


    @Test
    public void menuThreeTestNoMatchingZip() throws IOException, ParseException, ExecutionException, InterruptedException {
        getData d =  new getData("csv", "PhillyData-files/parking.csv", "PhillyData-files/properties.csv", "PhillyData-files/population.txt") {
            @Override
            public List<propertiesDataEntry> getPropertiesArray() throws IOException, ParseException {
                List<propertiesDataEntry> temp = new ArrayList<>();
                String[] arr1 = new String[73];
                Arrays.fill(arr1, "");
                arr1[34] = "2000";
                arr1[72] = "11216";
                temp.add(new propertiesDataEntry(arr1));

                String[] arr2 = new String[73];
                Arrays.fill(arr2, "");
                arr2[34] = "1000";
                arr2[72] = "11216";
                temp.add(new propertiesDataEntry(arr2));
                return temp;
            }
        };
        processData p = new processData(d);







        assertEquals(0,p.menuThree("11217"));
    }




}
