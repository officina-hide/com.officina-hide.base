package com.officina_hide.fx.base;

import javafx.scene.control.SplitPane;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;

/**
 * メイン画面クラス[Main screen class]<br>
 * @author officina-hide.net
 * @version 1.00 新規作成
 * @since 2021/11/22 Ver. 1.00
 */
public class FV_MainView {

	private SplitPane sp;
	private VBox leftBox;
	private VBox rightBox;
	private TabPane viewBox;

	public TabPane getViewBox() {
		return viewBox;
	}

	public SplitPane getSp() {
		if(sp == null) {
			sp = new SplitPane();
			leftBox = new VBox(5);
			rightBox = new VBox(5);
			viewBox = new TabPane();
			rightBox.getChildren().add(viewBox);
			sp.getItems().addAll(leftBox, rightBox);
			sp.setDividerPositions(0.2, 0.8);
		}
		return sp;
	}

}
