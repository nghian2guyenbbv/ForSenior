USE [ENI_HUB]
GO

ALTER TABLE [dbo].[eni_hub_order] DROP CONSTRAINT [DF__eni_hub_o__under__2B3F6F97]
GO

ALTER TABLE [dbo].[eni_hub_order] DROP CONSTRAINT [DF__eni_hub_o__ship___2A4B4B5E]
GO

/****** Object:  Table [dbo].[eni_hub_order]    Script Date: 10/24/2021 8:51:39 PM ******/
DROP TABLE [dbo].[eni_hub_order]
GO

/****** Object:  Table [dbo].[eni_hub_order]    Script Date: 10/24/2021 8:51:39 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[eni_hub_order](
	[organization_id] [int] NOT NULL,
	[order_id] [varchar](60) NOT NULL,
	[order_type] [varchar](30) NULL,
	[status_code] [varchar](30) NULL,
	[order_date] [datetime] NULL,
	[order_loc_id] [varchar](60) NULL,
	[subtotal] [decimal](17, 6) NULL,
	[tax_amount] [decimal](17, 6) NULL,
	[total] [decimal](17, 6) NULL,
	[balance_due] [decimal](17, 6) NULL,
	[special_instructions] [varchar](max) NULL,
	[ref_nbr] [varchar](60) NULL,
	[ship_via] [nvarchar](50) NULL,
	[ship_via_description] [nvarchar](50) NULL,
	[additional_freight_charges] [decimal](17, 6) NULL,
	[additional_charges] [decimal](17, 6) NULL,
	[ship_complete_flag] [bit] NULL,
	[freight] [decimal](17, 6) NULL,
	[freight_tax] [decimal](17, 6) NULL,
	[order_message] [nvarchar](4000) NULL,
	[gift] [nvarchar](4000) NULL,
	[gift_message] [nvarchar](4000) NULL,
	[under_review_flag] [bit] NULL,
	[status_code_reason] [varchar](30) NULL,
	[status_code_reason_note] [nvarchar](4000) NULL,
	[ecom_sent_flag] [bit] NULL,
	[create_user_id] [varchar](256) NULL,
	[create_date] [datetime] NULL,
	[update_user_id] [varchar](256) NULL,
	[update_date] [datetime] NULL,
	[record_state] [varchar](30) NULL,
 CONSTRAINT [pk_eni_hub_order] PRIMARY KEY CLUSTERED 
(
	[organization_id] ASC,
	[order_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, FILLFACTOR = 80) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO

ALTER TABLE [dbo].[eni_hub_order] ADD  DEFAULT ((0)) FOR [ship_complete_flag]
GO

ALTER TABLE [dbo].[eni_hub_order] ADD  DEFAULT ((0)) FOR [under_review_flag]
GO


