package com.officina_hide.fx.base;

import java.util.List;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.fx.model.FX_Menu;
import com.officina_hide.fx.model.X_FX_Menu;

import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * 画面共通クラス[Screen common class]<br>
 * @author officina-hide.net
 * @version 1.00 新規作成
 * @since 2021/11/17 Ver. 1.00
 */
public class FX_View_Common {

	/**
	 * 画面トップ設定[Screen top settings]<br>
	 * @author officina-hide.net
	 * @param root ルート[Root]
	 * @param env 環境情報[Environment information]
	 * @param root ルート[Root]
x	 */
	public void setTopArea(FD_EnvData env, VBox root) {
		HBox topBox = new HBox(5);
		root.getChildren().add(topBox);
		//メニューボタン設定
		topBox.getChildren().add(createMenuButton(env));
	}

	/**
	 * メニューボタン設定[Menu button setting]<br>
	 * @author officina-hide.net
	 * @since 2021/11/17 Ver. 1.00
	 * @param env 環境情報[Environment information]
	 * @return メニューボタン[Menu button]
	 */
	private MenuButton createMenuButton(FD_EnvData env) {
		MenuButton mb = new MenuButton("メニュー");
		FX_Menu menu = new FX_Menu();
		List<X_FX_Menu> mlist = menu.getMenuList(env);
		for(X_FX_Menu fm : mlist) {
			MenuItem mi = new MenuItem(fm.getFD_Name());
			mi.setUserData(fm);
//			mi.setOnAction(event->{
//				openView(event);
//			});
			mb.getItems().add(mi);
		}
		return mb;
	}

}
