import org.junit.jupiter.api.Test;
import static junit.framework.Assert.assertEquals;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import org.json.simple.parser.ParseException;
import project.data.getData;
import project.processor.processData;

public class menuFourTest {

    getData dataCSV = new getData("csv", "PhillyData-files/parking.csv", "PhillyData-files/properties.csv", "PhillyData-files/population.txt");
    getData dataJSON = new getData("json", "PhillyData-files/parking.json", "PhillyData-files/properties.csv", "PhillyData-files/population.txt");

    @Test
    public void testMenuFour19118() throws IOException, ParseException, ExecutionException, InterruptedException {
        processData p = new processData(dataCSV);
        int result = p.menuFour("19118");
        assertEquals(4346, result);
    }

    @Test
    public void testMenuFour19118JSON() throws IOException, ParseException, ExecutionException, InterruptedException {
        processData p = new processData(dataJSON);
        int result = p.menuFour("19118");
        assertEquals(4346, result);
    }

    @Test
    public void testMenuFour19111() throws IOException, ParseException, ExecutionException, InterruptedException {
        processData p = new processData(dataCSV);
        int result = p.menuFour("19111");
        assertEquals(1825, result);
    }

    @Test
    public void testMenuFour19111JSON() throws IOException, ParseException, ExecutionException, InterruptedException {
        processData p = new processData(dataJSON);
        int result = p.menuFour("19111");
        assertEquals(1825, result);
    }

    @Test
    public void testMenuFour00000() throws IOException, ParseException, ExecutionException, InterruptedException {
        processData p = new processData(dataCSV);
        int result = p.menuFour("00000");
        assertEquals(0, result);
    }

    @Test
    public void testMenuFour00000JSON() throws IOException, ParseException, ExecutionException, InterruptedException {
        processData p = new processData(dataJSON);
        int result = p.menuFour("00000");
        assertEquals(0, result);
    }
}