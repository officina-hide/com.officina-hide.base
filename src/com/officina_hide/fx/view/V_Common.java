package com.officina_hide.fx.view;

import java.util.List;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.common.FD_WhereData;
import com.officina_hide.base.model.I_FD_DB;
import com.officina_hide.fx.model.FX_Field;
import com.officina_hide.fx.model.I_FX_Field;
import com.officina_hide.fx.model.X_FX_Field;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * 画面共通処理クラス[Screen common processing class]<br>
 * @author officina-hide.net
 * @version 1.00 新規作成
 * @since 2021/10/24 Ver. 1.00
 */
public class V_Common implements I_FD_DB {

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
		for(X_FX_Field fd : flist) {
			HBox fbox = new HBox(5);
			root.getChildren().add(fbox);
			fbox.setAlignment(Pos.CENTER_LEFT);
			
			//ラベルセット
			Label label = new Label(fd.getFD_Name());
			fbox.getChildren().add(label);
			//項目セット
			switch(fd.getFD_TypeItem(env).getFD_TypeItem_Name()) {
			case FD_Field_SimpleText:
				TextField textField = new TextField("");
				fbox.getChildren().add(textField);
				break;
			}
		}
	}

}
