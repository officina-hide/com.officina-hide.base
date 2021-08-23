/* FX_Viewテーブル生成 */
DROP Table if Exists FX_View;
CREATE TABLE IF NOT EXISTS FX_View
(
	FX_View_ID int unsigned NOT NULL COMMENT '画面基本情報ID',
	FX_View_Name varchar(100) COMMENT '画面識別名',
	FD_Name varchar(100) COMMENT '名前',
	FD_Description text COMMENT '説明',
	FD_Group_ID int(10) unsigned COMMENT '管理グループID',
	FD_Created datetime DEFAULT NULL COMMENT '登録日',
	FD_CreatedBy int(10) unsigned DEFAULT NULL COMMENT '登録者ID',
	FD_Updated datetime DEFAULT NULL COMMENT '更新日',
	FD_UpdatedBy int(10) unsigned DEFAULT NULL COMMENT '更新者ID'
)
ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='画面基本情報';

/* データ登録 */
INSERT INTO FX_View
	(
	FX_View_ID, FX_View_Name, FD_Name, FD_Description,
	FD_Group_ID, FD_Created, FD_CreatedBy, FD_Updated, FD_UpdatedBy
	)
	VALUES
	(100, 'FX_FD_Table_View', 'テーブル情報単票画面', 'テーブル情報の内容を登録・変更する為の単票画面'
	, 101, '2021/08/23 11:00:00', 100, '2021/08/23 12:00:00', 100),
	(101, 'FX_Menu', '総合メニュー', '各機能への遷移を管理する。'
	, 101, '2021/08/23 11:00:00', 100, '2021/08/23 12:00:00', 100)
;