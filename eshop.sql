create database eshop;
go
USE [eshop];
GO

CREATE TABLE [dbo].[types](						--商品类别表
	[code] [int] IDENTITY(1,1) PRIMARY KEY,			--类别号
	[title] [varchar](20) NULL						--类别名称
) ON [PRIMARY]
GO

CREATE TABLE [dbo].[wares](						--商品表
	[code] [varchar](10) PRIMARY KEY,				--商品号
	[title] [varchar](20) NULL,						--品名
	[model] [varchar](20) NULL,						--型号
	[depict] [varchar](50) NULL,					--描述
	[photo] [varchar](20) NULL,						--图片文件
	[price] [smallmoney] NULL,						--单价
	[amount] int default 0,							--数量
	[type] [int] references [types](code)			--所属类别
) ON [PRIMARY]
GO

CREATE TABLE [dbo].[orders](					--订单表
	[code] [bigint] IDENTITY(1,1) PRIMARY KEY,		--订单号
	[owner] [varchar](18) NULL,						--所属客户
	[date] [date] default getDate(),				--下单日期
	[total] [money] NULL,							--总金额
	[consignee] [varchar](18),						--收货人
	[address] [varchar](36),						--收货地址
	[phone] [varchar](15)							--联系电话
) ON [PRIMARY]
GO

CREATE TABLE [dbo].[details](					--订单明细表
	[order] [bigint],								--所属订单
	[ware] [varchar](10) references wares(code),	--所购商品
	[amount] [int] NULL,							--数量
	[price] [smallmoney] NULL,						--单价
	PRIMARY KEY([order] ASC,[ware] ASC)
) ON [PRIMARY]
GO

CREATE TABLE [dbo].[clients](					--客户表
	[code] [varchar](18) PRIMARY KEY,				--客户号（email）
	[title] [varchar](18) NULL,						--客户名称
	[phone] [varchar](15) NULL,						--联系电话
	[wechat] [varchar](18) null,					--微信号
	[address] [varchar](36) NULL,					--住址
	[password] [varchar](15) NULL,					--登录密码
	[manager] [bit] default(0)						--是管理员吗（默认为否）
) ON [PRIMARY]
GO
insert clients values('zhsan@mail.com','张三','13812345678','13812345678','昆明市青年路100号','abc123',0);
insert clients values('lisi@mail.com','李思','13912345678','13912345678','昆明市青年路101号','abc321',0);
insert clients values('admin@mail.com','管理员','13712345678','13712345678','昆明市青年路102号','abc111',1);
go
INSERT [dbo].[types] VALUES (N'照相机')
INSERT [dbo].[types] VALUES (N'摄像机')
INSERT [dbo].[types] VALUES (N'手机')
INSERT [dbo].[types] VALUES (N'笔记本电脑')
GO
INSERT [dbo].[wares] VALUES (N'bjb151824', N'联想笔记本电脑   ', N'联想        ', N'4核/2G/200G', N'bjb151824', 6800.0000, 20, 4)
INSERT [dbo].[wares] VALUES (N'bjb152019', N'索尼笔记本电脑   ', N'SONY      ', N'6核/3G/300G', N'bjb152019', 10000.0000, 20, 4)
INSERT [dbo].[wares] VALUES (N'bjb152137', N'华硕笔记本电脑   ', N'ASUS      ', N'4核/3G/300G', N'bjb152137', 8000.0000, 20, 4)
INSERT [dbo].[wares] VALUES (N'bjb152242', N'华硕笔记本电脑   ', N'ASUS      ', N'4核/3G/400G', N'bjb152242', 8500.0000, 20, 4)
INSERT [dbo].[wares] VALUES (N'bjb152419', N'索尼笔记本电脑   ', N'SONY      ', N'6核/3G/350G', N'bjb152419', 10500.0000, 20, 4)
INSERT [dbo].[wares] VALUES (N'bjb152610', N'联想笔记本电脑   ', N'联想        ', N'5核/3G/300G', N'bjb152610', 7500.0000, 20, 4)
INSERT [dbo].[wares] VALUES (N'bjb152717', N'华硕笔记本电脑   ', N'ASUS      ', N'4核/2.5G/500G', N'bjb152717', 9000.0000, 20, 4)
INSERT [dbo].[wares] VALUES (N'sj123136', N'七喜手机      ', N'HEDY      ', N'3核/3G/500M', N'sj123136', 1800.0000, 20, 3)
INSERT [dbo].[wares] VALUES (N'sj123817', N'MOTOROLA手机', N'MOTOROLA  ', N'3核/3G/600M', N'sj123817', 2000.0000, 20, 3)
INSERT [dbo].[wares] VALUES (N'sj123929', N'三星手机      ', N'SAMSUNG   ', N'3核/3G/650M', N'sj123929', 2500.0000, 20, 3)
INSERT [dbo].[wares] VALUES (N'sxj122152', N'SONY摄像机   ', N'SONY      ', N'3.5"/2000M/48倍', N'sxj122152', 12500.0000, 20, 2)
INSERT [dbo].[wares] VALUES (N'sxj122618', N'Canon摄像机  ', N'CANON     ', N'4"/3000M/48倍', N'sxj122618', 15000.0000, 20, 2)
INSERT [dbo].[wares] VALUES (N'xj120912', N'Premier相机 ', N'PREMIER   ', N'7.0DM-7365/3X Super Zoom', N'xj120912', 1200.0000, 20, 1)
INSERT [dbo].[wares] VALUES (N'xj121208', N'CASIO相机   ', N'CASIO     ', N'600万像素A700', N'xj121208', 1500.0000, 20, 1)
INSERT [dbo].[wares] VALUES (N'xj121349', N'SAMSUNG相机 ', N'SAMSUNG   ', N'三星照相机的若干参数', N'xj121349', 1000.0000, 20, 1)
INSERT [dbo].[wares] VALUES (N'xj121618', N'SONY相机    ', N'SONY      ', N'这是索尼相机的有关参数', N'xj121618', 2000.0000, 20, 1)
INSERT [dbo].[wares] VALUES (N'xj121809', N'FUJIFILM相机', N'FUJIFILM  ', N'这是FUJIFILM相机的若干参数', N'xj121809', 1600.0000, 20, 1)
INSERT [dbo].[wares] VALUES (N'xj122028', N'OLYMPUS相机 ', N'OLYMPUS   ', N'有关参数：u760 All-Weather', N'xj122028', 1800.0000, 20, 1)
go

create proc addDetails
	@order bigint,
	@goods varchar(10),
	@amount int,
	@price smallmoney
as begin
	insert details values(@order,@goods,@amount,@price);
	update wares set amount=amount-@amount where code=@goods;
end