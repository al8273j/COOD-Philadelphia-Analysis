import org.junit.jupiter.api.Test;
import static junit.framework.Assert.assertEquals;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import org.json.simple.parser.ParseException;
import project.data.getData;
import project.processor.processData;

public class menuFiveTest {

    getData dataCSV = new getData("csv", "PhillyData-files/parking.csv", "PhillyData-files/properties.csv", "PhillyData-files/population.txt");
    getData dataJSON = new getData("json", "PhillyData-files/parking.json", "PhillyData-files/properties.csv", "PhillyData-files/population.txt");

    @Test
    public void testMenuFive19118() throws IOException, ParseException, ExecutionException, InterruptedException {
        processData p = new processData(dataCSV);
        int result = p.menuFive("19118");
        assertEquals(222802, result);
    }

    @Test
    public void testMenuFive19118JSON() throws IOException, ParseException, ExecutionException, InterruptedException {
        processData p = new processData(dataJSON);
        int result = p.menuFive("19118");
        assertEquals(222802, result);
    }

    @Test
    public void testMenuFive19111() throws IOException, ParseException, ExecutionException, InterruptedException {
        processData p = new processData(dataCSV);
        int result = p.menuFive("19111");
        assertEquals(66489, result);
    }

    @Test
    public void testMenuFive19111JSON() throws IOException, ParseException, ExecutionException, InterruptedException {
        processData p = new processData(dataJSON);
        int result = p.menuFive("19111");
        assertEquals(66489, result);
    }

    @Test
    public void testMenuFive00000() throws IOException, ParseException, ExecutionException, InterruptedException {
        processData p = new processData(dataCSV);
        int result = p.menuFive("00000");
        assertEquals(0, result);
    }

    @Test
    public void testMenuFive00000JSON() throws IOException, ParseException, ExecutionException, InterruptedException {
        processData p = new processData(dataJSON);
        int result = p.menuFive("00000");
        assertEquals(0, result);
    }
}