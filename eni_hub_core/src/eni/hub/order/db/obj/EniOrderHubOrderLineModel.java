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
 * BZ47094      201021          [Intergration] Xstore Integrated eCommerce Orders                                                                   
 *===================================================================
 */
package eni.hub.order.db.obj;

import java.math.BigDecimal;
import java.util.Date;

public class EniOrderHubOrderLineModel {
	public EniOrderHubOrderLineModel() {
		initDAO();
	}

	private EniOrderHubOrderLineDAO daoObject = null;

	private void initDAO() {
		setDAO(new EniOrderHubOrderLineDAO());
	}

	public void setDAO(EniOrderHubOrderLineDAO argDAO) {
		daoObject = argDAO;
	}

	public EniOrderHubOrderLineDAO getDAO() {
		return daoObject;
	}

	public int getOrganizationId() {
		return getDAO().getOrganizationId();
	}

	public void setOrganizationId(int argOrganizationId) {
		getDAO().setOrganizationId(argOrganizationId);
	}

	public String getOrderId() {
		return getDAO().getOrderId();
	}

	public void setOrderId(String argOrderId) {
		getDAO().setOrderId(argOrderId);
	}

	public int getLineNo() {
		return getDAO().getLineNo();
	}

	public void setLineNo(int argLineNo) {
		getDAO().setLineNo(argLineNo);
	}

	public String getItemUpcCode() {
		return getDAO().getItemUpcCode();
	}

	public void setItemUpcCode(String argItemUpcCode) {
		getDAO().setItemUpcCode(argItemUpcCode);
	}

	public String getItemEanCode() {
		return getDAO().getItemEanCode();
	}

	public void setItemEanCode(String argItemEanCode) {
		getDAO().setItemEanCode(argItemEanCode);
	}

	public String getExternalOrderId() {
		return getDAO().getExternalOrderId();
	}

	public void setExternalOrderId(String argExternalOrderId) {
		getDAO().setExternalOrderId(argExternalOrderId);
	}

	public String getItemId() {
		return getDAO().getItemId();
	}

	public void setItemId(String argItemId) {
		getDAO().setItemId(argItemId);
	}

	public BigDecimal getQuantity() {
		return getDAO().getQuantity();
	}

	public void setQuantity(BigDecimal argQuantity) {
		getDAO().setQuantity(argQuantity);
	}

	public String getFulfillmentType() {
		return getDAO().getFulfillmentType();
	}

	public void setFulfillmentType(String argFulfillmentType) {
		getDAO().setFulfillmentType(argFulfillmentType);
	}

	public String getStatusCode() {
		return getDAO().getStatusCode();
	}

	public void setStatusCode(String argStatusCode) {
		getDAO().setStatusCode(argStatusCode);
	}

	public BigDecimal getUnitPrice() {
		return getDAO().getUnitPrice();
	}

	public void setUnitPrice(BigDecimal argUnitPrice) {
		getDAO().setUnitPrice(argUnitPrice);
	}

	public BigDecimal getExtendedPrice() {
		return getDAO().getExtendedPrice();
	}

	public void setExtendedPrice(BigDecimal argExtendedPrice) {
		getDAO().setExtendedPrice(argExtendedPrice);
	}

	public BigDecimal getTaxAmount() {
		return getDAO().getTaxAmount();
	}

	public void setTaxAmount(BigDecimal argTaxAmount) {
		getDAO().setTaxAmount(argTaxAmount);
	}

	public String getSpecialInstructions() {
		return getDAO().getSpecialInstructions();
	}

	public void setSpecialInstructions(String argSpecialInstructions) {
		getDAO().setSpecialInstructions(argSpecialInstructions);
	}

	public String getTrackingNbr() {
		return getDAO().getTrackingNbr();
	}

	public void setTrackingNbr(String argTrackingNbr) {
		getDAO().setTrackingNbr(argTrackingNbr);
	}

	public boolean isVoidFlag() {
		return getDAO().isVoidFlag();
	}

	public void setVoidFlag(boolean argVoidFlag) {
		getDAO().setVoidFlag(argVoidFlag);
	}

	public String getActualShipMethod() {
		return getDAO().getActualShipMethod();
	}

	public void setActualShipMethod(String argActualShipMethod) {
		getDAO().setActualShipMethod(argActualShipMethod);
	}

	public boolean isDropShipFlag() {
		return getDAO().isDropShipFlag();
	}

	public void setDropShipFlag(boolean argDropShipFlag) {
		getDAO().setDropShipFlag(argDropShipFlag);
	}

	public String getStatusCodeReason() {
		return getDAO().getStatusCodeReason();
	}

	public void setStatusCodeReason(String argStatusCodeReason) {
		getDAO().setStatusCodeReason(argStatusCodeReason);
	}

	public String getStatusCodeReasonNote() {
		return getDAO().getStatusCodeReasonNote();
	}

	public void setStatusCodeReasonNote(String argStatusCodeReasonNote) {
		getDAO().setStatusCodeReasonNote(argStatusCodeReasonNote);
	}

	public BigDecimal getExtendedFreight() {
		return getDAO().getExtendedFreight();
	}

	public void setExtendedFreight(BigDecimal argExtendedFreight) {
		getDAO().setExtendedFreight(argExtendedFreight);
	}

	public BigDecimal getCustomizationCharge() {
		return getDAO().getCustomizationCharge();
	}

	public void setCustomizationCharge(BigDecimal argCustomizationCharge) {
		getDAO().setCustomizationCharge(argCustomizationCharge);
	}

	public boolean isGiftWrapFlag() {
		return getDAO().isGiftWrapFlag();
	}

	public void setGiftWrapFlag(boolean argGiftWrapFlag) {
		getDAO().setGiftWrapFlag(argGiftWrapFlag);
	}

	public boolean isShipAloneFlag() {
		return getDAO().isShipAloneFlag();
	}

	public void setShipAloneFlag(boolean argShipAloneFlag) {
		getDAO().setShipAloneFlag(argShipAloneFlag);
	}

	public BigDecimal getShipWeight() {
		return getDAO().getShipWeight();
	}

	public void setShipWeight(BigDecimal argShipWeight) {
		getDAO().setShipWeight(argShipWeight);
	}

	public String getLineMessage() {
		return getDAO().getLineMessage();
	}

	public void setLineMessage(String argLineMessage) {
		getDAO().setLineMessage(argLineMessage);
	}

	public Date getPickupByDate() {
		return getDAO().getPickupByDate();
	}

	public void setPickupByDate(Date argPickupByDate) {
		getDAO().setPickupByDate(argPickupByDate);
	}

	public String getCustomizationCode() {
		return getDAO().getCustomizationCode();
	}

	public void setCustomizationCode(String argCustomizationCode) {
		getDAO().setCustomizationCode(argCustomizationCode);
	}

	public String getCustomizationMessage() {
		return getDAO().getCustomizationMessage();
	}

	public void setCustomizationMessage(String argCustomizationMessage) {
		getDAO().setCustomizationMessage(argCustomizationMessage);
	}

	public Date getUpdateDate() {
		return getDAO().getUpdateDate();
	}

	public void setUpdateDate(Date argUpdateDate) {
		getDAO().setUpdateDate(argUpdateDate);
	}
	
	public String getCartonNumber() {
		return getDAO().getCartonNumber();
	}

	public void setCartonNumber(String argCartonNumber) {
		getDAO().setCartonNumber(argCartonNumber);
	}
}
