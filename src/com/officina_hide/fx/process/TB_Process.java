package com.officina_hide.fx.process;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.fx.base.FX_Fields;
import com.officina_hide.fx.model.I_FX_ToolBar;
import com.officina_hide.fx.model.X_FX_Toolbar;

import javafx.scene.control.DatePicker;

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
			System.out.println(fields.getFields().get(0).getFieldTypeName());
			DatePicker dt = (DatePicker) fields.getFields().get(0).getFieldItem();
			Calendar cal = new GregorianCalendar(new Locale(Locale.JAPAN.getLanguage(), Locale.JAPAN.getCountry()));
		}
	}

}
