package eni.hub.services.impl;

public class EniOrderHubQueryConstant {
   public static final int ORGANIZATION_ID_DEFAULT = 1;
   public static final String NEW_ORDER ="NEW_ORDER";
   public static String COMPLETE = "COMPLETE";
   public static final String CANCELED = "CANCELED";
   public static final String HUB_ORDER_STATUS_UPDATE_QUERY = "UPDATE [dbo].[eni_hub_order] SET status_code = ? WHERE organization_id = ? AND order_id = ? AND order_loc_id = ?"; 
   public static final String HUB_ORDER_LINE_STATUS_UPDATE_QUERY = "UPDATE [dbo].[eni_hub_order_line] SET status_code = ? WHERE organization_id = ? AND order_id = ? AND item_id = ? AND line_no = ?"; 
   public static final String HUB_ORDER_UPDATE_RECORD_STATE ="UPDATE [dbo].[eni_hub_order] SET record_state = ? WHERE organization_id = ? AND order_loc_id = ? AND order_id = ?";
   public static final String HUB_ORDER_LINE_UPDATE_RECORD_STATE ="UPDATE [dbo].[eni_hub_order_line] SET record_state = ? WHERE organization_id = ? AND order_id = ?";
}
