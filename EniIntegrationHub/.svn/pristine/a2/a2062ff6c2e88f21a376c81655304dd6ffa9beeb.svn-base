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

package eni.hub.order.model;

import java.util.Map;

import eni.hub.order.db.obj.EniOrderHubCustomerModel;
import eni.hub.order.db.obj.EniOrderHubOrderLineModel;
import eni.hub.order.db.obj.EniOrderHubOrderModel;

public class EniOrderHubOrderInfo {

	private EniOrderHubOrderModel order = null;
	private EniOrderHubCustomerModel customer = null;
	private Map<Integer, EniOrderHubOrderLineModel> orderLines = null;

	public EniOrderHubOrderModel getOrder() {
		return order;
	}

	public void setOrder(EniOrderHubOrderModel order) {
		this.order = order;
	}

	public EniOrderHubCustomerModel getCustomer() {
		return customer;
	}

	public void setCustomer(EniOrderHubCustomerModel customer) {
		this.customer = customer;
	}

	public Map<Integer, EniOrderHubOrderLineModel> getItem() {
		return orderLines;
	}

	public void setItem(Map<Integer, EniOrderHubOrderLineModel> argOrderLines) {
		this.orderLines = argOrderLines;
	}

}
