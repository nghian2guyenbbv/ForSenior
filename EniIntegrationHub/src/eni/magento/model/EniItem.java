
package eni.magento.model;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;

@Generated("jsonschema2pojo")
public class EniItem {

    private String orderItemId;
    private Object lineItemKey;
    private String sku;
    private String name;
    private String imageUrl;
    private Weight weight;
    private Integer quantity;
    private Double unitPrice;
    private Double taxAmount;
    private Double shippingAmount;
    private Object warehouseLocation;
    private List<Option> options = null;
    private Integer productId;
    private Object fulfillmentSku;
    private Boolean adjustment;
    private Object upc;
    private String createDate;
    private String modifyDate;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(String orderItemId) {
        this.orderItemId = orderItemId;
    }

    public Object getLineItemKey() {
        return lineItemKey;
    }

    public void setLineItemKey(Object lineItemKey) {
        this.lineItemKey = lineItemKey;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Weight getWeight() {
        return weight;
    }

    public void setWeight(Weight weight) {
        this.weight = weight;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Double getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(Double taxAmount) {
        this.taxAmount = taxAmount;
    }

    public Double getShippingAmount() {
        return shippingAmount;
    }

    public void setShippingAmount(Double shippingAmount) {
        this.shippingAmount = shippingAmount;
    }

    public Object getWarehouseLocation() {
        return warehouseLocation;
    }

    public void setWarehouseLocation(Object warehouseLocation) {
        this.warehouseLocation = warehouseLocation;
    }

    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Object getFulfillmentSku() {
        return fulfillmentSku;
    }

    public void setFulfillmentSku(Object fulfillmentSku) {
        this.fulfillmentSku = fulfillmentSku;
    }

    public Boolean getAdjustment() {
        return adjustment;
    }

    public void setAdjustment(Boolean adjustment) {
        this.adjustment = adjustment;
    }

    public Object getUpc() {
        return upc;
    }

    public void setUpc(Object upc) {
        this.upc = upc;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(String modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
