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

package eni.hub.order.db;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import dtv.util.crypto.IDtvDecrypter;
import eni.hub.order.EniOrderStatus;
import eni.hub.order.util.EniOrderHubConstants;
import eni.hub.order.db.obj.EniOrderHubCustomerDBA;
import eni.hub.order.db.obj.EniOrderHubCustomerModel;
import eni.hub.order.db.obj.EniOrderHubOrderDBA;
import eni.hub.order.db.obj.EniOrderHubOrderLineDBA;
import eni.hub.order.db.obj.EniOrderHubOrderLineModel;
import eni.hub.order.db.obj.EniOrderHubOrderModel;
import eni.hub.order.db.obj.EniOrderHubRetailLocationDBA;
import eni.hub.order.db.obj.EniOrderHubRetailLocationModel;
import eni.hub.order.db.obj.EniOrderHubTenderDBA;
import eni.hub.order.db.obj.EniOrderHubTenderModel;
import eni.hub.order.util.EniOrderHubUtils;

public class EniOrderHubDBHelper {

	private static Logger LOG = Logger.getLogger(EniOrderHubDBHelper.class);

	// private Connection conn_ = null;
	/**
	 * static Singleton instance.
	 */
	private static volatile EniOrderHubDBHelper instance;

	/**
	 * Private constructor for singleton.
	 */
	private EniOrderHubDBHelper() {
	}

	/**
	 * Return a singleton instance of BTMDataSourceHelper.
	 */
	public static EniOrderHubDBHelper getInstance() {
		// Double lock for thread safety.
		if (instance == null) {
			synchronized (EniOrderHubDBHelper.class) {
				if (instance == null) {
					instance = new EniOrderHubDBHelper();
				}
			}
		}
		return instance;
	}

	public Connection initConnection(IDtvDecrypter decrypter) {
		LOG.info("<<<< +[openConnection] END");
		LOG.info(">>>> +[initConnection] START");
		Connection conn_ = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			// For Order Hub DB configuration
			/*
			 * String url =
			 * EniOrderHubUtils.getConfigProperties(EniOrderHubConstants.ORDER_HUB_DB_URL);
			 */
			String url="jdbc:sqlserver://vnevenxou03.clienttest.btmgcs.com:1433;databaseName=ENI_HUB";
			if (LOG.isDebugEnabled()) {
				LOG.debug(">>>>>> +[initConnection] -> url: " + url);
			}
			/*
			 * String user =
			 * EniOrderHubUtils.getConfigProperties(EniOrderHubConstants.ORDER_HUB_DB_USER);
			 */
			String user ="HubLogin";
			if (LOG.isDebugEnabled()) {
				LOG.debug(">>>>>> +[initConnection] -> user is encrypted: " + user);
			}
			/*
			 * String password =
			 * EniOrderHubUtils.getBase64DeCode(EniOrderHubUtils.getConfigProperties(
			 * EniOrderHubConstants.ORDER_HUB_DB_PWD));
			 */
			String password = "Btmgcs@123";
			if (LOG.isDebugEnabled()) {
				LOG.debug(">>>>>> +[initConnection] -> password is encrypted: " + password);
			}

			user = decrypter.decryptIfEncrypted(user);
			if (LOG.isDebugEnabled()) {
				LOG.debug(">>>>>> +[initConnection] -> user is decrypted: " + user);
			}
			password = decrypter.decryptIfEncrypted(password);
			if (LOG.isDebugEnabled()) {
				LOG.debug(">>>>>> +[initConnection] -> password is decrypted: " + password);
			}

			conn_ = DriverManager.getConnection(url, user, password);
			if (conn_ == null) {
				LOG.error("Cannot to Order Hub DB database!!!");
			}
		} catch (Exception ex) {
			LOG.error(ex);
		}
		LOG.info("<<<< +[initConnection] END");
		return conn_;
	}
   
	public int insertData(Connection conn_, String query) {
		LOG.info(">>>> +[insertData] START");
		if (LOG.isDebugEnabled()) {
			LOG.debug(">>>>>> +[insertData]->query " + query);
		}
		Statement st = null;
		int result = 0;
		if (conn_ == null) {
			LOG.error("Cannot to Order Hub DB database!!!");
			return result;
		}
		try {
			st = conn_.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			result = st.executeUpdate(query);

			st.close();
		} catch (Exception ex) {
			try {
				st.close();
			} catch (SQLException se) {
				LOG.error("SQLException: " + se);
			}
			LOG.error("Exception: " + ex);
		}
		LOG.info("<<<< +[insertData] END");
		return result;
	}
    public boolean checkExistOrder(Connection conn, EniOrderHubOrderModel argOrderModel) {
    	LOG.info(">>>> +[checkExistOrder] START");
    	boolean result = false;
    	PreparedStatement preparesStatement =null;
    	ResultSet resultset = null;
    	if(conn == null) {
    		LOG.error("Can't connect to DB");
    		 result = true;
    	}
    	try {
    		EniOrderHubOrderDBA dbaObj = new EniOrderHubOrderDBA();
    		//dbaObj.fillDAO(argOrderModel.getDAO());
    		preparesStatement = conn.prepareStatement(dbaObj.getOrderStatusQuery());
    		preparesStatement = dbaObj.initParameterForRetrieveOrderStatus(preparesStatement, 1, argOrderModel.getOrderId(), argOrderModel.getOrderLocId());
    		resultset = preparesStatement.executeQuery();
    	    if(resultset.next()) {
    	    	result = true;
    	    }
    	}catch(Exception ex) {
    		LOG.error("SQLException: " + ex);
    	}
    	LOG.info("<<<< +[checkExistOrder] END");
    	return result;
    }
    /**
     * Check to update status for order in eni_hub_order
     * @param argConn_
     * @param argOrderModel
     * @return
     */
    public int updateStatusEniOrder(Connection argConn_, EniOrderHubOrderModel argOrderModel) {
    	LOG.info(">>>> +[updateStatusEniOrder] START");
    	PreparedStatement ps = null;
    	int result = 0;
    	if(argConn_  == null) {
    		LOG.error("Cannot to Order Hub DB database!!!");
			return result;
    	}try {
    		EniOrderHubOrderDBA eniOrderDBA = new EniOrderHubOrderDBA(); 
    		eniOrderDBA.fillDAO(argOrderModel.getDAO());
    		ps = argConn_.prepareStatement(eniOrderDBA.getUpdateStatusQuery());
    		ps = eniOrderDBA.initUpdateStatusParam(ps);
    		result = ps.executeUpdate();
    		ps.close();
    	}catch(SQLException ex) {
    		ex.printStackTrace();
    	}
    	
    	return result;
    }
	/***
	 * Check to insert data in eni_hub_order table
	 * 
	 * @param query
	 * @param map
	 * @return
	 */
	public int insertDataEniOrder(Connection argConn_, EniOrderHubOrderModel argOrderModel) {

		LOG.info(">>>> +[insertData] START");
		PreparedStatement ps = null;
		int result = 0;
		if (argConn_ == null) {
			LOG.error("Cannot to Order Hub DB database!!!");
			return result;
		}
		try {
			EniOrderHubOrderDBA dbaObj = new EniOrderHubOrderDBA();
			dbaObj.fillDAO(argOrderModel.getDAO());
			ps = argConn_.prepareStatement(dbaObj.getInsertQuery());
			ps = dbaObj.initParameterForInsert(ps);
			result = ps.executeUpdate();
			ps.close();

		} catch (Exception ex) {
			LOG.error("Exception: " + ex);
			try {
				ps.close();
			} catch (SQLException se) {
				LOG.error("SQLException: " + se);
			}
			LOG.error("Exception: " + ex);
		}
		LOG.info("<<<< +[insertData] END");
		return result;
	}

	public List<EniOrderHubOrderModel> retrieveOrderDataForOrderSearch(Connection argConn_, int argOrganizationId,
			String argOrderLocationId) {
		LOG.info(">>>> +[retrieveDataForOrderSearch] START");

		PreparedStatement ps = null;
		List<EniOrderHubOrderModel> orders = null;

		if (argConn_ == null) {
			LOG.error("Cannot connect to Order Hub DB database!!!");
			return null;
		}
		try {
			EniOrderHubOrderDBA dbaObj = new EniOrderHubOrderDBA();
			ps = argConn_.prepareStatement(dbaObj.getOrderSearchQuery(), ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			ps = dbaObj.initParameterForRetrieveOrderSearch(ps, argOrganizationId, argOrderLocationId);
			orders = excuteRetreiveOrderData(ps);
			ps.close();
		} catch (Exception ex) {
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException se) {
				LOG.error("SQLException: " + se);
			}
			LOG.error("Exception: " + ex);
		}
		if (LOG.isDebugEnabled()) {
			LOG.debug(">>>>>> +[retrieveDataForOrderSearch]->orders " + orders);
		}
		LOG.info("<<<< +[retrieveDataForOrderSearch] END");
		return orders;
	}
	
	public List<EniOrderHubOrderModel> retrieveOrderDataForOrderFullfillment(Connection argConn_, int argOrganizationId,
			String argOrderLocationId) {
		LOG.info(">>>> +[retrieveOrderDataForOrderFullfillment] START");

		PreparedStatement ps = null;
		List<EniOrderHubOrderModel> orders = null;

		if (argConn_ == null) {
			LOG.error("Cannot connect to Order Hub DB database!!!");
			return null;
		}
		try {
			EniOrderHubOrderDBA dbaObj = new EniOrderHubOrderDBA();
			ps = argConn_.prepareStatement(dbaObj.getOrderFullfillmentQuery(), ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			ps = dbaObj.initParameterForRetrieveOrderFullfillment(ps, argOrganizationId, argOrderLocationId);
			orders = excuteRetreiveOrderData(ps);
			ps.close();
		} catch (Exception ex) {
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException se) {
				LOG.error("SQLException: " + se);
			}
			LOG.error("Exception: " + ex);
		}
		if (LOG.isDebugEnabled()) {
			LOG.debug(">>>>>> +[retrieveOrderDataForOrderFullfillment]->orders " + orders);
		}
		LOG.info("<<<< +[retrieveOrderDataForOrderFullfillment] END");
		return orders;
	}
	
	public List<EniOrderHubOrderModel> retrieveOrderDataForOrderIntransit(Connection argConn_, int argOrganizationId,
			String argOrderLocationId) {
		LOG.info(">>>> +[retrieveOrderDataForOrderIntransit] START");

		PreparedStatement ps = null;
		List<EniOrderHubOrderModel> orders = null;

		if (argConn_ == null) {
			LOG.error("Cannot connect to Order Hub DB database!!!");
			return null;
		}
		try {
			EniOrderHubOrderDBA dbaObj = new EniOrderHubOrderDBA();
			ps = argConn_.prepareStatement(dbaObj.getOrderFullfillmentQuery(), ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			ps = dbaObj.initParameterForRetrieveOrderIntransit(ps, argOrganizationId, argOrderLocationId);
			orders = excuteRetreiveOrderData(ps);
			ps.close();
		} catch (Exception ex) {
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException se) {
				LOG.error("SQLException: " + se);
			}
			LOG.error("Exception: " + ex);
		}
		if (LOG.isDebugEnabled()) {
			LOG.debug(">>>>>> +[retrieveOrderDataForOrderIntransit]->orders " + orders);
		}
		LOG.info("<<<< +[retrieveOrderDataForOrderIntransit] END");
		return orders;
	}
	
	public EniOrderHubOrderModel retrieveOrderDataForOrderStatus(Connection argConn_, int argOrganizationId, String argOrderId,
			String argOrderLocationId) {
		LOG.info(">>>> +[retrieveOrderDataForOrderStatus] START");

		PreparedStatement ps = null;
		EniOrderHubOrderModel order = null;

		if (argConn_ == null) {
			LOG.error("Cannot connect to Order Hub DB database!!!");
			return null;
		}
		try {
			EniOrderHubOrderDBA dbaObj = new EniOrderHubOrderDBA();
			ps = argConn_.prepareStatement(dbaObj.getOrderStatusQuery(), ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			ps = dbaObj.initParameterForRetrieveOrderStatus(ps, argOrganizationId, argOrderId, argOrderLocationId);
			List<EniOrderHubOrderModel> orders = excuteRetreiveOrderData(ps);
			if (orders != null && orders.size() > 0) {
				order = orders.get(0);
			}
			ps.close();
		} catch (Exception ex) {
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException se) {
				LOG.error("SQLException: " + se);
			}
			LOG.error("Exception: " + ex);
		}
		if (LOG.isDebugEnabled()) {
			LOG.debug(">>>>>> +[retrieveOrderDataForOrderStatus]->order " + order);
		}
		LOG.info("<<<< +[retrieveOrderDataForOrderStatus] END");
		return order;
	}

	private List<EniOrderHubOrderModel> excuteRetreiveOrderData(PreparedStatement argPreparedStatement) {
		List<EniOrderHubOrderModel> orders = new ArrayList<EniOrderHubOrderModel>();
		ResultSet rs = null;
		try {
			rs = argPreparedStatement.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					EniOrderHubOrderModel order = new EniOrderHubOrderModel();
					order.setOrganizationId(rs.getInt("organization_id"));
					order.setOrderId(rs.getString("order_id"));
					order.setOrderLocId(rs.getString("order_loc_id"));
					order.setOrderType(rs.getString("order_type"));
					order.setRefNbr(rs.getString("ref_nbr"));
					order.setBalanceDue(rs.getBigDecimal("balance_due"));
					order.setFreight(rs.getBigDecimal("freight"));
					order.setFreightTax(rs.getBigDecimal("freight_tax"));
					order.setShipViaDescription(rs.getString("ship_via_description"));
					order.setShipVia(rs.getString("ship_via"));
					order.setSpecialInstructions(rs.getString("special_instructions"));
					order.setTotal(rs.getBigDecimal("total"));
					order.setTaxAmount(rs.getBigDecimal("tax_amount"));
					order.setSubtotal(rs.getBigDecimal("subtotal"));
					order.setOrderDate(rs.getDate("order_date"));
					order.setStatusCode(rs.getString("status_code"));
					order.setUnderReviewFlag(rs.getBoolean("under_review_flag"));
					order.setAdditionalFreightCharges(rs.getBigDecimal("additional_freight_charges"));
					order.setAdditionalCharges(rs.getBigDecimal("additional_charges"));
					order.setShipCompleteFlag(rs.getBoolean("ship_complete_flag"));
					order.setFreightTax(rs.getBigDecimal("freight_tax"));
					order.setOrderMessage(rs.getString("order_message"));
					order.setGift(rs.getString("gift"));
					order.setGiftMessage(rs.getString("gift_message"));

					orders.add(order);
				}
			}
		} catch (Exception ex) {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException se) {
				LOG.error("SQLException: " + se);
			}
			LOG.error("Exception: " + ex);
		}
		return orders;

	}

	public List<EniOrderHubOrderLineModel> retrieveOrderLineData(Connection argConn_, int argOrganizationId,
			String argOrderId) {
		List<EniOrderHubOrderLineModel> orderLineModels = new ArrayList<EniOrderHubOrderLineModel>();
		
		LOG.info(">>>> +[retrieveOrderLineDataForOrderSearch] START");

		PreparedStatement ps = null;
		ResultSet rs = null;
		EniOrderHubOrderLineModel orderLineModel = null;

		if (argConn_ == null) {
			LOG.error("Cannot connect to Order Hub DB database!!!");
			return null;
		}
		try {
			EniOrderHubOrderLineDBA dbaObj = new EniOrderHubOrderLineDBA();
			ps = argConn_.prepareStatement(dbaObj.getSelectQuery(), ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			ps = dbaObj.initParameterForRetrieveOrderLineData(ps, argOrganizationId, argOrderId);
			rs = ps.executeQuery();
			if(rs !=null) {
				while(rs.next()) {
					orderLineModel = new EniOrderHubOrderLineModel();
					orderLineModel.setOrganizationId(rs.getInt("organization_id"));
					orderLineModel.setOrderId(rs.getString("order_id"));
					orderLineModel.setLineNo(rs.getInt("line_no"));
					orderLineModel.setItemUpcCode(rs.getString("item_upc_code"));
					orderLineModel.setItemEanCode(rs.getString("item_ean_code"));
					orderLineModel.setExternalOrderId(rs.getString("external_order_id"));
					orderLineModel.setItemId(rs.getString("item_id"));
					orderLineModel.setQuantity(rs.getBigDecimal("quantity"));
					orderLineModel.setFulfillmentType(rs.getString("fulfillment_type"));
					orderLineModel.setStatusCode(rs.getString("status_code"));
					orderLineModel.setUnitPrice(rs.getBigDecimal("unit_price"));
					orderLineModel.setExtendedPrice(rs.getBigDecimal("extended_price"));
					orderLineModel.setTaxAmount(rs.getBigDecimal("tax_amount"));
					orderLineModel.setSpecialInstructions(rs.getString("special_instructions"));
					orderLineModel.setTrackingNbr(rs.getString("tracking_nbr"));
					orderLineModel.setVoidFlag(rs.getBoolean("void_flag"));
					orderLineModel.setActualShipMethod(rs.getString("actual_ship_method"));
					orderLineModel.setDropShipFlag(rs.getBoolean("drop_ship_flag"));
					orderLineModel.setStatusCodeReason(rs.getString("status_code_reason"));
					orderLineModel.setStatusCodeReasonNote(rs.getString("status_code_reason_note"));
					orderLineModel.setExtendedFreight(rs.getBigDecimal("extended_freight"));
					orderLineModel.setCustomizationCharge(rs.getBigDecimal("customization_charge"));
					orderLineModel.setGiftWrapFlag(rs.getBoolean("gift_wrap_flag"));
					orderLineModel.setShipAloneFlag(rs.getBoolean("ship_alone_flag"));
					orderLineModel.setShipWeight(rs.getBigDecimal("ship_weight"));
					orderLineModel.setLineMessage(rs.getString("line_message"));
					orderLineModel.setPickupByDate(rs.getDate("pickup_by_date"));
					/*
					 * orderLineModel.setCustomizationCode(rs.getString("customization_code"));
					 * orderLineModel.setCustomizationMessage(rs.getString("customization_message"))
					 * ; orderLineModel.setCartonNumber(rs.getString("carton_number"));
					 */
					orderLineModel.setUpdateDate(rs.getDate("update_date"));
					orderLineModels.add(orderLineModel);
				}
			}
			rs.close();
			ps.close();
		} catch (Exception ex) {
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException se) {
				LOG.error("SQLException: " + se);
			}
			LOG.error("Exception: " + ex);
		}
		if (LOG.isDebugEnabled()) {
			LOG.debug(">>>>>> +[retrieveOrderLineDataForOrderSearch]->orders " + orderLineModel);
		}
		LOG.info("<<<< +[retrieveOrderLineDataForOrderSearch] END");
		return orderLineModels;
	}
	
	public EniOrderHubRetailLocationModel retrieveRetailLocationData(Connection argConn_, int argOrganizationId,
			String argRltLocId) {
		LOG.info(">>>> +[retrieveRetailLocationData] START");

		PreparedStatement ps = null;
		ResultSet rs = null;
		EniOrderHubRetailLocationModel rtlLocModel = null;

		if (argConn_ == null) {
			LOG.error("Cannot connect to Order Hub DB database!!!");
			return null;
		}
		try {
			EniOrderHubRetailLocationDBA dbaObj = new EniOrderHubRetailLocationDBA();
			ps = argConn_.prepareStatement(dbaObj.getSelectQuery(), ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			ps = dbaObj.initParameterForRetrieveRetailLocationData(ps, argOrganizationId, argRltLocId);
			rs = ps.executeQuery();
			if (rs.next()) {
				rtlLocModel = new EniOrderHubRetailLocationModel();
				rtlLocModel.setOrganizationId(rs.getInt("organization_id"));				
				rtlLocModel.setRtlLocId(rs.getString("rtl_loc_id"));
				rtlLocModel.setStoreNbr(rs.getString("store_name"));
				rtlLocModel.setAddress1(rs.getString("address1"));
				rtlLocModel.setAddress2(rs.getString("address2"));
				rtlLocModel.setAddress3(rs.getString("address3"));
				rtlLocModel.setAddress4(rs.getString("address4"));
				rtlLocModel.setCity(rs.getString("city"));
				rtlLocModel.setState(rs.getString("state"));
				rtlLocModel.setDistrict(rs.getString("district"));
				rtlLocModel.setArea(rs.getString("area"));
				rtlLocModel.setPostalCode(rs.getString("postal_code"));
				rtlLocModel.setCounty(rs.getString("county"));
				rtlLocModel.setNeighborhood(rs.getString("neighborhood"));
				rtlLocModel.setCountry(rs.getString("country"));
				rtlLocModel.setLocale(rs.getString("locale"));
				rtlLocModel.setCurrencyId(rs.getString("currency_id"));
				rtlLocModel.setLatitude(rs.getBigDecimal("latitude"));
				rtlLocModel.setLongitude(rs.getBigDecimal("longitude"));
				rtlLocModel.setTelephone1(rs.getString("telephone1"));
				rtlLocModel.setTelephone2(rs.getString("telephone2"));
				rtlLocModel.setTelephone3(rs.getString("telephone3"));
				rtlLocModel.setTelephone4(rs.getString("telephone4"));
				rtlLocModel.setDescription(rs.getString("description"));
				rtlLocModel.setStoreNbr(rs.getString("store_nbr"));
				rtlLocModel.setApartment(rs.getString("apartment"));
				rtlLocModel.setStoreManager(rs.getString("store_manager"));
				rtlLocModel.setEmailAddr(rs.getString("email_addr"));
				rtlLocModel.setDefaultTaxPercentage(rs.getBigDecimal("default_tax_percentage"));
				rtlLocModel.setDeliveryAvailableFlag(rs.getBoolean("delivery_available_flag"));
				rtlLocModel.setLocationType(rs.getString("location_type"));
				rtlLocModel.setPickupAvailableFlag(rs.getBoolean("pickup_available_flag"));
				rtlLocModel.setTransferAvailableFlag(rs.getBoolean("transfer_available_flag"));
				rtlLocModel.setGeoCode(rs.getString("geo_code"));
				rtlLocModel.setUezFlag(rs.getBoolean("uez_flag"));
				rtlLocModel.setAlternateStoreNbr(rs.getString("alternate_store_nbr"));
				rtlLocModel.setUseTillAccountabilityFlag(rs.getBoolean("use_till_accountability_flag"));
				rtlLocModel.setDepositBankName(rs.getString("deposit_bank_name"));
				rtlLocModel.setDepositBankAccountNumber(rs.getString("deposit_bank_account_number"));
				rtlLocModel.setAirportCode(rs.getString("airport_code"));
				rtlLocModel.setLegalEntityId(rs.getString("legal_entity_id"));
			}
			rs.close();
			ps.close();
		} catch (Exception ex) {
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException se) {
				LOG.error("SQLException: " + se);
			}
			LOG.error("Exception: " + ex);
		}
		if (LOG.isDebugEnabled()) {
			LOG.debug(">>>>>> +[retrieveRetailLocationData]->orders " + rtlLocModel);
		}
		LOG.info("<<<< +[retrieveRetailLocationData] END");
		return rtlLocModel;
	}
	/**
	 * Update order line status in eni_order_hub_line
	 * @param argConn_
	 * @param argOrderLineModel
	 * @return
	 */
   public int updateStatusOrderLine(Connection argConn_, EniOrderHubOrderLineModel argOrderLineModel) {
		LOG.info(">>>> +[updateStatusOrderLine] START");
		PreparedStatement ps =null;
		int result =0;
		if(argConn_== null) {
			LOG.error("Cannot connect to Order hub line DB database !!!");
			return result;
		}
		try {
			EniOrderHubOrderLineDBA eniOrderLineHub = new EniOrderHubOrderLineDBA();
			eniOrderLineHub.fillDAO(argOrderLineModel.getDAO());
			ps = argConn_.prepareStatement(eniOrderLineHub.getOrderLineStatusUpdateQuery());
			ps = eniOrderLineHub.initParameterForUpdateStatus(ps);
			result = ps.executeUpdate();
			ps.close();
		}catch(Exception ex) {
			try {
				ps.close();
			} catch (SQLException se) {
				LOG.error("SQLException: " + se);
			}
			LOG.error("Exception: " + ex);
		}
		LOG.info("<<<< +[insertData] END");
		return result;
   }
	/***
	 * Check to insert into eni_hub_xom_order_line table
	 * 
	 * @param query
	 * @param map
	 * @return
	 */
	public int insertDataOrderLine(Connection argConn_, EniOrderHubOrderLineModel argOrderLineModel) {

		LOG.info(">>>> +[insertData] START");
		PreparedStatement ps = null;
		int result = 0;
		if (argConn_ == null) {
			LOG.error("Cannot to Order Hub DB database!!!");
			return result;
		}
		try {

			EniOrderHubOrderLineDBA dbaObj = new EniOrderHubOrderLineDBA();
			dbaObj.fillDAO(argOrderLineModel.getDAO());
			ps = argConn_.prepareStatement(dbaObj.getInsertQuery());
			ps = dbaObj.initParameterForInsert(ps);
			result = ps.executeUpdate();
			ps.close();

		} catch (Exception ex) {
			try {
				ps.close();
			} catch (SQLException se) {
				LOG.error("SQLException: " + se);
			}
			LOG.error("Exception: " + ex);
		}
		LOG.info("<<<< +[insertData] END");
		return result;
	}

	public int insertDataOrderCustomer(Connection argConn_, EniOrderHubCustomerModel argCustomerModel) {
		LOG.info(">>>> +[insertDataXomOrderCustomer] START");
		PreparedStatement ps = null;
		int result = 0;
		if (argConn_ == null) {
			LOG.error("Cannot to Order Hub database!!!");
			return result;
		}
		try {
			EniOrderHubCustomerDBA dbaObj = new EniOrderHubCustomerDBA();
			dbaObj.fillDAO(argCustomerModel.getDAO());
			ps = argConn_.prepareStatement(dbaObj.getInsertQuery());
			ps = dbaObj.initParameterForInsertCustomerData(ps);
			result = ps.executeUpdate();
			ps.close();

		} catch (Exception ex) {
			try {
				ps.close();
			} catch (SQLException se) {
				LOG.error("SQLException: " + se);
			}
			LOG.error("Exception: " + ex);
		}
		LOG.info("<<<< +[insertDataXomOrderCustomer] END");
		return result;
	}

	public EniOrderHubCustomerModel retrieveCustomerData(Connection argConn_, int argOrganizationId,
			String argOrderId) {
		LOG.info(">>>> +[retrieveCustomerData] START");

		PreparedStatement ps = null;
		ResultSet rs = null;
		EniOrderHubCustomerModel customer = null;

		if (argConn_ == null) {
			LOG.error("Cannot connect to Order Hub DB database!!!");
			return null;
		}
		try {
			EniOrderHubCustomerDBA dbaObj = new EniOrderHubCustomerDBA();
			ps = argConn_.prepareStatement(dbaObj.getSelectQuery(), ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			ps = dbaObj.initParameterForRetrieveCustomerData(ps, argOrganizationId, argOrderId);
			rs = ps.executeQuery();
			if (rs.next()) {
				customer = new EniOrderHubCustomerModel();
				customer.setOrganizationId(rs.getInt("organization_id"));
				customer.setOrderId(rs.getString("order_id"));
				customer.setCustomerId(rs.getString("customer_id"));
				customer.setFirstName(rs.getString("first_name"));
				customer.setMiddleName(rs.getString("middle_name"));
				customer.setLastName(rs.getString("last_name"));
				customer.setTelephone1(rs.getString("telephone1"));
				customer.setTelephone2(rs.getString("telephone2"));
				customer.setEmailaddress(rs.getString("email_address"));
				customer.setAddress1(rs.getString("address_1"));
				customer.setAddress2(rs.getString("address_2"));
				customer.setAddress3(rs.getString("address_3"));
				customer.setAddress4(rs.getString("address_4"));
				customer.setCity(rs.getString("city"));
				customer.setCountry(rs.getString("country"));
				customer.setCompanyName(rs.getString("company_name"));
				customer.setProvince(rs.getString("province"));
				customer.setPostal(rs.getString("postal"));
				customer.setPrefix(rs.getString("prefix"));
				customer.setSuffix(rs.getString("suffix"));
				customer.setApt(rs.getString("apt"));
			}
			ps.close();
		} catch (Exception ex) {
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException se) {
				LOG.error("SQLException: " + se);
			}
			LOG.error("Exception: " + ex);
		}
		if (LOG.isDebugEnabled()) {
			LOG.debug(">>>>>> +[retrieveCustomerData]->customer " + customer);
		}
		LOG.info("<<<< +[retrieveCustomerData] END");
		return customer;
	}

	/***
	 * Check to insert data into eni_hub_tender table
	 * 
	 * @param query
	 * @param map
	 * @return
	 */
	public int insertDataXomOrderTender(Connection conn_, String query, Map<Integer, Object> map) {

		LOG.info(">>>> +[insertData] START");
		PreparedStatement ps = null;
		int result = 0;
		if (conn_ == null) {
			LOG.error("Cannot connect to Order Hub DB database!!!");
			return result;
		}
		try {
			ps = conn_.prepareStatement(query);
			ps.setString(1, (String) map.get(1));
			ps.setInt(2, (int) map.get(2));
			ps.setString(3, (String) map.get(3));
			ps.setBigDecimal(4, (BigDecimal) map.get(4));
			ps.setString(5, (String) map.get(5));

			if (LOG.isDebugEnabled()) {
				LOG.debug(">>>>>> +[insertData]-> query " + query);
				LOG.debug(">>>>>> +[insertData]-> values: " + map.get(1) + "," + map.get(2) + "," + map.get(3) + ","
						+ map.get(4) + "," + map.get(5));
			}
			result = ps.executeUpdate();
			ps.close();

		} catch (Exception ex) {
			try {
				ps.close();
			} catch (SQLException se) {
				LOG.error("SQLException: " + se);
			}
			LOG.error("Exception: " + ex);
		}
		LOG.info("<<<< +[insertData] END");
		return result;
	}

	public EniOrderHubTenderModel retrieveTenderData(Connection argConn_, String argOrderId) {
		LOG.info(">>>> +[retrieveTenderData] START");

		PreparedStatement ps = null;
		ResultSet rs = null;
		EniOrderHubTenderModel tender = null;

		if (argConn_ == null) {
			LOG.error("Cannot connect to Order Hub DB database!!!");
			return null;
		}
		try {
			EniOrderHubTenderDBA dbaObj = new EniOrderHubTenderDBA();
			ps = argConn_.prepareStatement(dbaObj.getSelectQuery(), ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			ps = dbaObj.initParameterForRetrieveTenderData(ps, argOrderId);
			rs = ps.executeQuery();
			if (rs.next()) {
				tender = new EniOrderHubTenderModel();
				//tender.setOrganizationId(rs.getInt("organization_id"));
				tender.setOrderId(rs.getString("order_id"));
				tender.setLineNo(rs.getInt("line_no"));
				tender.setTenderDescription(rs.getString("tender_description"));
				tender.setTenderAmount(rs.getBigDecimal("tender_amount"));
				tender.setTenderAccount(rs.getString("tender_account"));
			}
			ps.close();
		} catch (Exception ex) {
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException se) {
				LOG.error("SQLException: " + se);
			}
			LOG.error("Exception: " + ex);
		}
		if (LOG.isDebugEnabled()) {
			LOG.debug(">>>>>> +[retrieveTenderData]->customer " + tender);
		}
		LOG.info("<<<< +[retrieveTenderData] END");
		return tender;
	}

	/***
	 * Begin transaction object setAutoCommit = false
	 */
	public void beginTransaction(Connection conn_) {
		LOG.info(">>>> +[beginConnection] START");
		try {
			if (conn_ != null) {
				LOG.info(">>>> +[beginConnection] setAutoCommit");
				conn_.setAutoCommit(false);
			}
		} catch (SQLException se) {
			LOG.error("beginConnection" + se);
		}
		LOG.info("<<<< +[beginConnection] END");
	}

	/***
	 * Commit transaction object
	 */
	public void commitTransaction(Connection conn_) {
		LOG.info(">>>> +[commitConnection] START");
		try {
			if (conn_ != null) {
				conn_.commit();
			}
		} catch (SQLException se) {
			LOG.error("commitConnection" + se);
		}
		LOG.info("<<<< +[commitConnection] END");
	}

	public int updateData(Connection conn_, String query) {
		LOG.info(">>>> +[updateData] START");
		if (LOG.isDebugEnabled()) {
			LOG.debug(">>>>>> +[updateData]->query " + query);
		}

		try {
			if (conn_.isClosed()) {
				LOG.info(">>>>> +[updateData] BEGIN re-connection");
				openConnection();
				LOG.error(">>>>> +[updateData] END re-connection");
			}
		} catch (SQLException e) {
			LOG.error("SQLException re-connection error: " + e);
		}

		Statement st = null;
		int result = 0;
		try {
			st = conn_.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			result = st.executeUpdate(query);

			st.close();
		} catch (Exception ex) {
			try {
				st.close();
			} catch (SQLException se) {
				LOG.error("SQLException: " + se);
			}
			LOG.error("Exception: " + ex);
		}
		LOG.info("<<<< +[updateData] END");
		return result;
	}

	public List<EniOrderStatus> retrieveData(Connection conn_, String query) {
		LOG.info(">>>> +[retrieveData] START");
		if (LOG.isDebugEnabled()) {
			LOG.debug(">>>>>> +[retrieveData]->query " + query);
		}
		ResultSet rs = null;
		Statement st = null;
		List<EniOrderStatus> orders = new ArrayList<EniOrderStatus>();
		EniOrderStatus orderStatus = null;
		if (conn_ == null) {
			LOG.error("Cannot to Order Hub DB database!!!");
			return null;
		}
		try {
			st = conn_.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = st.executeQuery(query);
			if (rs != null) {
				while (rs.next()) {
					orderStatus = new EniOrderStatus();
					orderStatus.setOrderId(rs.getString(1));
					orderStatus.setOrderStatus(rs.getString(2));
					orderStatus.setSentTimes(rs.getInt(3));
					orders.add(orderStatus);
				}
			}
			rs.close();
			st.close();
		} catch (Exception ex) {
			try {
				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
			} catch (SQLException se) {
				LOG.error("SQLException: " + se);
			}
			LOG.error("Exception: " + ex);
		}
		if (LOG.isDebugEnabled()) {
			LOG.debug(">>>>>> +[retrieveData]->orders " + orders);
		}
		LOG.info("<<<< +[retrieveData] END");
		return orders;
	}

	public boolean checkTableExist(Connection conn_, String tableName) {
		LOG.info(">>>> +[checkTableExist] START");
		if (LOG.isDebugEnabled()) {
			LOG.debug(">>>>>> +[checkTableExist]->tableName " + tableName);
		}
		boolean isExist = false;
		try {
			if (conn_ == null) {
				LOG.error("Cannot to Order Hub DB database!!!");
				return false;
			}
			try {
				DatabaseMetaData dbm = conn_.getMetaData();
				// Check if tableName is there
				ResultSet rs = dbm.getTables(null, null, tableName, null);
				if (rs.next()) {
					isExist = true;
				}
				rs.close();
			} catch (Exception ex) {
				LOG.error("Exception: " + ex);
			}
		} catch (Exception ex) {
			LOG.error(ex.getMessage());
		}
		LOG.info("<<<< +[checkTableExist] END");
		return isExist;
	}

	public boolean createHubSchema(Connection conn_, String query) {
		LOG.info(">>>> +[createHubSchema] START");
		if (LOG.isDebugEnabled()) {
			LOG.debug(">>>>>> +[createHubSchema]->query " + query);
		}
		boolean isSuccess = false;
		ResultSet rs = null;
		Statement st = null;
		try {
			if (conn_ == null) {
				LOG.error("Cannot to Order Hub DB database!!!");
				return false;
			}
			try {
				st = conn_.createStatement();
				rs = st.executeQuery(query);

				if (rs.next()) {
					isSuccess = true;
				}

				st.close();
				rs.close();
			} catch (Exception ex) {
				LOG.error("Exception: " + ex);
			}
		} catch (Exception ex) {
			LOG.error(ex.getMessage());
		}
		LOG.info("<<<< +[createHubSchema] END");
		return isSuccess;
	}

	/**
	 * Get connection object
	 * 
	 * @param name
	 * @return
	 */
	public Connection openConnection() {
		LOG.info(">>>> +[openConnection] START");
		return initConnection(EniOrderHubUtils.decrypter);
	}

	/**
	 * Close connection object
	 */
	public void closeConnection(Connection conn_) {
		LOG.info(">>>> +[closeConnection] START");
		try {
			if (conn_ != null) {
				conn_.close();
			}
		} catch (SQLException se) {
			LOG.error(se);
		}
		LOG.info("<<<< +[closeConnection] END");
	}
}
