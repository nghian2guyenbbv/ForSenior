USE [ENI_HUB]
GO

/****** Object:  Table [dbo].[eni_hub_customer]    Script Date: 10/24/2021 8:51:02 PM ******/
DROP TABLE [dbo].[eni_hub_customer]
GO

/****** Object:  Table [dbo].[eni_hub_customer]    Script Date: 10/24/2021 8:51:02 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[eni_hub_customer](
	[organization_id] [int] NOT NULL,
	[order_id] [varchar](60) NOT NULL,
	[customer_id] [varchar](60) NULL,
	[first_name] [varchar](60) NULL,
	[middle_name] [varchar](60) NULL,
	[last_name] [varchar](60) NULL,
	[telephone1] [varchar](32) NULL,
	[telephone2] [varchar](32) NULL,
	[email_address] [varchar](254) NULL,
	[address_1] [varchar](50) NULL,
	[address_2] [varchar](50) NULL,
	[address_3] [varchar](50) NULL,
	[address_4] [varchar](50) NULL,
	[city] [varchar](50) NULL,
	[country] [varchar](3) NULL,
	[company_name] [nvarchar](50) NULL,
	[province] [nvarchar](50) NULL,
	[postal] [nvarchar](50) NULL,
	[prefix] [varchar](50) NULL,
	[suffix] [varchar](50) NULL,
	[apt] [varchar](50) NULL,
	[create_user_id] [varchar](256) NULL,
	[create_date] [datetime] NULL,
	[update_user_id] [varchar](256) NULL,
	[update_date] [datetime] NULL,
	[record_state] [varchar](30) NULL,
 CONSTRAINT [pk_eni_hub_customer] PRIMARY KEY CLUSTERED 
(
	[organization_id] ASC,
	[order_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, FILLFACTOR = 80) ON [PRIMARY]
) ON [PRIMARY]

GO


