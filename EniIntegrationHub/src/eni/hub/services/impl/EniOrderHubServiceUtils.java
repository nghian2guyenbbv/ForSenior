package eni.hub.services.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.microsretail.locate.AddressTypeBean;
import com.microsretail.locate.FulfillmentResponseMessageOrderTypeBean;
import com.microsretail.locate.NameTypeBean;
import com.microsretail.locate.StatusUpdateRequestMessageItemTypeBean;
import com.microsretail.locate.StatusUpdateRequestMessageTypeBean;
import com.microsretail.locate.TransactionTenderTypeBean;
import com.microsretail.locate.TransactionTendersTypeBean;

import eni.hub.order.db.obj.EniOrderHubCustomerModel;
import eni.hub.order.db.obj.EniOrderHubTenderModel;

public class EniOrderHubServiceUtils extends EniOrderHubQueryConstant{
	private Logger LOG = LogManager.getLogger(EniOrderHubServiceUtils.class);
	// Order Type: 1-PICKUP, 2-DELIVERY, 3-SHIPTOSTORE, 4-RETAILPICKUP,
	// 5-SHIPFORPICKUP
	public int getOrderType(String orderTypeText) {
		int orderTypeInt = 0;
		switch (orderTypeText) {
		case "PICKUP":
			orderTypeInt = 1;
			break;
		case "DELIVERY":
			orderTypeInt = 2;
			break;
		case "SHIPTOSTORE":
			orderTypeInt = 3;
			break;
		case "RETAILPICKUP":
			orderTypeInt = 4;
			break;
		case "SHIPFORPICKUP":
			orderTypeInt = 5;
			break;
		}
		return orderTypeInt;
	}

	public void getTransactionTenderInfo(EniOrderHubTenderModel argTenderModel,
			TransactionTenderTypeBean transactionTenderTypeBean,
			TransactionTendersTypeBean transactionTendersTypeBean) {
		if (argTenderModel != null) {
			transactionTenderTypeBean = new TransactionTenderTypeBean();
			transactionTenderTypeBean.setLineItemNo(argTenderModel.getLineNo() + "");
			transactionTenderTypeBean.setTenderAccount(argTenderModel.getTenderAccount());
			transactionTenderTypeBean.setTenderAmount(argTenderModel.getTenderAmount());
			transactionTenderTypeBean.setTenderDescription(argTenderModel.getTenderDescription());
			transactionTendersTypeBean.getTransactionTender().add(transactionTenderTypeBean);
		}
	}

	public AddressTypeBean getAddressTypeBeanInfo(AddressTypeBean addressTypeBean, EniOrderHubCustomerModel argCustomerModel) {
		addressTypeBean = new AddressTypeBean();
		addressTypeBean.setAddress1(argCustomerModel.getAddress1());
		addressTypeBean.setAddress2(argCustomerModel.getAddress2());
		addressTypeBean.setAddress3(argCustomerModel.getAddress3());
		addressTypeBean.setAddress4(argCustomerModel.getAddress4());
		addressTypeBean.setApt(argCustomerModel.getApt());
		addressTypeBean.setPhone1(argCustomerModel.getTelephone1());
		addressTypeBean.setEmail(argCustomerModel.getEmailaddress());
		addressTypeBean.setCity(argCustomerModel.getCity());
		addressTypeBean.setPostal(argCustomerModel.getPostal());
		addressTypeBean.setCountry(argCustomerModel.getCountry());
		addressTypeBean.setProvince(argCustomerModel.getProvince());
		return addressTypeBean;
	}

	public NameTypeBean getNameTypeBean(NameTypeBean nameTypeBean, EniOrderHubCustomerModel argCustomerModel) {
		nameTypeBean = new NameTypeBean();
		nameTypeBean.setCompanyName(argCustomerModel.getCompanyName());
		nameTypeBean.setPrefix(argCustomerModel.getPrefix());
		nameTypeBean.setFirst(argCustomerModel.getFirstName());
		nameTypeBean.setMiddle(argCustomerModel.getMiddleName());
		nameTypeBean.setLast(argCustomerModel.getLastName());
		nameTypeBean.setSuffix(argCustomerModel.getSuffix());
		return nameTypeBean;
	}
	public void getCustomerInfoFromStatusUpdateReq(PreparedStatement hubOrderCustomerPs , StatusUpdateRequestMessageTypeBean statusUpdateRequestMessageTypeBean, String order_id) {
		List<StatusUpdateRequestMessageItemTypeBean> listItem = statusUpdateRequestMessageTypeBean.getMessageBody().getOrder().getItems().getItem();
		if(listItem != null) {
			StatusUpdateRequestMessageItemTypeBean messageBody = listItem.get(0);
			// get Name
			String company_name = messageBody.getShipTo().getName().getCompanyName();
			String prefix = messageBody.getShipTo().getName().getPrefix();
			String firstName = messageBody.getShipTo().getName().getFirst();
		    String middleName = messageBody.getShipTo().getName().getMiddle();
			String lastName= messageBody.getShipTo().getName().getLast();
		    String suffix = messageBody.getShipTo().getName().getSuffix();
		    // get Address
		    String address1 = messageBody.getShipTo().getAddress().getAddress1();
		    String address2 = messageBody.getShipTo().getAddress().getAddress2();
		    String address3 = messageBody.getShipTo().getAddress().getAddress3();
		    String address4 = messageBody.getShipTo().getAddress().getAddress4();
		    String apt = messageBody.getShipTo().getAddress().getApt();
		    String city = messageBody.getShipTo().getAddress().getCity();
		    String province = messageBody.getShipTo().getAddress().getProvince();
		    String postal = messageBody.getShipTo().getAddress().getPostal();
		    String email = messageBody.getShipTo().getAddress().getEmail();
		    String phone1 = messageBody.getShipTo().getAddress().getPhone1();
		    String phone2 = messageBody.getShipTo().getAddress().getPhone2();
		    String country = messageBody.getShipTo().getAddress().getCountry();
		    
		    try {
				hubOrderCustomerPs.setString(1, company_name);
				hubOrderCustomerPs.setString(2, prefix);
				hubOrderCustomerPs.setString(3, firstName);
				hubOrderCustomerPs.setString(4, middleName);
				hubOrderCustomerPs.setString(5, lastName);
				hubOrderCustomerPs.setString(6, suffix);
				
				hubOrderCustomerPs.setString(7, address1);
				hubOrderCustomerPs.setString(8, address2);
				hubOrderCustomerPs.setString(9, address3);
				hubOrderCustomerPs.setString(10, address4);
				hubOrderCustomerPs.setString(11, apt);
				hubOrderCustomerPs.setString(12, city);
				hubOrderCustomerPs.setString(13, province);
				hubOrderCustomerPs.setString(14, postal);
				hubOrderCustomerPs.setString(15, email);
				hubOrderCustomerPs.setString(16, phone1);
				hubOrderCustomerPs.setString(17, phone2);
				hubOrderCustomerPs.setString(18, country);
				
				hubOrderCustomerPs.setInt(19, 1);
				hubOrderCustomerPs.setString(20,order_id);
			} catch (SQLException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
				LOG.warn(ex);
				throw new RuntimeException(ex);
			}
		    
		}
		
	}
	public int getOrderIdInt(String order_id) {
		int orderIdInt = 0 ;
		if (order_id != null && order_id.length() >0 ) {
			try {
				orderIdInt = Integer.parseInt(order_id);
			}catch(Exception ex) {
				LOG.info("Can't convert Order Id to Integer");
				return orderIdInt;
			}
		}else{
			LOG.error("Can't find out order id");
		}
		return orderIdInt;
	}
	public void updateRecordState(PreparedStatement psOrderUpdate,PreparedStatement psOrderLineUpdate, List<FulfillmentResponseMessageOrderTypeBean> argFulfillmentList) {
		int[] rsOrderDataUpdate = null;
		for (FulfillmentResponseMessageOrderTypeBean fufillment : argFulfillmentList) {
			if(CANCELED.equalsIgnoreCase(fufillment.getTransactionStatus()) || COMPLETE.equalsIgnoreCase(fufillment.getTransactionStatus())) {
			try {
				psOrderUpdate.setString(1, fufillment.getTransactionStatus());
				psOrderUpdate.setInt(2,ORGANIZATION_ID_DEFAULT);
				psOrderUpdate.setString(3,fufillment.getStoreLocation().getLocationCd());
				psOrderUpdate.setString(4, fufillment.getOrderId());
				psOrderUpdate.addBatch();
			} catch (SQLException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
				LOG.error("Can't update record_state column in eni_hub_order"+ex);
			}
			try {
				psOrderLineUpdate.setString(1, fufillment.getTransactionStatus());
				psOrderLineUpdate.setInt(2,ORGANIZATION_ID_DEFAULT);
				psOrderLineUpdate.setString(3, fufillment.getOrderId());
				psOrderLineUpdate.addBatch();
			} catch (SQLException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
				LOG.error("Can't update record_state column in eni_hub_order_line"+ex);
			}
			
			}
		}
		if(psOrderUpdate !=null && psOrderLineUpdate != null ) {
			try {
				rsOrderDataUpdate = psOrderUpdate.executeBatch();
				if(rsOrderDataUpdate !=null) {
					LOG.info("Update record_state for eni_hub_order result: "+rsOrderDataUpdate.length);
				}
			}catch(Exception ex) {
				LOG.error("Can't update the record_state in eni_hub_order after fulfillment" +ex);
			}
			try {
				rsOrderDataUpdate = psOrderLineUpdate.executeBatch();
				if(rsOrderDataUpdate !=null) {
					LOG.info("Update record_state for eni_hub_order_line result: "+rsOrderDataUpdate.length);
				}
				
			}catch(Exception ex) {
				LOG.error("Can't update the record_state column in eni_hub_order_line after fulfillment" +ex);
			}
		}
	}
}
