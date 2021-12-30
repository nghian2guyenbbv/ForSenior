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

package eni.hub.order.util;

public class EniOrderHubConstants {

	public static final String UPLOADING_JOB_STARTED = "Uploading job started.";
	public static final String UPLOADING_JOB_FINISHED = "Uploading job finished.";
	public static final String DOWNLOADING_JOB_STARTED = "Downloading job started.";
	public static final String DOWNLOADING_JOB_FINISHED = "Downloading job finished.";
	public static final String ORDER_STATUS_UPDATE_JOB_STARTED = "Order status update job started.";
	public static final String ORDER_STATUS_UPDATE_JOB_FINISHED = "Order status update job finished.";
	public static final String DEFAULT_NEXT_RUN = "Next run of %s is: %s";
	public static final String DEFAULT_UPLOADING_JOB = "uploading process";
	public static final String DEFAULT_DOWNLOADING_JOB = "downloading process";
	public static final String DATE_TIME_FORMAT = "dateTimeFormat";
	public static final String DEFAULT_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";
	public static final String DEFAULT_REMOVED_SCH_OF = "Removed scheduler of %s";
	public static final String DEFAULT_RE_SCH = "Rescheduled %s";
	public static final String CSV_EXTENSION = ".csv";
	public static final String ZIP_EXTENSION = ".zip";
	public static final String COMMA = ", ";
	public static final String DEFAULT_SCH = "Scheduled %s";
	public static final String DEFAULT_RESTART_APP = "Job scheduler is not available, please restart this application";
	public static final String DEFAULT_STARTED = "%s started.";
	public static final String DEFAULT_FINISHED = "%s finished.";
	public static final String UNDERSCORE = "_";
	public static final String EMPTY = "";
	public static final String ROOT_RESOURCE = ".";
	public static final String CONFIG_FILE = "eni-hub.properties";
	public static final String LOG4J_CONFIG_FILE = "log4j.xml";

	// Order Hub database information

	public static final String ORDER_HUB_ORG_ID = "order.hub.org.id";
	public static final String ORDER_HUB_CUSTOMER_ID = "order.hub.CustomerId";
	public static final String ORDER_HUB_CUSTOMER_SALT = "order.hub.CustomerId.salt";
	public static final String ORDER_HUB_DB_URL = "order.hub.db.url";
	public static final String ORDER_HUB_DB_USER = "order.hub.db.user";
	public static final String ORDER_HUB_DB_PWD = "order.hub.db.password";
		
	public static final String ENI_HUB_XSO_WEB_ENABLE = "eni.hub.xso.web.enable";
	public static final String ENI_HUB_XSO_CLOUD_ENABLE = "eni.hub.xso.cloud.enable";
	
}
