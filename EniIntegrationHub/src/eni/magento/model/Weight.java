
package eni.magento.model;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;

@Generated("jsonschema2pojo")
public class Weight {

    private Double value;
    private String units;
    private Integer weightUnits;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public Integer getWeightUnits() {
        return weightUnits;
    }

    public void setWeightUnits(Integer weightUnits) {
        this.weightUnits = weightUnits;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
