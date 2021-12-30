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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Properties;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import dtv.util.crypto.IDtvDecrypter;

public class EniOrderHubUtils {

	private static Logger LOG = LogManager.getLogger(EniOrderHubUtils.class);
	
	private static Properties configFile;


	public static IDtvDecrypter decrypter = null;

	public static boolean isWebEnable = false;
	public static String webServerConfigPath = "";

	private EniOrderHubUtils() {

	}
	

	/**
	 * Initial resources data
	 */
	public static void loadConfigProperties() {
		LOG.info(">>>>>> +[loadConfigProperties] START");
		InputStream is = null;
		configFile = new java.util.Properties();	
		try {			
			is = new FileInputStream(EniOrderHubUtils.getConfigFile());
			configFile.load(is);
		
		} catch (Exception e) {
			LOG.error(e.getMessage());
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					LOG.error(e);
				}
			}
		}
		LOG.info("<<<<<< +[loadConfigProperties] END");
	}

	/**
	 * Format Date value by simple format
	 * 
	 * @param format
	 * @param date
	 * @return
	 */
	public static String formatDateTime(String format, Date date) {
		LOG.info(">>>>>> +[formatDateTime] START");
		try {
			SimpleDateFormat sfm = new SimpleDateFormat(format);
			return sfm.format(date);
		} catch (Exception e) {
			LOG.error(e);
		}
		LOG.info("<<<<<< +[formatDateTime] END");
		return date == null ? "" : date.toString();
	}

	/**
	 * Delete file if exist
	 * 
	 * @param path
	 */
	public static void deleteFileIfExist(String path) {
		LOG.info(">>>>>> +[deleteFileIfExist] START");
		File file = new File(path);
		if (file != null && file.exists()) {
			FileUtils.deleteQuietly(file);
		}
		LOG.info("<<<<<< +[deleteFileIfExist] END");
	}

	/**
	 * Delete file if exist
	 * 
	 * @param path
	 */
	public static void deleteFileIfExist(File file) {
		LOG.info(">>>>>> +[deleteFileIfExist] START");
		if (file != null && file.exists()) {
			FileUtils.deleteQuietly(file);
		}
		LOG.info("<<<<<< +[deleteFileIfExist] END");
	}

	public static String getRootPath() {
		if (isWebEnable) {
			return getWebRootPath();
		} else {
			URL url = ClassLoader.getSystemResource(".");
			File me = new File(url.getFile());
			return me.getAbsolutePath();
		}
	}

	public static String getWebRootPath() {
		return webServerConfigPath;
	}

	public static String getConfigPath() {
		return getRootPath();
	}

	public static File getConfigFile() {
		return new File(getConfigPath() + File.separator + EniOrderHubConstants.CONFIG_FILE);
	}

	public static File getLog4jConfigFile() {
		return new File(getConfigPath() + File.separator + EniOrderHubConstants.LOG4J_CONFIG_FILE);
	}
	
	public static String getConfigProperties(String argKey) {
		String value = null;
		if (configFile != null) {
			value = configFile.getProperty(argKey);
		}
		return value;
	}
	
	public static String getConfigProperties(String argKey, String argDefaultValue) {
		String value = getConfigProperties(argKey);		
		if (value == null || value.isEmpty()) {
			value = argDefaultValue;
		}
		return value;
	}
	
	public static String getBase64DeCode(String argEncoded) {
		byte[] decodedBytes =  Base64.getDecoder().decode(argEncoded.getBytes());
		return new String(decodedBytes);
	}

	
}