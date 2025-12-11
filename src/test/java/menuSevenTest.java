import org.junit.Test;
import project.common.propertiesDataEntry;
import project.processor.processData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertArrayEquals;

public class menuSevenTest {
   @Test
   public void testMenuSeven() throws IOException, ExecutionException, InterruptedException{
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
      processData p = new processData(null);
      p.properties = temp;
      assertArrayEquals(new int[]{300,900}, p.menuSeven("94062"));
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
}
