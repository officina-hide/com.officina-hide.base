/* FX_Fieldテーブル生成 */
DROP Table if Exists FX_Field;
CREATE TABLE IF NOT EXISTS FX_field
(
	FX_Field_ID int(10) unsigned NOT NULL COMMENT '画面フィールド情報ID',
	FX_Field_Name varchar(100) COMMENT 'フィールド名',
	FD_Group_ID int(10) unsigned COMMENT '管理グループID',
	FD_Created datetime DEFAULT NULL COMMENT '登録日',
	FD_CreatedBy int(10) unsigned DEFAULT NULL COMMENT '登録者ID',
	FD_Updated datetime DEFAULT NULL COMMENT '更新日',
	FD_UpdatedBy int(10) unsigned DEFAULT NULL COMMENT '更新者ID'
)
ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='ホームページ基本情報';