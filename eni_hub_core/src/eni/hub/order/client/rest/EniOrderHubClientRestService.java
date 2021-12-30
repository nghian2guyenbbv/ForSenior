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
package eni.hub.order.client.rest;

import javax.ws.rs.core.MediaType;
import org.apache.log4j.Logger;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class EniOrderHubClientRestService {

	private static Logger LOG = Logger.getLogger(EniOrderHubClientRestService.class);

	/**
	 * 
	 * @param serviceUrl
	 * @param requestBody
	 * @return
	 */
	public static ClientResponse sendRequestHTTP(String serviceUrl, String requestBody) {
		LOG.info(">>>> +[sendRequestHTTP] START");
		Client client = null;
		WebResource webResource = null;
		ClientResponse response = null;
		try {
			client = Client.create();
			if (LOG.isDebugEnabled()) {
				LOG.debug(">>>> +[sendRequestHTTP] -> serviceUrl: " + serviceUrl);
			}
			webResource = client.resource(serviceUrl);
			response = webResource.type(MediaType.APPLICATION_JSON).post(ClientResponse.class, requestBody);
			if (LOG.isDebugEnabled()) {
				LOG.debug(">>>> +[sendRequestHTTP] -> response: " + response);
			}
		} catch (Exception e) {
			LOG.error("sendRequestHTTP - " + e.toString());
		}
		LOG.info("<<<< +[sendRequestHTTP] END");
		return response;
	}
}
