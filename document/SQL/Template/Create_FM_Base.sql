/* FM_Baseテーブル生成 */
DROP Table if Exists FM_Base;
CREATE TABLE IF NOT EXISTS FM_Base
(
	FM_Base_ID int(10) unsigned NOT NULL COMMENT 'ホームページ基本情報ID',
	FM_Title varchar(100) COMMENT 'タイトル',
	FD_Group_ID int(10) unsigned COMMENT '管理グループID',
	FD_Created datetime DEFAULT NULL COMMENT '登録日',
	FD_CreatedBy int(10) unsigned DEFAULT NULL COMMENT '登録者ID',
	FD_Updated datetime DEFAULT NULL COMMENT '更新日',
	FD_UpdatedBy int(10) unsigned DEFAULT NULL COMMENT '更新者ID'
)
ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='ホームページ基本情報';
/* データ登録 */
INSERT INTO FM_Base
(FM_Base_ID, FM_Title,
 FD_Group_ID, FD_Created, FD_CreatedBy, FD_Updated, FD_UpdatedBy)
values
(101, '秀さんの情報工房',
 201, '2021/08/12 12:00:00', 100, '2021/08/12 12:00:00', 100);
