USE [master]
GO
/****** Object:  Database [Jarvis]    Script Date: 7/14/2018 4:36:50 PM ******/
CREATE DATABASE [Jarvis] ON  PRIMARY 
( NAME = N'Jarvis', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL10.SQLEXPRESS\MSSQL\DATA\Jarvis.mdf' , SIZE = 2048KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'Jarvis_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL10.SQLEXPRESS\MSSQL\DATA\Jarvis_log.ldf' , SIZE = 1024KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [Jarvis] SET COMPATIBILITY_LEVEL = 100
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [Jarvis].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [Jarvis] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [Jarvis] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [Jarvis] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [Jarvis] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [Jarvis] SET ARITHABORT OFF 
GO
ALTER DATABASE [Jarvis] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [Jarvis] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [Jarvis] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [Jarvis] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [Jarvis] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [Jarvis] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [Jarvis] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [Jarvis] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [Jarvis] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [Jarvis] SET  DISABLE_BROKER 
GO
ALTER DATABASE [Jarvis] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [Jarvis] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [Jarvis] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [Jarvis] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [Jarvis] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [Jarvis] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [Jarvis] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [Jarvis] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [Jarvis] SET  MULTI_USER 
GO
ALTER DATABASE [Jarvis] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [Jarvis] SET DB_CHAINING OFF 
GO
USE [Jarvis]
GO
/****** Object:  Table [dbo].[Hero]    Script Date: 7/14/2018 4:36:50 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Hero](
	[username] [nvarchar](50) NOT NULL,
	[password] [nvarchar](50) NOT NULL,
	[fullname] [nvarchar](50) NULL,
	[email] [nvarchar](50) NULL,
	[role] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_Hero] PRIMARY KEY CLUSTERED 
(
	[username] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Mark]    Script Date: 7/14/2018 4:36:50 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Mark](
	[id] [int] NOT NULL,
	[dateCreated] [date] NULL,
	[status] [nvarchar](10) NOT NULL,
	[height] [int] NOT NULL,
	[weight] [int] NOT NULL,
 CONSTRAINT [PK_Mark] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Mission]    Script Date: 7/14/2018 4:36:50 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Mission](
	[missionName] [nvarchar](50) NOT NULL,
	[place] [nvarchar](50) NOT NULL,
	[fromDate] [date] NOT NULL,
	[toDate] [date] NOT NULL,
	[status] [varchar](50) NOT NULL,
 CONSTRAINT [PK_Mission] PRIMARY KEY CLUSTERED 
(
	[missionName] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[MissionDetails]    Script Date: 7/14/2018 4:36:50 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[MissionDetails](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[missionName] [nvarchar](50) NOT NULL,
	[heroName] [nvarchar](50) NOT NULL,
	[subtask] [nvarchar](50) NOT NULL,
	[status] [nvarchar](10) NOT NULL CONSTRAINT [DF_MissionDetails_status]  DEFAULT (N'To do'),
PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
INSERT [dbo].[Hero] ([username], [password], [fullname], [email], [role]) VALUES (N'captain', N'captain', N'captain', N'captain@mail', N'user')
INSERT [dbo].[Hero] ([username], [password], [fullname], [email], [role]) VALUES (N'chi', N'chi', N'chi', N'chi@gmail.com', N'user')
INSERT [dbo].[Hero] ([username], [password], [fullname], [email], [role]) VALUES (N'hulk', N'hulk', N'hulk', N'hulk@mail', N'user')
INSERT [dbo].[Hero] ([username], [password], [fullname], [email], [role]) VALUES (N'jarvis', N'jarvis', N'jarvisss', N'jarvis@gmail.com', N'admin')
INSERT [dbo].[Hero] ([username], [password], [fullname], [email], [role]) VALUES (N'spiderman', N'spiderman', N'mann', N'a@bnchn', N'user')
INSERT [dbo].[Hero] ([username], [password], [fullname], [email], [role]) VALUES (N'thien', N'thien', N'hoanthien', N'hoanthien@gmail.com', N'user')
INSERT [dbo].[Hero] ([username], [password], [fullname], [email], [role]) VALUES (N'thor', N'thor', N'thor', N'thor@gmail.com', N'user')
INSERT [dbo].[Hero] ([username], [password], [fullname], [email], [role]) VALUES (N'tony', N'tony', N'tony', N'tony@gmail.com', N'user')
INSERT [dbo].[Mark] ([id], [dateCreated], [status], [height], [weight]) VALUES (1, CAST(N'2014-07-04' AS Date), N'ready', 299, 100)
INSERT [dbo].[Mark] ([id], [dateCreated], [status], [height], [weight]) VALUES (6, CAST(N'2015-02-10' AS Date), N'destroyed', 201, 200)
INSERT [dbo].[Mark] ([id], [dateCreated], [status], [height], [weight]) VALUES (8, CAST(N'2015-05-16' AS Date), N'ready', 200, 199)
INSERT [dbo].[Mark] ([id], [dateCreated], [status], [height], [weight]) VALUES (9, CAST(N'2016-07-03' AS Date), N'ready', 209, 90)
INSERT [dbo].[Mark] ([id], [dateCreated], [status], [height], [weight]) VALUES (28, CAST(N'2017-01-09' AS Date), N'ready', 250, 300)
INSERT [dbo].[Mark] ([id], [dateCreated], [status], [height], [weight]) VALUES (42, CAST(N'2018-03-04' AS Date), N'destroyed', 210, 255)
INSERT [dbo].[Mission] ([missionName], [place], [fromDate], [toDate], [status]) VALUES (N'avenger', N'usa', CAST(N'2015-09-30' AS Date), CAST(N'2018-12-09' AS Date), N'Doing')
INSERT [dbo].[Mission] ([missionName], [place], [fromDate], [toDate], [status]) VALUES (N'final', N'home', CAST(N'2018-07-05' AS Date), CAST(N'2018-07-13' AS Date), N'Failed')
INSERT [dbo].[Mission] ([missionName], [place], [fromDate], [toDate], [status]) VALUES (N'ghost', N'rusia', CAST(N'2018-07-15' AS Date), CAST(N'2018-08-02' AS Date), N'To do')
INSERT [dbo].[Mission] ([missionName], [place], [fromDate], [toDate], [status]) VALUES (N'p2p', N'vietnam', CAST(N'1995-04-20' AS Date), CAST(N'1995-12-30' AS Date), N'Failed')
INSERT [dbo].[Mission] ([missionName], [place], [fromDate], [toDate], [status]) VALUES (N'topdown', N'belgium', CAST(N'2018-07-01' AS Date), CAST(N'2018-08-15' AS Date), N'Doing')
SET IDENTITY_INSERT [dbo].[MissionDetails] ON 

INSERT [dbo].[MissionDetails] ([ID], [missionName], [heroName], [subtask], [status]) VALUES (1, N'p2p', N'spiderman', N'protect deadpool', N'Failed')
INSERT [dbo].[MissionDetails] ([ID], [missionName], [heroName], [subtask], [status]) VALUES (4, N'topdown', N'spiderman', N'kill mrX', N'Doing')
INSERT [dbo].[MissionDetails] ([ID], [missionName], [heroName], [subtask], [status]) VALUES (29, N'avenger', N'thien', N'try hard', N'Doing')
INSERT [dbo].[MissionDetails] ([ID], [missionName], [heroName], [subtask], [status]) VALUES (32, N'final', N'thien', N'try again', N'To do')
INSERT [dbo].[MissionDetails] ([ID], [missionName], [heroName], [subtask], [status]) VALUES (37, N'topdown', N'chi', N'defense in the HQ', N'To do')
INSERT [dbo].[MissionDetails] ([ID], [missionName], [heroName], [subtask], [status]) VALUES (39, N'p2p', N'thien', N'defense ', N'Done')
INSERT [dbo].[MissionDetails] ([ID], [missionName], [heroName], [subtask], [status]) VALUES (40, N'p2p', N'tony', N'attack', N'Done')
INSERT [dbo].[MissionDetails] ([ID], [missionName], [heroName], [subtask], [status]) VALUES (47, N'ghost', N'spiderman', N'attack Krum', N'To do')
INSERT [dbo].[MissionDetails] ([ID], [missionName], [heroName], [subtask], [status]) VALUES (48, N'ghost', N'thien', N'help spiderman', N'To do')
INSERT [dbo].[MissionDetails] ([ID], [missionName], [heroName], [subtask], [status]) VALUES (49, N'topdown', N'hulk', N'go to school now', N'Doing')
SET IDENTITY_INSERT [dbo].[MissionDetails] OFF
ALTER TABLE [dbo].[MissionDetails]  WITH CHECK ADD  CONSTRAINT [FK_memberOfMission_Hero] FOREIGN KEY([heroName])
REFERENCES [dbo].[Hero] ([username])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[MissionDetails] CHECK CONSTRAINT [FK_memberOfMission_Hero]
GO
ALTER TABLE [dbo].[MissionDetails]  WITH CHECK ADD  CONSTRAINT [FK_memberOfMission_Mission] FOREIGN KEY([missionName])
REFERENCES [dbo].[Mission] ([missionName])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[MissionDetails] CHECK CONSTRAINT [FK_memberOfMission_Mission]
GO
USE [master]
GO
ALTER DATABASE [Jarvis] SET  READ_WRITE 
GO
