package com.officina_hide.fx.model;

import java.util.List;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.common.FD_WhereData;

/**
 * タブ階層情報[Tab hierarchy information]<br>
 * @author officine-hide.net
 * @version 1.00
 * @since 2021/09/13
 */
public class FX_Tabs {

	private List<FX_Tab> tabList;
	
	/**
	 * コンストラクタ[Constructor]<br>
	 * @author officina-hide.net
	 * @since 1.00 2021/09/13
	 * @param env 環境情報[Environment information]
	 * @param viewId 画面情報Id[Screen Information ID]
	 * @param level タブレベル[Tab Level No]
	 */
	public FX_Tabs(FD_EnvData env, Integer viewId, int level) {
		/*
		 * 現段階では呼び出しレベルの一覧を作成する。
		 * 将来的に階層構造とする。
		 */
		FD_WhereData where = new FD_WhereData(I_Fx_Field.COLUMNNAME_Fx_View_ID, viewId);
//		where.add(FD_WhereData.AND, I_FX_Tab.COLUMNNAME_FX_Tab_Level, 0);
	}

}
