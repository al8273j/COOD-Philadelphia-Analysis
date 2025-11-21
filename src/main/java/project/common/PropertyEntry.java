package project.common;

public class PropertyEntry {
    private Double marketValue;
    private Double livableArea;
    private String zipCode;
    public PropertyEntry(Double marketValue, Double livableArea, String zipCode){
        this.marketValue = marketValue;
        this.livableArea=livableArea;
        this.zipCode=zipCode;
    }
    public Double getMarketValue(){
        return marketValue;
    }
    public Double getLivableArea(){
        return livableArea;
    }
    public String getZipCode(){
        return zipCode;
    }
}
