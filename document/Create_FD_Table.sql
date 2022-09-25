CREATE TABLE IF NOT EXISTS FD_Number 
(
	FD_Table_ID bigint unsigned NOT NULL PRIMARY KEY COMMENT 'テーブル情報ID',
	FD_Table_UU varchar(22) COMMENT 'テーブル情報UUID',
	FD_Created Date COMMENT '登録日',
	FD_CreatedBy bigint unsigned COMMENT '登録者ID'
)
ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='テーブル情報';