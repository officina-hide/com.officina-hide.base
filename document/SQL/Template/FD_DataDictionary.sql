/* 辞書情報 */
Drop table if exists FD_DataDictionnary;
create table if not exists FD_DataDictionnary
(
	FD_DataDictionnary_ID int unsigned NOT NULL COMMENT '辞書情報ID',
	FD_Name varchar(100) unique COMMENT '要素名',
	FD_Description text COMMENT '要素説明',
	FD_Created datetime DEFAULT NULL COMMENT '登録日',
	FD_CreatedBy int(10) unsigned DEFAULT NULL COMMENT '登録者ID',
	FD_Updated datetime DEFAULT NULL COMMENT '更新日',
	FD_UpdatedBy int(10) unsigned DEFAULT NULL COMMENT '更新者ID'
)
ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='テーブル情報';
/* 必要な辞書情報の登録 */
insert into FD_DataDictionnary
	( FD_DataDictionnary_ID, FD_Name, FD_Description
	, FD_Created, FD_CreatedBy, FD_Updated, FD_UpdatedBy)
values
	(100, 'FD_DataDictionnary_ID', '辞書情報ID'
	, '2021/07/31 12:00:00', 100, '2021/07/31 12:00:00', 100 ),
	(101, 'FD_Name', '要素名'
	, '2021/07/31 12:00:00', 100, '2021/07/31 12:00:00', 100 ),
	(102, 'FD_Description', '要素説明'
	, '2021/07/31 12:00:00', 100, '2021/07/31 12:00:00', 100 ),
	(103, 'FD_Created', '登録日'
	, '2021/07/31 12:00:00', 100, '2021/07/31 12:00:00', 100 ),
	(104, 'FD_CreatedBy', '登録者ID'
	, '2021/07/31 12:00:00', 100, '2021/07/31 12:00:00', 100 ),
	(105, 'FD_Updated', '更新日'
	, '2021/07/31 12:00:00', 100, '2021/07/31 12:00:00', 100 ),
	(106, 'FD_UpdatedBy', '更新者ID'
	, '2021/07/31 12:00:00', 100, '2021/07/31 12:00:00', 100 ),
	(107, 'FD_Table_ID', 'テーブル情報ID'
	, '2021/07/31 12:00:00', 100, '2021/07/31 12:00:00', 100 ),
	(108, 'FD_Table_Name', 'テーブル物理名'
	, '2021/07/31 12:00:00', 100, '2021/07/31 12:00:00', 100 )
;
