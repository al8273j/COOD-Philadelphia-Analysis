package project.common;

public class getPopulationData {

    private String[] populationData;
    public getPopulationData(String[] populationData) {
        this.populationData = populationData;
    }


    public int getZipCode(){
        return Integer.parseInt(populationData[0]);
    }

    public int getPopulationNumber(){
        return Integer.parseInt(populationData[1]);
    }
}
