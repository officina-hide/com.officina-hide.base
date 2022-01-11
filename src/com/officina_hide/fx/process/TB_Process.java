package com.officina_hide.fx.process;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.fx.base.FX_FieldItem;
import com.officina_hide.fx.base.FX_Fields;
import com.officina_hide.fx.model.I_FX_ToolBar;
import com.officina_hide.fx.model.X_FX_Toolbar;

import javafx.scene.control.TextField;

/**
 * ツールバー処理[Toolbar processing]<br>
 * @author officina-hide.net
 * @version 新規作成
 * @since 2021/12/07 Ver. 1.00
 */
public class TB_Process implements I_FX_ToolBar {

	/**
	 * 処理実行[Processing execute]<br>
	 * @author officina-hide.net
	 * @since 2021/12/07 Ver. 1.00
	 * @param env 環境情報[Environment information]
	 * @param toolBar ツールバー情報[ToolBar information]
	 * @param fields 画面項目情報[Screen item information]
	 */
	public void execute(FD_EnvData env, X_FX_Toolbar toolBar, FX_Fields fields) {
		/*
		 * 保存ボタンが押されたときの処理<br>
		 * TODO 別クラス化予定
		 */
		if(toolBar.getFD_Name().equals(NAME_TB_Save)) {
			saveProcess(fields);
//			for(int ix = 0; ix < fields.getFields().size(); ix++) {
//				FX_FieldItem fitem = fields.getFields().get(ix);
//				switch(fitem.getFieldTypeName()) {
//				case FD_Field_Date:
//					DatePicker dt = (DatePicker) fitem.getFieldItem();
//					Calendar cal = new GregorianCalendar(new Locale(Locale.JAPAN.getLanguage(), Locale.JAPAN.getCountry()));
//					cal.set(dt.getValue().getYear(), dt.getValue().getMonth().getValue(), dt.getValue().getDayOfMonth());
//					break;
//				case FD_Field_List:
//					ComboBox<String> combo = (ComboBox<String>) fitem.getFieldItem();
//					break;
//				}
//			}
		}
		
		/*
		 * 「新規」ボタンクリック時処理
		 */
		if(toolBar.getFD_Name().equals(NAME_TB_New)) {
			//項目クリア
			for(FX_FieldItem item : fields.getFields()) {
				switch(item.getFieldTypeName()) {
				case FD_Field_SimpleText:
					TextField text = (TextField) item.getFieldItem();
					text.clear();
					break;
				}
			}
		}
	}

	/**
	 * ツールバー保管処理[On the toolbar-save process]<br>
	 * @author officina-hide.net
	 * @since 2022/01/06 Ver. 1.00
	 * @param fields 画面項目情報[Screen item information]
	 */
	private void saveProcess(FX_Fields fields) {
		// TODO 必須登録等の実装については後で追加する。 2021/01/11 
		
	}

}
