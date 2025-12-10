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

    public int menuThree(String zipCode){
        /*
        * Menu Three:
        * Displays average residential market value for inputted ZIP Code
        * uses memoization
        * */


        String input = zipCode;
        if (menuThreeMemo.containsKey(input)) {
            return menuThreeMemo.get(input);

        }
        else{
            int matchingZipCodes = 0;
            double totalMarketValue = 0;
            for(propertiesDataEntry  prop: properties){

                if (prop.getZipCode().equals(input)){
                    matchingZipCodes++;
                    Double marketValue = prop.getMarketValue();

                    if (marketValue>0) {
                        totalMarketValue += marketValue;
                    }

                }
            }
            int result;
            if (matchingZipCodes==0){
                result = 0;
            }
            else{
                result = (int) totalMarketValue/matchingZipCodes;
            }
            menuThreeMemo.put(input, result);
            return result;
        }

        //public void menuFour(){}
      //  public void menuFive(){}
     //   public void menuSix(){}
     //   public void menuSeven(){}

    }
    /*
    Menu Seven returns number of houses in a given zipcode between
    a minimum and maximum value entered by the user. Uses streams
    and lambdas
     */
    public int menuSeven(String zipcode, double min, double max){
        if(zipcode==null || zipcode.isEmpty() || min<=0 || max<=0){
            return 0;
        }
        if(min>max){
            System.out.println("Please re-enter. Min>Max is not possible.");
            return 0;
        }
        return (int) properties.stream()
                .filter(p->zipcode.equals(p.getZipCode()))
                .map(p->p.getMarketValue())
                .filter(m->m!=null && m>0)
                .filter(m->m>=min&&m<=max)
                .count();
    }



}