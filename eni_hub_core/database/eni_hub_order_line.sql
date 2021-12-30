USE [ENI_HUB]
GO

ALTER TABLE [dbo].[eni_hub_order_line] DROP CONSTRAINT [DF__eni_hub_o__ship___30F848ED]
GO

ALTER TABLE [dbo].[eni_hub_order_line] DROP CONSTRAINT [DF__eni_hub_o__gift___300424B4]
GO

ALTER TABLE [dbo].[eni_hub_order_line] DROP CONSTRAINT [DF__eni_hub_o__drop___2F10007B]
GO

ALTER TABLE [dbo].[eni_hub_order_line] DROP CONSTRAINT [DF__eni_hub_o__void___2E1BDC42]
GO

/****** Object:  Table [dbo].[eni_hub_order_line]    Script Date: 10/24/2021 8:54:07 PM ******/
DROP TABLE [dbo].[eni_hub_order_line]
GO

/****** Object:  Table [dbo].[eni_hub_order_line]    Script Date: 10/24/2021 8:54:07 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[eni_hub_order_line](
	[organization_id] [int] NOT NULL,
	[order_id] [varchar](60) NOT NULL,
	[line_no] [int] NOT NULL,
	[item_upc_code] [varchar](60) NULL,
	[item_ean_code] [varchar](60) NULL,
	[external_order_id] [varchar](60) NULL,
	[item_id] [varchar](60) NULL,
	[quantity] [decimal](11, 4) NULL,
	[fulfillment_type] [varchar](20) NULL,
	[status_code] [varchar](30) NULL,
	[unit_price] [decimal](17, 6) NULL,
	[extended_price] [decimal](17, 6) NULL,
	[tax_amount] [decimal](17, 6) NULL,
	[special_instructions] [varchar](max) NULL,
	[tracking_nbr] [varchar](60) NULL,
	[void_flag] [bit] NOT NULL,
	[actual_ship_method] [varchar](60) NULL,
	[drop_ship_flag] [bit] NOT NULL,
	[status_code_reason] [varchar](30) NULL,
	[status_code_reason_note] [nvarchar](4000) NULL,
	[extended_freight] [decimal](17, 6) NULL,
	[customization_charge] [decimal](17, 6) NULL,
	[gift_wrap_flag] [bit] NULL,
	[ship_alone_flag] [bit] NULL,
	[ship_weight] [decimal](17, 6) NULL,
	[line_message] [nvarchar](4000) NULL,
	[pickup_by_date] [datetime] NULL,
	[customization_code] [nvarchar](50) NULL,
	[customization_message] [nvarchar](4000) NULL,
	[carton_number] [nvarchar](50) NULL,
	[create_user_id] [varchar](256) NULL,
	[create_date] [datetime] NULL,
	[update_user_id] [varchar](256) NULL,
	[update_date] [datetime] NULL,
	[record_state] [varchar](30) NULL,
 CONSTRAINT [pk_eni_hub_order_line] PRIMARY KEY CLUSTERED 
(
	[organization_id] ASC,
	[order_id] ASC,
	[line_no] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, FILLFACTOR = 80) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO

ALTER TABLE [dbo].[eni_hub_order_line] ADD  DEFAULT ((0)) FOR [void_flag]
GO

ALTER TABLE [dbo].[eni_hub_order_line] ADD  DEFAULT ((0)) FOR [drop_ship_flag]
GO

ALTER TABLE [dbo].[eni_hub_order_line] ADD  DEFAULT ((0)) FOR [gift_wrap_flag]
GO

ALTER TABLE [dbo].[eni_hub_order_line] ADD  DEFAULT ((0)) FOR [ship_alone_flag]
GO


