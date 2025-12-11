import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;
import project.common.populationDataEntry;
import project.common.propertiesDataEntry;
import project.data.getData;
import project.processor.processData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static junit.framework.Assert.assertEquals;

public class menuSixTest {

    @Test
    public void testMenuSixCalculation() throws IOException, ParseException, ExecutionException, InterruptedException {
        getData d = new getData("csv", "PhillyData-files/parking.csv", "PhillyData-files/properties.csv", "PhillyData-files/population.txt") {
            @Override
            public List<propertiesDataEntry> getPropertiesArray() throws IOException, ParseException {
                List<propertiesDataEntry> temp = new ArrayList<>();

                String[] arr1 = new String[73];
                Arrays.fill(arr1, "");
                arr1[34] = "100000";
                arr1[72] = "19118";
                temp.add(new propertiesDataEntry(arr1));

                String[] arr2 = new String[73];
                Arrays.fill(arr2, "");
                arr2[34] = "200000";
                arr2[72] = "19118";
                temp.add(new propertiesDataEntry(arr2));

                String[] arr3 = new String[73];
                Arrays.fill(arr3, "");
                arr3[34] = "300000";
                arr3[72] = "19118";
                temp.add(new propertiesDataEntry(arr3));

                String[] arr4 = new String[73];
                Arrays.fill(arr4, "");
                arr4[34] = "150000";
                arr4[72] = "19111";
                temp.add(new propertiesDataEntry(arr4));

                return temp;
            }

            @Override
            public List<populationDataEntry> getPopulationArray() throws IOException {
                return new ArrayList<>();
            }
        };

        processData p = new processData(d);
        int result = p.menuSix("19118", 100000, 200000);
        assertEquals(2, result);
    }

    @Test
    public void testMenuSixStreamCoverage() throws IOException, ParseException, ExecutionException, InterruptedException {
        getData d = new getData("csv", "PhillyData-files/parking.csv", "PhillyData-files/properties.csv", "PhillyData-files/population.txt") {
            @Override
            public List<propertiesDataEntry> getPropertiesArray() throws IOException, ParseException {
                List<propertiesDataEntry> temp = new ArrayList<>();

                String[] arr1 = new String[73];
                Arrays.fill(arr1, "");
                arr1[34] = "150000";
                arr1[72] = "19118-1234";
                temp.add(new propertiesDataEntry(arr1));

                String[] arr2 = new String[73];
                Arrays.fill(arr2, "");
                arr2[34] = "50000";
                arr2[72] = "19118";
                temp.add(new propertiesDataEntry(arr2));

                String[] arr3 = new String[73];
                Arrays.fill(arr3, "");
                arr3[34] = "-500";
                arr3[72] = "19118";
                temp.add(new propertiesDataEntry(arr3));

                return temp;
            }

            @Override
            public List<populationDataEntry> getPopulationArray() throws IOException {
                return new ArrayList<>();
            }
        };

        processData p = new processData(d);

        int result = p.menuSix("19118", 100000, 200000);
        assertEquals(1, result);
    }

    @Test
    public void testMenuSixMinGreaterThanMax() throws IOException, ParseException, ExecutionException, InterruptedException {
        getData d = new getData("csv", "PhillyData-files/parking.csv", "PhillyData-files/properties.csv", "PhillyData-files/population.txt") {
            @Override
            public List<propertiesDataEntry> getPropertiesArray() throws IOException, ParseException {
                return new ArrayList<>();
            }
            @Override
            public List<populationDataEntry> getPopulationArray() throws IOException {
                return new ArrayList<>();
            }
        };

        processData p = new processData(d);
        int result = p.menuSix("19118", 200000, 100000);
        assertEquals(0, result);
    }

    @Test
    public void testMenuSixInvalidInputs() throws IOException, ParseException, ExecutionException, InterruptedException {
        getData d = new getData("csv", "PhillyData-files/parking.csv", "PhillyData-files/properties.csv", "PhillyData-files/population.txt") {
            @Override
            public List<propertiesDataEntry> getPropertiesArray() throws IOException, ParseException {
                return new ArrayList<>();
            }
            @Override
            public List<populationDataEntry> getPopulationArray() throws IOException {
                return new ArrayList<>();
            }
        };

        processData p = new processData(d);

        assertEquals(0, p.menuSix(null, 0, 100));
        assertEquals(0, p.menuSix("", 0, 100));
        assertEquals(0, p.menuSix("19118", -10, 100));
        assertEquals(0, p.menuSix("19118", 0, -100));
    }

    @Test
    public void testMenuSixNullAndZeroValues() throws IOException, ParseException, ExecutionException, InterruptedException {
        getData d = new getData("csv", "PhillyData-files/parking.csv", "PhillyData-files/properties.csv", "PhillyData-files/population.txt") {
            @Override
            public List<propertiesDataEntry> getPropertiesArray() throws IOException, ParseException {
                List<propertiesDataEntry> temp = new ArrayList<>();

                String[] arr1 = new String[73];
                Arrays.fill(arr1, "");
                arr1[34] = null;
                arr1[72] = "19118";
                temp.add(new propertiesDataEntry(arr1));

                String[] arr2 = new String[73];
                Arrays.fill(arr2, "");
                arr2[34] = "0";
                arr2[72] = "19118";
                temp.add(new propertiesDataEntry(arr2));

                return temp;
            }

            @Override
            public List<populationDataEntry> getPopulationArray() throws IOException {
                return new ArrayList<>();
            }
        };

        processData p = new processData(d);

        int result = p.menuSix("19118", 0, 100000);
        assertEquals(0, result);
    }
    @Test
    public void testMenuSixMarketValuesNullAndNegative() throws IOException, ParseException, ExecutionException, InterruptedException {
        getData d = new getData("csv", "PhillyData-files/parking.csv", "PhillyData-files/properties.csv", "PhillyData-files/population.txt") {
            @Override
            public List<propertiesDataEntry> getPropertiesArray() throws IOException, ParseException {
                List<propertiesDataEntry> temp = new ArrayList<>();

                String[] arr = new String[73];
                Arrays.fill(arr, "");
                arr[72] = "19118";

                propertiesDataEntry nullEntry = new propertiesDataEntry(arr) {
                    @Override
                    public Double getMarketValue() {
                        return null;
                    }
                };
                temp.add(nullEntry);

                propertiesDataEntry negativeEntry = new propertiesDataEntry(arr) {
                    @Override
                    public Double getMarketValue() {
                        return -100.0;
                    }
                };
                temp.add(negativeEntry);

                propertiesDataEntry validEntry = new propertiesDataEntry(arr) {
                    @Override
                    public Double getMarketValue() {
                        return 150000.0;
                    }
                };
                temp.add(validEntry);

                return temp;
            }

            @Override
            public List<populationDataEntry> getPopulationArray() throws IOException {
                return new ArrayList<>();
            }
        };

        processData p = new processData(d);
        int result = p.menuSix("19118", 100000, 200000);
        assertEquals(1, result);
    }
}
