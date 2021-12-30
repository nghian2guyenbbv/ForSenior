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

public class EniOrderHubTenderModel {
	public EniOrderHubTenderModel() {
		initDAO();
	}

	private EniOrderHubTenderDAO daoObject = null;

	private void initDAO() {
		setDAO(new EniOrderHubTenderDAO());
	}

	public void setDAO(EniOrderHubTenderDAO argDAO) {
		daoObject = argDAO;
	}

	public EniOrderHubTenderDAO getDAO() {
		return daoObject;
	}

	public String getOrderId() {
		return getDAO().getOrderId();
	}

	public void setOrderId(String argOrderId) {
		getDAO().setOrderId(argOrderId);
	}

	public int getLineNo() {
		return getDAO().getLineNo();
	}

	public void setLineNo(int aregLineNo) {
		getDAO().setLineNo(aregLineNo);
	}

	public String getTenderDescription() {
		return getDAO().getTenderDescription();
	}

	public void setTenderDescription(String argTenderDescription) {
		getDAO().setTenderDescription(argTenderDescription);
	}

	public BigDecimal getTenderAmount() {
		return getDAO().getTenderAmount();
	}

	public void setTenderAmount(BigDecimal argTenderAmount) {
		getDAO().setTenderAmount(argTenderAmount);
	}

	public String getTenderAccount() {
		return getDAO().getTenderAccount();
	}

	public void setTenderAccount(String argTenderAccount) {
		getDAO().setTenderAccount(argTenderAccount);
	}

	public int getOrganizationId() {
		return getDAO().getOrganizationId();
	}

	public void setOrganizationId(int argOrganizationId) {
		getDAO().setOrganizationId(argOrganizationId);
	}
}
