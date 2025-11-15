package project;

import project.data.getData;
import project.processor.processData;

public class Main {
    public static void main(String[] args) {
        //validates number of runtime arguments
        if (args.length != 4) {
            //throw error
        }
        String parkingViolationsFormat = args[0];
        //validates file format input
        if (!(parkingViolationsFormat.equals("csv") || parkingViolationsFormat.equals("json"))) {
            //throw error
        }
        String parkingViolationsFile = args[1];
        String propertyValuesFile = args[2];
        String populationFile = args[3];


        //Data Tier
        getData data = new getData(parkingViolationsFormat, parkingViolationsFile, propertyValuesFile, populationFile);


        //Processor Tier
        processData processor = new processData(data);

        //UI Tier
        presentData ui = new presentData(processor);





    }
}

