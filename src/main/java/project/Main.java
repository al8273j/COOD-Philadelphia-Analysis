package project;

import org.json.simple.parser.ParseException;
import project.data.getData;
import project.presentation.presentData;
import project.processor.processData;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class Main {
    public static void main(String[] args) throws IOException, ParseException, ExecutionException, InterruptedException {
        //validates number of runtime arguments
        if (args.length != 4) {
           throw new IllegalArgumentException("Usage: <parking-file-type> <parking-filepath> <properties-filepath> <population-filepath>");
        }
        String parkingViolationsFormat = args[0];
        //validates file format input
        if (!(parkingViolationsFormat.equals("csv") || parkingViolationsFormat.equals("json"))) {
            //throw error
        }

        //json PhillyData-files/parking.json PhillyData-files/properties.csv PhillyData-files/population.txt

        String parkingViolationsFile = args[1];
        String propertyValuesFile = args[2];
        String populationFile = args[3];


        //Data Tier
        getData data = new getData(parkingViolationsFormat, parkingViolationsFile, propertyValuesFile, populationFile);


        //Processor Tier
        processData processor = new processData(data);

        //UI Tier
        presentData ui = new presentData(processor);
        ui.start();





    }
}
