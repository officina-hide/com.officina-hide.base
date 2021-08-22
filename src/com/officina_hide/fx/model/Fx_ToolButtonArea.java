package com.officina_hide.fx.model;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

/**
 * ツールボタン領域クラス[Tool button area class]<br>
 * @author officine-hide.net
 * @version 1.00
 * @since 2021/08/16
 */
public class Fx_ToolButtonArea {

	/** 「表示」ボタン */
	public static final String Fx_Disp_Button = "表示";
	/** 「新規ボタン」 */
	public static final String Fx_New_Button = "新規";
	/** 「保存ボタン」 */
	public static final String Fx_Save_Button = "保存";

	/** ボタン一覧 */
	private List<buttonData> buttonList = new ArrayList<>();
 	
	/**
	 * コンストラクタ[Constructor]<br>
	 * @author officine-hide.net
	 * @since 1.00 2021/08/16
	 */
	public Fx_ToolButtonArea() {
		buttonList.add(new buttonData(Fx_Disp_Button));
		buttonList.add(new buttonData(Fx_Save_Button));
		buttonList.add(new buttonData(Fx_New_Button));
	}

	/**
	 * 領域生成[area generate]<br>
	 * @author officine-hide.net
	 * @since 1.00 2021/08/16
	 * @return ツールボタン領域
	 */
	public Node createNode() {
		
		HBox toolButonArea = new HBox(5);
		for(buttonData bd : buttonList) {
			if(bd.isActive() == false) {
				//ボタンが非アクティブの時は表示しない。
				continue;
			}
			//ボタン追加
			Button button = new Button(bd.getButtonName());
			button.setFont(new Font("Meiryo UI", 12));
			//ボタンクリック時の行動追加
			button.setOnAction(event->{
				try {
					bd.getMethod().invoke(bd.getClazz(), event);
				} catch (SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					e.printStackTrace();
				}
			});
			toolButonArea.getChildren().add(button);
		}
		
		return toolButonArea;
	}

	/**
	 * ボタン情報取得[Get button information]<br>
	 * @author officina-hide.net
	 * @since 1.00 2021/08/16
	 * @param buttonName ボタン名[Button Name]
	 */
	public buttonData getButtonData(String buttonName) {
		for(buttonData bd : buttonList) {
			if(bd.getButtonName().equals(buttonName)) {
				return bd;
			}
		}
		return null;
	}

	/**
	 * ボタン情報[Button information]<br>
	 * TODO テーブル化予定
	 * @author officina-hide.net
	 * @version 0.00
	 * @since 2021/08/14
	 */
	public class buttonData {
		/** ボタン名 */
		private String buttonName;
		/** メソッド */
		private Method method;
		/** 処理クラス */
		private Object clazz;
		/** アクティブ */
		private boolean active = true;

		public buttonData(String buttonName) {
			setButtonName(buttonName);
		}

		public String getButtonName() {
			return buttonName;
		}
		public void setButtonName(String buttonName) {
			this.buttonName = buttonName;
		}
		public Method getMethod() {
			return method;
		}
		public void setMethod(Method method) {
			this.method = method;
		}
		public Object getClazz() {
			return clazz;
		}
		public void setClazz(Object clazz) {
			this.clazz = clazz;
		}
		public boolean isActive() {
			return active;
		}
		public void setActive(boolean active) {
			this.active = active;
		}
	}

}
