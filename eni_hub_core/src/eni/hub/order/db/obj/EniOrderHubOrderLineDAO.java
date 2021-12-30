/**
 *CONFIDENTIAL AND PROPRIETARY SOURCE CODE. 
 * 
 * Use and distribution of this code is subject to applicable 
 * licenses and the permission of the code owner. This notice 
 * does not indicate the actual or intended publication of 
 * this source code.
 * 
 * Portions developed for Event Network by BTM Global Consulting
 * LLC and are the property of Event Network.
 * 
 * ===== BTM Modification ===========================================
 * Bug#         ddMMyy          Description
 * POC          261021          BTM Hub-XSO Integration                                                                   
 *===================================================================
 */
package eni.hub.order.db.obj;

import java.math.BigDecimal;
import java.util.Date;

public class EniOrderHubOrderLineDAO {
	private int organizationId = 1;
	private String orderId = null;
	private int lineNo = -1;
	private String itemUpcCode = null;
	private String itemEanCode = null;
	private String externalOrderId = null;
	private String itemId = null;
	private BigDecimal quantity = null;
	private String fulfillmentType = null;
	private String statusCode = null;
	private BigDecimal unitPrice = null;
	private BigDecimal extendedPrice = null;
	private BigDecimal taxAmount = null;
	private String specialInstructions = null;
	private String trackingNbr = null;
	private boolean voidFlag = false;
	private String actualShipMethod = null;
	private boolean dropShipFlag = false;
	private String statusCodeReason = null;
	private String statusCodeReasonNote = null;
	private BigDecimal extendedFreight = null;
	private BigDecimal customizationCharge = null;
	private boolean giftWrapFlag = false;
	private boolean shipAloneFlag = false;
	private BigDecimal shipWeight = null;
	private String lineMessage = null;
	private Date pickupByDate = null;	
	private String customizationCode = null;
	private String customizationMessage = null;
	private String cartonNumber = null;
	private Date updateDate = null;
	

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public int getLineNo() {
		return lineNo;
	}

	public void setLineNo(int lineNo) {
		this.lineNo = lineNo;
	}

	public String getItemUpcCode() {
		return itemUpcCode;
	}

	public void setItemUpcCode(String itemUpcCode) {
		this.itemUpcCode = itemUpcCode;
	}

	public String getItemEanCode() {
		return itemEanCode;
	}

	public void setItemEanCode(String itemEanCode) {
		this.itemEanCode = itemEanCode;
	}

	public String getExternalOrderId() {
		return externalOrderId;
	}

	public void setExternalOrderId(String externalOrderId) {
		this.externalOrderId = externalOrderId;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public String getFulfillmentType() {
		return fulfillmentType;
	}

	public void setFulfillmentType(String fulfillmentType) {
		this.fulfillmentType = fulfillmentType;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public BigDecimal getExtendedPrice() {
		return extendedPrice;
	}

	public void setExtendedPrice(BigDecimal extendedPrice) {
		this.extendedPrice = extendedPrice;
	}

	public BigDecimal getTaxAmount() {
		return taxAmount;
	}

	public void setTaxAmount(BigDecimal taxAmount) {
		this.taxAmount = taxAmount;
	}

	public String getSpecialInstructions() {
		return specialInstructions;
	}

	public void setSpecialInstructions(String specialInstructions) {
		this.specialInstructions = specialInstructions;
	}

	public String getTrackingNbr() {
		return trackingNbr;
	}

	public void setTrackingNbr(String trackingNbr) {
		this.trackingNbr = trackingNbr;
	}

	public boolean isVoidFlag() {
		return voidFlag;
	}

	public void setVoidFlag(boolean voidFlag) {
		this.voidFlag = voidFlag;
	}

	public String getActualShipMethod() {
		return actualShipMethod;
	}

	public void setActualShipMethod(String actualShipMethod) {
		this.actualShipMethod = actualShipMethod;
	}

	public boolean isDropShipFlag() {
		return dropShipFlag;
	}

	public void setDropShipFlag(boolean dropShipFlag) {
		this.dropShipFlag = dropShipFlag;
	}

	public String getStatusCodeReason() {
		return statusCodeReason;
	}

	public void setStatusCodeReason(String statusCodeReason) {
		this.statusCodeReason = statusCodeReason;
	}

	public String getStatusCodeReasonNote() {
		return statusCodeReasonNote;
	}

	public void setStatusCodeReasonNote(String statusCodeReasonNote) {
		this.statusCodeReasonNote = statusCodeReasonNote;
	}

	public BigDecimal getExtendedFreight() {
		return extendedFreight;
	}

	public void setExtendedFreight(BigDecimal extendedFreight) {
		this.extendedFreight = extendedFreight;
	}

	public BigDecimal getCustomizationCharge() {
		return customizationCharge;
	}

	public void setCustomizationCharge(BigDecimal customizationCharge) {
		this.customizationCharge = customizationCharge;
	}

	public boolean isGiftWrapFlag() {
		return giftWrapFlag;
	}

	public void setGiftWrapFlag(boolean giftWrapFlag) {
		this.giftWrapFlag = giftWrapFlag;
	}

	public boolean isShipAloneFlag() {
		return shipAloneFlag;
	}

	public void setShipAloneFlag(boolean shipAloneFlag) {
		this.shipAloneFlag = shipAloneFlag;
	}

	public BigDecimal getShipWeight() {
		return shipWeight;
	}

	public void setShipWeight(BigDecimal shipWeight) {
		this.shipWeight = shipWeight;
	}

	public String getLineMessage() {
		return lineMessage;
	}

	public void setLineMessage(String lineMessage) {
		this.lineMessage = lineMessage;
	}

	public int getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(int organizationId) {
		this.organizationId = organizationId;
	}

	public Date getPickupByDate() {
		return pickupByDate;
	}

	public void setPickupByDate(Date pickupByDate) {
		this.pickupByDate = pickupByDate;
	}

	public String getCustomizationCode() {
		return customizationCode;
	}

	public void setCustomizationCode(String customizationCode) {
		this.customizationCode = customizationCode;
	}

	public String getCustomizationMessage() {
		return customizationMessage;
	}

	public void setCustomizationMessage(String customizationMessage) {
		this.customizationMessage = customizationMessage;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getCartonNumber() {
		return cartonNumber;
	}

	public void setCartonNumber(String cartonNumber) {
		this.cartonNumber = cartonNumber;
	}
}
