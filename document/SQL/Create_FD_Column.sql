/* FD_Column生成 */
DROP Table if Exists FD_Column;
CREATE TABLE IF NOT EXISTS FD_Column
(
FD_Column_ID int unsigned NOT NULL PRIMARY KEY COMMENT 'テーブル項目情報ID',
FD_Column_Name varchar(100) COMMENT 'テーブル識別名',
FD_DataDictionnary_ID int unsigned NOT NULL COMMENT '辞書情報ID',
FD_Table_ID int unsigned COMMENT 'テーブル情報ID',
FD_Group_ID int(10) unsigned COMMENT '管理グループID',
FD_Created datetime DEFAULT NULL COMMENT '登録日',
FD_CreatedBy int(10) unsigned DEFAULT NULL COMMENT '登録者ID',
FD_Updated datetime DEFAULT NULL COMMENT '更新日',
FD_UpdatedBy int(10) unsigned DEFAULT NULL COMMENT '更新者ID'
)
ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='テーブル項目情報';
/* 必要なテーブル情報の登録 */
INSERT INTO FD_Column
	(FD_Column_ID, FD_Column_Name, FD_DataDictionnary_ID, FD_Table_ID
	, FD_Group_ID, FD_Created, FD_CreatedBy, FD_Updated, FD_UpdatedBy)
VALUES
	(100, 'FD_Table_ID', 107, 103
	, 100, '2021/09/03 12:00:00', 100, '2021/09/03 12:00:00', 100),
	(101, 'FD_Table_Name', 108, 103
	, 100, '2021/09/03 12:00:00', 100, '2021/09/03 12:00:00', 100),
	(102, 'FD_Name', 101, 103
	, 100, '2021/09/03 12:00:00', 100, '2021/09/03 12:00:00', 100)
;