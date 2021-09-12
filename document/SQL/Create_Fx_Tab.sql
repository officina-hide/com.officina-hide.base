/* FX_Tab生成 */
DROP Table if Exists FX_Tab;
CREATE TABLE IF NOT EXISTS FX_Tab 
(
	FX_Tab_ID int(10) unsigned NOT NULL PRIMARY KEY COMMENT '画面タブ情報ID',
	FX_Tab_Name varchar(100) COMMENT 'タブ識別名',
	FD_Tab_Seq int(10) unsigned COMMENT 'タブ並び順',
	FD_Tab_Level int(10) unsigned COMMENT 'タブ階層番号',
	FD_TableID int(10) unsigned COMMENT 'テーブル情報ID',
	FD_Group_ID int(10) unsigned COMMENT '管理グループID',
	FD_Created datetime DEFAULT NULL COMMENT '登録日',
	FD_CreatedBy int(10) unsigned DEFAULT NULL COMMENT '登録者ID',
	FD_Updated datetime DEFAULT NULL COMMENT '更新日',
	FD_UpdatedBy int(10) unsigned DEFAULT NULL COMMENT '更新者ID'	
)
ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='画面タブ情報';