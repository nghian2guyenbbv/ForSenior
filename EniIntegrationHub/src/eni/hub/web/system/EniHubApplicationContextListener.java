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

package eni.hub.web.system;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import eni.hub.order.main.EniOrderHubMain;
import eni.hub.order.util.EniOrderHubUtils;

/**
 * Listener for setting the configuration for running BTM Hub Web Application
 */
public class EniHubApplicationContextListener implements ServletContextListener {

	private static final Logger LOG = Logger.getLogger(EniHubApplicationContextListener.class.getName());

	// Use for development with debugging on Tomcat
	public static final String tomcatWebServerConfigPath = System.getProperty("catalina.base");
	public static final String tomcatWebServerConfigFolder = "\\conf";

	// Use for development with debugging on Tomcat
	public static String jettyWebServerConfigPath = System.getProperty("jetty.home");
	public static final String jettyWebServerConfigFolder = "\\etc\\eni";

	@Override
	public void contextInitialized(ServletContextEvent event) {
		try {
			/*
			 * Update webServerConfigPath of EniHubXsoUtils to load application
			 * configuration file correctly and it needs to run after BtmHubXsoMain
			 */
			if (null != jettyWebServerConfigPath) {
				EniOrderHubUtils.webServerConfigPath = jettyWebServerConfigPath + jettyWebServerConfigFolder;
			} else {
				EniOrderHubUtils.webServerConfigPath = tomcatWebServerConfigPath + tomcatWebServerConfigFolder;
			}
			EniOrderHubUtils.isWebEnable = true;

			// Update Log4j configuration
			// and it needs to run first before writing log
			DOMConfigurator.configure(EniOrderHubUtils.getLog4jConfigFile().getAbsolutePath());

			LOG.info(">> +[contextInitialized] START");
			// Trigger to run main scheduler
			EniOrderHubMain.run();

			String webVersion = EniHubApplicationContextListener.class.getPackage().getImplementationVersion();
			LOG.info(">> +[contextInitialized] -> Web version " + webVersion);

			if (LOG.isDebugEnabled()) {
				LOG.info(">>> +[contextInitialized] -> jettyWebServerConfigPath: " + jettyWebServerConfigPath);
				LOG.info(">>> +[contextInitialized] -> tomcatWebServerConfigPath: " + tomcatWebServerConfigPath);
			}
			LOG.info("<< +[contextInitialized] END");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}


}
