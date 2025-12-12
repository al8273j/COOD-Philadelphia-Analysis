import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;
import project.common.parkingViolationFromCSV;
import project.common.parkingViolationFromJSON;
import project.common.populationDataEntry;
import project.common.propertiesDataEntry;
import project.data.getData;
import project.processor.processData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;
import java.util.concurrent.ExecutionException;

import static junit.framework.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;


public class menuSevenTest {
   @Test
   public void testMenuSeven() throws IOException, ExecutionException, InterruptedException, ParseException {
      List<propertiesDataEntry> temp = new ArrayList<>();
      temp.add(new propertiesDataEntry(null){//zipcode null
         @Override
         public String getZipCode() { return null;}
         @Override
         public Double getTotalLivableArea() {return 500.0;}
      });
      temp.add(new propertiesDataEntry(null){//zipcode correct, null area
         @Override
         public String getZipCode() { return "94062";}
         @Override
         public Double getTotalLivableArea() {return null;}
      });
      temp.add(new propertiesDataEntry(null){//zipcode correct, negative area
         @Override
         public String getZipCode() { return "94062";}
         @Override
         public Double getTotalLivableArea() {return -500.0;}
      });
      temp.add(new propertiesDataEntry(null){//zipcode correct, valid min
         @Override
         public String getZipCode() { return "94062";}
         @Override
         public Double getTotalLivableArea() {return 500.0;}
      });
      temp.add(new propertiesDataEntry(null){//zipcode correct, valid max
         @Override
         public String getZipCode() { return "94062";}
         @Override
         public Double getTotalLivableArea() {return 1000.0;}
      });
      getData data = new getData("csv", "PhillyData-files/parking.csv", "PhillyData-files/properties.csv", "PhillyData-files/population.txt"){
         @Override
         public List<propertiesDataEntry> getPropertiesArray(){
            return temp;
         }
      };
      processData p = new processData(data);
      p.properties = temp;
      assertArrayEquals(new int[]{500,1000}, p.menuSeven("94062"));
      assertArrayEquals(new int[]{0,0}, p.menuSeven(null));
      assertArrayEquals(new int[]{0,0}, p.menuSeven(""));
      List<propertiesDataEntry> temp2 = new ArrayList<>();
      temp2.add(new propertiesDataEntry(null){//zipcode correct, null area
         @Override
         public String getZipCode() {return "94062";}
         @Override
         public Double getTotalLivableArea() {return null;}
      });
      p.properties = temp2;
      assertArrayEquals(new int[]{0,0}, p.menuSeven("94062"));
   }
   @Test
   public void testNullZip() throws Exception{
      getData data = new getData("csv", "PhillyData-files/parking.csv", "PhillyData-files/properties.csv", "PhillyData-files/population.txt"){
         @Override
         public List<propertiesDataEntry> getPropertiesArray(){
            return new ArrayList<>();
         }
      };
      processData p = new processData(data);
      assertArrayEquals(new int[]{0,0}, p.menuSeven(null));
   }
   @Test
   public void testEmptyZip() throws Exception {
      getData data = new getData("csv", "PhillyData-files/parking.csv", "PhillyData-files/properties.csv", "PhillyData-files/population.txt") {
         @Override
         public List<propertiesDataEntry> getPropertiesArray() {
            return new ArrayList<>();
         }
      };
      processData p = new processData(data);
      assertArrayEquals(new int[]{0,0}, p.menuSeven(null));
      }
   @Test
   public void testZipBadArea() throws Exception {
      List<propertiesDataEntry> entries = new ArrayList<>();
      entries.add(new propertiesDataEntry(null){
         @Override
         public String getZipCode(){
            return "94062";
         }
         @Override
         public Double getTotalLivableArea() {
            return null;
         }
      });
      entries.add(new propertiesDataEntry(null){
         @Override
         public String getZipCode(){
            return "94062";
         }
         @Override
         public Double getTotalLivableArea() {
            return -100.0;
         }
      });
      getData data = new getData("csv", "PhillyData-files/parking.csv", "PhillyData-files/properties.csv", "PhillyData-files/population.txt") {
         @Override
         public List<propertiesDataEntry> getPropertiesArray() {
            return new ArrayList<>();
         }
      };
      processData p = new processData(data);
      assertArrayEquals(new int[]{0,0}, p.menuSeven("94062"));
   }
   @Test
   public void testZipNotPresent() throws Exception {
      List<propertiesDataEntry> entries = new ArrayList<>();
      entries.add(new propertiesDataEntry(null){
         @Override
         public String getZipCode(){
            return "11111";
         }
         @Override
         public Double getTotalLivableArea() {
            return 500.0;
         }
      });
      getData data = new getData("csv", "PhillyData-files/parking.csv", "PhillyData-files/properties.csv", "PhillyData-files/population.txt") {
         @Override
         public List<propertiesDataEntry> getPropertiesArray() {
            return new ArrayList<>();
         }
      };
      processData p = new processData(data);
      assertArrayEquals(new int[]{0,0}, p.menuSeven("94062"));
   }
   @Test
   public void testValidEntries() throws Exception {
      List<propertiesDataEntry> entries = new ArrayList<>();
      entries.add(new propertiesDataEntry(null){
         @Override
         public String getZipCode(){
            return "94062";
         }
         @Override
         public Double getTotalLivableArea() {
            return 500.0;
         }
      });
      entries.add(new propertiesDataEntry(null){
         @Override
         public String getZipCode(){
            return "94062";
         }
         @Override
         public Double getTotalLivableArea() {
            return 1000.0;
         }
      });
      getData data = new getData("csv", "PhillyData-files/parking.csv", "PhillyData-files/properties.csv", "PhillyData-files/population.txt") {
         @Override
         public List<propertiesDataEntry> getPropertiesArray() {
            return entries;
         }
      };
      processData p = new processData(data);
      assertArrayEquals(new int[]{500,1000}, p.menuSeven("94062"));
   }


}
