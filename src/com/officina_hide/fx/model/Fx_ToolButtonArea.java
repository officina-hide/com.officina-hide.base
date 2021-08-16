package com.officina_hide.fx.model;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.Node;
import javafx.scene.layout.HBox;

/**
 * ツールボタン領域クラス[Tool button area class]<br>
 * @author officine-hide.net
 * @version 1.00
 * @since 2021/08/16
 */
public class Fx_ToolButtonArea {

	/** 「表示」ボタン */
	private static final String Fx_Disp_Button = "表示";
	/** 「新規ボタン」 */
	private static final String Fx_New_Button = "新規";

	/** ボタン一覧 */
	private List<buttonData> buttonList = new ArrayList<>();
	
	/**
	 * コンストラクタ[Constructor]<br>
	 * @author officine-hide.net
	 * @since 1.00 2021/08/16
	 * @param FxName 呼び出し画面クラス名[Call screen class name]
	 */
	public Fx_ToolButtonArea(String FxName) {
		System.out.println(FxName);
		//ボタン初期化
		buttonList.add(new buttonData(Fx_Disp_Button, "dispSelected"));
		buttonList.add(new buttonData(Fx_New_Button, "newSelected"));
	}

	/**
	 * 領域生成[area generate]<br>
	 * @author officine-hide.net
	 * @since 1.00 2021/08/16
	 * @return ツールボタン領域
	 */
	public Node createNode() {
		HBox toolButonArea = new HBox(5);
		
		return toolButonArea;
	}

	/**
	 * ボタン情報[Button information]<br>
	 * TODO テーブル化予定
	 * @author officina-hide.net
	 * @version 0.00
	 * @since 2021/08/14
	 */
	private class buttonData {
		/** ボタン名 */
		private String buttonName;
		/** 処理メソッド */
		private String processMethod;

		public buttonData(String buttonName, String methodName) {
			setButtonName(buttonName);
			setProcessMethod(methodName);
		}

		public String getButtonName() {
			return buttonName;
		}
		public void setButtonName(String buttonName) {
			this.buttonName = buttonName;
		}
		public String getProcessMethod() {
			return processMethod;
		}
		public void setProcessMethod(String processMethod) {
			this.processMethod = processMethod;
		}
	}

}
