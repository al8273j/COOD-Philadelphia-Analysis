package project.common;

public class populationDataEntry {

    private String[] populationData;
    public populationDataEntry(String[] populationData) {
        this.populationData = populationData;
    }


    public String getZipCode(){
        return populationData[0];
    }

    public int getPopulationNumber(){
        return Integer.parseInt(populationData[1]);
    }
}
