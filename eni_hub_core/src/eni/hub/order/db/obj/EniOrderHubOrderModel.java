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

public class EniOrderHubOrderModel {
	
	public EniOrderHubOrderModel() {
		initDAO();
	}

	private EniOrderHubOrderDAO daoObject = null;

	private void initDAO() {
		setDAO(new EniOrderHubOrderDAO());
	}

	public void setDAO(EniOrderHubOrderDAO argDAO) {
		daoObject = argDAO;
	}

	public EniOrderHubOrderDAO getDAO() {
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
		;
	}

	public String getOrderType() {
		return getDAO().getOrderType();
	}

	public void setOrderType(String argOrderType) {
		getDAO().setOrderType(argOrderType);
	}

	public String getStatusCode() {
		return getDAO().getStatusCode();
	}

	public void setStatusCode(String argStatusCode) {
		getDAO().setStatusCode(argStatusCode);
	}

	public Date getOrderDate() {
		return getDAO().getOrderDate();
	}

	public void setOrderDate(Date argOrderDate) {
		getDAO().setOrderDate(argOrderDate);
	}

	public String getOrderLocId() {
		return getDAO().getOrderLocId();
	}

	public void setOrderLocId(String argOrderLocId) {
		getDAO().setOrderLocId(argOrderLocId);
	}

	public BigDecimal getSubtotal() {
		return getDAO().getSubtotal();
	}

	public void setSubtotal(BigDecimal argSubtotal) {
		getDAO().setSubtotal(argSubtotal);
	}

	public BigDecimal getTaxAmount() {
		return getDAO().getTaxAmount();
	}

	public void setTaxAmount(BigDecimal argTaxAmount) {
		getDAO().setTaxAmount(argTaxAmount);
	}

	public BigDecimal getTotal() {
		return getDAO().getTotal();
	}

	public void setTotal(BigDecimal argTotal) {
		getDAO().setTotal(argTotal);
	}

	public BigDecimal getBalanceDue() {
		return getDAO().getBalanceDue();
	}

	public void setBalanceDue(BigDecimal argBalanceDue) {
		getDAO().setBalanceDue(argBalanceDue);
	}

	public String getSpecialInstructions() {
		return getDAO().getSpecialInstructions();
	}

	public void setSpecialInstructions(String argSpecialInstructions) {
		getDAO().setSpecialInstructions(argSpecialInstructions);
	}

	public String getRefNbr() {
		return getDAO().getRefNbr();
	}

	public void setRefNbr(String argRefNbr) {
		getDAO().setRefNbr(argRefNbr);
	}

	public String getShipVia() {
		return getDAO().getShipVia();
	}

	public void setShipVia(String argShipVia) {
		getDAO().setShipVia(argShipVia);
	}

	public String getShipViaDescription() {
		return getDAO().getShipViaDescription();
	}

	public void setShipViaDescription(String argShipViaDescription) {
		getDAO().setShipViaDescription(argShipViaDescription);
	}

	public BigDecimal getAdditionalFreightCharges() {
		return getDAO().getAdditionalFreightCharges();
	}

	public void setAdditionalFreightCharges(BigDecimal argAdditionalFreightCharges) {
		getDAO().setAdditionalFreightCharges(argAdditionalFreightCharges);
	}

	public BigDecimal getAdditionalCharges() {
		return getDAO().getAdditionalCharges();
	}

	public void setAdditionalCharges(BigDecimal argAdditionalCharges) {
		getDAO().setAdditionalCharges(argAdditionalCharges);
	}

	public boolean isShipCompleteFlag() {
		return getDAO().isShipCompleteFlag();
	}

	public void setShipCompleteFlag(boolean argShipCompleteFlag) {
		getDAO().setShipCompleteFlag(argShipCompleteFlag);
	}

	public BigDecimal getFreight() {
		return getDAO().getFreight();
	}

	public void setFreight(BigDecimal argFreight) {
		getDAO().setFreight(argFreight);
	}

	public BigDecimal getFreightTax() {
		return getDAO().getFreightTax();
	}

	public void setFreightTax(BigDecimal argFreightTax) {
		getDAO().setFreightTax(argFreightTax);
	}

	public String getOrderMessage() {
		return getDAO().getOrderMessage();
	}

	public void setOrderMessage(String argOrderMessage) {
		getDAO().setOrderMessage(argOrderMessage);
	}

	public String getGift() {
		return getDAO().getGift();
	}

	public void setGift(String argGift) {
		getDAO().setGift(argGift);
	}

	public String getGiftMessage() {
		return getDAO().getGiftMessage();
	}

	public void setGiftMessage(String argGiftMessage) {
		getDAO().setGiftMessage(argGiftMessage);
	}

	public boolean isUnderReviewFlag() {
		return getDAO().isUnderReviewFlag();
	}

	public void setUnderReviewFlag(boolean argUnderReviewFlag) {
		getDAO().setUnderReviewFlag(argUnderReviewFlag);
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
	
	public boolean isEcomSentFlag() {
		return getDAO().isEcomSentFlag();
	}

	public void setEcomSentFlag(boolean argEcomSentFlag) {
		getDAO().setEcomSentFlag(argEcomSentFlag);
	}
}
