
package eni.magento.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;

@Generated("jsonschema2pojo")
public class AdvancedOptions {

    private Integer warehouseId;
    private Boolean nonMachinable;
    private Boolean saturdayDelivery;
    private Boolean containsAlcohol;
    private Boolean mergedOrSplit;
    private List<Object> mergedIds = null;
    private Object parentId;
    private Integer storeId;
    private String customField1;
    private Object customField2;
    private Object customField3;
    private Object source;
    private Object billToParty;
    private Object billToAccount;
    private Object billToPostalCode;
    private Object billToCountryCode;
    private Object billToMyOtherAccount;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Integer getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Integer warehouseId) {
        this.warehouseId = warehouseId;
    }

    public Boolean getNonMachinable() {
        return nonMachinable;
    }

    public void setNonMachinable(Boolean nonMachinable) {
        this.nonMachinable = nonMachinable;
    }

    public Boolean getSaturdayDelivery() {
        return saturdayDelivery;
    }

    public void setSaturdayDelivery(Boolean saturdayDelivery) {
        this.saturdayDelivery = saturdayDelivery;
    }

    public Boolean getContainsAlcohol() {
        return containsAlcohol;
    }

    public void setContainsAlcohol(Boolean containsAlcohol) {
        this.containsAlcohol = containsAlcohol;
    }

    public Boolean getMergedOrSplit() {
        return mergedOrSplit;
    }

    public void setMergedOrSplit(Boolean mergedOrSplit) {
        this.mergedOrSplit = mergedOrSplit;
    }

    public List<Object> getMergedIds() {
        return mergedIds;
    }

    public void setMergedIds(List<Object> mergedIds) {
        this.mergedIds = mergedIds;
    }

    public Object getParentId() {
        return parentId;
    }

    public void setParentId(Object parentId) {
        this.parentId = parentId;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public String getCustomField1() {
        return customField1;
    }

    public void setCustomField1(String customField1) {
        this.customField1 = customField1;
    }

    public Object getCustomField2() {
        return customField2;
    }

    public void setCustomField2(Object customField2) {
        this.customField2 = customField2;
    }

    public Object getCustomField3() {
        return customField3;
    }

    public void setCustomField3(Object customField3) {
        this.customField3 = customField3;
    }

    public Object getSource() {
        return source;
    }

    public void setSource(Object source) {
        this.source = source;
    }

    public Object getBillToParty() {
        return billToParty;
    }

    public void setBillToParty(Object billToParty) {
        this.billToParty = billToParty;
    }

    public Object getBillToAccount() {
        return billToAccount;
    }

    public void setBillToAccount(Object billToAccount) {
        this.billToAccount = billToAccount;
    }

    public Object getBillToPostalCode() {
        return billToPostalCode;
    }

    public void setBillToPostalCode(Object billToPostalCode) {
        this.billToPostalCode = billToPostalCode;
    }

    public Object getBillToCountryCode() {
        return billToCountryCode;
    }

    public void setBillToCountryCode(Object billToCountryCode) {
        this.billToCountryCode = billToCountryCode;
    }

    public Object getBillToMyOtherAccount() {
        return billToMyOtherAccount;
    }

    public void setBillToMyOtherAccount(Object billToMyOtherAccount) {
        this.billToMyOtherAccount = billToMyOtherAccount;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
