package com.officina_hide.fx.model;

import javafx.scene.Node;
import javafx.scene.layout.HBox;

/**
 * ツールボタン領域クラス[Tool button area class]<br>
 * @author officine-hide.net
 * @version 1.00
 * @since 2021/08/16
 */
public class Fx_ToolButtonArea {

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

}
