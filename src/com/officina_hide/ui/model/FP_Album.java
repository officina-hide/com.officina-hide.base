package com.officina_hide.ui.model;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.model.FD_Column;
import com.officina_hide.base.model.FD_DB;
import com.officina_hide.base.model.FD_Numbering;
import com.officina_hide.base.model.FD_Table;

/**
 * アルバム情報クラス[Album information class]<br>
 * 各種メディアをまとめる単位、すべてのメディア情報はアルバムでまとめられる。<br>
 * A unit for collecting various media, all media information is collected in an album.
 * @author officina-hide.net
 * @version 1.00 新規作成[New create]
 * @since 2022/06/01 Ver. 1.00
 */
public class FP_Album extends FD_DB implements I_FP_Album {

	/** 環境情報[Environment information] */
	private FD_EnvData env;
	
	/**
	 * コンストラクター[Constructor]<br>
	 * @author officina-hide.net
	 * @since 2022/06/01 Ver. 1.00
	 * @param env 環境情報[Environment information]
	 */
	public FP_Album(FD_EnvData env) {
		this.env = env;
	}

	/**
	 * アルバム情報テーブル生成[Album information table generation]
	 * @author officina-hide.net
	 * @since 2022/06/01 Ver. 1.00
	 */
	public void createTable() {
		FD_Table table = new FD_Table(env);
		table.add(Entry_FD_Table);
		FD_Numbering num = new FD_Numbering(env);
		num.add(Entry_FD_Number);
		FD_Column column = new FD_Column(env);
		column.add(Entry_FD_Column_FP_Album_ID);
		column.add(Entry_FD_Column_FP_Album_Code);
		column.add(Entry_FD_Column_FD_Name);
		column.add(Entry_FD_Column_FD_Created);
		
		deleteTable(env, Table_Name);
		createTable(env, Table_Name, Table_Disp_Name);
	}

}
