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

package eni.hub.rest;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.json.JSONObject;

/**
 * Testing class to simulate for order status update of E-Commerce
 */
@Path("/updateOrderStatus")
public class EniOrderHubReceiveOrderStatusService {

	private static Logger LOG = Logger.getLogger(EniOrderHubReceiveOrderStatusService.class);
	private String message = "Order has created successfully.";
	private static final String ORDER_NO = "orderNo";
	private static final String STATUS_CODE = "statusCode";

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateOrderStatus(InputStream incomingData) {
		LOG.info(">> +[updateOrderStatus] START");
		String orderNo = null;
		String statusCode = null;
		JSONObject obj = receiveOrderInfo(incomingData);
		orderNo = obj.getString(ORDER_NO);
		statusCode = obj.getString(STATUS_CODE);

		if (StringUtils.isEmpty(orderNo) || StringUtils.isEmpty(statusCode)) {
			return Response.status(400).entity(this.message).build();
		}
		LOG.info("<< +[createNewOrder] END");
		return Response.status(200).entity(this.message).build();
	}

	/**
	 * Receive order data from incoming data
	 * 
	 * @param incomingData
	 * @return obj
	 */
	private JSONObject receiveOrderInfo(InputStream incomingData) {
		LOG.info(">> +[receiveOrderInfo] START");
		StringBuilder str = new StringBuilder();
		JSONObject obj = null;
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(incomingData));
			String line = null;
			while ((line = in.readLine()) != null) {
				str.append(line);
			}
			obj = new JSONObject(str.toString());
		} catch (Exception e) {
			LOG.warn("receiveOrderInfo - Error bad request: - " + e.toString());
			this.message = "Error bad request: - " + e.toString();
		}
		LOG.info("<< +[receiveOrderInfo] END");
		return obj;
	}
}
