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
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

import org.apache.log4j.Logger;

import eni.hub.order.util.EniOrderHubUtils;

public class EniOrderHubTenderDBA {
	private static Logger LOG = Logger.getLogger(EniOrderHubTenderDBA.class);

	private String INSERT_OBJECT = "INSERT INTO [dbo].[eni_hub_tender] (organization_id, order_id, line_no, tender_description, tender_amount, tender_account, create_user_id, create_date, update_user_id, update_date) VALUES(?,?,?,?,?,?,?,?,?,?)";
	public static final String SELECT_OBJECT = "SELECT order_id,line_no,tender_description,tender_amount,tender_account FROM  [dbo].[eni_hub_tender] where order_id = ?";
	private int organizationId = 1;
	private String orderId = null;
	private int lineNo = -1;
	private String tenderDescription = null;
	private BigDecimal tenderAmount = null;
	private String tenderAccount = null;

	public String getInsertQuery() {
		return INSERT_OBJECT;
	}

	public String getSelectQuery() {
		return SELECT_OBJECT;
	}

	public EniOrderHubTenderDAO loadDAO() {
		EniOrderHubTenderDAO dao = new EniOrderHubTenderDAO();
		dao.setOrganizationId(this.organizationId);
		dao.setOrderId(this.orderId);
		dao.setLineNo(this.lineNo);
		dao.setTenderDescription(this.tenderDescription);
		dao.setTenderAccount(this.tenderAccount);
		dao.setTenderAmount(this.tenderAmount);

		return dao;
	}

	public void fillDAO(EniOrderHubTenderDAO argDAO) {
		this.organizationId = argDAO.getOrganizationId();
		this.orderId = argDAO.getOrderId();
		this.lineNo = argDAO.getLineNo();
		this.tenderDescription = argDAO.getTenderDescription();
		this.tenderAccount = argDAO.getTenderAccount();
		this.tenderAmount = argDAO.getTenderAmount();
	}

	public PreparedStatement initParameterForInsert(PreparedStatement argPreparedStatement) throws SQLException {

		String createDate = EniOrderHubUtils.formatDateTime("yyyy-MM-dd HH:mm:ss", new Date());
		String createUserId = "ORDER_HUB";

		argPreparedStatement.setInt(1, this.organizationId);
		argPreparedStatement.setString(2, this.orderId);
		argPreparedStatement.setInt(3, this.lineNo);
		argPreparedStatement.setString(4, this.tenderDescription);
		argPreparedStatement.setBigDecimal(5, this.tenderAmount);
		argPreparedStatement.setString(6, this.tenderAccount);
		argPreparedStatement.setString(7, createDate);
		argPreparedStatement.setString(8, createUserId);
		argPreparedStatement.setString(9, null);
		argPreparedStatement.setString(10, null);

		if (LOG.isDebugEnabled()) {
			LOG.debug(">>>>>> +[insertData]-> query " + getInsertQuery());
			LOG.debug(">>>>>> +[insertData]-> values: " + this.organizationId + "," + this.orderId + "," + this.lineNo
					+ "," + this.tenderDescription + "," + this.tenderAmount + "," + this.tenderAccount);
		}

		return argPreparedStatement;
	}

	public PreparedStatement initParameterForRetrieveTenderData(PreparedStatement argPreparedStatement,
			 String argOrderLocationId) throws SQLException {
		argPreparedStatement.setString(1, argOrderLocationId);
		if (LOG.isDebugEnabled()) {
			LOG.debug(">>>>>> +[initParameterForRetrieveTenderData]-> query " + getSelectQuery());
			LOG.debug(">>>>>> +[initParameterForRetrieveTenderData]-> values: " + argOrderLocationId );
		}
		return argPreparedStatement;
	}
}
