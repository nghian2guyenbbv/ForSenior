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
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class EniOrderHubRetailLocationDBA {
	private static Logger LOG = Logger.getLogger(EniOrderHubRetailLocationDBA.class);
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

	public static final String SELECT_OBJECT = "SELECT organization_id,rtl_loc_id,store_name,address1,address2,address3,address4,city,state,district,area,postal_code,country,neighborhood,county,locale,currency_id,latitude,longitude,telephone1,telephone2,telephone3,telephone4,description,store_nbr,apartment,store_manager,email_addr,default_tax_percentage,location_type,delivery_available_flag,pickup_available_flag,transfer_available_flag,geo_code,uez_flag,alternate_store_nbr,use_till_accountability_flag,deposit_bank_name,deposit_bank_account_number,airport_code,legal_entity_id FROM  [dbo].[eni_hub_rtl_loc] where organization_id = ? AND rtl_loc_id = ?";

	public String getSelectQuery() {
		return SELECT_OBJECT;
	}

	public EniOrderHubRetailLocationDAO loadDAO() {
		EniOrderHubRetailLocationDAO dao = new EniOrderHubRetailLocationDAO();
		dao.setOrganizationId(this.organizationId);
		dao.setRtlLocId(this.rtlLocId);
		dao.setStoreNbr(this.storeName);
		dao.setAddress1(this.address1);
		dao.setAddress2(this.address2);
		dao.setAddress3(this.address3);
		dao.setAddress4(this.address4);
		dao.setCity(this.city);
		dao.setState(this.state);
		dao.setDistrict(this.district);
		dao.setArea(this.area);
		dao.setPostalCode(this.postalCode);
		dao.setCounty(this.county);
		dao.setNeighborhood(this.neighborhood);
		dao.setCountry(this.country);
		dao.setLocale(this.locale);
		dao.setCurrencyId(this.currencyId);
		dao.setLatitude(this.latitude);
		dao.setLongitude(this.longitude);
		dao.setTelephone1(this.telephone1);
		dao.setTelephone2(this.telephone2);
		dao.setTelephone3(this.telephone3);
		dao.setTelephone4(this.telephone4);
		dao.setDescription(this.description);
		dao.setStoreNbr(this.storeNbr);
		dao.setApartment(this.apartment);
		dao.setStoreManager(this.storeManager);
		dao.setEmailAddr(this.emailAddr);
		dao.setDefaultTaxPercentage(this.defaultTaxPercentage);
		dao.setDeliveryAvailableFlag(this.deliveryAvailableFlag);
		dao.setLocationType(this.locationType);
		dao.setPickupAvailableFlag(this.pickupAvailableFlag);
		dao.setTransferAvailableFlag(this.transferAvailableFlag);
		dao.setGeoCode(this.geoCode);
		dao.setUezFlag(this.uezFlag);
		dao.setAlternateStoreNbr(this.alternateStoreNbr);
		dao.setUseTillAccountabilityFlag(this.useTillAccountabilityFlag);
		dao.setDepositBankName(this.depositBankName);
		dao.setDepositBankAccountNumber(this.depositBankAccountNumber);
		dao.setAirportCode(this.airportCode);
		dao.setLegalEntityId(this.legalEntityId);

		return dao;
	}

	public void fillDAO(EniOrderHubRetailLocationDAO argDAO) {
		this.organizationId = argDAO.getOrganizationId();
		this.rtlLocId = argDAO.getRtlLocId();
		this.storeName = argDAO.getStoreName();
		this.address1 = argDAO.getAddress1();
		this.address2 = argDAO.getAddress2();
		this.address3 = argDAO.getAddress3();
		this.address4 = argDAO.getAddress4();
		this.city = argDAO.getCity();
		this.state = argDAO.getState();
		this.district = argDAO.getDistrict();
		this.area = argDAO.getArea();
		this.postalCode = argDAO.getPostalCode();
		this.country = argDAO.getCountry();
		this.neighborhood = argDAO.getNeighborhood();
		this.county = argDAO.getCounty();
		this.locale = argDAO.getLocale();
		this.currencyId = argDAO.getCurrencyId();
		this.latitude = argDAO.getLatitude();
		this.longitude = argDAO.getLongitude();
		this.telephone1 = argDAO.getTelephone1();
		this.telephone2 = argDAO.getTelephone2();
		this.telephone3 = argDAO.getTelephone3();
		this.telephone4 = argDAO.getAddress4();
		this.description = argDAO.getDescription();
		this.storeNbr = argDAO.getStoreNbr();
		this.apartment = argDAO.getApartment();
		this.storeManager = argDAO.getStoreManager();
		this.emailAddr = argDAO.getEmailAddr();
		this.defaultTaxPercentage = argDAO.getDefaultTaxPercentage();
		this.locationType = argDAO.getLocationType();
		this.deliveryAvailableFlag = argDAO.isDeliveryAvailableFlag();
		this.pickupAvailableFlag = argDAO.isPickupAvailableFlag();
		this.transferAvailableFlag = argDAO.isTransferAvailableFlag();
		this.geoCode = argDAO.getGeoCode();
		this.uezFlag = argDAO.isUezFlag();
		this.alternateStoreNbr = argDAO.getAlternateStoreNbr();
		this.useTillAccountabilityFlag = argDAO.isUseTillAccountabilityFlag();
		this.depositBankName = argDAO.getDepositBankName();
		this.depositBankAccountNumber = argDAO.getDepositBankAccountNumber();
		this.airportCode = argDAO.getAirportCode();
		this.legalEntityId = argDAO.getLegalEntityId();
	}

	public PreparedStatement initParameterForRetrieveRetailLocationData(PreparedStatement argPreparedStatement,
			int argOrganizationId, String argRetailLocationId) throws SQLException {
		argPreparedStatement.setInt(1, argOrganizationId);
		argPreparedStatement.setString(2, argRetailLocationId);
		if (LOG.isDebugEnabled()) {
			LOG.debug(">>>>>> +[initParameterForRetrieveRetailLocationData]-> query " + getSelectQuery());
			LOG.debug(">>>>>> +[initParameterForRetrieveRetailLocationData]-> values: " + argOrganizationId + ","
					+ argRetailLocationId);
		}
		return argPreparedStatement;
	}
}
