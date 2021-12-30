USE [ENI_HUB]
GO
DROP TABLE [dbo].[eni_hub_rtl_loc]
GO

CREATE TABLE [dbo].[eni_hub_rtl_loc](
[organization_id] INT NOT NULL,
[rtl_loc_id] INT NOT NULL,
[store_name] VARCHAR(254),
[address1] VARCHAR(254),
[address2] VARCHAR(254),
[address3] VARCHAR(254),
[address4] VARCHAR(254),
[city] VARCHAR(254),
[state] VARCHAR(30),
[district] VARCHAR(30),
[area] VARCHAR(30),
[postal_code] VARCHAR(30),
[country] VARCHAR(2),
[neighborhood] VARCHAR(254),
[county] VARCHAR(254),
[locale] VARCHAR(30) NOT NULL,
[currency_id] VARCHAR(3),
[latitude] DECIMAL(17, 6),
[longitude] DECIMAL(17, 6),
[telephone1] VARCHAR(32),
[telephone2] VARCHAR(32),
[telephone3] VARCHAR(32),
[telephone4] VARCHAR(32),
[description] VARCHAR(254),
[store_nbr] VARCHAR(254),
[apartment] VARCHAR(30),
[store_manager] VARCHAR(254),
[email_addr] VARCHAR(254),
[default_tax_percentage] DECIMAL(8, 6),
[location_type] VARCHAR(60),
[delivery_available_flag] BIT DEFAULT (0) NOT NULL,
[pickup_available_flag] BIT DEFAULT (0) NOT NULL,
[transfer_available_flag] BIT DEFAULT (0) NOT NULL,
[geo_code] VARCHAR(20),
[uez_flag] BIT DEFAULT (0) NOT NULL,
[alternate_store_nbr] VARCHAR(254),
[use_till_accountability_flag] BIT DEFAULT (0) NOT NULL,
[deposit_bank_name] VARCHAR(254),
[deposit_bank_account_number] VARCHAR(30),
[airport_code] VARCHAR(3),
[legal_entity_id] VARCHAR(30),
[create_user_id] VARCHAR(256),
[create_date] DATETIME,
[update_user_id] VARCHAR(256),
[update_date] DATETIME,
[record_state] VARCHAR(30), 
CONSTRAINT [pk_loc_rtl_loc] PRIMARY KEY CLUSTERED (organization_id, rtl_loc_id))
GO
