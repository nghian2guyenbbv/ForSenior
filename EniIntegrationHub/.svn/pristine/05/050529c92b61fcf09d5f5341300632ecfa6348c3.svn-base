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

package eni.hub.services.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import javax.jws.HandlerChain;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.microsretail.locate.AddressTypeBean;
import com.microsretail.locate.CustomerTypeBean;
import com.microsretail.locate.DSSalesOrderCustomizationTypeBean;
import com.microsretail.locate.DSSalesOrderCustomizationsTypeBean;
import com.microsretail.locate.FulfillmentResponseMessageBodyTypeBean;
import com.microsretail.locate.FulfillmentResponseMessageItemTypeBean;
import com.microsretail.locate.FulfillmentResponseMessageItemsTypeBean;
import com.microsretail.locate.FulfillmentResponseMessageOrderTypeBean;
import com.microsretail.locate.FulfillmentResponseMessageOrdersTypeBean;
import com.microsretail.locate.FulfillmentResponseMessageStoreLocationTypeBean;
import com.microsretail.locate.FulfillmentResponseMessageTypeBean;
import com.microsretail.locate.IntransitResponseMessageBodyTypeBean;
import com.microsretail.locate.IntransitResponseMessageItemTypeBean;
import com.microsretail.locate.IntransitResponseMessageItemsTypeBean;
import com.microsretail.locate.IntransitResponseMessageOrderTypeBean;
import com.microsretail.locate.IntransitResponseMessageOrdersTypeBean;
import com.microsretail.locate.IntransitResponseMessageStoreLocationTypeBean;
import com.microsretail.locate.IntransitResponseMessageTypeBean;
import com.microsretail.locate.InvalidSoapRequestException;
import com.microsretail.locate.Locate;
import com.microsretail.locate.LocateIemsRequestMessageBodyTypeBean;
import com.microsretail.locate.LocateItemsRequestMessageHeaderTypeBean;
import com.microsretail.locate.LocateItemsResponseMessageTypeBean;
import com.microsretail.locate.MessageHeaderTypeBean;
import com.microsretail.locate.NameTypeBean;
import com.microsretail.locate.SoldToCustomerTypeBean;
import com.microsretail.locate.StatusInquiryResponseMessageBodyTypeBean;
import com.microsretail.locate.StatusInquiryResponseMessageItemTypeBean;
import com.microsretail.locate.StatusInquiryResponseMessageItemsTypeBean;
import com.microsretail.locate.StatusInquiryResponseMessageOrderTypeBean;
import com.microsretail.locate.StatusInquiryResponseMessageOrdersTypeBean;
import com.microsretail.locate.StatusResponseMessageTypeBean;
import com.microsretail.locate.StatusUpdateRequestMessageItemTypeBean;
import com.microsretail.locate.StatusUpdateRequestMessageItemsTypeBean;
import com.microsretail.locate.StatusUpdateResponseMessageBodyTypeBean;
import com.microsretail.locate.StatusUpdateResponseMessageResponseTypeBean;
import com.microsretail.locate.StatusUpdateResponseMessageTypeBean;
import com.microsretail.locate.TaxTypeBean;
import com.microsretail.locate.TaxesTypeBean;
import com.microsretail.locate.TransactionTenderTypeBean;
import com.microsretail.locate.TransactionTendersTypeBean;

import eni.hub.order.db.EniOrderHubDBHelper;
import eni.hub.order.db.obj.EniOrderHubCustomerModel;
import eni.hub.order.db.obj.EniOrderHubOrderLineModel;
import eni.hub.order.db.obj.EniOrderHubOrderModel;
import eni.hub.order.db.obj.EniOrderHubRetailLocationModel;
import eni.hub.order.db.obj.EniOrderHubTenderModel;
import eni.hub.order.util.EniOrderHubUtils;

/**
 * Order Service Implementation Please modify this class to meet your needs This
 * class are implementing for 3 methods
 */

@javax.jws.WebService(serviceName = "LocateService", portName = "LocatePort", targetNamespace = "http://microsretail.com/Locate", wsdlLocation = "file:LocateServices.wsdl", endpointInterface = "com.microsretail.locate.Locate")
@HandlerChain(file = "handler-chain.xml")
public class EniOrderHubServiceImpl extends EniOrderHubServiceUtils implements Locate {

	private static final Logger LOG = Logger.getLogger(EniOrderHubServiceImpl.class.getName());

	private static final String ACTION_RESPONSE_OK = "OK";
	private static String ACTION_RESPONSE_SUCCESS = "SUCCESS - No Orders for Location ";

	private static final int STATUS_UPDATE_RESPONSE_SUCCESS_CODE = 0;
	private static final int STATUS_UPDATE_RESPONSE_FAIL_CODE = 1;

	private static final String STATUS_UPDATE_RESPONSE_SUCCESS_DESC = "Status updated successfully.";
	private static final String STATUS_UPDATE_RESPONSE_FAIL_DESC = "Status update failure.";
    
    private static final int    ORDER_UPDATE_RESPONSE_SUCCESS_CODE = 0;
	private static final int    ORDER_UPDATE_RESPONSE_FAIL_CODE    = 1;

	private static final String ORDER_UPDATE_RESPONSE_SUCCESS_DESC = "Order updated successfully.";
	private static final String ORDER_UPDATE_RESPONSE_FAIL_DESC    = "Order update failure.";

	private static final String ACTION_TYPE = "INFO";
	private static final String SOURCE = "CMFL";
	private static final String DESTINATION = "XSTORE";
	private static final String SYSTEM = "System (XSTORE)";

	private static final String ORIGINAL_LOCATION_CD = "999999";
	private static final String ORIGINAL_LOCATION_DESC = "Originating Location";

	private static final String CURRENCY = "USD";

	private static final String ORDER_LINE_POLLED_STATUS = "polled";
	private static final String ORDER_LINE_ACCEPTED_STATUS = "accepted";
	private static final String ORDER_LINE_PICKED_STATUS = "picked";
	private static final String ORDER_LINE_FULFILLED_STATUS = "fulfilled";
	private static final String ORDER_LINE_REJECTED_STATUS = "rejected";
	private static final String ORDER_LINE_CANCELLED_STATUS = "cancelled";
	private static final String ORDER_LINE_UNFULFILLABLE_STATUS = "unfulfillable";

	private static final String ORDER_OPEN_STATUS = "open";
	private static final String ORDER_READY_FOR_PICKUP_STATUS = "ready_for_pick_up";
	private static final String ORDER_COMPLETE_STATUS = "complete";
	private static final String ORDER_CANCELlED_STATUS = "cancelled";


	/*
	 * (non-Javadoc)
	 * 
	 * @see com.microsretail.locate.Locate#submitOrder(com.microsretail.locate.
	 * SubmitOrderRequestMessageTypeBean orderRequestMessage)*
	 */
	public com.microsretail.locate.SubmitOrderResponseMessageTypeBean submitOrder(
			com.microsretail.locate.SubmitOrderRequestMessageTypeBean orderRequestMessage) {
		LOG.info("Executing operation submitOrder");
		try {
			com.microsretail.locate.SubmitOrderResponseMessageTypeBean _return = null;
			return _return;
		} catch (java.lang.Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.microsretail.locate.Locate#availabilityUpdate(com.microsretail.locate
	 * .AvailabilityUpdateRequestMessageTypeBean availabilityUpdateRequestMessage)*
	 */
	public com.microsretail.locate.ProductUpdateResponseMessageTypeBean availabilityUpdate(
			com.microsretail.locate.AvailabilityUpdateRequestMessageTypeBean availabilityUpdateRequestMessage) {
		LOG.info("Executing operation availabilityUpdate");
		try {
			com.microsretail.locate.ProductUpdateResponseMessageTypeBean _return = null;
			return _return;
		} catch (java.lang.Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.microsretail.locate.Locate#orderSearch(com.microsretail.locate.
	 * OrderSearchRequestMessageTypeBean orderSearchRequestMessage)*
	 */
	public com.microsretail.locate.StatusResponseMessageTypeBean orderSearch(
			com.microsretail.locate.OrderSearchRequestMessageTypeBean orderSearchRequestMessage) {
		LOG.info("Executing operation orderSearch");
		LOG.info(">> +[ordersearch] START");
		StatusResponseMessageTypeBean _return = new StatusResponseMessageTypeBean();
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US);
			format.setTimeZone(TimeZone.getTimeZone("UTC"));
			// Header bean for generating message header
			MessageHeaderTypeBean messageHeaderTypeBean = new MessageHeaderTypeBean();
			messageHeaderTypeBean.setXactionType(ACTION_TYPE);
			messageHeaderTypeBean.setDatetime(format.format(new Date()));
			messageHeaderTypeBean.setVersion(orderSearchRequestMessage.getMessageHeader().getVersion());
			messageHeaderTypeBean.setSource(SOURCE);
			messageHeaderTypeBean.setDestination(DESTINATION);
			// Body bean for generating message body
			StatusInquiryResponseMessageBodyTypeBean statusResponseMessageBodyTypeBean = new StatusInquiryResponseMessageBodyTypeBean();
			// Order Bean Type that it contains order list
			StatusInquiryResponseMessageOrdersTypeBean statusInquiryResponseMessageOrdersTypeBean = new StatusInquiryResponseMessageOrdersTypeBean();
			StatusInquiryResponseMessageOrderTypeBean statusInquiryResponseMessageOrderTypeBean = null;
			// Item type
			StatusInquiryResponseMessageItemsTypeBean statusInquiryResponseMessageItemsTypeBean = new StatusInquiryResponseMessageItemsTypeBean();
			StatusInquiryResponseMessageItemTypeBean statusInquiryResponseMessageItemTypeBean = null;
			// get Location code
			String locationCode = orderSearchRequestMessage.getMessageBody().getLookupData().getStoreLocation()
					.getLocationCd();
			if (locationCode == null || "".contentEquals(locationCode)) {
				messageHeaderTypeBean.setXactionResponse(ACTION_RESPONSE_SUCCESS);
			} else {
				messageHeaderTypeBean.setXactionResponse(ACTION_RESPONSE_OK);
			}
			// Step1 - Open connection to HUB database
			LOG.info(">>> START STEP1-OPEN CONNECTION TO DATABASE");
			Connection conn = EniOrderHubDBHelper.getInstance().openConnection();
			LOG.info("<<< END STEP1-OPEN CONNECTION TO DATABASE");

			// Step 2 - Retrieve order status data from Hub Order Tables
			// for building message response and return to Xstore
			LOG.info(">>> START STEP2-RETRIEVE ORDER STATUS DATA FROM HUB TABLES");
			
			String orderIdLoc = orderSearchRequestMessage.getMessageBody().getLookupData().getOrderId();
			List<EniOrderHubOrderModel> ordersModel = EniOrderHubDBHelper.getInstance()
					.retrieveOrderDataForOrderSearch(conn, ORGANIZATION_ID_DEFAULT, orderIdLoc);
			EniOrderHubTenderModel tenderModel = null;
			EniOrderHubCustomerModel customerModel = null;

			if (ordersModel == null || ordersModel.size() <= 0) {
				statusInquiryResponseMessageOrdersTypeBean.getOrder().add(statusInquiryResponseMessageOrderTypeBean);
				statusResponseMessageBodyTypeBean.setOrders(statusInquiryResponseMessageOrdersTypeBean);
				LOG.warn("<<" + ACTION_RESPONSE_SUCCESS + locationCode + SYSTEM);
				messageHeaderTypeBean.setXactionResponse(ACTION_RESPONSE_SUCCESS + locationCode + SYSTEM);
				LOG.warn("No order was founded");
			} else {
				// Must move cursor to the first row
				String orderId = "";
				int orgId = 0;
				SoldToCustomerTypeBean soldToCustomerTypeBean = null;
				AddressTypeBean addressTypeBean = null;
				NameTypeBean nameTypeBean = null;
				CustomerTypeBean customerTypeBean = null;
				TaxesTypeBean taxesTypeBean = null;
				TaxTypeBean taxTypeBean = null;
				DSSalesOrderCustomizationsTypeBean dSSalesOrderCustomizationsTypeBean = new DSSalesOrderCustomizationsTypeBean();
				DSSalesOrderCustomizationTypeBean dSSalesOrderCustomizationTypeBean = null;
				TransactionTendersTypeBean transactionTendersTypeBean = new TransactionTendersTypeBean();
				TransactionTenderTypeBean transactionTenderTypeBean = null;
				for (EniOrderHubOrderModel orderModel : ordersModel) {
					orderId = orderModel.getOrderId();
					orgId = orderModel.getOrganizationId();
					statusInquiryResponseMessageOrderTypeBean = new StatusInquiryResponseMessageOrderTypeBean();
					statusInquiryResponseMessageItemTypeBean = new StatusInquiryResponseMessageItemTypeBean();
					// Get customer data from order_id
					customerModel = EniOrderHubDBHelper.getInstance().retrieveCustomerData(conn, orgId, orderId);
					// if customer info exist in db
					// will get it to build response
					String customerNo = "";
					if (customerModel != null) {
						customerNo = customerModel.getCustomerId();
						if (customerNo != null && !"".equals(customerNo)) {
							// set customer information bean
							soldToCustomerTypeBean = new SoldToCustomerTypeBean();
							addressTypeBean = getAddressTypeBeanInfo(addressTypeBean, customerModel);
							nameTypeBean = getNameTypeBean(nameTypeBean, customerModel);
							soldToCustomerTypeBean.setCustomerNo(customerNo);
							soldToCustomerTypeBean.setAddress(addressTypeBean);
							soldToCustomerTypeBean.setName(nameTypeBean);
						} else { // If customer_no is null but customer is exist
							LOG.warn("The customer_no is null, so we missing Customer information ");

						}
					}
					statusInquiryResponseMessageOrderTypeBean.setSoldToCustomer(soldToCustomerTypeBean);
					// For order general informations
					statusInquiryResponseMessageOrderTypeBean.setBalanceDue(
							orderModel.getBalanceDue() != null ? orderModel.getBalanceDue().doubleValue() : 0);
					statusInquiryResponseMessageOrderTypeBean.setCurrency(CURRENCY);
					statusInquiryResponseMessageOrderTypeBean
							.setFreight(orderModel.getFreight() != null ? orderModel.getFreight().doubleValue() : 0);
					statusInquiryResponseMessageOrderTypeBean.setFreightTax(orderModel.getFreightTax());
					statusInquiryResponseMessageOrderTypeBean.setGift(orderModel.getGift());
					statusInquiryResponseMessageOrderTypeBean.setGiftMessage(orderModel.getGiftMessage());
					statusInquiryResponseMessageOrderTypeBean
							.setOrderAdditionalCharges(orderModel.getAdditionalCharges());
					statusInquiryResponseMessageOrderTypeBean
							.setOrderAdditionalFreightCharges(orderModel.getAdditionalFreightCharges());
					statusInquiryResponseMessageOrderTypeBean.setOrderDate(format.format(orderModel.getOrderDate()));
					statusInquiryResponseMessageOrderTypeBean.setOrderId(orderModel.getOrderId());
					statusInquiryResponseMessageOrderTypeBean.setOrderMessage(orderModel.getOrderMessage());
					statusInquiryResponseMessageOrderTypeBean.setOrderStatus(orderModel.getStatusCode());
					// Order Type: 1-PICKUP, 2-DELIVERY, 3-SHIPTOSTORE, 4-RETAILPICKUP,
					// 5-SHIPFORPICKUP
					statusInquiryResponseMessageOrderTypeBean.setOrderType(getOrderType(orderModel.getOrderType()));
					statusInquiryResponseMessageOrderTypeBean.setOriginatingLocationCd(locationCode);
					statusInquiryResponseMessageOrderTypeBean.setOriginatingLocationDescription("");
					statusInquiryResponseMessageOrderTypeBean.setOriginatingSystemCd(DESTINATION);
					statusInquiryResponseMessageOrderTypeBean.setRefTransactionNo(orderModel.getRefNbr());
					statusInquiryResponseMessageOrderTypeBean.setRequestId(getOrderIdInt(orderModel.getOrderId()));
					statusInquiryResponseMessageOrderTypeBean.setShipComplete(orderModel.isShipCompleteFlag() + "");
					statusInquiryResponseMessageOrderTypeBean.setShipVia(orderModel.getShipVia());
					statusInquiryResponseMessageOrderTypeBean.setShipViaDescription(orderModel.getShipViaDescription());
					statusInquiryResponseMessageItemTypeBean.setShippingAgent(orderModel.getShipVia() != null ? orderModel.getShipVia() : "");
					statusInquiryResponseMessageOrderTypeBean.setSoldToCustomer(soldToCustomerTypeBean);
					statusInquiryResponseMessageOrderTypeBean.setSourceCode("");
					statusInquiryResponseMessageOrderTypeBean.setSpecialInstructions(orderModel.getSpecialInstructions());
					statusInquiryResponseMessageOrderTypeBean.setTransactionSubtotal(orderModel.getSubtotal() != null ? orderModel.getSubtotal().doubleValue() : 0);
					statusInquiryResponseMessageOrderTypeBean.setTransactionTax(orderModel.getTaxAmount() != null ? orderModel.getTaxAmount().doubleValue() : 0);
					statusInquiryResponseMessageOrderTypeBean.setTransactionTotal(orderModel.getTotal() != null ? orderModel.getTotal().doubleValue() : 0);
					statusInquiryResponseMessageOrderTypeBean.setUnderReview(orderModel.isUnderReviewFlag() + "");
					
					tenderModel = EniOrderHubDBHelper.getInstance().retrieveTenderData(conn, orderId);
					getTransactionTenderInfo(tenderModel, transactionTenderTypeBean, transactionTendersTypeBean);
					statusInquiryResponseMessageOrderTypeBean.setTransactionTenders(transactionTendersTypeBean);
					// Get item information from order_id
					
					List<EniOrderHubOrderLineModel>  orderLineModels = EniOrderHubDBHelper.getInstance().retrieveOrderLineData(conn, orgId, orderId);
					if (orderLineModels != null) {
						for(EniOrderHubOrderLineModel orderLineModel : orderLineModels )  {
							taxTypeBean = new TaxTypeBean();
							taxTypeBean.setAmount(orderLineModel.getTaxAmount());
							taxTypeBean.setDescription(orderLineModel.getLineMessage());
							taxTypeBean.setLineItemNo(orderLineModel.getLineNo() + "");
							dSSalesOrderCustomizationTypeBean = new DSSalesOrderCustomizationTypeBean();
							dSSalesOrderCustomizationTypeBean.setCustomizationCode(orderLineModel.getCustomizationCode());
							dSSalesOrderCustomizationTypeBean
									.setCustomizationMessage(orderLineModel.getCustomizationMessage());
							dSSalesOrderCustomizationsTypeBean.getCustomization().add(dSSalesOrderCustomizationTypeBean);
							customerTypeBean = new CustomerTypeBean();
							customerTypeBean.setAddress(addressTypeBean);
							customerTypeBean.setName(nameTypeBean);
							taxesTypeBean = new TaxesTypeBean();
							taxesTypeBean.getTax().add(taxTypeBean);
							statusInquiryResponseMessageItemTypeBean.setCustomizations(dSSalesOrderCustomizationsTypeBean);
							statusInquiryResponseMessageItemTypeBean.setFulfillingLocationCd(locationCode);
							statusInquiryResponseMessageItemTypeBean.setFulfillingSystemCd(DESTINATION);
							statusInquiryResponseMessageItemTypeBean.setFulfillmentId(orderId);
							statusInquiryResponseMessageItemTypeBean.setItemDescription("");
							statusInquiryResponseMessageItemTypeBean.setItemEanCd(orderLineModel.getItemEanCode());
							statusInquiryResponseMessageItemTypeBean.setItemId(orderLineModel.getItemId());
							statusInquiryResponseMessageItemTypeBean.setItemQty(
									orderLineModel.getQuantity() != null ? orderLineModel.getQuantity().intValue() : 0);
							statusInquiryResponseMessageItemTypeBean.setItemStatus(orderLineModel.getStatusCode());
							statusInquiryResponseMessageItemTypeBean.setItemUpcCd(orderLineModel.getItemUpcCode());
							statusInquiryResponseMessageItemTypeBean.setLineNo(orderLineModel.getLineNo());
							statusInquiryResponseMessageItemTypeBean.setOrderLineCustomizationCharge(orderLineModel.getCustomizationCharge()); // defaultDecimalAmt
							statusInquiryResponseMessageItemTypeBean.setOrderLineExtendedFreight(orderLineModel.getExtendedFreight()); // defaultDecimalAmt
							statusInquiryResponseMessageItemTypeBean.setOrderLineGiftWrap(orderLineModel.isGiftWrapFlag() + "");
							statusInquiryResponseMessageItemTypeBean.setOrderLineMessage(orderLineModel.getLineMessage());
							statusInquiryResponseMessageItemTypeBean.setOrderLineShipAlone(orderLineModel.isShipAloneFlag() + "");
							statusInquiryResponseMessageItemTypeBean.setOrderLineShipWeight(orderLineModel.getShipWeight()); // defaultDecimalAmt
							statusInquiryResponseMessageItemTypeBean.setPickupByDate(orderLineModel.getPickupByDate() != null ? format.format(orderLineModel.getPickupByDate()) : "");
							statusInquiryResponseMessageItemTypeBean.setRequestingSystemLineNo(orderLineModel.getLineNo());
							statusInquiryResponseMessageItemTypeBean.setShipforpickupLocationCd(locationCode);
							statusInquiryResponseMessageItemTypeBean.setShipforpickupSystemCd(DESTINATION);
							statusInquiryResponseMessageItemTypeBean.setShipTo(customerTypeBean);
							statusInquiryResponseMessageItemTypeBean.setSpecialInstructions(orderLineModel.getSpecialInstructions());
							statusInquiryResponseMessageItemTypeBean.setStatusDate(orderLineModel.getUpdateDate() != null ? format.format(orderLineModel.getUpdateDate()) : "");
							statusInquiryResponseMessageItemTypeBean.setTaxAmount(orderLineModel.getTaxAmount() != null ? orderLineModel.getTaxAmount().doubleValue() : 0);
							statusInquiryResponseMessageItemTypeBean.setTaxes(taxesTypeBean);
							statusInquiryResponseMessageItemTypeBean.setTrackingNumber(orderLineModel.getTrackingNbr());
							statusInquiryResponseMessageItemTypeBean.setUnitPrice(orderLineModel.getUnitPrice() != null ? orderLineModel.getUnitPrice().doubleValue() : 0);
							statusInquiryResponseMessageItemsTypeBean.getItem().add(statusInquiryResponseMessageItemTypeBean);
						}
					}
					statusInquiryResponseMessageOrderTypeBean.setItems(statusInquiryResponseMessageItemsTypeBean);
					statusInquiryResponseMessageOrdersTypeBean.getOrder()
							.add(statusInquiryResponseMessageOrderTypeBean);
				}
				if (statusInquiryResponseMessageOrdersTypeBean.getOrder() == null
						|| statusInquiryResponseMessageOrdersTypeBean.getOrder().size() == 0) {
					LOG.warn("<<" + ACTION_RESPONSE_SUCCESS + locationCode + SYSTEM);
					messageHeaderTypeBean.setXactionResponse(ACTION_RESPONSE_SUCCESS + locationCode + SYSTEM);
				}
				statusResponseMessageBodyTypeBean.setOrders(statusInquiryResponseMessageOrdersTypeBean);
			}
			LOG.info("<<< END STEP2- RETRIEVE ORDER DATA FROM HUB");

			// Step3 - Close connection to database

			LOG.info(">>> START STEP3-CLOSE CONNECTION TO DATABASE");
			// Close local connection
			conn.close();
			// Close global connection
			EniOrderHubDBHelper.getInstance().closeConnection(conn);
			LOG.info("<<< END STEP3-CLOSE CONNECTION TO DATABASE");

			_return.setMessageHeader(messageHeaderTypeBean);
			_return.setMessageBody(statusResponseMessageBodyTypeBean);
		} /*
			 * catch (Exception ex) { LOG.error("Can't find orders" +ex); throw new
			 * RuntimeException(ex);
			 * 
			 * }
			 */catch (Exception ex) {
			LOG.error(ex);
		}
		LOG.info("<< +[orderSearchs] END");
		return _return;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.microsretail.locate.Locate#intransit(com.microsretail.locate.
	 * IntransitRequestMessageTypeBean intransitRequestMessage)*
	 */
	public IntransitResponseMessageTypeBean intransit(
			com.microsretail.locate.IntransitRequestMessageTypeBean intransitRequestMessage) {
		LOG.info("Executing operation intransit");
		IntransitResponseMessageTypeBean _return = new IntransitResponseMessageTypeBean();
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US);
			format.setTimeZone(TimeZone.getTimeZone("UTC"));

			// Header bean for generating message header
			MessageHeaderTypeBean messageHeaderTypeBean = new MessageHeaderTypeBean();
			messageHeaderTypeBean.setXactionType(ACTION_TYPE);
			messageHeaderTypeBean.setDatetime(format.format(new Date()));
			messageHeaderTypeBean.setVersion(intransitRequestMessage.getMessageHeader().getVersion());
			messageHeaderTypeBean.setSource(SOURCE);
			messageHeaderTypeBean.setDestination(DESTINATION);

			// Body bean for generating message body
			IntransitResponseMessageBodyTypeBean intransitResponseMessageBodyTypeBean = new IntransitResponseMessageBodyTypeBean();

			// Order Bean Type that it contains order list
			IntransitResponseMessageOrdersTypeBean intransitResponseMessageOrdersTypeBean = new IntransitResponseMessageOrdersTypeBean();

			// Item List
			IntransitResponseMessageItemsTypeBean intransitResponseMessageItemsTypeBean = null;
			// Item Taxes
			TaxesTypeBean taxesTypeBean = null;
			TaxTypeBean taxTypeBean = null;

			IntransitResponseMessageOrderTypeBean intransitResponseMessageOrderTypeBean = null;

			// Get order data from HUB tables that it meets
			// with location_cd in message request from Xstore
			String locationCode = intransitRequestMessage.getMessageBody().getStoreLocation().getLocationCd();
			if (locationCode == null || "".contentEquals(locationCode)) {
				messageHeaderTypeBean.setXactionResponse(ACTION_RESPONSE_SUCCESS);
			} else {
				messageHeaderTypeBean.setXactionResponse(ACTION_RESPONSE_OK);
			}

			// Step1 - Open connection to xcenter database
			LOG.info(">>> START STEP1-OPEN CONNECTION TO DATABASE");
			Connection conn = EniOrderHubDBHelper.getInstance().openConnection();
			LOG.info("<<< END STEP1-OPEN CONNECTION TO DATABASE");

			// Step 2 - Retrieve order data from Hub Order Tables
			// for building message response and return to Xstore
			LOG.info(">>> START STEP2-RETRIEVE ORDER DATA FROM HUB TABLES");

			List<EniOrderHubOrderModel> ordersModel = EniOrderHubDBHelper.getInstance()
					.retrieveOrderDataForOrderIntransit(conn, ORGANIZATION_ID_DEFAULT, locationCode);
			EniOrderHubCustomerModel customerModel = null;
			EniOrderHubRetailLocationModel rtlLocModel = null;

			if (ordersModel == null || ordersModel.size() <= 0) {
				intransitResponseMessageOrdersTypeBean.getOrder().add(intransitResponseMessageOrderTypeBean);
				intransitResponseMessageBodyTypeBean.setOrders(intransitResponseMessageOrdersTypeBean);

				LOG.warn("<<" + ACTION_RESPONSE_SUCCESS + locationCode + SYSTEM);
				messageHeaderTypeBean.setXactionResponse(ACTION_RESPONSE_SUCCESS + locationCode + SYSTEM);
			} else {

				String orderId = "";
				int orgId = 0;
				// Format: yyyyMMddHHmmss + orderId
				String customerNo = "";
				String transactionNo = EniOrderHubUtils.formatDateTime("yyyyMMddHHmmss", new Date());
				String transactionTypeId = "PICKUP";
				String defaultVal = "DEFAULT";
				Double defaultDoubleAmt = 0.0;
				BigDecimal defaultDecimalAmt = new BigDecimal(0);
				String shipViaDescription = "In Store Pick Up";

				SoldToCustomerTypeBean soldToCustomerTypeBean = null;
				AddressTypeBean addressTypeBean = null;
				NameTypeBean nameTypeBean = null;
				for (EniOrderHubOrderModel orderModel : ordersModel) {
					{
						orderId = orderModel.getOrderId();
						orgId = orderModel.getOrganizationId();

						intransitResponseMessageOrderTypeBean = new IntransitResponseMessageOrderTypeBean();

						// Get data from [dbo].[eni_hub_xom_customer] based on order_id
						customerModel = EniOrderHubDBHelper.getInstance().retrieveCustomerData(conn, orgId,
								locationCode);
						// If customer info exist in [dbo].[eni_hub_customer]
						// will get it to build response
						if (customerModel != null) {
							customerNo = customerModel.getCustomerId();
							if (customerNo != null && !"".equals(customerNo)) {
								// For customer information
								soldToCustomerTypeBean = new SoldToCustomerTypeBean();

								addressTypeBean = new AddressTypeBean();
								addressTypeBean.setAddress1(customerModel.getAddress1());
								addressTypeBean.setAddress2(customerModel.getAddress2());
								addressTypeBean.setAddress3(customerModel.getAddress3());
								addressTypeBean.setAddress4(customerModel.getAddress4());
								addressTypeBean.setApt(customerModel.getApt());

								addressTypeBean.setPhone1(customerModel.getTelephone1());
								addressTypeBean.setPhone2(customerModel.getTelephone2());
								addressTypeBean.setEmail(customerModel.getEmailaddress());
								addressTypeBean.setCity(customerModel.getCity());
								addressTypeBean.setPostal(customerModel.getPostal());
								addressTypeBean.setCountry(customerModel.getCountry());
								addressTypeBean.setProvince(customerModel.getProvince());

								nameTypeBean = new NameTypeBean();
								nameTypeBean.setCompanyName(customerModel.getCompanyName());
								nameTypeBean.setPrefix(customerModel.getPrefix());
								nameTypeBean.setFirst(customerModel.getFirstName());
								nameTypeBean.setMiddle(customerModel.getMiddleName());
								nameTypeBean.setLast(customerModel.getLastName());
								nameTypeBean.setSuffix(null);

								soldToCustomerTypeBean.setCustomerNo(customerNo);
								soldToCustomerTypeBean.setAddress(addressTypeBean);
								soldToCustomerTypeBean.setName(nameTypeBean);

							} else { // If [dbo].[eni_hub_xom_customer] has data but customer_no is empty
										// Need to switch to loc_rtl_loc table to get info
								rtlLocModel = EniOrderHubDBHelper.getInstance().retrieveRetailLocationData(conn, orgId,
										locationCode);

								if (rtlLocModel != null) {
									soldToCustomerTypeBean = new SoldToCustomerTypeBean();

									addressTypeBean = new AddressTypeBean();
									addressTypeBean.setAddress1(rtlLocModel.getAddress1());
									addressTypeBean.setAddress2(rtlLocModel.getAddress2());
									addressTypeBean.setAddress3(rtlLocModel.getAddress3());
									addressTypeBean.setAddress4(rtlLocModel.getAddress4());
									addressTypeBean.setApt(rtlLocModel.getApartment());

									addressTypeBean.setPhone1(rtlLocModel.getTelephone1());
									addressTypeBean.setPhone2(rtlLocModel.getTelephone2());
									addressTypeBean.setCity(rtlLocModel.getCity());
									addressTypeBean.setPostal(rtlLocModel.getPostalCode());
									addressTypeBean.setCountry(rtlLocModel.getCountry());
									addressTypeBean.setProvince(rtlLocModel.getState());

									nameTypeBean = new NameTypeBean();
									nameTypeBean.setCompanyName(null);
									nameTypeBean.setPrefix(null);
									nameTypeBean.setFirst(rtlLocModel.getStoreName());
									nameTypeBean.setMiddle(null);
									nameTypeBean.setLast(null);
									nameTypeBean.setSuffix(null);

									soldToCustomerTypeBean.setCustomerNo(orderId);
									soldToCustomerTypeBean.setAddress(addressTypeBean);
									soldToCustomerTypeBean.setName(nameTypeBean);
								}
							}
						} else {
							// Order is missing Customer Information
							LOG.warn("The Order:" + orderId + " is missing customer information!");
							continue;
						}
						intransitResponseMessageOrderTypeBean.setSoldToCustomer(soldToCustomerTypeBean);

						// For order general information
						intransitResponseMessageOrderTypeBean.setOrderId(orderId);
						intransitResponseMessageOrderTypeBean.setRequestId(Integer.parseInt(orderId));
						intransitResponseMessageOrderTypeBean.setTransactionNo(transactionNo);
						intransitResponseMessageOrderTypeBean
								.setTransactionStatus(orderModel.getStatusCode().toLowerCase());
						intransitResponseMessageOrderTypeBean.setTransactionTypeId(transactionTypeId);

						intransitResponseMessageOrderTypeBean.setOrderMessage(orderModel.getOrderMessage());

						intransitResponseMessageOrderTypeBean.setRefTransactionNo(defaultVal);
						Date date = orderModel.getOrderDate();
						intransitResponseMessageOrderTypeBean.setTransactionDate(format.format(date));
						intransitResponseMessageOrderTypeBean.setEmployeeId(defaultVal);
						intransitResponseMessageOrderTypeBean.setTransactionSubtotal(
								orderModel.getSubtotal() != null ? orderModel.getSubtotal().doubleValue() : 0);
						intransitResponseMessageOrderTypeBean.setTransactionTax(
								orderModel.getTaxAmount() != null ? orderModel.getTaxAmount().doubleValue() : 0);
						intransitResponseMessageOrderTypeBean.setTransactionTotal(
								orderModel.getTotal() != null ? orderModel.getTotal().doubleValue() : 0);
						intransitResponseMessageOrderTypeBean
								.setSpecialInstructions(orderModel.getSpecialInstructions());
						intransitResponseMessageOrderTypeBean.setTransactionChannel(defaultVal);
						intransitResponseMessageOrderTypeBean.setShipVia(transactionTypeId);
						intransitResponseMessageOrderTypeBean.setShipViaDescription(shipViaDescription);
						intransitResponseMessageOrderTypeBean.setGift(defaultVal);
						intransitResponseMessageOrderTypeBean.setSourceCode(defaultVal);
						intransitResponseMessageOrderTypeBean.setFreightAmount(defaultDoubleAmt);

						intransitResponseMessageOrderTypeBean.setBalanceDue(
								orderModel.getBalanceDue() != null ? orderModel.getBalanceDue().doubleValue() : 0);
						intransitResponseMessageOrderTypeBean.setCurrency(CURRENCY);
						intransitResponseMessageOrderTypeBean.setUnderReview("N");
						intransitResponseMessageOrderTypeBean.setOrderAdditionalFreightCharges(defaultDecimalAmt);
						intransitResponseMessageOrderTypeBean.setOrderAdditionalCharges(defaultDecimalAmt);
						intransitResponseMessageOrderTypeBean.setShipComplete("N");
						intransitResponseMessageOrderTypeBean.setFreightTax(defaultDecimalAmt);

						// For store location information
						IntransitResponseMessageStoreLocationTypeBean responseMessageStoreLocationTypeBean = new IntransitResponseMessageStoreLocationTypeBean();
						responseMessageStoreLocationTypeBean.setLocationCd(locationCode);
						responseMessageStoreLocationTypeBean.setRequestLocationCd(ORIGINAL_LOCATION_CD);
						responseMessageStoreLocationTypeBean.setRequestSystemCd(DESTINATION);
						responseMessageStoreLocationTypeBean.setSystemCd(DESTINATION);

						intransitResponseMessageOrderTypeBean.setStoreLocation(responseMessageStoreLocationTypeBean);
						List<EniOrderHubOrderLineModel> orderLineModels =	EniOrderHubDBHelper.getInstance().retrieveOrderLineData(conn, orgId, orderId);
						intransitResponseMessageItemsTypeBean = new IntransitResponseMessageItemsTypeBean();
						if (orderLineModels == null) {
							// Empty for Order Line
							LOG.warn("The Order:" + orderId + " is empty for order line!");
							continue;
						} else {
							for(EniOrderHubOrderLineModel orderLineModel : orderLineModels) {
								IntransitResponseMessageItemTypeBean intransitResponseMessageItemTypeBean = new IntransitResponseMessageItemTypeBean();

								intransitResponseMessageItemTypeBean.setLineItemNo(orderLineModel.getLineNo());
								intransitResponseMessageItemTypeBean
										.setRequestingSystemLineNo(orderLineModel.getLineNo() + "");
								intransitResponseMessageItemTypeBean
										.setLineItemStatus(orderLineModel.getStatusCode().toLowerCase());
								intransitResponseMessageItemTypeBean.setLocationCd(locationCode);
								intransitResponseMessageItemTypeBean.setSystemCd(DESTINATION);
								intransitResponseMessageItemTypeBean.setItemId(orderLineModel.getItemId());
								intransitResponseMessageItemTypeBean.setQty(
										orderLineModel.getQuantity() != null ? orderLineModel.getQuantity().intValue() : 0);
								intransitResponseMessageItemTypeBean.setUnitPrice(
										orderLineModel.getUnitPrice() != null ? orderLineModel.getUnitPrice().doubleValue()
												: 0);

								taxesTypeBean = new TaxesTypeBean();

								taxTypeBean = new TaxTypeBean();
								taxTypeBean.setDescription("Tax");
								taxTypeBean.setLineItemNo(orderLineModel.getLineNo() + "");
								taxTypeBean.setAmount(orderLineModel.getTaxAmount());
								taxesTypeBean.getTax().add(taxTypeBean);
								intransitResponseMessageItemTypeBean.setTaxes(taxesTypeBean);

								intransitResponseMessageItemTypeBean.setOrderLineExtendedFreight(defaultDecimalAmt);
								intransitResponseMessageItemTypeBean.setOrderLineCustomizationCharge(defaultDecimalAmt);
								intransitResponseMessageItemTypeBean.setOrderLineGiftWrap("N");
								intransitResponseMessageItemTypeBean.setOrderLineShipAlone("N");
								intransitResponseMessageItemTypeBean.setOrderLineShipWeight(defaultDecimalAmt);

								intransitResponseMessageItemsTypeBean.getItemDetail()
										.add(intransitResponseMessageItemTypeBean);
								intransitResponseMessageOrderTypeBean.setItems(intransitResponseMessageItemsTypeBean);
							}
							
						}

						intransitResponseMessageOrdersTypeBean.getOrder()
								.add(intransitResponseMessageOrderTypeBean);
					}

					if (intransitResponseMessageOrdersTypeBean.getOrder() == null
							|| intransitResponseMessageOrdersTypeBean.getOrder().size() == 0) {
						LOG.warn("<<" + ACTION_RESPONSE_SUCCESS + locationCode + SYSTEM);
						messageHeaderTypeBean.setXactionResponse(ACTION_RESPONSE_SUCCESS + locationCode + SYSTEM);
					}

					intransitResponseMessageBodyTypeBean.setOrders(intransitResponseMessageOrdersTypeBean);
				}
				LOG.info("<<< END STEP2-RETRIEVE ORDER DATA FROM HUB TABLES");

				// Step3 - Close connection to database
				// Close PreparedStatement & ResultSets

				LOG.info(">>> START STEP3-CLOSE CONNECTION TO DATABASE");
				// Close local connection
				conn.close();
				// Close global connection
				EniOrderHubDBHelper.getInstance().closeConnection(conn);
				LOG.info("<<< END STEP3-CLOSE CONNECTION TO DATABASE");

				_return.setMessageHeader(messageHeaderTypeBean);
				_return.setMessageBody(intransitResponseMessageBodyTypeBean);

			}
		} catch (Exception ex) {
			LOG.error(ex);
		}
		return _return;
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.microsretail.locate.Locate#fulfillments(com.microsretail.locate.
	 * FulfillmentRequestMessageTypeBean fulfillmentRequestMessage)*
	 */
	public com.microsretail.locate.FulfillmentResponseMessageTypeBean fulfillments(
			com.microsretail.locate.FulfillmentRequestMessageTypeBean fulfillmentRequestMessage) {
		LOG.info(">> +[fulfillments] START");
		FulfillmentResponseMessageTypeBean _return = new FulfillmentResponseMessageTypeBean();
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US);
			format.setTimeZone(TimeZone.getTimeZone("UTC"));

			// Header bean for generating message header
			MessageHeaderTypeBean messageHeaderTypeBean = new MessageHeaderTypeBean();
			messageHeaderTypeBean.setXactionType(ACTION_TYPE);
			messageHeaderTypeBean.setDatetime(format.format(new Date()));
			messageHeaderTypeBean.setVersion(fulfillmentRequestMessage.getMessageHeader().getVersion());
			messageHeaderTypeBean.setSource(SOURCE);
			messageHeaderTypeBean.setDestination(DESTINATION);

			// Body bean for generating message body
			FulfillmentResponseMessageBodyTypeBean fulfillmentResponseMessageBodyTypeBean = new FulfillmentResponseMessageBodyTypeBean();

			// Order Bean Type that it contains order list
			FulfillmentResponseMessageOrdersTypeBean fulfillmentResponseMessageOrdersTypeBean = new FulfillmentResponseMessageOrdersTypeBean();

			// Item List
			FulfillmentResponseMessageItemsTypeBean fulfillmentResponseMessageItemsTypeBean = null;
			// Item Taxes
			TaxesTypeBean taxesTypeBean = null;
			TaxTypeBean taxTypeBean = null;

			FulfillmentResponseMessageOrderTypeBean fulfillmentResponseMessageOrderTypeBean = null;

			// Get order data from HUB tables that it meets
			// with location_cd in message request from Xstore
			String locationCode = fulfillmentRequestMessage.getMessageBody().getStoreLocation().getLocationCd();
			if (locationCode == null || "".contentEquals(locationCode)) {
				messageHeaderTypeBean.setXactionResponse(ACTION_RESPONSE_SUCCESS);
			} else {
				messageHeaderTypeBean.setXactionResponse(ACTION_RESPONSE_OK);
			}

			// Step1 - Open connection to xcenter database
			LOG.info(">>> START STEP1-OPEN CONNECTION TO DATABASE");
			Connection conn = EniOrderHubDBHelper.getInstance().openConnection();
			LOG.info("<<< END STEP1-OPEN CONNECTION TO DATABASE");

			// Step 2 - Retrieve order data from Hub Order Tables
			// for building message response and return to Xstore
			LOG.info(">>> START STEP2-RETRIEVE ORDER DATA FROM HUB TABLES");

			List<EniOrderHubOrderModel> ordersModel = EniOrderHubDBHelper.getInstance()
					.retrieveOrderDataForOrderFullfillment(conn, ORGANIZATION_ID_DEFAULT, locationCode);

			EniOrderHubCustomerModel customerModel = null;
			EniOrderHubRetailLocationModel rtlLocModel = null;
			if (ordersModel == null || ordersModel.size() <= 0) {
				fulfillmentResponseMessageOrdersTypeBean.getOrder().add(fulfillmentResponseMessageOrderTypeBean);
				fulfillmentResponseMessageBodyTypeBean.setOrders(fulfillmentResponseMessageOrdersTypeBean);

				LOG.warn("<<" + ACTION_RESPONSE_SUCCESS + locationCode + SYSTEM);
				messageHeaderTypeBean.setXactionResponse(ACTION_RESPONSE_SUCCESS + locationCode + SYSTEM);
			} else {

				String orderId = "";
				int orgId = 0;
				// Format: yyyyMMddHHmmss + orderId
				String customerNo = "";
				String transactionNo = EniOrderHubUtils.formatDateTime("yyyyMMddHHmmss", new Date());
				String transactionTypeId = "PICKUP";
				String defaultVal = "DEFAULT";
				Double defaultDoubleAmt = 0.0;
				BigDecimal defaultDecimalAmt = new BigDecimal(0);
				String shipViaDescription = "In Store Pick Up";

				SoldToCustomerTypeBean soldToCustomerTypeBean = null;
				AddressTypeBean addressTypeBean = null;
				NameTypeBean nameTypeBean = null;
				for (EniOrderHubOrderModel orderModel : ordersModel) {
					{
						orderId = orderModel.getOrderId();
						orgId = orderModel.getOrganizationId();

						fulfillmentResponseMessageOrderTypeBean = new FulfillmentResponseMessageOrderTypeBean();

						// Get data from [dbo].[eni_hub_xom_customer] based on order_id
						customerModel = EniOrderHubDBHelper.getInstance().retrieveCustomerData(conn, orgId,
								orderId);

						// If customer info exist in [dbo].[eni_hub_xom_customer]
						// will get it to build response
						if (customerModel != null) {
							customerNo = customerModel.getCustomerId();
							if (customerNo != null && !"".equals(customerNo)) {
								// For customer information
								soldToCustomerTypeBean = new SoldToCustomerTypeBean();

								addressTypeBean = new AddressTypeBean();
								addressTypeBean.setAddress1(customerModel.getAddress1());
								addressTypeBean.setAddress2(customerModel.getAddress2());
								addressTypeBean.setAddress3(customerModel.getAddress3());
								addressTypeBean.setAddress4(customerModel.getAddress4());
								addressTypeBean.setApt(customerModel.getApt());

								addressTypeBean.setPhone1(customerModel.getTelephone1());
								addressTypeBean.setPhone2(customerModel.getTelephone2());
								addressTypeBean.setEmail(customerModel.getEmailaddress());
								addressTypeBean.setCity(customerModel.getCity());
								addressTypeBean.setPostal(customerModel.getPostal());
								addressTypeBean.setCountry(customerModel.getCountry());
								addressTypeBean.setProvince(customerModel.getProvince());

								nameTypeBean = new NameTypeBean();
								nameTypeBean.setCompanyName(customerModel.getCompanyName());
								nameTypeBean.setPrefix(customerModel.getPrefix());
								nameTypeBean.setFirst(customerModel.getFirstName());
								nameTypeBean.setMiddle(customerModel.getMiddleName());
								nameTypeBean.setLast(customerModel.getLastName());
								nameTypeBean.setSuffix(null);

								soldToCustomerTypeBean.setCustomerNo(customerNo);
								soldToCustomerTypeBean.setAddress(addressTypeBean);
								soldToCustomerTypeBean.setName(nameTypeBean);

							} else { // If [dbo].[eni_hub_xom_customer] has data but customer_no is empty
										// Need to switch to loc_rtl_loc table to get info
								rtlLocModel = EniOrderHubDBHelper.getInstance().retrieveRetailLocationData(conn, orgId,
										locationCode);

								if (rtlLocModel != null) {
									soldToCustomerTypeBean = new SoldToCustomerTypeBean();

									addressTypeBean = new AddressTypeBean();
									addressTypeBean.setAddress1(rtlLocModel.getAddress1());
									addressTypeBean.setAddress2(rtlLocModel.getAddress2());
									addressTypeBean.setAddress3(rtlLocModel.getAddress3());
									addressTypeBean.setAddress4(rtlLocModel.getAddress4());
									addressTypeBean.setApt(rtlLocModel.getApartment());

									addressTypeBean.setPhone1(rtlLocModel.getTelephone1());
									addressTypeBean.setPhone2(rtlLocModel.getTelephone2());
									addressTypeBean.setCity(rtlLocModel.getCity());
									addressTypeBean.setPostal(rtlLocModel.getPostalCode());
									addressTypeBean.setCountry(rtlLocModel.getCountry());
									addressTypeBean.setProvince(rtlLocModel.getState());

									nameTypeBean = new NameTypeBean();
									nameTypeBean.setCompanyName(null);
									nameTypeBean.setPrefix(null);
									nameTypeBean.setFirst(rtlLocModel.getStoreName());
									nameTypeBean.setMiddle(null);
									nameTypeBean.setLast(null);
									nameTypeBean.setSuffix(null);

									soldToCustomerTypeBean.setCustomerNo(orderId);
									soldToCustomerTypeBean.setAddress(addressTypeBean);
									soldToCustomerTypeBean.setName(nameTypeBean);
								}
							}
						} else {
							// Order is missing Customer Information
							LOG.warn("The Order:" + orderId + " is missing customer information!");
							continue;
						}
						fulfillmentResponseMessageOrderTypeBean.setSoldToCustomer(soldToCustomerTypeBean);

						// For order general information
						fulfillmentResponseMessageOrderTypeBean.setOrderId(orderId);
						// order number from eCommer can't convert to integer
						fulfillmentResponseMessageOrderTypeBean.setRequestId(0);
						fulfillmentResponseMessageOrderTypeBean.setTransactionNo(transactionNo);
						fulfillmentResponseMessageOrderTypeBean
								.setTransactionStatus(orderModel.getStatusCode().toLowerCase());
						fulfillmentResponseMessageOrderTypeBean.setTransactionTypeId(transactionTypeId);

						fulfillmentResponseMessageOrderTypeBean.setOrderMessage(orderModel.getOrderMessage());

						fulfillmentResponseMessageOrderTypeBean.setRefTransactionNo(defaultVal);
						Date date = orderModel.getOrderDate();
						fulfillmentResponseMessageOrderTypeBean.setTransactionDate(format.format(date));
						fulfillmentResponseMessageOrderTypeBean.setEmployeeId(defaultVal);
						fulfillmentResponseMessageOrderTypeBean.setTransactionSubtotal(
								orderModel.getSubtotal() != null ? orderModel.getSubtotal().doubleValue() : 0);
						fulfillmentResponseMessageOrderTypeBean.setTransactionTax(
								orderModel.getTaxAmount() != null ? orderModel.getTaxAmount().doubleValue() : 0);
						fulfillmentResponseMessageOrderTypeBean.setTransactionTotal(
								orderModel.getTotal() != null ? orderModel.getTotal().doubleValue() : 0);
						fulfillmentResponseMessageOrderTypeBean
								.setSpecialInstructions(orderModel.getSpecialInstructions());
						fulfillmentResponseMessageOrderTypeBean.setTransactionChannel(defaultVal);
						fulfillmentResponseMessageOrderTypeBean.setShipVia(transactionTypeId);
						fulfillmentResponseMessageOrderTypeBean.setShipViaDescription(shipViaDescription);
						fulfillmentResponseMessageOrderTypeBean.setGift(defaultVal);
						fulfillmentResponseMessageOrderTypeBean.setSourceCode(defaultVal);
						fulfillmentResponseMessageOrderTypeBean.setFreightAmount(defaultDoubleAmt);

						fulfillmentResponseMessageOrderTypeBean.setBalanceDue(
								orderModel.getBalanceDue() != null ? orderModel.getBalanceDue().doubleValue() : 0);
						fulfillmentResponseMessageOrderTypeBean.setCurrency(CURRENCY);
						fulfillmentResponseMessageOrderTypeBean.setUnderReview("N");
						fulfillmentResponseMessageOrderTypeBean.setOrderAdditionalFreightCharges(defaultDecimalAmt);
						fulfillmentResponseMessageOrderTypeBean.setOrderAdditionalCharges(defaultDecimalAmt);
						fulfillmentResponseMessageOrderTypeBean.setShipComplete("N");
						fulfillmentResponseMessageOrderTypeBean.setFreightTax(defaultDecimalAmt);

						// For store location information
						FulfillmentResponseMessageStoreLocationTypeBean responseMessageStoreLocationTypeBean = new FulfillmentResponseMessageStoreLocationTypeBean();
						responseMessageStoreLocationTypeBean.setLocationCd(locationCode);
						responseMessageStoreLocationTypeBean.setRequestLocationCd(ORIGINAL_LOCATION_CD);
						responseMessageStoreLocationTypeBean.setRequestSystemCd(DESTINATION);
						responseMessageStoreLocationTypeBean.setSystemCd(DESTINATION);

						fulfillmentResponseMessageOrderTypeBean.setStoreLocation(responseMessageStoreLocationTypeBean);

						List<EniOrderHubOrderLineModel> orderLineModels = EniOrderHubDBHelper.getInstance().retrieveOrderLineData(conn, orgId, orderId);

						fulfillmentResponseMessageItemsTypeBean = new FulfillmentResponseMessageItemsTypeBean();
						if (orderLineModels == null) {
							// Empty for Order Line
							LOG.warn("The Order:" + orderId + " is empty for order line!");
							continue;
						} else {
							for (EniOrderHubOrderLineModel orderLineModel : orderLineModels ) {
								FulfillmentResponseMessageItemTypeBean fulfillmentResponseMessageItemTypeBean = new FulfillmentResponseMessageItemTypeBean();

								fulfillmentResponseMessageItemTypeBean.setLineItemNo(orderLineModel.getLineNo());
								fulfillmentResponseMessageItemTypeBean
										.setRequestingSystemLineNo(orderLineModel.getLineNo() + "");
								fulfillmentResponseMessageItemTypeBean
										.setLineItemStatus(orderLineModel.getStatusCode().toLowerCase());
								fulfillmentResponseMessageItemTypeBean.setLocationCd(locationCode);
								fulfillmentResponseMessageItemTypeBean.setSystemCd(DESTINATION);
								fulfillmentResponseMessageItemTypeBean.setItemId(orderLineModel.getItemId());
								fulfillmentResponseMessageItemTypeBean.setQty(
										orderLineModel.getQuantity() != null ? orderLineModel.getQuantity().intValue() : 0);
								fulfillmentResponseMessageItemTypeBean.setUnitPrice(
										orderLineModel.getUnitPrice() != null ? orderLineModel.getUnitPrice().doubleValue()
												: 0);

								taxesTypeBean = new TaxesTypeBean();

								taxTypeBean = new TaxTypeBean();
								taxTypeBean.setDescription("Tax");
								taxTypeBean.setLineItemNo(orderLineModel.getLineNo() + "");
								taxTypeBean.setAmount(orderLineModel.getTaxAmount());
								taxesTypeBean.getTax().add(taxTypeBean);
								fulfillmentResponseMessageItemTypeBean.setTaxes(taxesTypeBean);

								fulfillmentResponseMessageItemTypeBean.setOrderLineExtendedFreight(defaultDecimalAmt);
								fulfillmentResponseMessageItemTypeBean.setOrderLineCustomizationCharge(defaultDecimalAmt);
								fulfillmentResponseMessageItemTypeBean.setOrderLineGiftWrap("N");
								fulfillmentResponseMessageItemTypeBean.setOrderLineShipAlone("N");
								fulfillmentResponseMessageItemTypeBean.setOrderLineShipWeight(defaultDecimalAmt);

								fulfillmentResponseMessageItemsTypeBean.getItemDetail()
										.add(fulfillmentResponseMessageItemTypeBean);
								fulfillmentResponseMessageOrderTypeBean.setItems(fulfillmentResponseMessageItemsTypeBean);
							}
						}

						fulfillmentResponseMessageOrdersTypeBean.getOrder()
								.add(fulfillmentResponseMessageOrderTypeBean);
					}

					if (fulfillmentResponseMessageOrdersTypeBean.getOrder() == null
							|| fulfillmentResponseMessageOrdersTypeBean.getOrder().size() == 0) {
						LOG.warn("<<" + ACTION_RESPONSE_SUCCESS + locationCode + SYSTEM);
						messageHeaderTypeBean.setXactionResponse(ACTION_RESPONSE_SUCCESS + locationCode + SYSTEM);
					}else {
					LOG.info(">> START STEP3 UPDATE record_state BEFORE SEND FULFILLMENT RESPONSE");
					PreparedStatement psOrderUpdate;
					PreparedStatement psOrderLineUpdate;
					psOrderUpdate = conn.prepareStatement(EniOrderHubQueryConstant.HUB_ORDER_UPDATE_RECORD_STATE);
					psOrderLineUpdate = conn.prepareStatement(EniOrderHubQueryConstant.HUB_ORDER_LINE_UPDATE_RECORD_STATE);
					List<FulfillmentResponseMessageOrderTypeBean> fulfillmentList = fulfillmentResponseMessageOrdersTypeBean.getOrder();
					updateRecordState(psOrderUpdate, psOrderLineUpdate, fulfillmentList);
					LOG.info("<< END STEP3 UPDATE record_state BEFORE SEND FULFILLMENT RESPONSE");
					}

					fulfillmentResponseMessageBodyTypeBean.setOrders(fulfillmentResponseMessageOrdersTypeBean);
				}
				LOG.info("<<< END STEP2-RETRIEVE ORDER DATA FROM HUB TABLES");
				
				// Close PreparedStatement & ResultSets

				LOG.info(">>> START STEP4-CLOSE CONNECTION TO DATABASE");
				// Close local connection
				conn.close();
				// Close global connection
				EniOrderHubDBHelper.getInstance().closeConnection(conn);
				LOG.info("<<< END STEP4-CLOSE CONNECTION TO DATABASE");
                
				_return.setMessageHeader(messageHeaderTypeBean);
				_return.setMessageBody(fulfillmentResponseMessageBodyTypeBean);
				

			}
		} catch (Exception ex) {
			LOG.error(ex);
			throw new RuntimeException(ex);
		}
		LOG.info("<< +[fulfillments] END");
		return _return;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.microsretail.locate.Locate#productUpdate(com.microsretail.locate.
	 * ProductUpdateRequestMessageTypeBean productUpdateRequestMessage)*
	 */
	public com.microsretail.locate.ProductUpdateResponseMessageTypeBean productUpdate(
			com.microsretail.locate.ProductUpdateRequestMessageTypeBean productUpdateRequestMessage) {
		LOG.info("Executing operation productUpdate");
		try {
			com.microsretail.locate.ProductUpdateResponseMessageTypeBean _return = null;
			return _return;
		} catch (java.lang.Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.microsretail.locate.Locate#echoTest(java.lang.String testMesg)*
	 */
	public java.lang.String echoTest(java.lang.String testMesg) {
		LOG.info("Executing operation echoTest");
		try {
			java.lang.String _return = "";
			return _return;
		} catch (java.lang.Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.microsretail.locate.Locate#statusRequest(com.microsretail.locate.
	 * StatusInquiryRequestMessageTypeBean statusRequestMessage)*
	 */
	public com.microsretail.locate.StatusResponseMessageTypeBean statusRequest(
			com.microsretail.locate.StatusInquiryRequestMessageTypeBean statusRequestMessage) {
		LOG.info(">> +[statusRequest] START");
		com.microsretail.locate.StatusResponseMessageTypeBean _return = new StatusResponseMessageTypeBean();
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US);
			format.setTimeZone(TimeZone.getTimeZone("UTC"));

			// Header bean for generating message header
			MessageHeaderTypeBean messageHeaderTypeBean = new MessageHeaderTypeBean();
			messageHeaderTypeBean.setXactionType(ACTION_TYPE);
			messageHeaderTypeBean.setDatetime(format.format(new Date()));
			messageHeaderTypeBean.setVersion(statusRequestMessage.getMessageHeader().getVersion());
			messageHeaderTypeBean.setSource(SOURCE);
			messageHeaderTypeBean.setDestination(DESTINATION);

			// Body bean for generating message body
			StatusInquiryResponseMessageBodyTypeBean statusInquiryResponseMessageBodyTypeBean = new StatusInquiryResponseMessageBodyTypeBean();

			// Order Bean Type that it contains order list
			StatusInquiryResponseMessageOrdersTypeBean statusInquiryResponseMessageOrdersTypeBean = new StatusInquiryResponseMessageOrdersTypeBean();
			StatusInquiryResponseMessageOrderTypeBean statusInquiryResponseMessageOrderTypeBean = null;

			// Item List
			StatusInquiryResponseMessageItemsTypeBean statusInquiryResponseMessageItemsTypeBean = null;
			// Item Taxes
			TaxesTypeBean taxesTypeBean = null;
			TaxTypeBean taxTypeBean = null;

			// Get order data from HUB tables that it meets
			// with location_cd in message request from Xstore
			String locationCode = statusRequestMessage.getMessageBody().getLookupData().getStoreLocation()
					.getLocationCd();
			String orderId = statusRequestMessage.getMessageBody().getLookupData().getOrderId();
			if (locationCode == null || "".contentEquals(locationCode)) {
				messageHeaderTypeBean.setXactionResponse(ACTION_RESPONSE_SUCCESS);
			} else {
				messageHeaderTypeBean.setXactionResponse(ACTION_RESPONSE_OK);
			}

			// Step1 - Open connection to xcenter database
			LOG.info(">>> START STEP1-OPEN CONNECTION TO DATABASE");
			Connection conn = EniOrderHubDBHelper.getInstance().openConnection();
			LOG.info("<<< END STEP1-OPEN CONNECTION TO DATABASE");

			// Step 2 - Retrieve order data from Hub Order Tables
			// for building message response and return to Xstore
			LOG.info(">>> START STEP2-RETRIEVE ORDER DATA FROM HUB TABLES");
			
			EniOrderHubOrderModel orderModel = EniOrderHubDBHelper.getInstance().retrieveOrderDataForOrderStatus(conn, ORGANIZATION_ID_DEFAULT, orderId, locationCode);
			
			EniOrderHubRetailLocationModel rtlLocationModel = null;
			EniOrderHubCustomerModel customerModel = null;
			
			if (orderModel == null) {
				statusInquiryResponseMessageOrdersTypeBean.getOrder().add(statusInquiryResponseMessageOrderTypeBean);
				statusInquiryResponseMessageBodyTypeBean.setOrders(statusInquiryResponseMessageOrdersTypeBean);

				messageHeaderTypeBean.setXactionResponse(ACTION_RESPONSE_SUCCESS + locationCode + SYSTEM);
			} else {				
				int orgId = 0;
				// Format: yyyyMMddHHmmss + orderId
				String customerNo = "";
				String defaultVal = "HUB-DEFAULT";
				Double defaultDoubleAmt = 0.0;

				SoldToCustomerTypeBean soldToCustomerTypeBean = null;
				AddressTypeBean addressTypeBean = null;
				NameTypeBean nameTypeBean = null;
				orderId = orderModel.getOrderId();
				orgId = orderModel.getOrganizationId();

				statusInquiryResponseMessageOrderTypeBean = new StatusInquiryResponseMessageOrderTypeBean();

				// Get data from [dbo].[eni_hub_xom_customer] based on order_id
				customerModel = EniOrderHubDBHelper.getInstance().retrieveCustomerData(conn, orgId, orderId);

				// If customer info exist in [dbo].[eni_hub_xom_customer]
				// will get it to build response
				if (customerModel != null) {
					customerNo = customerModel.getCustomerId();
					if (customerNo != null && !"".equals(customerNo)) {
						// For customer information
						soldToCustomerTypeBean = new SoldToCustomerTypeBean();

						addressTypeBean = new AddressTypeBean();
						addressTypeBean.setAddress1(customerModel.getAddress1());
						addressTypeBean.setAddress2(customerModel.getAddress2());
						addressTypeBean.setAddress3(customerModel.getAddress3());
						addressTypeBean.setAddress4(customerModel.getAddress4());
						addressTypeBean.setApt(customerModel.getApt());

						addressTypeBean.setPhone1(customerModel.getTelephone1());
						addressTypeBean.setPhone2(customerModel.getTelephone2());
						addressTypeBean.setEmail(customerModel.getEmailaddress());
						addressTypeBean.setCity(customerModel.getCity());
						addressTypeBean.setPostal(customerModel.getPostal());
						addressTypeBean.setCountry(customerModel.getCountry());
						addressTypeBean.setProvince(customerModel.getProvince());

						nameTypeBean = new NameTypeBean();
						nameTypeBean.setCompanyName(customerModel.getCompanyName());
						nameTypeBean.setPrefix(customerModel.getPrefix());
						nameTypeBean.setFirst(customerModel.getFirstName());
						nameTypeBean.setMiddle(customerModel.getMiddleName());
						nameTypeBean.setLast(customerModel.getLastName());
						nameTypeBean.setSuffix(customerModel.getSuffix());

						soldToCustomerTypeBean.setCustomerNo(customerNo);
						soldToCustomerTypeBean.setAddress(addressTypeBean);
						soldToCustomerTypeBean.setName(nameTypeBean);

					} else { // If [dbo].[eni_hub_xom_customer] has data but customer_no is empty
								// Need to switch to loc_rtl_loc table to get info					
						rtlLocationModel = EniOrderHubDBHelper.getInstance().retrieveRetailLocationData(conn, orgId, locationCode);

						if (rtlLocationModel != null) {
							soldToCustomerTypeBean = new SoldToCustomerTypeBean();

							addressTypeBean = new AddressTypeBean();
							addressTypeBean.setAddress1(rtlLocationModel.getAddress1());
							addressTypeBean.setAddress2(rtlLocationModel.getAddress2());
							addressTypeBean.setAddress3(rtlLocationModel.getAddress3());
							addressTypeBean.setAddress4(rtlLocationModel.getAddress4());
							addressTypeBean.setApt(rtlLocationModel.getApartment());

							addressTypeBean.setPhone1(rtlLocationModel.getTelephone1());
							addressTypeBean.setPhone2(rtlLocationModel.getTelephone2());
							addressTypeBean.setCity(rtlLocationModel.getCity());
							addressTypeBean.setPostal(rtlLocationModel.getPostalCode());
							addressTypeBean.setCountry(rtlLocationModel.getCountry());
							addressTypeBean.setProvince(rtlLocationModel.getState());

							nameTypeBean = new NameTypeBean();
							nameTypeBean.setCompanyName(null);
							nameTypeBean.setPrefix(null);
							nameTypeBean.setFirst(rtlLocationModel.getStoreName());
							nameTypeBean.setMiddle(null);
							nameTypeBean.setLast(null);
							nameTypeBean.setSuffix(null);

							soldToCustomerTypeBean.setCustomerNo(orderId);
							soldToCustomerTypeBean.setAddress(addressTypeBean);
							soldToCustomerTypeBean.setName(nameTypeBean);
						}
					}
				}

				// For sold customer
				statusInquiryResponseMessageOrderTypeBean.setSoldToCustomer(soldToCustomerTypeBean);

				// For order general information
				statusInquiryResponseMessageOrderTypeBean.setBalanceDue(orderModel.getBalanceDue() != null ? orderModel.getBalanceDue().doubleValue() : 0);
				statusInquiryResponseMessageOrderTypeBean.setCurrency(CURRENCY);
				statusInquiryResponseMessageOrderTypeBean.setFreight(defaultDoubleAmt);
				statusInquiryResponseMessageOrderTypeBean.setGift(defaultVal);
				statusInquiryResponseMessageOrderTypeBean
						.setOrderDate(format.format(orderModel.getOrderDate()));
				statusInquiryResponseMessageOrderTypeBean.setOrderId(orderId);
				statusInquiryResponseMessageOrderTypeBean
						.setOrderStatus(orderModel.getStatusCode().toLowerCase());
				statusInquiryResponseMessageOrderTypeBean.setOriginatingLocationCd(ORIGINAL_LOCATION_CD);
				statusInquiryResponseMessageOrderTypeBean.setOriginatingLocationDescription(ORIGINAL_LOCATION_DESC);
				statusInquiryResponseMessageOrderTypeBean.setOriginatingSystemCd(DESTINATION);

				statusInquiryResponseMessageOrderTypeBean.setRefTransactionNo(defaultVal);
				//order number from eCommer can't convert to integer
				statusInquiryResponseMessageOrderTypeBean.setRequestId(0);
				statusInquiryResponseMessageOrderTypeBean.setShipVia(defaultVal);
				statusInquiryResponseMessageOrderTypeBean.setShipViaDescription(defaultVal);
				statusInquiryResponseMessageOrderTypeBean.setSourceCode(defaultVal);
				statusInquiryResponseMessageOrderTypeBean.setSpecialInstructions(orderModel.getSpecialInstructions());
				statusInquiryResponseMessageOrderTypeBean.setTransactionSubtotal(orderModel.getSubtotal() != null ? orderModel.getSubtotal().doubleValue() : 0);
				statusInquiryResponseMessageOrderTypeBean.setTransactionTax(orderModel.getTaxAmount() != null ? orderModel.getTaxAmount().doubleValue() : 0);
				statusInquiryResponseMessageOrderTypeBean.setTransactionTotal(orderModel.getTotal() != null ? orderModel.getTotal().doubleValue() : 0);

				// Order Type: 1-PICKUP, 2-DELIVERY, 3-SHIPTOSTORE, 4-RETAILPICKUP,
				// 5-SHIPFORPICKUP
				statusInquiryResponseMessageOrderTypeBean.setOrderType(1);

				// For items information
				// Get data from [dbo].[eni_hub_xom_order_line]				
				List<EniOrderHubOrderLineModel> orderLineModels = EniOrderHubDBHelper.getInstance().retrieveOrderLineData(conn, orgId, orderId);

				statusInquiryResponseMessageItemsTypeBean = new StatusInquiryResponseMessageItemsTypeBean();
				String itemStatus = null;
				if (orderLineModels != null) {
					StatusInquiryResponseMessageItemTypeBean inquiryResponseMessageItemTypeBean = new StatusInquiryResponseMessageItemTypeBean();
                    for(EniOrderHubOrderLineModel orderLineModel : orderLineModels ) {
                    	inquiryResponseMessageItemTypeBean.setFulfillingLocationCd(locationCode);
    					inquiryResponseMessageItemTypeBean.setFulfillingSystemCd(DESTINATION);
    					inquiryResponseMessageItemTypeBean.setItemDescription(defaultVal);
    					inquiryResponseMessageItemTypeBean.setItemId(orderLineModel.getItemId());
    					inquiryResponseMessageItemTypeBean.setItemQty(orderLineModel.getQuantity() != null ? orderLineModel.getQuantity().intValue() : 0);
    					itemStatus = orderLineModel.getStatusCode().toLowerCase();
    					if (ORDER_LINE_CANCELLED_STATUS.equals(itemStatus)) {
    						itemStatus = "canceled";
    					}
    					inquiryResponseMessageItemTypeBean.setItemStatus(itemStatus);
    					inquiryResponseMessageItemTypeBean.setLineNo(orderLineModel.getLineNo());
    					inquiryResponseMessageItemTypeBean.setRequestingSystemLineNo(orderLineModel.getLineNo());
    					inquiryResponseMessageItemTypeBean.setShippingAgent(defaultVal);
    					inquiryResponseMessageItemTypeBean.setSpecialInstructions(defaultVal);
    					inquiryResponseMessageItemTypeBean.setStatusDate(format.format(new Date()));
    					inquiryResponseMessageItemTypeBean.setUnitPrice(orderLineModel.getUnitPrice() != null ? orderLineModel.getUnitPrice().doubleValue() : 0);
    					inquiryResponseMessageItemTypeBean.setShipTo(soldToCustomerTypeBean);

    					taxesTypeBean = new TaxesTypeBean();
    					taxTypeBean = new TaxTypeBean();
    					taxTypeBean.setDescription("Tax");
    					taxTypeBean.setLineItemNo(orderLineModel.getLineNo() + "");
    					taxTypeBean.setAmount(orderLineModel.getTaxAmount());
    					inquiryResponseMessageItemTypeBean.setTaxAmount(taxTypeBean.getAmount().doubleValue());
    					taxesTypeBean.getTax().add(taxTypeBean);
    					inquiryResponseMessageItemTypeBean.setTaxes(taxesTypeBean);

    					// For transaction tenders
    					TransactionTendersTypeBean transactionTendersTypeBean = new TransactionTendersTypeBean();
    					TransactionTenderTypeBean transactionTenderTypeBean = new TransactionTenderTypeBean();
    					transactionTenderTypeBean.setLineItemNo(orderLineModel.getLineNo() + "");
    					transactionTenderTypeBean.setTenderDescription(defaultVal);
    					transactionTenderTypeBean.setTenderAccount(defaultVal);
    					transactionTendersTypeBean.getTransactionTender().add(transactionTenderTypeBean);
    					statusInquiryResponseMessageOrderTypeBean.setTransactionTenders(transactionTendersTypeBean);
    					statusInquiryResponseMessageItemsTypeBean.getItem().add(inquiryResponseMessageItemTypeBean);
    					statusInquiryResponseMessageOrderTypeBean.setItems(statusInquiryResponseMessageItemsTypeBean);
                    }
					
				}

				statusInquiryResponseMessageOrdersTypeBean.getOrder().add(statusInquiryResponseMessageOrderTypeBean);

				statusInquiryResponseMessageBodyTypeBean.setOrders(statusInquiryResponseMessageOrdersTypeBean);
			}
			LOG.info("<<< END STEP2-RETRIEVE ORDER DATA FROM HUB TABLES");

			// Step3 - Close connection to database
			
			LOG.info(">>> START STEP3-CLOSE CONNECTION TO DATABASE");
			EniOrderHubDBHelper.getInstance().closeConnection(conn);
			LOG.info("<<< END STEP3-CLOSE CONNECTION TO DATABASE");

			_return.setMessageHeader(messageHeaderTypeBean);
			_return.setMessageBody(statusInquiryResponseMessageBodyTypeBean);
		} catch (Exception ex) {
			LOG.error(ex);
			throw new RuntimeException(ex);
		}
		LOG.info("<< +[statusRequest] END");
		return _return;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.microsretail.locate.Locate#locateItems(com.microsretail.locate.
	 * LocateItemsRequestMessageTypeBean locationRequestMessage)*
	 */
	public com.microsretail.locate.LocateItemsResponseMessageTypeBean locateItems(
			com.microsretail.locate.LocateItemsRequestMessageTypeBean locationRequestMessage) {
		LOG.info("Executing operation locateItems");
		try {
			com.microsretail.locate.LocateItemsResponseMessageTypeBean _return = null;
			return _return;
		} catch (java.lang.Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.microsretail.locate.Locate#productAvailability(com.microsretail.
	 * locate.AvailabilityByOrderTypeRequestMessageTypeBean
	 * productAvailabilityRequestMessage)*
	 */
	public com.microsretail.locate.ProductAvailabilityResponseMessageTypeBean productAvailability(
			com.microsretail.locate.AvailabilityByOrderTypeRequestMessageTypeBean productAvailabilityRequestMessage) {
		LOG.info("Executing operation productAvailability");
		try {
			com.microsretail.locate.ProductAvailabilityResponseMessageTypeBean _return = null;
			return _return;
		} catch (java.lang.Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.microsretail.locate.Locate#statusUpdate(com.microsretail.locate.
	 * StatusUpdateRequestMessageTypeBean statusUpdateRequestMessage)*
	 */
	public com.microsretail.locate.StatusUpdateResponseMessageTypeBean statusUpdate(
			com.microsretail.locate.StatusUpdateRequestMessageTypeBean statusUpdateRequestMessage) {
        LOG.info(">> +[statusUpdate] START");
        StatusUpdateResponseMessageTypeBean _return = new StatusUpdateResponseMessageTypeBean();
        // Header bean for generating message header
        MessageHeaderTypeBean messageHeaderTypeBean = new MessageHeaderTypeBean();
        // Body bean for generating message body
        StatusUpdateResponseMessageBodyTypeBean statusUpdateResponseMessageBodyTypeBean = new StatusUpdateResponseMessageBodyTypeBean();
        StatusUpdateResponseMessageResponseTypeBean statusUpdateResponseMessageResponseTypeBean = new StatusUpdateResponseMessageResponseTypeBean();
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US);
            format.setTimeZone(TimeZone.getTimeZone("UTC"));
            messageHeaderTypeBean.setXactionType(ACTION_TYPE);
            messageHeaderTypeBean.setDatetime(format.format(new Date()));
            messageHeaderTypeBean.setVersion(statusUpdateRequestMessage.getMessageHeader().getVersion());
            messageHeaderTypeBean.setSource(SOURCE);
            messageHeaderTypeBean.setDestination(DESTINATION);
            statusUpdateResponseMessageResponseTypeBean
                    .setOrderId(statusUpdateRequestMessage.getMessageBody().getOrder().getRequestId());
            statusUpdateResponseMessageResponseTypeBean.setRequestId(getOrderIdInt(statusUpdateRequestMessage.getMessageBody().getOrder().getRequestId()));
            // Extract order data from Xstore request
            // and update status of line items to [dbo].[sci_hub_xom_order_line] accordingly
            /**
             * * Order status: OPEN / CANCELLED, the Item status can be: POLLED
             * -> ACCEPTED or REJECTED. * Order status: FULFILLMENT READY (READY
             * for PICKUP), the Item status is: RESERVED * Order status:
             * COMPLETE, the Item status: FULFILLED
             */
            String orderId = statusUpdateRequestMessage.getMessageBody().getOrder().getOrderId();
            String orderStatus = statusUpdateRequestMessage.getMessageBody().getOrder().getOrderStatus();
            String requestId = statusUpdateRequestMessage.getMessageBody().getOrder().getRequestId();
            String locationCode = statusUpdateRequestMessage.getMessageBody().getOrder().getStoreLocation()
                    .getLocationCd();
             
            StatusUpdateRequestMessageItemsTypeBean statusUpdateRequestMessageItemsTypeBean = statusUpdateRequestMessage
                    .getMessageBody().getOrder().getItems();

            List<StatusUpdateRequestMessageItemTypeBean> statusUpdateRequestMessageItemTypeBeans = statusUpdateRequestMessageItemsTypeBean
                    .getItem();
            // Loop item list to update status for each line item
            int itemSize = statusUpdateRequestMessageItemTypeBeans.size();
            // Item Id
            String itemId = "";
            // Item Id
            int itemLineNo = 0;
            // Line item status code
            String itemStatusCode = "";
            // Result array for executing batch update;
            int[] results = null;
            int result = 0;
            // Flag for E-Commerce send
            //boolean isEcomSentFlag = false;
            
            // Step1 - Open connection to Hub database
            LOG.info(">>> START STEP1-OPEN CONNECTION TO DATABASE");
            Connection conn = EniOrderHubDBHelper.getInstance().openConnection();
            PreparedStatement hubOrderPs = null;
            PreparedStatement hubOrderLinePs = null;
            
            LOG.info("<<< END STEP1-OPEN CONNECTION TO DATABASE");
            
            // Step 2 - Retrieve order data from Hub Order Tables
            // for building message response and return to Xstore
            LOG.info(">>> START STEP2-UPDATE ORDER DATA INTO HUB TABLES");
             
            
            if (LOG.isDebugEnabled()) {
                LOG.debug(">>>>[STEP2][hubOrderQuery]:" + EniOrderHubQueryConstant.HUB_ORDER_STATUS_UPDATE_QUERY);
            }
            
            if (LOG.isDebugEnabled()) {
                LOG.debug(">>>>[STEP2][hubOrderLineQuery]:" + EniOrderHubQueryConstant.HUB_ORDER_LINE_STATUS_UPDATE_QUERY);
            }
         
            hubOrderPs = conn.prepareStatement(EniOrderHubQueryConstant.HUB_ORDER_STATUS_UPDATE_QUERY);
            hubOrderLinePs = conn.prepareStatement(EniOrderHubQueryConstant.HUB_ORDER_LINE_STATUS_UPDATE_QUERY);
            // Use auto commit is false to execute update batch
            conn.setAutoCommit(false);

            for (int i = 0; i < itemSize; i++) {
                // Update data into [dbo].[eni_hub_order_line]
                itemId = statusUpdateRequestMessageItemTypeBeans.get(i).getItemId();
                itemLineNo = statusUpdateRequestMessageItemTypeBeans.get(i).getLineNo();
                itemStatusCode = statusUpdateRequestMessageItemTypeBeans.get(i).getItemStatus();

                if ("canceled".equals(itemStatusCode)) { 
                    itemStatusCode = ORDER_LINE_CANCELLED_STATUS;
                } else if ("rejected".equals(itemStatusCode)) {                  
                    itemStatusCode = ORDER_LINE_UNFULFILLABLE_STATUS;
                }
                hubOrderLinePs.setString(1, itemStatusCode);
                //hubOrderLinePs.setString(2, itemStatusCode);
                hubOrderLinePs.setInt(2, ORGANIZATION_ID_DEFAULT);
                if (orderId == null || "".equals(orderId)) {
                    orderId = requestId;
                    hubOrderLinePs.setString(3, requestId);
                } else {
                    hubOrderLinePs.setString(3, orderId);
                }
                hubOrderLinePs.setString(4, itemId);
                hubOrderLinePs.setInt(5, itemLineNo);
                hubOrderLinePs.addBatch();
                // Check Status Order Line Items to define Status Order
				/*
				 * if (ORDER_LINE_POLLED_STATUS.equals(itemStatusCode) ||
				 * ORDER_LINE_PICKED_STATUS.equals(itemStatusCode) ||
				 * ORDER_LINE_FULFILLED_STATUS.equals(itemStatusCode) ||
				 * ORDER_LINE_CANCELLED_STATUS.equals(itemStatusCode) ||
				 * ORDER_LINE_UNFULFILLABLE_STATUS.equals(itemStatusCode)) { isEcomSentFlag =
				 * true; }
				 */
                if (ORDER_LINE_POLLED_STATUS.equals(itemStatusCode) || ORDER_LINE_ACCEPTED_STATUS.equals(itemStatusCode)
                        || ORDER_LINE_REJECTED_STATUS.equals(itemStatusCode)) {
                    orderStatus = ORDER_OPEN_STATUS;
                } else if (ORDER_LINE_UNFULFILLABLE_STATUS.equals(itemStatusCode)) { 
                    orderStatus = ORDER_LINE_UNFULFILLABLE_STATUS;
                } else if (ORDER_LINE_FULFILLED_STATUS.equals(itemStatusCode)) {
                    orderStatus = ORDER_COMPLETE_STATUS;
                } else if (ORDER_LINE_PICKED_STATUS.equals(itemStatusCode)) {
                    orderStatus = ORDER_READY_FOR_PICKUP_STATUS;
                } else if (ORDER_LINE_CANCELLED_STATUS.equals(itemStatusCode)) { 
                    orderStatus = ORDER_CANCELlED_STATUS;
                }
            }
            if (itemSize > 0) {
                // Execute update batch
            	try {
                results = hubOrderLinePs.executeBatch();
            	}catch(Exception ex) {
            		ex.printStackTrace();
            		LOG.error("Can't update eni_hub_order_line"+ex);
            	}
            }
            LOG.info(">>>>[STEP2][results]:" + results.length);
            // Update data into eni_hub_order
            if (StringUtils.isNotEmpty(orderStatus) && StringUtils.isNotEmpty(orderId)) {
                hubOrderPs.setString(1, orderStatus);
                //hubOrderPs.setString(2, itemStatusCode);
                hubOrderPs.setInt(2, ORGANIZATION_ID_DEFAULT);
                hubOrderPs.setString(3, orderId);
                hubOrderPs.setString(4, locationCode);

                // Execute update
                try {
                result = hubOrderPs.executeUpdate();
                
                }catch(Exception ex) {
                	ex.printStackTrace();
                	LOG.error("Can't update order status for eni_hub_order"+ex);
                }
                LOG.info(">>>>[STEP2][result]:" + result);
                // Update ecom_sent_flag to 0 to allow job sends status to E-Commerce
				/*
				 * if (result > 0 & isEcomSentFlag) { hubOrderQuery =
				 * "UPDATE [dbo].[eni_hub_order] SET ecom_sent_flag = 0 WHERE organization_id = ? AND order_id = ? AND order_loc_id = ?"
				 * ;
				 * 
				 * hubOrderPs = conn.prepareStatement(hubOrderQuery); hubOrderPs.setInt(1,
				 * ORGANIZATION_ID_DEFAULT); hubOrderPs.setString(2, orderId);
				 * hubOrderPs.setString(3, locationCode);
				 * 
				 * // Execute update result = hubOrderPs.executeUpdate();
				 * LOG.info(">>>>[STEP2.1][result]:" + result); }
				 */
              
            }
            LOG.info("<<< END STEP2-UPDATE ORDER DATA INTO HUB TABLES");

            // Commit all changes into [dbo].[eni_hub_order] and [dbo].[eni_hub_order_line]
            conn.commit();
			 
			
            // Step3 - Close connection to database
            // Close Statements & ResultSets
            if (hubOrderLinePs != null) {
                hubOrderLinePs.close();
            }
            if (hubOrderPs != null) {
                hubOrderPs.close();
            }
					
			LOG.info(">>> START STEP3-CLOSE CONNECTION TO DATABASE");
	         // Close local connection
	          conn.close();
	        // Close global connection
	         EniOrderHubDBHelper.getInstance().closeConnection(conn);
	         LOG.info("<<< END STEP3-CLOSE CONNECTION TO DATABASE");


            // For status update successfully
            statusUpdateResponseMessageResponseTypeBean.setResponseCode(STATUS_UPDATE_RESPONSE_SUCCESS_CODE);
            statusUpdateResponseMessageResponseTypeBean.setResponseDescription(STATUS_UPDATE_RESPONSE_SUCCESS_DESC);
        } catch (java.lang.Exception ex) {
            LOG.error(ex);
            // For status update failed with any reasons
            statusUpdateResponseMessageResponseTypeBean.setResponseCode(STATUS_UPDATE_RESPONSE_FAIL_CODE);
            statusUpdateResponseMessageResponseTypeBean.setResponseDescription(STATUS_UPDATE_RESPONSE_FAIL_DESC);
            // Rollback all changes
			/*
			 * try { conn.rollback(); } catch (SQLException se) { se.printStackTrace();
			 * LOG.error(se); }
			 */
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        statusUpdateResponseMessageBodyTypeBean.setResponse(statusUpdateResponseMessageResponseTypeBean);
        _return.setMessageHeader(messageHeaderTypeBean);
        _return.setMessageBody(statusUpdateResponseMessageBodyTypeBean);
        LOG.info("<< +[statusUpdate] END");
        return _return;
    }

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.microsretail.locate.Locate#statusListRequest(com.microsretail.locate.
	 * StatusListInquiryRequestMessageTypeBean statusListRequestMessage)*
	 */
	public com.microsretail.locate.StatusListInquiryResponseMessageTypeBean statusListRequest(
			com.microsretail.locate.StatusListInquiryRequestMessageTypeBean statusListRequestMessage) {
		LOG.info("Executing operation statusListRequest");
		try {
			com.microsretail.locate.StatusListInquiryResponseMessageTypeBean _return = null;
			return _return;
		} catch (java.lang.Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.microsretail.locate.Locate#orderUpdate(com.microsretail.locate.
	 * OrderUpdateRequestMessageTypeBean orderUpdateRequestMessage)*
	 */
	public com.microsretail.locate.OrderUpdateResponseMessageTypeBean orderUpdate(
			com.microsretail.locate.OrderUpdateRequestMessageTypeBean orderUpdateRequestMessage) {
		LOG.info("Executing operation orderUpdate");
		try {
			com.microsretail.locate.OrderUpdateResponseMessageTypeBean _return = null;
			return _return;
		} catch (java.lang.Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
	}

	@Override
	public LocateItemsResponseMessageTypeBean locateItems(LocateItemsRequestMessageHeaderTypeBean messageHeader,
			LocateIemsRequestMessageBodyTypeBean messageBody) throws InvalidSoapRequestException {
		// TODO Auto-generated method stub
		return null;
	}
}
