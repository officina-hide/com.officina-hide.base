package com.officina_hide.base.tools;

import com.officina_hide.base.model.I_FD_DB;

/**
 * クラス用共通処理クラス[Common processing class for class]
 * @author officina-hide.met
 * @since 2022/02/21 Ver. 1.00
 */
public class FD_ClazzUtil implements I_FD_DB {

	/**
	 * タブ文字列生成[Tab string generation]
	 * @author officina-hide.net
	 * @since 2022/02/21 Ver. 1.00
	 * @param cnt タブ数[Tab count]
	 * @return タブ文字列[Tab String]
	 */
	public String tab(int cnt) {
		StringBuffer tb = new StringBuffer();
		for(int ix = 0; ix < cnt ; ix++) {
			tb.append(FD_TAB);
		}
		return tb.toString();
	}

}
