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
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

import org.apache.log4j.Logger;

import eni.hub.order.util.EniOrderHubUtils;

public class EniOrderHubOrderLineDBA {
	private static Logger LOG = Logger.getLogger(EniOrderHubOrderLineDBA.class);

	/*
	 * private String INSERT_OBJECT =
	 * "INSERT INTO [dbo].[eni_hub_order_line] (organization_id,order_id,line_no,item_upc_code,item_ean_code,external_order_id,item_id,quantity,fulfillment_type,status_code,unit_price,extended_price,tax_amount,special_instructions,tracking_nbr,void_flag,actual_ship_method,drop_ship_flag,status_code_reason,"
	 * +
	 * "status_code_reason_note,extended_freight,customization_charge,gift_wrap_flag,ship_alone_flag,ship_weight,line_message,pickup_by_date,customization_code, customization_message, carton_number, create_user_id,create_date,update_user_id,update_date,record_state) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"
	 * ;
	 */
	private String INSERT_OBJECT = "INSERT INTO [dbo].[eni_hub_order_line] (organization_id,order_id,line_no,item_upc_code,item_ean_code,external_order_id,item_id,quantity,fulfillment_type,status_code,unit_price,extended_price,tax_amount,special_instructions,tracking_nbr,void_flag,actual_ship_method,drop_ship_flag,status_code_reason,"
			+ "status_code_reason_note,extended_freight,customization_charge,gift_wrap_flag,ship_alone_flag,ship_weight,line_message,pickup_by_date, create_user_id,create_date,update_user_id,record_state) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	/*
	 * public static final String SELECT_OBJECT =
	 * "SELECT  organization_id,order_id,line_no,item_upc_code,item_ean_code,external_order_id,item_id,quantity,fulfillment_type,status_code,unit_price,extended_price,tax_amount,special_instructions,tracking_nbr,void_flag,actual_ship_method,drop_ship_flag,status_code_reason, "
	 * +
	 * " status_code_reason_note,extended_freight,customization_charge,gift_wrap_flag,ship_alone_flag,ship_weight,line_message,pickup_by_date,customization_code, customization_message,carton_number,create_user_id,create_date,update_user_id,update_date,record_state	FROM [dbo].[eni_hub_order_line] where organization_id = ? AND order_id = ?"
	 * ;
	 */
	public static final String SELECT_OBJECT = "SELECT  organization_id,order_id,line_no,item_upc_code,item_ean_code,external_order_id,item_id,quantity,fulfillment_type,status_code,unit_price,extended_price,tax_amount,special_instructions,tracking_nbr,void_flag,actual_ship_method,drop_ship_flag,status_code_reason,  status_code_reason_note,extended_freight,customization_charge,gift_wrap_flag,ship_alone_flag,ship_weight,line_message,pickup_by_date,create_user_id,create_date,update_user_id,update_date,record_state	FROM [dbo].[eni_hub_order_line] where organization_id = ? AND order_id = ?";
	
	private static final String ORDER_UPDATE_CONDITION ="WHERE organization_id = ? AND  order_id = ?";
	
	private static final String UPDATE_STATUS_OBJECT = "UPDATE [dbo].[eni_hub_order_line] SET status_code = ? ";
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
	private String cartonNumber  = null;
	private Date updateDate = null;

	public String getInsertQuery() {
		return INSERT_OBJECT;
	}

	public String getSelectQuery() {
		return SELECT_OBJECT;
	}
    public String getOrderLineStatusUpdateQuery() {
    	return UPDATE_STATUS_OBJECT + ORDER_UPDATE_CONDITION;
    }
	public EniOrderHubOrderLineDAO loadDAO() {
		EniOrderHubOrderLineDAO dao = new EniOrderHubOrderLineDAO();
		dao.setOrganizationId(this.organizationId);
		dao.setOrderId(this.orderId);
		dao.setLineNo(this.lineNo);
		dao.setItemUpcCode(this.itemUpcCode);
		dao.setItemEanCode(this.itemEanCode);
		dao.setExternalOrderId(this.externalOrderId);
		dao.setItemId(this.itemId);
		dao.setQuantity(this.quantity);
		dao.setFulfillmentType(this.fulfillmentType);
		dao.setStatusCode(this.statusCode);
		dao.setUnitPrice(this.unitPrice);
		dao.setExtendedPrice(this.extendedPrice);
		dao.setTaxAmount(this.taxAmount);
		dao.setSpecialInstructions(this.specialInstructions);
		dao.setTrackingNbr(this.trackingNbr);
		dao.setVoidFlag(this.voidFlag);
		dao.setActualShipMethod(this.actualShipMethod);
		dao.setDropShipFlag(this.dropShipFlag);
		dao.setStatusCodeReason(this.statusCodeReason);
		dao.setStatusCodeReasonNote(this.statusCodeReasonNote);
		dao.setExtendedFreight(this.extendedFreight);
		dao.setCustomizationCharge(this.customizationCharge);
		dao.setGiftWrapFlag(this.giftWrapFlag);
		dao.setShipAloneFlag(this.shipAloneFlag);
		dao.setShipWeight(this.shipWeight);
		dao.setLineMessage(this.lineMessage);
		dao.setPickupByDate(this.pickupByDate);
		dao.setCustomizationCode(this.customizationCode);
		dao.setCustomizationMessage(this.customizationMessage);
		dao.setCartonNumber(this.cartonNumber);
		dao.setUpdateDate(this.updateDate);

		return dao;
	}

	public void fillDAO(EniOrderHubOrderLineDAO argDAO) {
		this.organizationId = argDAO.getOrganizationId();
		this.orderId = argDAO.getOrderId();
		this.lineNo = argDAO.getLineNo();
		this.itemUpcCode = argDAO.getItemUpcCode();
		this.itemEanCode = argDAO.getItemEanCode();
		this.externalOrderId = argDAO.getExternalOrderId();
		this.itemId = argDAO.getItemId();
		this.quantity = argDAO.getQuantity();
		this.fulfillmentType = argDAO.getFulfillmentType();
		this.statusCode = argDAO.getStatusCode();
		this.unitPrice = argDAO.getUnitPrice();
		this.extendedPrice = argDAO.getExtendedPrice();
		this.taxAmount = argDAO.getTaxAmount();
		this.specialInstructions = argDAO.getSpecialInstructions();
		this.trackingNbr = argDAO.getTrackingNbr();
		this.voidFlag = argDAO.isVoidFlag();
		this.actualShipMethod = argDAO.getActualShipMethod();
		this.dropShipFlag = argDAO.isDropShipFlag();
		this.statusCodeReason = argDAO.getStatusCodeReason();
		this.statusCodeReasonNote = argDAO.getStatusCodeReasonNote();
		this.extendedFreight = argDAO.getExtendedFreight();
		this.customizationCharge = argDAO.getCustomizationCharge();
		this.giftWrapFlag = argDAO.isGiftWrapFlag();
		this.shipAloneFlag = argDAO.isShipAloneFlag();
		this.shipWeight = argDAO.getShipWeight();
		this.lineMessage = argDAO.getLineMessage();
		this.pickupByDate = argDAO.getPickupByDate();
		this.customizationCode = argDAO.getCustomizationCode();
		this.customizationMessage = argDAO.getCustomizationMessage();
		this.cartonNumber = argDAO.getCartonNumber();
		this.updateDate = argDAO.getUpdateDate();
	}
    public PreparedStatement initParameterForUpdateStatus(PreparedStatement argPreparedStatement) throws SQLException{
    	argPreparedStatement.setString(1, this.statusCode);
    	argPreparedStatement.setInt(2, this.organizationId);
		argPreparedStatement.setString(3, this.orderId);
		if (LOG.isDebugEnabled()) {
			LOG.debug(">>>>>> +[updateOrderLineStatus]-> query " + getOrderLineStatusUpdateQuery());
			LOG.debug(">>>>>> +[updateOrderLineStatus]-> values: " + this.organizationId + "," + this.orderId + "," + this.statusCode);
		};
		return argPreparedStatement; 
    }
	
	public PreparedStatement initParameterForInsert(PreparedStatement argPreparedStatement) throws SQLException {

		String createDate = EniOrderHubUtils.formatDateTime("yyyy-MM-dd HH:mm:ss", new Date());
		String createUserId = "ORDER_HUB";
		String recordState = "NEW_ORDER";

		argPreparedStatement.setInt(1, this.organizationId);
		argPreparedStatement.setString(2, this.orderId);
		argPreparedStatement.setInt(3, this.lineNo);
		argPreparedStatement.setString(4, this.itemUpcCode);
		argPreparedStatement.setString(5, this.itemEanCode);
		argPreparedStatement.setString(6, this.externalOrderId);
		argPreparedStatement.setString(7, this.itemId);
		argPreparedStatement.setBigDecimal(8, this.quantity);
		argPreparedStatement.setString(9, this.fulfillmentType);
		argPreparedStatement.setString(10, this.statusCode);
		argPreparedStatement.setBigDecimal(11, this.unitPrice);
		argPreparedStatement.setBigDecimal(12, this.extendedPrice);
		argPreparedStatement.setBigDecimal(13, this.taxAmount);
		argPreparedStatement.setString(14, this.specialInstructions);
		argPreparedStatement.setString(15, this.trackingNbr);
		argPreparedStatement.setBoolean(16, this.voidFlag);
		argPreparedStatement.setString(17, this.actualShipMethod);
		argPreparedStatement.setBoolean(18, this.dropShipFlag);
		argPreparedStatement.setString(19, this.statusCodeReason);
		argPreparedStatement.setString(20, this.statusCodeReasonNote);
		argPreparedStatement.setBigDecimal(21, this.extendedFreight);
		argPreparedStatement.setBigDecimal(22, this.customizationCharge);
		argPreparedStatement.setBoolean(23, this.giftWrapFlag);
		argPreparedStatement.setBoolean(24, this.shipAloneFlag);
		argPreparedStatement.setBigDecimal(25, this.shipWeight);
		argPreparedStatement.setString(26, this.lineMessage);
		String stPickupByDate = EniOrderHubUtils.formatDateTime("yyyy-MM-dd HH:mm:ss", this.pickupByDate);
		argPreparedStatement.setString(27, stPickupByDate);
		/*
		 * argPreparedStatement.setString(28, this.customizationCode);
		 * argPreparedStatement.setString(29, this.customizationMessage);
		 * argPreparedStatement.setString(30, this.cartonNumber);
		 */
		argPreparedStatement.setString(28, createUserId);
		argPreparedStatement.setString(29, createDate);
		argPreparedStatement.setString(30, null);
		argPreparedStatement.setString(31, recordState);

		if (LOG.isDebugEnabled()) {
			LOG.debug(">>>>>> +[insertData]-> query " + getInsertQuery());
			LOG.debug(">>>>>> +[insertData]-> values: " + this.organizationId + "," + this.orderId + "," + this.lineNo
					+ "," + this.itemUpcCode + "," + this.itemEanCode + "," + this.externalOrderId + ", " + this.itemId
					+ "," + this.quantity + "," + this.specialInstructions + "," + this.trackingNbr + ","
					+ this.voidFlag + "," + this.actualShipMethod + "," + this.dropShipFlag + this.statusCodeReason
					+ "," + this.statusCodeReasonNote + "," + this.extendedFreight + "," + this.customizationCharge
					+ "," + this.giftWrapFlag + this.shipAloneFlag + "," + this.shipWeight + "," + this.lineMessage
					+ "," + stPickupByDate + "," + createDate + this.customizationCode + "," + this.customizationMessage
					+ "," + cartonNumber + "," + createUserId + null + "," + recordState);
		}

		return argPreparedStatement;
	}

	public PreparedStatement initParameterForRetrieveOrderLineData(PreparedStatement argPreparedStatement,
			int argOrganizationId, String argOrderId) throws SQLException {
		argPreparedStatement.setInt(1, argOrganizationId);
		argPreparedStatement.setString(2, argOrderId);
		if (LOG.isDebugEnabled()) {
			LOG.debug(">>>>>> +[initParameterForRetrieveOrderLineData]-> query " + getSelectQuery());
			LOG.debug(">>>>>> +[initParameterForRetrieveOrderLineData]-> values: " + argOrganizationId + ","
					+ argOrderId);
		}
		return argPreparedStatement;
	}
}
