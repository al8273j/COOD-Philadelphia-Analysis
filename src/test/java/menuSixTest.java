import org.junit.jupiter.api.Test;
import static junit.framework.Assert.assertEquals;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import org.json.simple.parser.ParseException;
import project.data.getData;
import project.processor.processData;

public class menuSixTest {
    getData dataCSV = new getData("csv", "PhillyData-files/parking.csv", "PhillyData-files/properties.csv", "PhillyData-files/population.txt");
    getData dataJSON = new getData("json", "PhillyData-files/parking.json", "PhillyData-files/properties.csv", "PhillyData-files/population.txt");

    @Test
    public void testMenuSix19118() throws IOException, ParseException, ExecutionException, InterruptedException {
        processData p = new processData(dataCSV);
        int result = p.menuSix("19118", 0, 200000);
        assertEquals(197, result);
    }

    @Test
    public void testMenuSix19118JSON() throws IOException, ParseException, ExecutionException, InterruptedException {
        processData p = new processData(dataJSON);
        int result = p.menuSix("19118", 0, 200000);
        assertEquals(197, result);
    }

    @Test
    public void testMenuSix19111() throws IOException, ParseException, ExecutionException, InterruptedException {
        processData p = new processData(dataCSV);
        int result = p.menuSix("19111", 0, 200000);
        assertEquals(11691, result);
    }

    @Test
    public void testMenuSix19111JSON() throws IOException, ParseException, ExecutionException, InterruptedException {
        processData p = new processData(dataJSON);
        int result = p.menuSix("19111", 0, 200000);
        assertEquals(11691, result);
    }
}