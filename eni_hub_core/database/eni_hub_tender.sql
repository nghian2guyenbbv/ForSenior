USE [ENI_HUB]
GO

/****** Object:  Table [dbo].[eni_hub_tender]    Script Date: 10/24/2021 8:54:29 PM ******/
DROP TABLE [dbo].[eni_hub_tender]
GO

/****** Object:  Table [dbo].[eni_hub_tender]    Script Date: 10/24/2021 8:54:29 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[eni_hub_tender](
	[order_id] [varchar](60) NOT NULL,
	[line_no] [int] NOT NULL,
	[tender_description] [varchar](50) NULL,
	[tender_amount] [decimal](19, 4) NULL,
	[tender_account] [varchar](50) NULL,
	[create_user_id] [varchar](256) NULL,
	[create_date] [datetime] NULL,
	[update_user_id] [varchar](256) NULL,
	[update_date] [datetime] NULL,
 CONSTRAINT [pk_eni_hub_tender] PRIMARY KEY CLUSTERED 
(
	[order_id] ASC,
	[line_no] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, FILLFACTOR = 80) ON [PRIMARY]
) ON [PRIMARY]

GO