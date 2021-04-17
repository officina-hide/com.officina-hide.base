/* 
	汎用 : テーブル削除
	?にテーブル名が入るが、使用時は  sql = sql.replaceAll("\\?", テーブル名);で対応する事。
*/
DROP TABLE IF EXISTS ?;