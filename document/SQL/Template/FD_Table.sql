/* FD_Table削除 */
DROP TABLE IF EXISTS FD_Table;

/* FD_TAble生成 */
CREATE TABLE IF NOT EXISTS FD_Table 
(
FD_Table_ID int(10) unsigned NOT NULL COMMENT 'テーブル情報ID',
FD_Name varchar(100) COMMENT 'テーブル名',
FD_Created datetime DEFAULT NULL COMMENT '登録日',
FD_CreatedBy int(10) unsigned DEFAULT NULL COMMENT '登録者ID',
FD_Updated datetime DEFAULT NULL COMMENT '更新日',
FD_UpdatedBy int(10) unsigned DEFAULT NULL COMMENT '更新者ID'
)
ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='テーブル情報';