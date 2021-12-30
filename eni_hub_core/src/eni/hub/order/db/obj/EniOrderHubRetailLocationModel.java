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
 * BZ47094      021121          [Intergration] Xstore Integrated eCommerce Orders                                                                   
 *===================================================================
 */
package eni.hub.order.db.obj;

import java.math.BigDecimal;

public class EniOrderHubRetailLocationModel {

	public EniOrderHubRetailLocationModel() {
		initDAO();
	}

	private EniOrderHubRetailLocationDAO daoObject = null;

	private void initDAO() {
		setDAO(new EniOrderHubRetailLocationDAO());
	}

	public void setDAO(EniOrderHubRetailLocationDAO argDAO) {
		daoObject = argDAO;
	}

	public EniOrderHubRetailLocationDAO getDAO() {
		return daoObject;
	}

	public int getOrganizationId() {
		return getDAO().getOrganizationId();
	}

	public void setOrganizationId(int argOrganizationId) {
		getDAO().setOrganizationId(argOrganizationId);
	}

	public String getRtlLocId() {
		return getDAO().getRtlLocId();
	}

	public void setRtlLocId(String argRtlLocId) {
		getDAO().setRtlLocId(argRtlLocId);
	}

	public String getStoreName() {
		return getDAO().getStoreName();
	}

	public void setStoreName(String argStoreName) {
		getDAO().setStoreName(argStoreName);
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

	public String getState() {
		return getDAO().getState();
	}

	public void setState(String argState) {
		getDAO().setState(argState);
	}

	public String getDistrict() {
		return getDAO().getDistrict();
	}

	public void setDistrict(String argDistrict) {
		getDAO().setDistrict(argDistrict);
	}

	public String getArea() {
		return getDAO().getArea();
	}

	public void setArea(String argArea) {
		getDAO().setArea(argArea);
	}

	public String getPostalCode() {
		return getDAO().getPostalCode();
	}

	public void setPostalCode(String argPostalCode) {
		getDAO().setPostalCode(argPostalCode);
	}

	public String getCountry() {
		return getDAO().getCountry();
	}

	public void setCountry(String argCountry) {
		getDAO().setCountry(argCountry);
	}

	public String getNeighborhood() {
		return getDAO().getNeighborhood();
	}

	public void setNeighborhood(String argNeighborhood) {
		getDAO().setNeighborhood(argNeighborhood);
	}

	public String getCounty() {
		return getDAO().getCounty();
	}

	public void setCounty(String argCounty) {
		getDAO().setCounty(argCounty);
	}

	public String getLocale() {
		return getDAO().getLocale();
	}

	public void setLocale(String argLocale) {
		getDAO().setLocale(argLocale);
	}

	public String getCurrencyId() {
		return getDAO().getCurrencyId();
	}

	public void setCurrencyId(String argCurrencyId) {
		getDAO().setCurrencyId(argCurrencyId);
	}

	public BigDecimal getLatitude() {
		return getDAO().getLatitude();
	}

	public void setLatitude(BigDecimal argLatitude) {
		getDAO().setLatitude(argLatitude);
	}

	public BigDecimal getLongitude() {
		return getDAO().getLongitude();
	}

	public void setLongitude(BigDecimal argLongitude) {
		getDAO().setLongitude(argLongitude);
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

	public String getTelephone3() {
		return getDAO().getTelephone3();
	}

	public void setTelephone3(String argTelephone3) {
		getDAO().setTelephone3(argTelephone3);
	}

	public String getTelephone4() {
		return getDAO().getTelephone4();
	}

	public void setTelephone4(String argTelephone4) {
		getDAO().setTelephone4(argTelephone4);
	}

	public String getDescription() {
		return getDAO().getDescription();
	}

	public void setDescription(String argDescription) {
		getDAO().setDescription(argDescription);
	}

	public String getStoreNbr() {
		return getDAO().getStoreNbr();
	}

	public void setStoreNbr(String argStoreNbr) {
		getDAO().setStoreNbr(argStoreNbr);
	}

	public String getApartment() {
		return getDAO().getApartment();
	}

	public void setApartment(String argApartment) {
		getDAO().setApartment(argApartment);
	}

	public String getStoreManager() {
		return getDAO().getStoreManager();
	}

	public void setStoreManager(String argStoreManager) {
		getDAO().setStoreManager(argStoreManager);
	}

	public String getEmailAddr() {
		return getDAO().getEmailAddr();
	}

	public void setEmailAddr(String argEmailAddr) {
		getDAO().setEmailAddr(argEmailAddr);
	}

	public BigDecimal getDefaultTaxPercentage() {
		return getDAO().getDefaultTaxPercentage();
	}

	public void setDefaultTaxPercentage(BigDecimal argDefaultTaxPercentage) {
		getDAO().setDefaultTaxPercentage(argDefaultTaxPercentage);
	}

	public String getLocationType() {
		return getDAO().getLocationType();
	}

	public void setLocationType(String argLocationType) {
		getDAO().setLocationType(argLocationType);
	}

	public boolean isDeliveryAvailableFlag() {
		return getDAO().isDeliveryAvailableFlag();
	}

	public void setDeliveryAvailableFlag(boolean argDeliveryAvailableFlag) {
		getDAO().setDeliveryAvailableFlag(argDeliveryAvailableFlag);
	}

	public boolean isPickupAvailableFlag() {
		return getDAO().isPickupAvailableFlag();
	}

	public void setPickupAvailableFlag(boolean argPickupAvailableFlag) {
		getDAO().setPickupAvailableFlag(argPickupAvailableFlag);
	}

	public boolean isTransferAvailableFlag() {
		return getDAO().isTransferAvailableFlag();
	}

	public void setTransferAvailableFlag(boolean argTransferAvailableFlag) {
		getDAO().setTransferAvailableFlag(argTransferAvailableFlag);
	}

	public String getGeoCode() {
		return getDAO().getGeoCode();
	}

	public void setGeoCode(String argGeoCode) {
		getDAO().setGeoCode(argGeoCode);
	}

	public boolean isUezFlag() {
		return getDAO().isUezFlag();
	}

	public void setUezFlag(boolean argUezFlag) {
		getDAO().setUezFlag(argUezFlag);
	}

	public String getAlternateStoreNbr() {
		return getDAO().getAlternateStoreNbr();
	}

	public void setAlternateStoreNbr(String argAlternateStoreNbr) {
		getDAO().setAlternateStoreNbr(argAlternateStoreNbr);
	}

	public boolean isUseTillAccountabilityFlag() {
		return getDAO().isUseTillAccountabilityFlag();
	}

	public void setUseTillAccountabilityFlag(boolean argUseTillAccountabilityFlag) {
		getDAO().setUseTillAccountabilityFlag(argUseTillAccountabilityFlag);
	}

	public String getDepositBankName() {
		return getDAO().getDepositBankAccountNumber();
	}

	public void setDepositBankName(String argDepositBankName) {
		getDAO().setDepositBankAccountNumber(argDepositBankName);
	}

	public String getDepositBankAccountNumber() {
		return getDAO().getDepositBankAccountNumber();
	}

	public void setDepositBankAccountNumber(String argDepositBankAccountNumber) {
		getDAO().setDepositBankAccountNumber(argDepositBankAccountNumber);
	}

	public String getAirportCode() {
		return getDAO().getAirportCode();
	}

	public void setAirportCode(String argAirportCode) {
		getDAO().setAirportCode(argAirportCode);
	}

	public String getLegalEntityId() {
		return getDAO().getLegalEntityId();
	}

	public void setLegalEntityId(String argLegalEntityId) {
		getDAO().setLegalEntityId(argLegalEntityId);
	}
}
