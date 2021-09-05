/* FD_Type生成 */
DROP Table if Exists FD_Type;
CREATE TABLE IF NOT EXISTS FD_Type
(
	FD_Type_ID int unsigned NOT NULL PRIMARY KEY COMMENT '種別情報ID',
	FD_Type_Name varchar(100) COMMENT '種別識別名',
	FD_Name varchar(100) COMMENT '名前',
	FD_Description text COMMENT '説明',
	FD_Group_ID int(10) unsigned COMMENT '管理グループID',
	FD_Created datetime DEFAULT NULL COMMENT '登録日',
	FD_CreatedBy int(10) unsigned DEFAULT NULL COMMENT '登録者ID',
	FD_Updated datetime DEFAULT NULL COMMENT '更新日',
	FD_UpdatedBy int(10) unsigned DEFAULT NULL COMMENT '更新者ID'
)
ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='種別情報';

/* 情報登録 */
INSERT INTO FD_Type 
	(
	FD_Type_ID, FD_Type_Name, FD_Name, FD_Description, 
	FD_Group_ID, FD_Created, FD_CreatedBy, FD_Updated, FD_UpdatedBy
	)
VALUES
	(100, 'FD_ID', '情報ID', 'テーブル内の情報をユニークに管理する番号',
	101, '2021/09/04 12:00:00', 100, '2021/09/04 12:00:00', 100),
	(101, 'FD_String', '文字列', '単一行のテキスト情報',
	101, '2021/09/04 12:00:00', 100, '2021/09/04 12:00:00', 100),
	(102, 'FD_Text', 'テキスト', '複数行のテキスト情報',
	101, '2021/09/04 12:00:00', 100, '2021/09/04 12:00:00', 100),
	(103, 'FD_Date', '日付', '日付情報',
	101, '2021/09/04 12:00:00', 100, '2021/09/04 12:00:00', 100)
;