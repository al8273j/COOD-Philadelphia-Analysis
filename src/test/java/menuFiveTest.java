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

public class menuFiveTest {
    @Test
    public void testMenuFive19118() throws IOException, ParseException, ExecutionException, InterruptedException {
        getData d =  new getData("csv", "PhillyData-files/parking.csv", "PhillyData-files/properties.csv", "PhillyData-files/population.txt") {
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
                arr3[34] = "20000";
                arr3[72] = "19111";
                temp.add(new propertiesDataEntry(arr3));
                return temp;
            }
            @Override
            public List<populationDataEntry> getPopulationArray() throws IOException {
                List<populationDataEntry> temp = new ArrayList<>();
                String[] populationArr = new String[2];
                populationArr[0] = "19118";
                populationArr[1] = "10";

                temp.add(new populationDataEntry(populationArr));
                return temp;
            }
        };

        processData p = new processData(d);
        int result = p.menuFive("19118");
        assertEquals(30000, result);
    }

    @Test
    public void testMenuFive19118JSON() throws IOException, ParseException, ExecutionException, InterruptedException {
        getData dj = new getData("json", "PhillyData-files/parking.json", "PhillyData-files/properties.csv", "PhillyData-files/population.txt") {
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
                arr3[34] = "20000";
                arr3[72] = "19111";
                temp.add(new propertiesDataEntry(arr3));
                return temp;
            }
            @Override
            public List<populationDataEntry> getPopulationArray() throws IOException {
                List<populationDataEntry> temp = new ArrayList<>();
                String[] populationArr = new String[2];
                populationArr[0] = "19118";
                populationArr[1] = "10";

                temp.add(new populationDataEntry(populationArr));
                return temp;
            }
        };

        processData p = new processData(dj);
        int result = p.menuFive("19118");
        assertEquals(30000, result);
    }

    @Test
    public void testMenuFive19111() throws IOException, ParseException, ExecutionException, InterruptedException {
        getData d =  new getData("csv", "PhillyData-files/parking.csv", "PhillyData-files/properties.csv", "PhillyData-files/population.txt") {
            @Override
            public List<propertiesDataEntry> getPropertiesArray() throws IOException, ParseException {
                List<propertiesDataEntry> temp = new ArrayList<>();
                String[] arr1 = new String[73];
                Arrays.fill(arr1, "");
                arr1[34] = "20000";
                arr1[72] = "19111";
                temp.add(new propertiesDataEntry(arr1));

                String[] arr2 = new String[73];
                Arrays.fill(arr2, "");
                arr2[34] = "80000";
                arr2[72] = "19111";
                temp.add(new propertiesDataEntry(arr2));

                String[] arr3 = new String[73];
                Arrays.fill(arr3, "");
                arr3[34] = "200000";
                arr3[72] = "19118";
                temp.add(new propertiesDataEntry(arr3));
                return temp;
            }
            @Override
            public List<populationDataEntry> getPopulationArray() throws IOException {
                List<populationDataEntry> temp = new ArrayList<>();
                String[] populationArr = new String[2];
                populationArr[0] = "19111";
                populationArr[1] = "100";

                temp.add(new populationDataEntry(populationArr));
                return temp;
            }
        };

        processData p = new processData(d);
        int result = p.menuFive("19111");
        assertEquals(1000, result);
    }

    @Test
    public void testMenuFive00000() throws IOException, ParseException, ExecutionException, InterruptedException {
        getData d =  new getData("csv", "PhillyData-files/parking.csv", "PhillyData-files/properties.csv", "PhillyData-files/population.txt") {
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
                arr3[34] = "5000";
                arr3[72] = "19111";
                temp.add(new propertiesDataEntry(arr3));
                return temp;
            }
            @Override
            public List<populationDataEntry> getPopulationArray() throws IOException {
                List<populationDataEntry> temp = new ArrayList<>();
                String[] populationArr = new String[2];
                populationArr[0] = "19118";
                populationArr[1] = "10";

                temp.add(new populationDataEntry(populationArr));
                return temp;
            }
        };
        processData p = new processData(d);
        int result = p.menuFive("00000");
        assertEquals(0, result);
    }

    @Test
    public void testMenuFiveValueIsNull() throws IOException, ParseException, ExecutionException, InterruptedException {
        getData dj = new getData("json", "PhillyData-files/parking.json", "PhillyData-files/properties.csv", "PhillyData-files/population.txt") {
            @Override
            public List<propertiesDataEntry> getPropertiesArray() throws IOException, ParseException {
                List<propertiesDataEntry> temp = new ArrayList<>();
                String[] arr1 = new String[73];
                Arrays.fill(arr1, "");
                arr1[34] = null;
                arr1[72] = "19111";
                temp.add(new propertiesDataEntry(arr1));

                String[] arr2 = new String[73];
                Arrays.fill(arr2, "");
                arr2[34] = null;
                arr2[72] = "19111";
                temp.add(new propertiesDataEntry(arr2));

                String[] arr3 = new String[73];
                Arrays.fill(arr3, "");
                arr3[34] = null;
                arr3[72] = "19118";
                temp.add(new propertiesDataEntry(arr3));
                return temp;
            }
            @Override
            public List<populationDataEntry> getPopulationArray() throws IOException {
                List<populationDataEntry> temp = new ArrayList<>();
                String[] populationArr = new String[2];
                populationArr[0] = "19111";
                populationArr[1] = "10";

                temp.add(new populationDataEntry(populationArr));
                return temp;
            }
        };
        processData p = new processData(dj);
        int result = p.menuFive("19111");
        assertEquals(0, result);
    }

    @Test
    public void testMenuFiveValueIsZero() throws IOException, ParseException, ExecutionException, InterruptedException {
        getData dj = new getData("json", "PhillyData-files/parking.json", "PhillyData-files/properties.csv", "PhillyData-files/population.txt") {
            @Override
            public List<propertiesDataEntry> getPropertiesArray() throws IOException, ParseException {
                List<propertiesDataEntry> temp = new ArrayList<>();
                String[] arr1 = new String[73];
                Arrays.fill(arr1, "");
                arr1[34] = "0";
                arr1[72] = "19111";
                temp.add(new propertiesDataEntry(arr1));

                String[] arr2 = new String[73];
                Arrays.fill(arr2, "");
                arr2[34] = "0";
                arr2[72] = "19111";
                temp.add(new propertiesDataEntry(arr2));

                String[] arr3 = new String[73];
                Arrays.fill(arr3, "");
                arr3[34] = "0";
                arr3[72] = "19118";
                temp.add(new propertiesDataEntry(arr3));
                return temp;
            }
            @Override
            public List<populationDataEntry> getPopulationArray() throws IOException {
                List<populationDataEntry> temp = new ArrayList<>();
                String[] populationArr = new String[2];
                populationArr[0] = "19111";
                populationArr[1] = "10";

                temp.add(new populationDataEntry(populationArr));
                return temp;
            }
        };
        processData p = new processData(dj);
        int result = p.menuFive("19111");
        assertEquals(0, result);
    }

    @Test
    public void testMenuPopulationIsZero() throws IOException, ParseException, ExecutionException, InterruptedException {
        getData dj = new getData("json", "PhillyData-files/parking.json", "PhillyData-files/properties.csv", "PhillyData-files/population.txt") {
            @Override
            public List<propertiesDataEntry> getPropertiesArray() throws IOException, ParseException {
                List<propertiesDataEntry> temp = new ArrayList<>();
                String[] arr1 = new String[73];
                Arrays.fill(arr1, "");
                arr1[34] = "20000";
                arr1[72] = "19111";
                temp.add(new propertiesDataEntry(arr1));

                String[] arr2 = new String[73];
                Arrays.fill(arr2, "");
                arr2[34] = "80000";
                arr2[72] = "19111";
                temp.add(new propertiesDataEntry(arr2));

                String[] arr3 = new String[73];
                Arrays.fill(arr3, "");
                arr3[34] = "0";
                arr3[72] = "19118";
                temp.add(new propertiesDataEntry(arr3));
                return temp;
            }
            @Override
            public List<populationDataEntry> getPopulationArray() throws IOException {
                List<populationDataEntry> temp = new ArrayList<>();
                String[] populationArr = new String[2];
                populationArr[0] = "19111";
                populationArr[1] = "0";

                temp.add(new populationDataEntry(populationArr));
                return temp;
            }
        };
        processData p = new processData(dj);
        int result = p.menuFive("19111");
        assertEquals(0, result);
    }

    @Test
    public void testMenuFiveValueCoverage() throws IOException, ParseException, ExecutionException, InterruptedException {
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

                propertiesDataEntry zeroEntry = new propertiesDataEntry(arr) {
                    @Override
                    public Double getMarketValue() {
                        return 0.0;
                    }
                };
                temp.add(zeroEntry);

                propertiesDataEntry negativeEntry = new propertiesDataEntry(arr) {
                    @Override
                    public Double getMarketValue() {
                        return -5000.0;
                    }
                };
                temp.add(negativeEntry);

                propertiesDataEntry validEntry = new propertiesDataEntry(arr) {
                    @Override
                    public Double getMarketValue() {
                        return 100000.0;
                    }
                };
                temp.add(validEntry);

                return temp;
            }

            @Override
            public List<populationDataEntry> getPopulationArray() throws IOException {
                List<populationDataEntry> temp = new ArrayList<>();
                String[] populationArr = new String[2];
                populationArr[0] = "19118";
                populationArr[1] = "10";
                temp.add(new populationDataEntry(populationArr));
                return temp;
            }
        };

        processData p = new processData(d);
        int result = p.menuFive("19118");
        assertEquals(10000, result);
    }
}