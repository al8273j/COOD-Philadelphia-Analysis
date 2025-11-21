/*
* This class stores the data from each inputted file into their necessary objects.
* Information from the JSON file (parking.json) can be retrieved as a JSONArray
* Information from the txt file (population.txt) can be retrieved as a ArrayList of String arrays (ArrayList<String[]>)
* */

package project.data;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import project.common.ParkingCSVEntry;
import project.common.PropertyEntry;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class getData {

    String parkingViolationsFormat;
    String parkingViolationsFile;
    String propertyValuesFile;
    String populationFile;


    public getData(String parkingViolationsFormat, String parkingViolationsFile, String propertyValuesFile, String populationFile) {
        this.parkingViolationsFormat = parkingViolationsFormat;
        this.parkingViolationsFile = parkingViolationsFile;
        this.propertyValuesFile = propertyValuesFile;
        this.populationFile = populationFile;
    }

    public String getParkingViolationsFormat() {
        return parkingViolationsFormat;
    }

    public JSONArray getParkingViolationsJSON() throws IOException, ParseException {
        JSONArray parkingViolationsJSON = (JSONArray) new JSONParser().parse(new FileReader(parkingViolationsFile));
        return parkingViolationsJSON;
    }

    public ArrayList<String[]> getPopulationArray() throws IOException {
        ArrayList<String[]> populationArray = new ArrayList<>();
        FileReader fileReader = new FileReader(populationFile);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;
        while((line = bufferedReader.readLine())!= null){
            populationArray.add(line.split(" "));
        }
        return populationArray;
    }
    public List<ParkingCSVEntry> getParkingCSV(String filename){
        List<ParkingCSVEntry> result = new ArrayList<>();
        try(BufferedReader r = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = r.readLine()) != null) {
                String[] sections = line.split(",");
                if (sections.length != 7) {
                    continue;
                }
                String timeStamp = sections[0];
                double fine;
                try {
                    fine = Double.parseDouble(sections[1]);
                } catch (NumberFormatException e) {
                    continue;
                }
                String description = sections[2];
                String anonymousID = sections[3];
                String licensePlateState = sections[4];
                String uniqueID = sections[5];
                String zipCode = sections[6];
                ParkingCSVEntry entry = new ParkingCSVEntry(timeStamp, fine, description, anonymousID, licensePlateState, uniqueID, zipCode);
                result.add(entry);
            }
        }catch(IOException e){
            System.out.println("IOException");
        }
        return result;
    }
    public List<PropertyEntry> getPropertyValues(String filename){
        List<PropertyEntry> result = new ArrayList<>();
        try(BufferedReader r = new BufferedReader(new FileReader(filename))){
            String header = r.readLine();
            if(header == null){
                return result;
            }
            String[] headers = header.split(",");
            int market = -1;
            int area = -1;
            int zip = -1;
            for(int i=0; i<headers.length;i++){
                String head = headers[i].trim().toLowerCase();
                switch (head) {
                    case "market_value":
                        market =i;
                        break;
                    case "total_livable_area":
                        area = i;
                        break;
                    case "zip_code":
                        zip=i;
                        break;
                    default:
                }
            }
            String line;
            while((line = r.readLine())!= null){
                String[] sections = line.split(",");
                if(market>=sections.length || area>=sections.length || zip>= sections.length){
                    continue;
                }
                String dirtyZip = sections[zip];
                String cleanZip;
                if(dirtyZip.length()>=5){
                    cleanZip=dirtyZip.substring(0,5);
                }else{
                    cleanZip=dirtyZip;
                }
                Double cleanMarket = cleanPosDoubles(sections[market]);
                Double cleanArea = cleanPosDoubles(sections[area]);
                PropertyEntry p = new PropertyEntry(cleanMarket,cleanArea,cleanZip);
                result.add(p);
            }
        }catch(IOException e){
            System.out.println("IOException");
        }
        return result;
    }
    private Double cleanPosDoubles(String string){
        try{
            double d = Double.parseDouble(string.trim());
            if(d>0){
                return d;
            }
        }catch(NumberFormatException e){
            System.out.println("NumberFormatException");
        }
        return null;
    }

}
