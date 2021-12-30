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
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import eni.hub.order.util.EniOrderHubUtils;

public class EniOrderHubOrderDBA {
	private static Logger LOG = Logger.getLogger(EniOrderHubOrderDBA.class);

	private String INSERT_OBJECT = "INSERT INTO [dbo].[eni_hub_order] (organization_id, order_id, order_type, status_code, order_date, order_loc_id, subtotal, "
			+ "tax_amount, total, balance_due, special_instructions, ref_nbr, ship_via, ship_via_description, additional_freight_charges, additional_charges, ship_complete_flag, freight, freight_tax, order_message, gift, gift_message, under_review_flag, status_code_reason, status_code_reason_note, ecom_sent_flag, create_date, create_user_id, update_user_id, update_date, record_state) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	private String SELECT_OBJECT = "SELECT organization_id,order_id,order_loc_id, record_state,order_type,ref_nbr,balance_due, freight, freight_tax,ship_via_description,"
			+ "ship_via,special_instructions,total,tax_amount,subtotal,order_date,status_code,under_review_flag,additional_freight_charges,additional_charges,"
			+ "ship_complete_flag,freight_tax,order_message,gift, gift_message, ecom_sent_flag FROM [dbo].[eni_hub_order] ";

	private String UPDATE_STATUS_OBJECT = "UPDATE [dbo].[eni_hub_order] SET STATUS_CODE = ?";
	
	private String ORDER_UPDATE_CONDITION = " WHERE organization_id = ? AND order_id= ?";
	
	private String ORDER_SEARCH_CONDITION = " WHERE organization_id = ? AND order_id= ?";
	
	private String ORDER_FULLFILLMENT_CONDITION = " WHERE organization_id = ? AND record_state = 'NEW_ORDER' AND order_loc_id= ?";
	
	private String ORDER_FULLFILLMENT_INTRANSIT = " WHERE organization_id = ? AND record_state = 'NEW_ORDER' AND order_loc_id= ?";
	
	private String ORDER_STATUS_CONDITION = "WHERE organization_id = ? AND order_id = ? AND order_loc_id = ?";
	
	private int organizationId = 1;
	private String record_state ="NEW_ORDER";
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

	public String getInsertQuery() {
		return INSERT_OBJECT;
	}
    
	public String getUpdateStatusQuery() {
		return UPDATE_STATUS_OBJECT + ORDER_UPDATE_CONDITION;
	}
	public String getOrderSearchQuery() {
		return SELECT_OBJECT + ORDER_SEARCH_CONDITION;
	}
	
	public String getOrderFullfillmentQuery() {
		return SELECT_OBJECT + ORDER_FULLFILLMENT_CONDITION;
	}
	
	public String getOrderIntransitQuery() {
		return SELECT_OBJECT + ORDER_FULLFILLMENT_INTRANSIT;
	}
	
	public String getOrderStatusQuery() {
		return SELECT_OBJECT + ORDER_STATUS_CONDITION;
	}

	public EniOrderHubOrderDAO loadDAO() {
		EniOrderHubOrderDAO dao = new EniOrderHubOrderDAO();
		dao.setOrganizationId(this.organizationId);
		dao.setOrderId(this.orderId);
		dao.setOrderType(this.orderType);
		dao.setStatusCode(this.statusCode);
		dao.setOrderDate(this.orderDate);
		dao.setOrderLocId(this.orderLocId);
		dao.setSubtotal(this.subtotal);
		dao.setTaxAmount(this.taxAmount);
		dao.setTotal(this.total);
		dao.setBalanceDue(this.balanceDue);
		dao.setSpecialInstructions(this.specialInstructions);
		dao.setRefNbr(this.refNbr);
		dao.setShipVia(this.shipVia);
		dao.setShipViaDescription(this.shipViaDescription);
		dao.setAdditionalFreightCharges(this.additionalFreightCharges);
		dao.setAdditionalCharges(this.additionalCharges);
		dao.setShipCompleteFlag(this.shipCompleteFlag);
		dao.setFreight(this.freight);
		dao.setFreightTax(this.freightTax);
		dao.setOrderMessage(this.orderMessage);
		dao.setGift(this.gift);
		dao.setGiftMessage(this.giftMessage);
		dao.setUnderReviewFlag(this.underReviewFlag);
		dao.setStatusCodeReason(this.statusCodeReason);
		dao.setStatusCodeReasonNote(this.statusCodeReasonNote);
		dao.setEcomSentFlag(this.ecomSentFlag);

		return dao;
	}

	public void fillDAO(EniOrderHubOrderDAO argDAO) {
		this.organizationId = argDAO.getOrganizationId();
		this.orderId = argDAO.getOrderId();
		this.orderType = argDAO.getOrderType();
		this.statusCode = argDAO.getStatusCode();
		this.orderDate = argDAO.getOrderDate();
		this.orderLocId = argDAO.getOrderLocId();
		this.subtotal = argDAO.getSubtotal();
		this.taxAmount = argDAO.getTaxAmount();
		this.total = argDAO.getTotal();
		this.balanceDue = argDAO.getBalanceDue();
		this.specialInstructions = argDAO.getSpecialInstructions();
		this.refNbr = argDAO.getRefNbr();
		this.shipVia = argDAO.getShipVia();
		this.shipViaDescription = argDAO.getShipViaDescription();
		this.additionalFreightCharges = argDAO.getAdditionalFreightCharges();
		this.additionalCharges = argDAO.getAdditionalCharges();
		this.shipCompleteFlag = argDAO.isShipCompleteFlag();
		this.freight = argDAO.getFreight();
		this.freightTax = argDAO.getFreightTax();
		this.orderMessage = argDAO.getOrderMessage();
		this.gift = argDAO.getGift();
		this.giftMessage = argDAO.getGiftMessage();
		this.underReviewFlag = argDAO.isUnderReviewFlag();
		this.statusCodeReason = argDAO.getStatusCodeReason();
		this.statusCodeReasonNote = argDAO.getStatusCodeReasonNote();
		this.ecomSentFlag = argDAO.isEcomSentFlag();
	}
    public PreparedStatement initUpdateStatusParam(PreparedStatement argPreparedStatement) throws SQLException{
    	argPreparedStatement.setString(1,this.statusCode);
    	argPreparedStatement.setInt(2, this.organizationId);
		argPreparedStatement.setString(3, this.orderId);
		if(LOG.isDebugEnabled()) {
			LOG.debug(">>>>>> +[updateStatus]-> query " + getUpdateStatusQuery());
			LOG.debug(">>>>>> +[updateStatus]-> values: " + this.organizationId + "," + this.orderId + "," + this.statusCode );
		}
		return argPreparedStatement;
    }
	public PreparedStatement initParameterForInsert(PreparedStatement argPreparedStatement) throws SQLException {

		String createDate = EniOrderHubUtils.formatDateTime("yyyy-MM-dd HH:mm:ss", new Date());
		String createUserId = "ORDER_HUB";

		argPreparedStatement.setInt(1, this.organizationId);
		argPreparedStatement.setString(2, this.orderId);
		argPreparedStatement.setString(3, this.orderType);
		argPreparedStatement.setString(4, this.statusCode);
		String stOrderDate = EniOrderHubUtils.formatDateTime("yyyy-MM-dd HH:mm:ss", this.orderDate);
		argPreparedStatement.setString(5, stOrderDate);
		argPreparedStatement.setString(6, this.orderLocId);
		argPreparedStatement.setBigDecimal(7, this.subtotal);
		argPreparedStatement.setBigDecimal(8, this.taxAmount);
		argPreparedStatement.setBigDecimal(9, this.total);
		argPreparedStatement.setBigDecimal(10, this.balanceDue);
		argPreparedStatement.setString(11, this.specialInstructions);
		argPreparedStatement.setString(12, this.refNbr);
		argPreparedStatement.setString(13, this.shipVia);
		argPreparedStatement.setString(14, this.shipViaDescription);
		argPreparedStatement.setBigDecimal(15, this.additionalFreightCharges);
		argPreparedStatement.setBigDecimal(16, this.additionalCharges);
		argPreparedStatement.setBoolean(17, this.shipCompleteFlag);
		argPreparedStatement.setBigDecimal(18, this.freight);
		argPreparedStatement.setBigDecimal(19, this.freightTax);
		argPreparedStatement.setString(20, this.orderMessage);
		argPreparedStatement.setString(21, this.gift);
		argPreparedStatement.setString(22, this.giftMessage);
		argPreparedStatement.setBoolean(23, this.underReviewFlag);
		argPreparedStatement.setString(24, this.statusCodeReason);
		argPreparedStatement.setString(25, statusCodeReasonNote);
		argPreparedStatement.setBoolean(26, this.ecomSentFlag);
		argPreparedStatement.setString(27, createDate);
		argPreparedStatement.setString(28, createUserId);
		argPreparedStatement.setString(29, null);
		argPreparedStatement.setString(30, null);
		argPreparedStatement.setString(31, record_state);

		if (LOG.isDebugEnabled()) {
			LOG.debug(">>>>>> +[insertData]-> query " + getInsertQuery());
			LOG.debug(">>>>>> +[insertData]-> values: " + this.organizationId + "," + this.orderId + ","
					+ this.orderType + "," + this.statusCode + "," + stOrderDate + "," + this.orderLocId + ", "
					+ this.subtotal + "," + this.taxAmount + "," + this.total + "," + this.balanceDue + ","
					+ this.specialInstructions + "," + this.refNbr + "," + this.shipVia + this.shipViaDescription + ","
					+ this.additionalFreightCharges + "," + this.additionalCharges + "," + this.shipCompleteFlag + ","
					+ this.freight + "," + this.freightTax + "," + this.orderMessage + "," + this.gift + ","
					+ this.giftMessage + "," + this.underReviewFlag + "," + this.statusCodeReason + ","
					+ this.statusCodeReasonNote + "," + this.ecomSentFlag + "," + createDate + "," + createUserId + null + "," + null);
		}

		return argPreparedStatement;
	}

	public PreparedStatement initParameterForRetrieveOrderSearch(PreparedStatement argPreparedStatement,
			int argOrganizationId, String argOrderLocationId) throws SQLException {
		argPreparedStatement.setInt(1, argOrganizationId);
		argPreparedStatement.setString(2, argOrderLocationId);
		if (LOG.isDebugEnabled()) {
			LOG.debug(">>>>>> +[initParameterForRetrieveOrderSearch]-> query " + getOrderSearchQuery());
			LOG.debug(">>>>>> +[initParameterForRetrieveOrderSearch]-> values: " + argOrganizationId + ","
					+ argOrderLocationId);
		}
		return argPreparedStatement;
	}
	
	public PreparedStatement initParameterForRetrieveOrderFullfillment(PreparedStatement argPreparedStatement,
			int argOrganizationId, String argOrderLocationId) throws SQLException {
		argPreparedStatement.setInt(1, argOrganizationId);
		argPreparedStatement.setString(2, argOrderLocationId);
		if (LOG.isDebugEnabled()) {
			LOG.debug(">>>>>> +[initParameterForRetrieveOrderFullfillment]-> query " + getOrderFullfillmentQuery());
			LOG.debug(">>>>>> +[initParameterForRetrieveOrderFullfillment]-> values: " + argOrganizationId + ","
					+ argOrderLocationId);
		}
		return argPreparedStatement;
	}
	
	public PreparedStatement initParameterForRetrieveOrderIntransit(PreparedStatement argPreparedStatement,
			int argOrganizationId, String argOrderLocationId) throws SQLException {
		argPreparedStatement.setInt(1, argOrganizationId);
		argPreparedStatement.setString(2, argOrderLocationId);
		if (LOG.isDebugEnabled()) {
			LOG.debug(">>>>>> +[initParameterForRetrieveOrderIntransit]-> query " + getOrderIntransitQuery());
			LOG.debug(">>>>>> +[initParameterForRetrieveOrderIntransit]-> values: " + argOrganizationId + ","
					+ argOrderLocationId);
		}
		return argPreparedStatement;
	}
	
	public PreparedStatement initParameterForRetrieveOrderStatus(PreparedStatement argPreparedStatement,
			int argOrganizationId, String argOrderId, String argOrderLocationId) throws SQLException {
		argPreparedStatement.setInt(1, argOrganizationId);
		argPreparedStatement.setString(2, argOrderId);
		argPreparedStatement.setString(3, argOrderLocationId);
		if (LOG.isDebugEnabled()) {
			LOG.debug(">>>>>> +[initParameterForRetrieveOrderStatus]-> query " + getOrderStatusQuery());
			LOG.debug(">>>>>> +[initParameterForRetrieveOrderStatus]-> values: " + argOrganizationId + "," + argOrderId + "," 
					+ argOrderLocationId);
		}
		return argPreparedStatement;
	}
}
