import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;
import project.common.*;
import project.data.getData;
import project.processor.processData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;
import java.util.concurrent.ExecutionException;

import static junit.framework.Assert.assertEquals;


public class menuTwoTest {


    @Test
    public void menuTwoTest() throws IOException, ParseException, ExecutionException, InterruptedException {
        getData d =  new getData("csv", "PhillyData-files/parking.csv", "PhillyData-files/properties.csv", "PhillyData-files/population.txt"){
            @Override
            public List<parkingViolationDataEntry> getParkingViolationsCSVData(){
                List<parkingViolationDataEntry> temp =  new ArrayList<>();
                temp.add(new parkingViolationFromCSV(new String[]{
                        "2024-09-10", "50", "Expired Meter", "A001", "PA", "U1001", "11121"
                }));

                temp.add(new parkingViolationFromCSV(new String[]{
                        null, "80", "No Parking", "A002", "NY", "U1002", "11121"
                }));

                temp.add(new parkingViolationFromCSV(new String[]{
                        "2024-09-15", "25", "Street Cleaning", "A003", "PA", "U1003", "11124"
                }));

                temp.add(new parkingViolationFromCSV(new String[]{
                        "2024-09-18", "60", "Double Parking", "A004", "PA", "U1004", "11127"
                }));

                temp.add(new parkingViolationFromCSV(new String[]{
                        "2024-09-20", "0", "Warning", "A005", "NY", "U1005", "11128"
                }));
                return temp;
            }

            @Override
            public List<populationDataEntry> getPopulationArray() throws IOException {
                List<populationDataEntry> temp =  new ArrayList<>();
                temp.add(new populationDataEntry(new String[]{"11121", "100"}));
                temp.add(new populationDataEntry(new String[]{"11122", "2030"}));
                temp.add(new populationDataEntry(new String[]{"11123", "10110"}));
                temp.add(new populationDataEntry(new String[]{"11124", "4220"}));
                temp.add(new populationDataEntry(new String[]{"11125", "5330"}));
                temp.add(new populationDataEntry(new String[]{"11128", "0"}));
                return temp;
            }

        };
        processData p = new processData(d);




        TreeMap<String, String> expected = new TreeMap<>();
        expected.put("11121", "2.0000");
        expected.put("11124", "168.8000");
        assertEquals(expected, p.menuTwo());
    }
}
