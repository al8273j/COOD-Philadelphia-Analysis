import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;
import project.common.propertiesDataEntry;
import project.data.getData;
import project.processor.processData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static junit.framework.Assert.assertEquals;

public class menuFourTest {

    @Test
    public void testMenuFour19118() throws IOException, ParseException, ExecutionException, InterruptedException {
        getData d =  new getData("csv", "PhillyData-files/parking.csv", "PhillyData-files/properties.csv", "PhillyData-files/population.txt") {
            @Override
            public List<propertiesDataEntry> getPropertiesArray() throws IOException, ParseException {
                List<propertiesDataEntry> temp = new ArrayList<>();
                String[] arr1 = new String[73];
                Arrays.fill(arr1, "");
                arr1[64] = "1000";
                arr1[72] = "19118";
                temp.add(new propertiesDataEntry(arr1));

                String[] arr2 = new String[73];
                Arrays.fill(arr2, "");
                arr2[64] = "2000";
                arr2[72] = "19118";
                temp.add(new propertiesDataEntry(arr2));

                String[] arr3 = new String[73];
                Arrays.fill(arr3, "");
                arr3[64] = "5000";
                arr3[72] = "19111";
                temp.add(new propertiesDataEntry(arr3));
                return temp;
            }
        };
        processData p = new processData(d);
        int result = p.menuFour("19118");
        assertEquals(1500, result);
    }

    @Test
    public void testMenuFour19118JSON() throws IOException, ParseException, ExecutionException, InterruptedException {
        getData dj = new getData("json", "PhillyData-files/parking.json", "PhillyData-files/properties.csv", "PhillyData-files/population.txt") {
            @Override
            public List<propertiesDataEntry> getPropertiesArray() throws IOException, ParseException {
                List<propertiesDataEntry> temp = new ArrayList<>();
                String[] arr1 = new String[73];
                Arrays.fill(arr1, "");
                arr1[64] = "1000";
                arr1[72] = "19118";
                temp.add(new propertiesDataEntry(arr1));

                String[] arr2 = new String[73];
                Arrays.fill(arr2, "");
                arr2[64] = "2000";
                arr2[72] = "19118";
                temp.add(new propertiesDataEntry(arr2));

                String[] arr3 = new String[73];
                Arrays.fill(arr3, "");
                arr3[64] = "5000";
                arr3[72] = "19111";
                temp.add(new propertiesDataEntry(arr3));
                return temp;
            }
        };
        processData p = new processData(dj);
        int result = p.menuFour("19118");
        assertEquals(1500, result);
    }

    @Test
    public void testMenuFour19111() throws IOException, ParseException, ExecutionException, InterruptedException {
        getData d =  new getData("csv", "PhillyData-files/parking.csv", "PhillyData-files/properties.csv", "PhillyData-files/population.txt") {
            @Override
            public List<propertiesDataEntry> getPropertiesArray() throws IOException, ParseException {
                List<propertiesDataEntry> temp = new ArrayList<>();
                String[] arr1 = new String[73];
                Arrays.fill(arr1, "");
                arr1[64] = "2000";
                arr1[72] = "19111";
                temp.add(new propertiesDataEntry(arr1));

                String[] arr2 = new String[73];
                Arrays.fill(arr2, "");
                arr2[64] = "2000";
                arr2[72] = "19111";
                temp.add(new propertiesDataEntry(arr2));

                String[] arr3 = new String[73];
                Arrays.fill(arr3, "");
                arr3[64] = "5000";
                arr3[72] = "19118";
                temp.add(new propertiesDataEntry(arr3));
                return temp;
            }
        };
        processData p = new processData(d);
        int result = p.menuFour("19111");
        assertEquals(2000, result);
    }

    @Test
    public void testMenuFour00000() throws IOException, ParseException, ExecutionException, InterruptedException {
        getData d =  new getData("csv", "PhillyData-files/parking.csv", "PhillyData-files/properties.csv", "PhillyData-files/population.txt") {
            @Override
            public List<propertiesDataEntry> getPropertiesArray() throws IOException, ParseException {
                List<propertiesDataEntry> temp = new ArrayList<>();
                String[] arr1 = new String[73];
                Arrays.fill(arr1, "");
                arr1[64] = "1000";
                arr1[72] = "19118";
                temp.add(new propertiesDataEntry(arr1));

                String[] arr2 = new String[73];
                Arrays.fill(arr2, "");
                arr2[64] = "2000";
                arr2[72] = "19118";
                temp.add(new propertiesDataEntry(arr2));

                String[] arr3 = new String[73];
                Arrays.fill(arr3, "");
                arr3[64] = "5000";
                arr3[72] = "19111";
                temp.add(new propertiesDataEntry(arr3));
                return temp;
            }
        };
        processData p = new processData(d);
        int result = p.menuFour("00000");
        assertEquals(0, result);
    }

    @Test
    public void testMenuFourAreaIsNull() throws IOException, ParseException, ExecutionException, InterruptedException {
        getData dj = new getData("json", "PhillyData-files/parking.json", "PhillyData-files/properties.csv", "PhillyData-files/population.txt") {
            @Override
            public List<propertiesDataEntry> getPropertiesArray() throws IOException, ParseException {
                List<propertiesDataEntry> temp = new ArrayList<>();
                String[] arr1 = new String[73];
                Arrays.fill(arr1, "");
                arr1[64] = null;
                arr1[72] = "19111";
                temp.add(new propertiesDataEntry(arr1));

                String[] arr2 = new String[73];
                Arrays.fill(arr2, "");
                arr2[64] = null;
                arr2[72] = "19111";
                temp.add(new propertiesDataEntry(arr2));

                String[] arr3 = new String[73];
                Arrays.fill(arr3, "");
                arr3[64] = null;
                arr3[72] = "19118";
                temp.add(new propertiesDataEntry(arr3));
                return temp;
            }
        };
        processData p = new processData(dj);
        int result = p.menuFour("19111");
        assertEquals(0, result);
    }
    @Test
    public void testMenuFourAreaIsZero() throws IOException, ParseException, ExecutionException, InterruptedException {
        getData dj = new getData("json", "PhillyData-files/parking.json", "PhillyData-files/properties.csv", "PhillyData-files/population.txt") {
            @Override
            public List<propertiesDataEntry> getPropertiesArray() throws IOException, ParseException {
                List<propertiesDataEntry> temp = new ArrayList<>();
                String[] arr1 = new String[73];
                Arrays.fill(arr1, "");
                arr1[64] = "0";
                arr1[72] = "19111";
                temp.add(new propertiesDataEntry(arr1));

                String[] arr2 = new String[73];
                Arrays.fill(arr2, "");
                arr2[64] = "0";
                arr2[72] = "19111";
                temp.add(new propertiesDataEntry(arr2));

                String[] arr3 = new String[73];
                Arrays.fill(arr3, "");
                arr3[64] = "0";
                arr3[72] = "19118";
                temp.add(new propertiesDataEntry(arr3));
                return temp;
            }
        };
        processData p = new processData(dj);
        int result = p.menuFour("19111");
        assertEquals(0, result);
    }
}