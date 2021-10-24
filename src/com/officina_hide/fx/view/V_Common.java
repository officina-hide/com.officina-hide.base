package com.officina_hide.fx.view;

import java.util.List;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.common.FD_WhereData;
import com.officina_hide.fx.model.FX_Field;
import com.officina_hide.fx.model.I_FX_Field;
import com.officina_hide.fx.model.X_FX_Field;

import javafx.scene.layout.VBox;

/**
 * 画面共通処理クラス[Screen common processing class]<br>
 * @author officina-hide.net
 * @version 1.00 新規作成
 * @since 2021/10/24 Ver. 1.00
 */
public class V_Common {

	/**
	 * 画面項目設定[Screen item settings]<br>
	 * @author officina-hide.net
	 * @since 2021/10/24 Ver. 1.00
	 * @param env 環境情報[Environment information]
	 * @param root ルート[Root]
	 * @param tabId タブID[Tab ID]
	 */
	public void setFIeld(FD_EnvData env, VBox root, long tabId) {
		FX_Field field = new FX_Field();
		FD_WhereData where = new FD_WhereData(I_FX_Field.COLUMNNAME_FX_Tab_ID, tabId);
		List<X_FX_Field> flist = field.getList(env, where);
	}

}
