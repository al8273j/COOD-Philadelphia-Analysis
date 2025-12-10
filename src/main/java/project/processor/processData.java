package project.processor;


import org.json.simple.parser.ParseException;
import project.common.ScannerSingleton;
import project.common.parkingViolationDataEntry;
import project.common.populationDataEntry;
import project.common.propertiesDataEntry;
import project.data.getData;

import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class processData {

    public getData data;
    public List<propertiesDataEntry> properties;
    public List<parkingViolationDataEntry> parkingViolations;
    public List<populationDataEntry> population;
    public Scanner scanner = ScannerSingleton.getScannerInstance();
    public HashMap<String,Integer> menuThreeMemo = new HashMap<>();

    public processData(getData data) throws IOException, ParseException, ExecutionException, InterruptedException {
        this.data = data;
        properties = data.getPropertiesArray();
        population = data.getPopulationArray();

        if (data.getParkingViolationsFormat().equals("csv")) {
            parkingViolations = data.getParkingViolationsCSVData();
        }
        else{
            parkingViolations = data.getParkingViolationsJSONData();
        }


    }

    public int menuOne(){
        /*
        * Menu One:
        * The total population for all of the ZIP Codes in the population input file
        * uses streams
         * */
        int val= population.stream().map((p)->p.getPopulationNumber()).reduce(0, (x, y) -> x+y);
        return val;
    }

    public HashMap<String, String> menuTwo() throws ExecutionException, InterruptedException {

        /*
        * Menu Two:
        * The total aggregate fines divided by the population of that ZIP Code
        * Runs fine aggregation and population mapping concurrently
        * uses threads
        * */
        HashMap<String, String> result = new HashMap<>();
        HashMap<String, Integer> populationPerZipCode = new HashMap<>();
        ExecutorService es = Executors.newSingleThreadExecutor();

        Future<TreeMap<String, Double>> future = es.submit(() -> {
            TreeMap<String, Double> totalFinesPerCapita = new TreeMap<>();
            for(parkingViolationDataEntry pv: parkingViolations){

                String zipCode = pv.getZipCode();
                if (zipCode == null || zipCode.length() == 0) {
                    continue;
                }
                double fine = pv.getFine();
                String state = pv.getState();
                if (!state.equals("PA")) {
                    continue;
                }


                totalFinesPerCapita.put(zipCode, totalFinesPerCapita.getOrDefault(zipCode, 0.0)+fine);
            }
            return totalFinesPerCapita;
        });

        for(populationDataEntry pde : population){
            int populationNumber = pde.getPopulationNumber();
            String zipCode = pde.getZipCode();
            if (zipCode == null || zipCode.isEmpty()) {
                continue;
            }
            populationPerZipCode.put(zipCode, populationNumber);
        }
        try{
        TreeMap<String, Double> totalFinesPerCapita = future.get();
        es.shutdown();
        DecimalFormat df = new DecimalFormat("0.0000");
        df.setRoundingMode(RoundingMode.HALF_UP);


        for (Map.Entry<String, Double> entry : totalFinesPerCapita.entrySet()){
            String zipCode = entry.getKey();
            Double fineTotal = entry.getValue();
            if (fineTotal == 0)
                continue;

            if (populationPerZipCode.containsKey(zipCode)) {
                int pop = populationPerZipCode.get(zipCode);
                if (pop==0)
                    continue;


                result.put(zipCode, df.format(pop / fineTotal));
            }
        }
        }
        catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return result;

    }

    public int menuThree(String zipCode) {
        /*
         * Menu Three:
         * Displays average residential market value for inputted ZIP Code
         * uses memoization
         * */


        String input = zipCode;
        if (menuThreeMemo.containsKey(input)) {
            return menuThreeMemo.get(input);

        } else {
            int matchingZipCodes = 0;
            double totalMarketValue = 0;
            for (propertiesDataEntry prop : properties) {

                if (prop.getZipCode().equals(input)) {
                    matchingZipCodes++;
                    Double marketValue = prop.getMarketValue();

                    if (marketValue > 0) {
                        totalMarketValue += marketValue;
                    }

                }
            }
            int result;
            if (matchingZipCodes == 0) {
                result = 0;
            } else {
                result = (int) totalMarketValue / matchingZipCodes;
            }
            menuThreeMemo.put(input, result);
            return result;
        }
    }
    public int menuFour(String zipcode) {
        /*
         * Menu Four:
         * Displays average total livable area for inputted ZIP Code
         * */

        String input = zipcode;
        int count = 0;
        double totalArea = 0;

        for (propertiesDataEntry pde : properties) {
            if (pde.getZipCode().startsWith(input)) {
                Double area = pde.getTotalLivableArea();
                if (area != null && area > 0) {
                    totalArea += area;
                    count++;
                }
            }
        }

        if (count == 0) {
            return 0;
        }
        else {
            return (int) Math.round(totalArea / count);
        }
    }

    public int menuFive(String zipcode){
        /*
         * Menu Five:
         * The total market value divided by the population of that ZIP Code
         * */


        String input = zipcode;
        double totalMarketVal = 0;
        boolean hasValidResidences = false;

        for (propertiesDataEntry pde : properties) {
            if (pde.getZipCode().startsWith(input)) {
                Double val = pde.getMarketValue();
                if (val != null && val > 0) {
                    totalMarketVal += val;
                    hasValidResidences = true;
                }
            }
        }

        int pop = 0;
        for (populationDataEntry pde : population) {
            if (pde.getZipCode().startsWith(input)) {
                pop = pde.getPopulationNumber();
                break;
            }
        }

        if (!hasValidResidences || pop == 0) {
            return 0;
        }
        else {
            return (int) Math.round(totalMarketVal / pop);
        }
    }
     public int menuSix(String zipcode, int min, int max){
         /*
          * Menu Six:
          * Counts number of homes within a specific market value range
          */

         String input = zipcode;
         int count = 0;

         for (propertiesDataEntry pde : properties) {
             if (pde.getZipCode().startsWith(input)) {
                 Double val = pde.getMarketValue();

                 if (val != null && val >= min && val <= max) {
                     count++;
                 }
             }
         }
         return count;
     }
    public int[] menuSeven(String zipcode) {
        if (zipcode == null || zipcode.isEmpty()) {
            return new int[]{0, 0};
        }
        final int[] minAndMax = {Integer.MAX_VALUE, Integer.MIN_VALUE};
        properties.stream()
                .filter(p -> p.getZipCode() != null && p.getZipCode().equals(zipcode))
                .forEach(p -> {
                    Double area = p.getTotalLivableArea();
                    if (area == null || area <= 0) return;
                    int value = area.intValue();
                    if (value < minAndMax[0]) minAndMax[0] = value;
                    if (value > minAndMax[1]) minAndMax[1] = value;
                });
        if (minAndMax[0] == Integer.MAX_VALUE) {
            return new int[]{0, 0};
        }
        return minAndMax;
    }
}