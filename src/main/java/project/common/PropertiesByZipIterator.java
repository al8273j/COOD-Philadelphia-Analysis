package project.common;

import project.common.propertiesDataEntry;

import java.util.Iterator;
import java.util.List;

public class PropertiesByZipIterator implements Iterator<propertiesDataEntry> {
    private final Iterator<propertiesDataEntry> base;
    private final String zip;
    private propertiesDataEntry nextMatch;

    public PropertiesByZipIterator(List<propertiesDataEntry> properties, String zip) {
        this.base = properties.iterator();
        this.zip = zip;
        advance();
    }

    private void advance() {
        nextMatch = null;
        while (base.hasNext()) {
            propertiesDataEntry candidate = base.next();
            if (candidate.getZipCode().startsWith(zip)) {
                nextMatch = candidate;
                break;
            }
        }
    }

    @Override
    public boolean hasNext() {
        return nextMatch != null;
    }

    @Override
    public propertiesDataEntry next() {
        propertiesDataEntry current = nextMatch;
        advance();
        return current;
    }
}
