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

public class EniOrderHubCustomerModel {
	public EniOrderHubCustomerModel() {
		initDAO();
	}

	private EniOrderHubCustomerDAO daoObject = null;

	private void initDAO() {
		setDAO(new EniOrderHubCustomerDAO());
	}

	
	public void setDAO(EniOrderHubCustomerDAO argDAO) {
		daoObject = argDAO;
	}

	public EniOrderHubCustomerDAO getDAO() {
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

	public String getCustomerId() {
		return getDAO().getCustomerId();
	}

	public void setCustomerId(String argCustomerId) {
		getDAO().setCustomerId(argCustomerId);
	}

	public String getFirstName() {
		return getDAO().getFirstName();
	}

	public void setFirstName(String argFirstName) {
		getDAO().setFirstName(argFirstName);
	}

	public String getMiddleName() {
		return getDAO().getMiddleName();
	}

	public void setMiddleName(String argMiddleName) {
		getDAO().setMiddleName(argMiddleName);
	}

	public String getLastName() {
		return getDAO().getLastName();
	}

	public void setLastName(String argLastName) {
		getDAO().setLastName(argLastName);
	}

	public String getTelephone1() {
		return getDAO().getTelephone1();
	}

	public void setTelephone1(String argTelephone1) {
		getDAO().setTelephone1(argTelephone1);
	}

	public String getTelephone2() {
		return getDAO().getTelephone2();
	}

	public void setTelephone2(String argTelephone2) {
		getDAO().setTelephone2(argTelephone2);
	}

	public String getEmailaddress() {
		return getDAO().getEmailaddress();
	}

	public void setEmailaddress(String argEmailaddress) {
		getDAO().setEmailaddress(argEmailaddress);
	}

	public String getAddress1() {
		return getDAO().getAddress1();
	}

	public void setAddress1(String argAddress1) {
		getDAO().setAddress1(argAddress1);
	}

	public String getAddress2() {
		return getDAO().getAddress2();
	}

	public void setAddress2(String argAddress2) {
		getDAO().setAddress2(argAddress2);
	}

	public String getAddress3() {
		return getDAO().getAddress3();
	}

	public void setAddress3(String argAddress3) {
		getDAO().setAddress3(argAddress3);
	}

	public String getAddress4() {
		return getDAO().getAddress4();
	}

	public void setAddress4(String argAddress4) {
		getDAO().setAddress4(argAddress4);
	}

	public String getCity() {
		return getDAO().getCity();
	}

	public void setCity(String argCity) {
		getDAO().setCity(argCity);
	}

	public String getCountry() {
		return getDAO().getCountry();
	}

	public void setCountry(String argCountry) {
		getDAO().setCountry(argCountry);
	}

	public String getCompanyName() {
		return getDAO().getCompanyName();
	}

	public void setCompanyName(String argCompanyName) {
		getDAO().setCompanyName(argCompanyName);
	}

	public String getProvince() {
		return getDAO().getProvince();
	}

	public void setProvince(String argProvince) {
		getDAO().setProvince(argProvince);
	}

	public String getPostal() {
		return getDAO().getPostal();
	}

	public void setPostal(String argPostal) {
		getDAO().setPostal(argPostal);
	}

	public String getPrefix() {
		return getDAO().getPrefix();
	}

	public void setPrefix(String argPrefix) {
		getDAO().setPrefix(argPrefix);
	}

	public String getSuffix() {
		return getDAO().getSuffix();
	}

	public void setSuffix(String argSuffix) {
		getDAO().setSuffix(argSuffix);
	}

	public String getApt() {
		return getDAO().getApt();
	}

	public void setApt(String argApt) {
		getDAO().setApt(argApt);
	}

	public String getCustomerName() {
		return getDAO().getFirstName() + " " + getDAO().getLastName();
	}
}
