/* FX_Fieldテーブル生成 */
DROP Table if Exists FX_Field;
CREATE TABLE IF NOT EXISTS FX_Field
(
	FX_Field_ID int(10) unsigned NOT NULL PRIMARY KEY COMMENT '画面項目情報ID',
	FX_VIew_ID int(10) unsigned COMMENT '画面基本情報ID',
	FX_Field_Name varchar(100) COMMENT '画面項目識別名',
	FD_Name varchar(100) COMMENT '名前',
	FD_Column_ID int(10) unsigned COMMENT 'テーブル項目情報ID',
	Fx_isListField ENUM('Y', 'N') COMMENT 'リスト用項目判定',
	FD_Group_ID int(10) unsigned COMMENT '管理グループID',
	FD_Created datetime DEFAULT NULL COMMENT '登録日',
	FD_CreatedBy int(10) unsigned DEFAULT NULL COMMENT '登録者ID',
	FD_Updated datetime DEFAULT NULL COMMENT '更新日',
	FD_UpdatedBy int(10) unsigned DEFAULT NULL COMMENT '更新者ID'
)
ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='画面項目情報';

/* 情報登録 */
INSERT INTO FX_Field
	(
	FX_Field_ID, FX_VIew_ID, FX_Field_Name, FD_Name, FD_Column_ID, Fx_isListField,
	FD_Group_ID, FD_Created, FD_CreatedBy, FD_Updated, FD_UpdatedBy
	)
	VALUES
	(100, 100, 'FD_Table_Name', 'テーブル識別名', 101, 'Y',
	 101, '2021/08/23 11:00:00', 100, '2021/08/23 12:00:00', 100),
	(101, 100, 'FD_Name', '名前', 102, 'Y',
	 101, '2021/08/23 11:00:00', 100, '2021/08/23 12:00:00', 100),
	(102, 100, 'FD_Description', '説明', 103, 'Y',
	 101, '2021/08/23 11:00:00', 100, '2021/08/23 12:00:00', 100)
;