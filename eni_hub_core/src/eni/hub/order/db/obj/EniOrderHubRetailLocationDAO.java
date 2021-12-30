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

public class EniOrderHubRetailLocationDAO {
	private int organizationId = 1;
	private String rtlLocId = null;
	private String storeName = null;
	private String address1 = null;
	private String address2 = null;
	private String address3 = null;
	private String address4 = null;
	private String city = null;
	private String state = null;
	private String district = null;
	private String area = null;
	private String postalCode = null;
	private String country = null;
	private String neighborhood = null;
	private String county = null;
	private String locale = null;
	private String currencyId = null;
	private BigDecimal latitude = null;
	private BigDecimal longitude = null;
	private String telephone1 = null;
	private String telephone2 = null;
	private String telephone3 = null;
	private String telephone4 = null;
	private String description = null;
	private String storeNbr = null;
	private String apartment = null;
	private String storeManager = null;
	private String emailAddr = null;
	private BigDecimal defaultTaxPercentage = null;
	private String locationType = null;
	private boolean deliveryAvailableFlag = false;
	private boolean pickupAvailableFlag = false;
	private boolean transferAvailableFlag = false;
	private String geoCode = null;
	private boolean uezFlag = false;
	private String alternateStoreNbr = null;
	private boolean useTillAccountabilityFlag = false;
	private String depositBankName = null;
	private String depositBankAccountNumber = null;
	private String airportCode = null;
	private String legalEntityId = null;

	public int getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(int organizationId) {
		this.organizationId = organizationId;
	}

	public String getRtlLocId() {
		return rtlLocId;
	}

	public void setRtlLocId(String rtlLocId) {
		this.rtlLocId = rtlLocId;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getAddress3() {
		return address3;
	}

	public void setAddress3(String address3) {
		this.address3 = address3;
	}

	public String getAddress4() {
		return address4;
	}

	public void setAddress4(String address4) {
		this.address4 = address4;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getNeighborhood() {
		return neighborhood;
	}

	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public String getCurrencyId() {
		return currencyId;
	}

	public void setCurrencyId(String currencyId) {
		this.currencyId = currencyId;
	}

	public BigDecimal getLatitude() {
		return latitude;
	}

	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}

	public BigDecimal getLongitude() {
		return longitude;
	}

	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}

	public String getTelephone1() {
		return telephone1;
	}

	public void setTelephone1(String telephone1) {
		this.telephone1 = telephone1;
	}

	public String getTelephone2() {
		return telephone2;
	}

	public void setTelephone2(String telephone2) {
		this.telephone2 = telephone2;
	}

	public String getTelephone3() {
		return telephone3;
	}

	public void setTelephone3(String telephone3) {
		this.telephone3 = telephone3;
	}

	public String getTelephone4() {
		return telephone4;
	}

	public void setTelephone4(String telephone4) {
		this.telephone4 = telephone4;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStoreNbr() {
		return storeNbr;
	}

	public void setStoreNbr(String storeNbr) {
		this.storeNbr = storeNbr;
	}

	public String getApartment() {
		return apartment;
	}

	public void setApartment(String apartment) {
		this.apartment = apartment;
	}

	public String getStoreManager() {
		return storeManager;
	}

	public void setStoreManager(String storeManager) {
		this.storeManager = storeManager;
	}

	public String getEmailAddr() {
		return emailAddr;
	}

	public void setEmailAddr(String emailAddr) {
		this.emailAddr = emailAddr;
	}

	public BigDecimal getDefaultTaxPercentage() {
		return defaultTaxPercentage;
	}

	public void setDefaultTaxPercentage(BigDecimal defaultTaxPercentage) {
		this.defaultTaxPercentage = defaultTaxPercentage;
	}

	public String getLocationType() {
		return locationType;
	}

	public void setLocationType(String locationType) {
		this.locationType = locationType;
	}

	public boolean isDeliveryAvailableFlag() {
		return deliveryAvailableFlag;
	}

	public void setDeliveryAvailableFlag(boolean deliveryAvailableFlag) {
		this.deliveryAvailableFlag = deliveryAvailableFlag;
	}

	public boolean isPickupAvailableFlag() {
		return pickupAvailableFlag;
	}

	public void setPickupAvailableFlag(boolean pickupAvailableFlag) {
		this.pickupAvailableFlag = pickupAvailableFlag;
	}

	public boolean isTransferAvailableFlag() {
		return transferAvailableFlag;
	}

	public void setTransferAvailableFlag(boolean transferAvailableFlag) {
		this.transferAvailableFlag = transferAvailableFlag;
	}

	public String getGeoCode() {
		return geoCode;
	}

	public void setGeoCode(String geoCode) {
		this.geoCode = geoCode;
	}

	public boolean isUezFlag() {
		return uezFlag;
	}

	public void setUezFlag(boolean uezFlag) {
		this.uezFlag = uezFlag;
	}

	public String getAlternateStoreNbr() {
		return alternateStoreNbr;
	}

	public void setAlternateStoreNbr(String alternateStoreNbr) {
		this.alternateStoreNbr = alternateStoreNbr;
	}

	public boolean isUseTillAccountabilityFlag() {
		return useTillAccountabilityFlag;
	}

	public void setUseTillAccountabilityFlag(boolean useTillAccountabilityFlag) {
		this.useTillAccountabilityFlag = useTillAccountabilityFlag;
	}

	public String getDepositBankName() {
		return depositBankName;
	}

	public void setDepositBankName(String depositBankName) {
		this.depositBankName = depositBankName;
	}

	public String getDepositBankAccountNumber() {
		return depositBankAccountNumber;
	}

	public void setDepositBankAccountNumber(String depositBankAccountNumber) {
		this.depositBankAccountNumber = depositBankAccountNumber;
	}

	public String getAirportCode() {
		return airportCode;
	}

	public void setAirportCode(String airportCode) {
		this.airportCode = airportCode;
	}

	public String getLegalEntityId() {
		return legalEntityId;
	}

	public void setLegalEntityId(String legalEntityId) {
		this.legalEntityId = legalEntityId;
	}
}
