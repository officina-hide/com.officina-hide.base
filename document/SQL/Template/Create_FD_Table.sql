/* FD_TAble生成 */
CREATE TABLE IF NOT EXISTS FD_Table 
(
FD_Table_ID int(10) unsigned NOT NULL COMMENT 'テーブル情報ID',
FD_Table_Name varchar(100) COMMENT 'テーブル物理名',
FD_Name varchar(100) COMMENT 'テーブル表示名',
FD_Description text COMMENT '説明',
FD_Group_ID int(10) unsigned COMMENT '管理グループID',
FD_Created datetime DEFAULT NULL COMMENT '登録日',
FD_CreatedBy int(10) unsigned DEFAULT NULL COMMENT '登録者ID',
FD_Updated datetime DEFAULT NULL COMMENT '更新日',
FD_UpdatedBy int(10) unsigned DEFAULT NULL COMMENT '更新者ID'
)
ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='テーブル情報';
/* 必要なテーブル情報の登録 */
INSERT INTO FD_Table
	( FD_Table_ID, FD_Table_Name, FD_Name, FD_Description
	, FD_Group_ID, FD_Created, FD_CreatedBy, FD_Updated, FD_UpdatedBy)
	VALUES
	( 100, 'FD_Table', 'テーブル情報', 'テーブルの基本情報を管理する。'
	, 100, '2021/07/12 12:42:00', 100, '2021/07/12 12:42:00', 100 );
	