
package eni.magento.model;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;

@Generated("jsonschema2pojo")
public class CustomsItem {

    private Integer customsItemId;
    private String description;
    private Integer quantity;
    private Double value;
    private String harmonizedTariffCode;
    private String countryOfOrigin;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Integer getCustomsItemId() {
        return customsItemId;
    }

    public void setCustomsItemId(Integer customsItemId) {
        this.customsItemId = customsItemId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getHarmonizedTariffCode() {
        return harmonizedTariffCode;
    }

    public void setHarmonizedTariffCode(String harmonizedTariffCode) {
        this.harmonizedTariffCode = harmonizedTariffCode;
    }

    public String getCountryOfOrigin() {
        return countryOfOrigin;
    }

    public void setCountryOfOrigin(String countryOfOrigin) {
        this.countryOfOrigin = countryOfOrigin;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
