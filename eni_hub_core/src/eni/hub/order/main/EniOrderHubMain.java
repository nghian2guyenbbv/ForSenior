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

package eni.hub.order.main;

import org.apache.log4j.Logger;
import dtv.util.crypto.DtvDecrypter;
import dtv.util.crypto.IDtvDecrypter;
import eni.hub.order.util.EniOrderHubUtils;

public class EniOrderHubMain {

	private static Logger LOG = Logger.getLogger(EniOrderHubMain.class);

	// This method for running in web container
	public static void run() {
		try {
			LOG.info(">>> +[run] START");
			String coreVersion = EniOrderHubMain.class.getPackage().getImplementationVersion();
			LOG.info(">>>> +[run] -> Core version " + coreVersion);

			// Load application properties
			LOG.info(">>>> +[SystemPropertiesLoader] START");
			EniOrderHubUtils.loadConfigProperties();
			LOG.info("<<<< +[SystemPropertiesLoader] END");

			// Initialize Decrypter Instance
			LOG.info(">>>> +[DtvDecrypter] START");
			IDtvDecrypter decrypter = DtvDecrypter.getInstance("config");
			EniOrderHubUtils.decrypter = decrypter;
			LOG.info("<<<< +[DtvDecrypter] END");

			LOG.info("<<< +[run] END");
		} catch (Exception e) {
			LOG.error(e);
		}
	}

	// This method for running in console mode
	public static void main(String[] args) {
		run();
	}
}
