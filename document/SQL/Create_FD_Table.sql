/* FD_TAble生成 */
DROP Table if Exists FD_Table;
CREATE TABLE IF NOT EXISTS FD_Table 
(
FD_Table_ID int(10) unsigned NOT NULL PRIMARY KEY COMMENT 'テーブル情報ID',
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
	( 100, 'FD_DataDictionary', '辞書情報', 'プロジェクト内で使用するオブジェクトの存在を定義する。'
	, 100, '2021/07/31 12:00:00', 100, '2021/07/31 12:00:00', 100 ),
	( 101, 'FD_Group', 'グループ情報', 'プロジェクト内で使用される情報の管理単位を定義する。'
	, 100, '2021/07/12 12:00:00', 100, '2021/07/12 12:00:00', 100 ),
	( 102, 'FD_User', 'ユーザー情報', 'プロジェクトを利用するユーザーを管理する。'
	, 100, '2021/07/12 12:00:00', 100, '2021/07/12 12:00:00', 100 ),
	( 103, 'FD_Table', 'テーブル情報', 'テーブルの基本情報を定義する。'
	, 100, '2021/07/12 12:00:00', 100, '2021/07/12 12:00:00', 100 ),
	( 104, 'FD_Column', 'テーブル項目情報', 'テーブルで使用する項目を管理する。'
	, 100, '2021/09/02 12:00:00', 100, '2021/09/02 12:00:00', 100 ),
	( 201, 'FX_View', '画面基本情報', 'Fx画面の基本情報を管理する。'
	, 100, '2021/07/12 12:00:00', 100, '2021/07/12 12:00:00', 100 ),
	( 202, 'FX_Field', '画面項目情報', 'Fx画面の項目情報を管理する。'
	, 100, '2021/07/12 12:00:00', 100, '2021/07/12 12:00:00', 100 )
;