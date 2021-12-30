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

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

import org.apache.log4j.Logger;

import eni.hub.order.util.EniOrderHubUtils;

public class EniOrderHubCustomerDBA {
	private static Logger LOG = Logger.getLogger(EniOrderHubCustomerDBA.class);

	private String INSERT_OBJECT = "INSERT INTO [dbo].[eni_hub_customer] (organization_id, order_id, customer_id, first_name, middle_name, last_name, telephone1, telephone2, email_address, address_1, address_2, address_3, address_4, city, country, company_name, province, postal, prefix, suffix, apt, create_date, create_user_id,update_user_id, update_date, record_state) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	public static final String SELECT_OBJECT = "SELECT organization_id, order_id, customer_id, first_name, middle_name, last_name, telephone1, telephone2, email_address, address_1, address_2, address_3, address_4, city, country, company_name, province, postal, prefix, suffix, apt"
			+ " FROM [dbo].[eni_hub_customer] WHERE organization_id = ? AND order_id = ?";

	private int organizationId = 1;
	private String orderId = null;
	private String customerId = null;
	private String firstName = null;
	private String middleName = null;
	private String lastName = null;
	private String telephone1 = null;
	private String telephone2 = null;
	private String emailaddress = null;
	private String address1 = null;
	private String address2 = null;
	private String address3 = null;
	private String address4 = null;
	private String city = null;
	private String country = null;
	private String companyName = null;
	private String province = null;
	private String postal = null;
	private String prefix = null;
	private String suffix = null;
	private String apt = null;

	public String getInsertQuery() {
		return INSERT_OBJECT;
	}

	public String getSelectQuery() {
		return SELECT_OBJECT;
	}

	public EniOrderHubCustomerDAO loadDAO() {
		EniOrderHubCustomerDAO dao = new EniOrderHubCustomerDAO();
		dao.setOrganizationId(this.organizationId);
		dao.setOrderId(this.orderId);
		dao.setCustomerId(this.customerId);
		dao.setFirstName(this.firstName);
		dao.setMiddleName(this.middleName);
		dao.setLastName(this.lastName);
		dao.setTelephone1(this.telephone1);
		dao.setTelephone2(this.telephone2);
		dao.setEmailaddress(this.emailaddress);
		dao.setAddress1(this.address1);
		dao.setAddress2(this.address2);
		dao.setAddress3(this.address3);
		dao.setAddress4(this.address4);
		dao.setCity(this.city);
		dao.setCountry(this.country);
		dao.setCompanyName(this.companyName);
		dao.setProvince(this.province);
		dao.setPrefix(this.prefix);
		dao.setSuffix(this.suffix);
		dao.setApt(this.apt);
		return dao;
	}

	public void fillDAO(EniOrderHubCustomerDAO argDAO) {
		this.organizationId = argDAO.getOrganizationId();
		this.orderId = argDAO.getOrderId();
		this.customerId = argDAO.getCustomerId();
		this.firstName = argDAO.getFirstName();
		this.middleName = argDAO.getMiddleName();
		this.lastName = argDAO.getLastName();
		this.telephone1 = argDAO.getTelephone1();
		this.telephone2 = argDAO.getTelephone2();
		this.emailaddress = argDAO.getEmailaddress();
		this.address1 = argDAO.getAddress1();
		this.address2 = argDAO.getAddress2();
		this.address3 = argDAO.getAddress3();
		this.address4 = argDAO.getAddress4();
		this.city = argDAO.getCity();
		this.country = argDAO.getCountry();
		this.companyName = argDAO.getCompanyName();
		this.province = argDAO.getProvince();
		this.prefix = argDAO.getPrefix();
		this.suffix = argDAO.getSuffix();
		this.apt = argDAO.getApt();
	}

	public PreparedStatement initParameterForInsertCustomerData(PreparedStatement argPreparedStatement)
			throws SQLException {

		String createDate = EniOrderHubUtils.formatDateTime("yyyy-MM-dd HH:mm:ss", new Date());
		String createUserId = "ORDER_HUB";
		String recordState = "NEW_ORDER";
		argPreparedStatement.setInt(1, this.organizationId);
		argPreparedStatement.setString(2, this.orderId);
		argPreparedStatement.setString(3, this.customerId);
		argPreparedStatement.setString(4, this.firstName);
		argPreparedStatement.setString(5, this.middleName);
		argPreparedStatement.setString(6, this.lastName);
		argPreparedStatement.setString(7, this.telephone1);
		argPreparedStatement.setString(8, this.telephone2);
		argPreparedStatement.setString(9, this.emailaddress);
		argPreparedStatement.setString(10, this.address1);
		argPreparedStatement.setString(11, this.address2);
		argPreparedStatement.setString(12, this.address3);
		argPreparedStatement.setString(13, this.address4);
		argPreparedStatement.setString(14, this.city);
		argPreparedStatement.setString(15, this.country);
		argPreparedStatement.setString(16, this.companyName);
		argPreparedStatement.setString(17, this.province);
		argPreparedStatement.setString(18, this.postal);
		argPreparedStatement.setString(19, this.prefix);
		argPreparedStatement.setString(20, this.suffix);
		argPreparedStatement.setString(21, this.apt);
		argPreparedStatement.setString(22, createDate);
		argPreparedStatement.setString(23, createUserId);
		argPreparedStatement.setString(24, null);
		argPreparedStatement.setString(25, null);
		argPreparedStatement.setString(26, recordState);

		if (LOG.isDebugEnabled()) {
			LOG.debug(">>>>>> +[insertData]-> query " + getInsertQuery());
			LOG.debug(">>>>>> +[insertData]-> values: " + this.organizationId + "," + this.orderId + ","
					+ this.customerId + "," + this.firstName + "," + this.middleName + "," + this.lastName + ", "
					+ this.telephone1 + "," + this.telephone2 + "," + this.emailaddress + "," + this.address1 + ","
					+ this.address2 + "," + this.address3 + "," + this.address4 + this.city + "," + this.country + ","
					+ this.companyName + "," + this.province + "," + this.postal + this.prefix + "," + this.suffix + ","
					+ this.apt + "," + createDate + "," + createUserId + null + "," + null);
		}

		return argPreparedStatement;
	}

	public PreparedStatement initParameterForRetrieveCustomerData(PreparedStatement argPreparedStatement,
			int argOrganizationId, String argOrderId) throws SQLException {
		argPreparedStatement.setInt(1, argOrganizationId);
		argPreparedStatement.setString(2, argOrderId);
		if (LOG.isDebugEnabled()) {
			LOG.debug(">>>>>> +[initParameterForRetrieveCustomerData]-> query " + getSelectQuery());
			LOG.debug(
					">>>>>> +[initParameterForRetrieveCustomerData]-> values: " + argOrganizationId + "," + argOrderId);
		}
		return argPreparedStatement;
	}
}
