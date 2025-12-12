package project.common;

import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface parkingViolationStrategy {
    List<parkingViolationDataEntry> parseParkingViolationFile(String fileName) throws IOException, ParseException;
}
