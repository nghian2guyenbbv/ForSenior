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

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import dtv.util.crypto.IDtvDecrypter;
import eni.hub.order.EniOrderStatus;

public class EniOrderHubDatasourceHelper {

	private static Logger LOG = Logger.getLogger(EniOrderHubDatasourceHelper.class);

	// private Connection conn_ = null;
	/**
	 * static Singleton instance.
	 */
	private static volatile EniOrderHubDatasourceHelper instance;

	/**
	 * Private constructor for singleton.
	 */
	private EniOrderHubDatasourceHelper() {
	}

	/**
	 * Return a singleton instance of BTMDataSourceHelper.
	 */
	public static EniOrderHubDatasourceHelper getInstance() {
		// Double lock for thread safety.
		if (instance == null) {
			synchronized (EniOrderHubDatasourceHelper.class) {
				if (instance == null) {
					instance = new EniOrderHubDatasourceHelper();
				}
			}
		}
		return instance;
	}

	public Connection initConnection(IDtvDecrypter decrypter) {
		LOG.info("<<<< +[openConnection] END");
		LOG.info(">>>> +[initConnection] START");
		Connection conn_ = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			// For xcenter configuration
			String url = EniOrderHubUtils.getConfigProperties(EniOrderHubConstants.ORDER_HUB_DB_URL);
			if (LOG.isDebugEnabled()) {
				LOG.debug(">>>>>> +[initConnection] -> url: " + url);
			}
			String user = EniOrderHubUtils.getConfigProperties(EniOrderHubConstants.ORDER_HUB_DB_USER);
			if (LOG.isDebugEnabled()) {
				LOG.debug(">>>>>> +[initConnection] -> user is encrypted: " + user);
			}
			String password = EniOrderHubUtils.getConfigProperties(EniOrderHubConstants.ORDER_HUB_DB_PWD);
			if (LOG.isDebugEnabled()) {
				LOG.debug(">>>>>> +[initConnection] -> password is encrypted: " + password);
			}

			user = decrypter.decryptIfEncrypted(user);
			if (LOG.isDebugEnabled()) {
				LOG.debug(">>>>>> +[initConnection] -> user is decrypted: " + user);
			}
			password = decrypter.decryptIfEncrypted(password);
			if (LOG.isDebugEnabled()) {
				LOG.debug(">>>>>> +[initConnection] -> password is decrypted: " + password);
			}

			conn_ = DriverManager.getConnection(url, user, password);
			if (conn_ == null) {
				LOG.error("Cannot to Xcenter database!!!");
			}
		} catch (Exception ex) {
			LOG.error(ex);
		}
		LOG.info("<<<< +[initConnection] END");
		return conn_;
	}

	public int insertData(Connection conn_, String query) {
		LOG.info(">>>> +[insertData] START");
		if (LOG.isDebugEnabled()) {
			LOG.debug(">>>>>> +[insertData]->query " + query);
		}
		Statement st = null;
		int result = 0;
		if (conn_ == null) {
			LOG.error("Cannot to Xcenter database!!!");
			return result;
		}
		try {
			st = conn_.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			result = st.executeUpdate(query);

			st.close();
		} catch (Exception ex) {
			try {
				st.close();
			} catch (SQLException se) {
				LOG.error("SQLException: " + se);
			}
			LOG.error("Exception: " + ex);
		}
		LOG.info("<<<< +[insertData] END");
		return result;
	}

	/***
	 * Check to insert data in eni_hub_order table
	 * 
	 * @param query
	 * @param map
	 * @return
	 */
	public int insertDataXomOrder(Connection conn_, String query, Map<Integer, Object> map) {

		LOG.info(">>>> +[insertData] START");
		PreparedStatement ps = null;
		int result = 0;
		if (conn_ == null) {
			LOG.error("Cannot to Xcenter database!!!");
			return result;
		}
		try {

			ps = conn_.prepareStatement(query);
			ps.setInt(1, (int) map.get(1));
			ps.setString(2, (String) map.get(2));
			ps.setString(3, (String) map.get(3).toString());
			ps.setString(4, (String) map.get(4).toString());
			ps.setString(5, (String) map.get(5).toString());
			ps.setString(6, (String) map.get(6));
			ps.setBigDecimal(7, (BigDecimal) map.get(7));
			ps.setBigDecimal(8, (BigDecimal) map.get(8));
			ps.setBigDecimal(9, (BigDecimal) map.get(9));
			ps.setBigDecimal(10, (BigDecimal) map.get(10));
			ps.setString(11, (String) map.get(11));
			ps.setString(12, (String) map.get(12));
			ps.setString(13, (String) map.get(13));
			ps.setString(14, (String) map.get(14));
			ps.setBigDecimal(15, (BigDecimal) map.get(15));
			ps.setBigDecimal(16, (BigDecimal) map.get(16));
			ps.setBoolean(17, (Boolean) map.get(17));
			ps.setBigDecimal(18, (BigDecimal) map.get(18));
			ps.setString(19, (String) map.get(19));
			ps.setString(20, (String) map.get(20));
			ps.setBoolean(21, (Boolean) map.get(21));
			ps.setString(22, (String) map.get(22));
			ps.setString(23, (String) map.get(23));
			ps.setString(24, (String) map.get(24));
			ps.setString(25, (String) map.get(25).toString());
			ps.setString(26, (String) map.get(26));
			ps.setString(27, (String) map.get(27).toString());
			ps.setString(28, (String) map.get(28));

			if (LOG.isDebugEnabled()) {
				LOG.debug(">>>>>> +[insertData]-> query " + query);
				LOG.debug(">>>>>> +[insertData]-> values: " + map.get(1) + "," + map.get(2) + "," + map.get(3) + ","
						+ map.get(4) + "," + map.get(5) + "," + map.get(6) + ", " + map.get(7) + "," + map.get(8) + ","
						+ map.get(9) + "," + map.get(10) + "," + map.get(11) + "," + map.get(12) + "," + map.get(13)
						+ map.get(14) + "," + map.get(15) + "," + map.get(16) + "," + map.get(17) + "," + map.get(18)
						+ map.get(19) + "," + map.get(20) + "," + map.get(21) + "," + map.get(22) + "," + map.get(23)
						+ map.get(24) + "," + map.get(25) + "," + map.get(26) + "," + map.get(27) + "," + map.get(28));
			}
			result = ps.executeUpdate();

			ps.close();

		} catch (Exception ex) {
			LOG.error("Exception: " + ex);
			try {
				ps.close();
			} catch (SQLException se) {
				LOG.error("SQLException: " + se);
			}
			LOG.error("Exception: " + ex);
		}
		LOG.info("<<<< +[insertData] END");
		return result;
	}

	/***
	 * Check to insert into eni_hub_xom_order_line table
	 * 
	 * @param query
	 * @param map
	 * @return
	 */
	public int insertDataXomOrderLine(Connection conn_, String query, Map<Integer, Object> map) {

		LOG.info(">>>> +[insertData] START");
		PreparedStatement ps = null;
		int result = 0;
		if (conn_ == null) {
			LOG.error("Cannot to Xcenter database!!!");
			return result;
		}
		try {

			ps = conn_.prepareStatement(query);
			ps.setInt(1, (int) map.get(1));
			ps.setString(2, (String) map.get(2));
			ps.setInt(3, (int) map.get(3));
			ps.setString(4, (String) map.get(4).toString());
			ps.setString(5, (String) map.get(5).toString());
			ps.setString(6, (String) map.get(6));
			ps.setString(7, (String) map.get(7));
			ps.setBigDecimal(8, (BigDecimal) map.get(8));
			ps.setString(9, (String) map.get(9));
			ps.setString(10, (String) map.get(10));
			ps.setBigDecimal(11, (BigDecimal) map.get(11));
			ps.setBigDecimal(12, (BigDecimal) map.get(12));
			ps.setBigDecimal(13, (BigDecimal) map.get(13));
			ps.setString(14, (String) map.get(14));
			ps.setString(15, (String) map.get(15));
			ps.setBoolean(16, (Boolean) map.get(16));
			ps.setString(17, (String) map.get(17));
			ps.setBoolean(18, (Boolean) map.get(18));
			ps.setString(19, (String) map.get(19));
			ps.setString(20, (String) map.get(20));
			ps.setBigDecimal(21, (BigDecimal) map.get(21));
			ps.setBigDecimal(22, (BigDecimal) map.get(22));
			ps.setBoolean(23, (Boolean) map.get(23));
			ps.setBoolean(24, (Boolean) map.get(24));
			ps.setBigDecimal(25, (BigDecimal) map.get(25));
			ps.setString(26, (String) map.get(26));
			ps.setString(27, (String) map.get(27));
			ps.setString(28, (String) map.get(28).toString());
			ps.setString(29, (String) map.get(29));
			ps.setString(30, (String) map.get(30).toString());
			ps.setString(31, (String) map.get(31));

			if (LOG.isDebugEnabled()) {
				LOG.debug(">>>>>> +[insertData]-> query " + query);
				LOG.debug(">>>>>> +[insertData]-> values: " + map.get(1) + "," + map.get(2) + "," + map.get(3) + ","
						+ map.get(4) + "," + map.get(5) + "," + map.get(6) + ", " + map.get(7) + "," + map.get(8) + ","
						+ map.get(9) + "," + map.get(10) + "," + map.get(11) + "," + map.get(12) + "," + map.get(13)
						+ map.get(14) + "," + map.get(15) + "," + map.get(16) + "," + map.get(17) + "," + map.get(18)
						+ map.get(19) + "," + map.get(20) + "," + map.get(21) + "," + map.get(22) + "," + map.get(23)
						+ map.get(24) + "," + map.get(25) + "," + map.get(26) + "," + map.get(27) + "," + map.get(28)
						+ map.get(29) + "," + map.get(30) + "," + map.get(31));
			}

			result = ps.executeUpdate();
			ps.close();

		} catch (Exception ex) {
			try {
				ps.close();
			} catch (SQLException se) {
				LOG.error("SQLException: " + se);
			}
			LOG.error("Exception: " + ex);
		}
		LOG.info("<<<< +[insertData] END");
		return result;
	}

	/***
	 * Check to insert data into eni_hub_customer table
	 * 
	 * @param query
	 * @param map
	 * @return
	 */
	public int insertDataXomOrderCustomer(Connection conn_, String query, Map<Integer, Object> map) {

		LOG.info(">>>> +[insertData] START");
		PreparedStatement ps = null;
		int result = 0;
		if (conn_ == null) {
			LOG.error("Cannot to Xcenter database!!!");
			return result;
		}
		try {
			ps = conn_.prepareStatement(query);
			ps.setInt(1, (int) map.get(1));
			ps.setString(2, (String) map.get(2));
			ps.setString(3, (String) map.get(3));
			ps.setString(4, (String) map.get(4));
			ps.setString(5, (String) map.get(5));
			ps.setString(6, (String) map.get(6));
			ps.setString(7, (String) map.get(7));
			ps.setString(8, (String) map.get(8));
			ps.setString(9, (String) map.get(9));
			ps.setString(10, (String) map.get(10));
			ps.setString(11, (String) map.get(11));
			ps.setString(12, (String) map.get(12));
			ps.setString(13, (String) map.get(13));
			ps.setString(14, (String) map.get(14));
			ps.setString(15, (String) map.get(15));
			ps.setString(16, (String) map.get(16));
			ps.setString(17, (String) map.get(17));
			ps.setString(18, (String) map.get(18));
			ps.setString(19, (String) map.get(19));
			ps.setString(20, (String) map.get(20));
			ps.setString(21, (String) map.get(21));
			ps.setString(22, (String) map.get(22));
			ps.setString(23, (String) map.get(23).toString());
			ps.setString(24, (String) map.get(24));
			ps.setString(25, (String) map.get(25).toString());
			ps.setString(26, (String) map.get(26));
			if (LOG.isDebugEnabled()) {
				LOG.debug(">>>>>> +[insertData]-> query " + query);
				LOG.debug(">>>>>> +[insertData]-> values: " + map.get(1) + "," + map.get(2) + "," + map.get(3) + ","
						+ map.get(4) + "," + map.get(5) + "," + map.get(6) + ", " + map.get(7) + "," + map.get(8) + ","
						+ map.get(9) + "," + map.get(10) + "," + map.get(11) + "," + map.get(12) + "," + map.get(13)
						+ map.get(14) + "," + map.get(15) + "," + map.get(16) + "," + map.get(17) + "," + map.get(18)
						+ map.get(19) + "," + map.get(20) + "," + map.get(21) + "," + map.get(22) + "," + map.get(23)
						+ map.get(24) + "," + map.get(25));
			}
			result = ps.executeUpdate();
			ps.close();

		} catch (Exception ex) {
			try {
				ps.close();
			} catch (SQLException se) {
				LOG.error("SQLException: " + se);
			}
			LOG.error("Exception: " + ex);
		}
		LOG.info("<<<< +[insertData] END");
		return result;
	}

	/***
	 * Check to insert data into eni_hub_tender table
	 * 
	 * @param query
	 * @param map
	 * @return
	 */
	public int insertDataXomOrderTender(Connection conn_, String query, Map<Integer, Object> map) {

		LOG.info(">>>> +[insertData] START");
		PreparedStatement ps = null;
		int result = 0;
		if (conn_ == null) {
			LOG.error("Cannot to Xcenter database!!!");
			return result;
		}
		try {
			ps = conn_.prepareStatement(query);
			ps.setString(1, (String) map.get(1));
			ps.setInt(2, (int) map.get(2));
			ps.setString(3, (String) map.get(3));
			ps.setBigDecimal(4, (BigDecimal) map.get(4));
			ps.setString(5, (String) map.get(5));

			if (LOG.isDebugEnabled()) {
				LOG.debug(">>>>>> +[insertData]-> query " + query);
				LOG.debug(">>>>>> +[insertData]-> values: " + map.get(1) + "," + map.get(2) + "," + map.get(3) + ","
						+ map.get(4) + "," + map.get(5));
			}
			result = ps.executeUpdate();
			ps.close();

		} catch (Exception ex) {
			try {
				ps.close();
			} catch (SQLException se) {
				LOG.error("SQLException: " + se);
			}
			LOG.error("Exception: " + ex);
		}
		LOG.info("<<<< +[insertData] END");
		return result;
	}

	/***
	 * Begin transaction object setAutoCommit = false
	 */
	public void beginTransaction(Connection conn_) {
		LOG.info(">>>> +[beginConnection] START");
		try {
			if (conn_ != null) {
				LOG.info(">>>> +[beginConnection] setAutoCommit");
				conn_.setAutoCommit(false);
			}
		} catch (SQLException se) {
			LOG.error("beginConnection" + se);
		}
		LOG.info("<<<< +[beginConnection] END");
	}

	/***
	 * Commit transaction object
	 */
	public void commitTransaction(Connection conn_) {
		LOG.info(">>>> +[commitConnection] START");
		try {
			if (conn_ != null) {
				conn_.commit();
			}
		} catch (SQLException se) {
			LOG.error("commitConnection" + se);
		}
		LOG.info("<<<< +[commitConnection] END");
	}

	public int updateData(Connection conn_, String query) {
		LOG.info(">>>> +[updateData] START");
		if (LOG.isDebugEnabled()) {
			LOG.debug(">>>>>> +[updateData]->query " + query);
		}

		try {
			if (conn_.isClosed()) {
				LOG.info(">>>>> +[updateData] BEGIN re-connection");
				openConnection();
				LOG.error(">>>>> +[updateData] END re-connection");
			}
		} catch (SQLException e) {
			LOG.error("SQLException re-connection error: " + e);
		}

		Statement st = null;
		int result = 0;
		try {
			st = conn_.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			result = st.executeUpdate(query);

			st.close();
		} catch (Exception ex) {
			try {
				st.close();
			} catch (SQLException se) {
				LOG.error("SQLException: " + se);
			}
			LOG.error("Exception: " + ex);
		}
		LOG.info("<<<< +[updateData] END");
		return result;
	}

	public List<EniOrderStatus> retrieveData(Connection conn_, String query) {
		LOG.info(">>>> +[retrieveData] START");
		if (LOG.isDebugEnabled()) {
			LOG.debug(">>>>>> +[retrieveData]->query " + query);
		}
		ResultSet rs = null;
		Statement st = null;
		List<EniOrderStatus> orders = new ArrayList<EniOrderStatus>();
		EniOrderStatus orderStatus = null;
		if (conn_ == null) {
			LOG.error("Cannot to Xcenter database!!!");
			return null;
		}
		try {
			st = conn_.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = st.executeQuery(query);
			if (rs != null) {
				while (rs.next()) {
					orderStatus = new EniOrderStatus();
					orderStatus.setOrderId(rs.getString(1));
					orderStatus.setOrderStatus(rs.getString(2));
					orderStatus.setSentTimes(rs.getInt(3));
					orders.add(orderStatus);
				}
			}
			rs.close();
			st.close();
		} catch (Exception ex) {
			try {
				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
			} catch (SQLException se) {
				LOG.error("SQLException: " + se);
			}
			LOG.error("Exception: " + ex);
		}
		if (LOG.isDebugEnabled()) {
			LOG.debug(">>>>>> +[retrieveData]->orders " + orders);
		}
		LOG.info("<<<< +[retrieveData] END");
		return orders;
	}

	public boolean checkTableExist(Connection conn_, String tableName) {
		LOG.info(">>>> +[checkTableExist] START");
		if (LOG.isDebugEnabled()) {
			LOG.debug(">>>>>> +[checkTableExist]->tableName " + tableName);
		}
		boolean isExist = false;
		try {
			if (conn_ == null) {
				LOG.error("Cannot to Xcenter database!!!");
				return false;
			}
			try {
				DatabaseMetaData dbm = conn_.getMetaData();
				// Check if tableName is there
				ResultSet rs = dbm.getTables(null, null, tableName, null);
				if (rs.next()) {
					isExist = true;
				}
				rs.close();
			} catch (Exception ex) {
				LOG.error("Exception: " + ex);
			}
		} catch (Exception ex) {
			LOG.error(ex.getMessage());
		}
		LOG.info("<<<< +[checkTableExist] END");
		return isExist;
	}

	public boolean createHubSchema(Connection conn_, String query) {
		LOG.info(">>>> +[createHubSchema] START");
		if (LOG.isDebugEnabled()) {
			LOG.debug(">>>>>> +[createHubSchema]->query " + query);
		}
		boolean isSuccess = false;
		ResultSet rs = null;
		Statement st = null;
		try {
			if (conn_ == null) {
				LOG.error("Cannot to Xcenter database!!!");
				return false;
			}
			try {
				st = conn_.createStatement();
				rs = st.executeQuery(query);

				if (rs.next()) {
					isSuccess = true;
				}

				st.close();
				rs.close();
			} catch (Exception ex) {
				LOG.error("Exception: " + ex);
			}
		} catch (Exception ex) {
			LOG.error(ex.getMessage());
		}
		LOG.info("<<<< +[createHubSchema] END");
		return isSuccess;
	}

	/**
	 * Get connection object
	 * 
	 * @param name
	 * @return
	 */
	public Connection openConnection() {
		LOG.info(">>>> +[openConnection] START");
		return initConnection(EniOrderHubUtils.decrypter);
	}

	/**
	 * Close connection object
	 */
	public void closeConnection(Connection conn_) {
		LOG.info(">>>> +[closeConnection] START");
		try {
			if (conn_ != null) {
				conn_.close();
			}
		} catch (SQLException se) {
			LOG.error(se);
		}
		LOG.info("<<<< +[closeConnection] END");
	}
}
