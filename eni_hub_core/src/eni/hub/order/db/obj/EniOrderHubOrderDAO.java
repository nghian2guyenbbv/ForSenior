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

public class EniOrderHubOrderDAO {
	private int organizationId = 1;
	private String orderId = null;
	private String orderType = null;
	private String statusCode = null;
	private Date orderDate = null;
	private String orderLocId = null;
	private BigDecimal subtotal = null;
	private BigDecimal taxAmount = null;
	private BigDecimal total = null;
	private BigDecimal balanceDue = null;
	private String specialInstructions = null;
	private String refNbr = null;
	private String shipVia = null;
	private String shipViaDescription = null;
	private BigDecimal additionalFreightCharges = null;
	private BigDecimal additionalCharges = null;
	private boolean shipCompleteFlag = false;
	private BigDecimal freight = null;
	private BigDecimal freightTax = null;
	private String orderMessage = null;
	private String gift = null;
	private String giftMessage = null;
	private boolean underReviewFlag = false;
	private String statusCodeReason = null;
	private String statusCodeReasonNote = null;
	private boolean ecomSentFlag = false;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public String getOrderLocId() {
		return orderLocId;
	}

	public void setOrderLocId(String orderLocId) {
		this.orderLocId = orderLocId;
	}

	public BigDecimal getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
	}

	public BigDecimal getTaxAmount() {
		return taxAmount;
	}

	public void setTaxAmount(BigDecimal taxAmount) {
		this.taxAmount = taxAmount;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public BigDecimal getBalanceDue() {
		return balanceDue;
	}

	public void setBalanceDue(BigDecimal balanceDue) {
		this.balanceDue = balanceDue;
	}

	public String getSpecialInstructions() {
		return specialInstructions;
	}

	public void setSpecialInstructions(String specialInstructions) {
		this.specialInstructions = specialInstructions;
	}

	public String getRefNbr() {
		return refNbr;
	}

	public void setRefNbr(String refNbr) {
		this.refNbr = refNbr;
	}

	public String getShipVia() {
		return shipVia;
	}

	public void setShipVia(String shipVia) {
		this.shipVia = shipVia;
	}

	public String getShipViaDescription() {
		return shipViaDescription;
	}

	public void setShipViaDescription(String shipViaDescription) {
		this.shipViaDescription = shipViaDescription;
	}

	public BigDecimal getAdditionalFreightCharges() {
		return additionalFreightCharges;
	}

	public void setAdditionalFreightCharges(BigDecimal additionalFreightCharges) {
		this.additionalFreightCharges = additionalFreightCharges;
	}

	public BigDecimal getAdditionalCharges() {
		return additionalCharges;
	}

	public void setAdditionalCharges(BigDecimal additionalCharges) {
		this.additionalCharges = additionalCharges;
	}

	public boolean isShipCompleteFlag() {
		return shipCompleteFlag;
	}

	public void setShipCompleteFlag(boolean shipCompleteFlag) {
		this.shipCompleteFlag = shipCompleteFlag;
	}

	public BigDecimal getFreightTax() {
		return freightTax;
	}

	public void setFreightTax(BigDecimal freightTax) {
		this.freightTax = freightTax;
	}

	public String getOrderMessage() {
		return orderMessage;
	}

	public void setOrderMessage(String orderMessage) {
		this.orderMessage = orderMessage;
	}

	public String getGiftMessage() {
		return giftMessage;
	}

	public void setGiftMessage(String giftMessage) {
		this.giftMessage = giftMessage;
	}

	public boolean isUnderReviewFlag() {
		return underReviewFlag;
	}

	public void setUnderReviewFlag(boolean underReviewFlag) {
		this.underReviewFlag = underReviewFlag;
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

	public int getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(int organizationId) {
		this.organizationId = organizationId;
	}

	public BigDecimal getFreight() {
		return freight;
	}

	public void setFreight(BigDecimal freight) {
		this.freight = freight;
	}

	public String getGift() {
		return gift;
	}

	public void setGift(String gift) {
		this.gift = gift;
	}

	public boolean isEcomSentFlag() {
		return ecomSentFlag;
	}

	public void setEcomSentFlag(boolean ecomSentFlag) {
		this.ecomSentFlag = ecomSentFlag;
	}
}
