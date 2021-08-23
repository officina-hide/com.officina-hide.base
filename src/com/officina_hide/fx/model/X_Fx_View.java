package com.officina_hide.fx.model;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.common.FD_WhereData;
import com.officina_hide.base.model.FD_DB;

/**
 * Fx画面基本情報I/Oクラス[Fx screen basic information I / O class]<br>
 * @author officine-hide.net
 * @version 1.00
 * @since 2021/08/23
 */
public class X_Fx_View extends FD_DB implements I_Fx_View {

	/**
	 * コンストラクタ[Constructor]<br>
	 * @author officine-hide.net
	 * @since 1.00 2021/08/23
	 * @param env 環境情報[Environment Information]
	 * @param where 条件情報[Where clouse Information]
	 */
	public X_Fx_View(FD_EnvData env, FD_WhereData where) {
		load(env, Table_Name, where);
	}

	/**
	 * 情報抽出[Information extraction]<br>
	 * TODO FD_DBに汎用化する。(2021/08/23)
	 * @author officine-hide.net
	 * @since 1.00 2021/08/23
	 * @param env 環境情報[Environment Information]
	 * @param tableName テーブル名[Table Name]
	 * @param where 条件情報[Where Clouse]
	 */
	private void load(FD_EnvData env, String tableName, FD_WhereData where) {
		//SQL文作成
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM ").append(tableName).append(" ");
		sql.append(where.toString()).append(" ");
	}

}
