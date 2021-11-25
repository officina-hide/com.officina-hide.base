package com.officina_hide.fx.base;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.common.FD_Items;
import com.officina_hide.base.common.FD_WhereData;
import com.officina_hide.base.model.FD_DB;
import com.officina_hide.base.model.I_FD_DB;
import com.officina_hide.base.model.X_FD_Table;
import com.officina_hide.fx.model.FX_Field;
import com.officina_hide.fx.model.FX_Menu;
import com.officina_hide.fx.model.I_FX_Field;
import com.officina_hide.fx.model.I_FX_Tab;
import com.officina_hide.fx.model.X_FX_Field;
import com.officina_hide.fx.model.X_FX_Menu;
import com.officina_hide.fx.model.X_FX_Tab;
import com.officina_hide.fx.model.X_FX_View;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * 画面共通クラス[Screen common class]<br>
 * @author officina-hide.net
 * @version 1.00 新規作成
 * @since 2021/11/17 Ver. 1.00
 */
public class FX_View_Common implements I_FD_DB {

	/**
	 * 画面トップ設定[Screen top settings]<br>
	 * @author officina-hide.net
	 * @param root ルート[Root]
	 * @param env 環境情報[Environment information]
	 * @param root ルート[Root]
	 */
	public void setTopArea(FD_EnvData env, VBox root) {
		HBox topBox = new HBox(5);
		root.getChildren().add(topBox);
		//メニューボタン設定
		topBox.getChildren().add(createMenuButton(env));
	}

	/**
	 * メイン画面設定[Main screen setting]<br>
	 * @author officina-hide.net
	 * @since 2021/11/20 Ver. 1.00
	 * @param env 環境情報[Environment information]
	 * @param root ルート[Root]
	 */
	public void setMainArea(FD_EnvData env, VBox root) {
		root.getChildren().add(env.getMainView().getSp());
		env.getMainView().getSp().setPrefHeight(400);
		env.getMainView().getSp().setPrefWidth(800);

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
			mi.setOnAction(event->{
				openView(env, event);
			});
			mb.getItems().add(mi);
		}
		return mb;
	}

	/**
	 * 画面表示[Open screen]<br>
	 * @author officina-hide.net
	 * @since 2021/11/20 Ver. 1.00
	 * @param env 環境情報[Environment information]
	 * @param event イベント情報[Event information]
	 */
	private void openView(FD_EnvData env, ActionEvent event) {
		MenuItem item = (MenuItem) event.getTarget();
		X_FX_Menu mn = (X_FX_Menu) item.getUserData();
		Tab tb = new Tab();
		env.getMainView().getViewBox().getTabs().add(tb);
		env.getMainView().getViewBox().getSelectionModel().select(env.getMainView().getViewBox().getTabs().size() - 1);
		VBox tabBox = new VBox(5);
		tb.setContent(tabBox);
		/*
		 * 画面情報取得
		 * 最初(Level = 0; 並び順 = 最小)のタブ情報を取得する。
		 * 次にフィールド情報を抽出し、画面に項目をセットする。
		 * 画面にセットした項目に1件目の情報をセットする。
		 */
		X_FX_View view = new X_FX_View(env, mn.getFX_Target_ID()); 
		FD_WhereData where = new FD_WhereData(I_FX_Tab.COLUMNNAME_FX_View_ID, view.getFX_View_ID());
		where.add("AND", I_FX_Tab.COLUMNNAME_FX_Tab_Level, 0);
		X_FX_Tab tab = new X_FX_Tab(env, where);
		tb.setText(tab.getFD_Name());
		FX_Field field = new FX_Field();
		where = new FD_WhereData(I_FX_Field.COLUMNNAME_FX_Tab_ID, tab.getFX_Tab_ID());
		List<X_FX_Field> flist = field.getList(env, where);
		for(X_FX_Field fd : flist) {
			HBox fieldBox = new HBox(5);
			tabBox.getChildren().add(fieldBox);
			Label lebel = new Label(fd.getFD_Name());
			fieldBox.getChildren().add(lebel);
			switch(fd.getFD_TypeItem(env).getFD_TypeItem_Name()) {
			case FD_Field_SimpleText:
				TextField textField = new TextField("");
				fieldBox.getChildren().add(textField);
				break;
			}
		}
		
		List<FD_Items> dlist = getDataList(env, tab.getFX_Tab_ID());
		System.out.println(dlist.size());
	}

	private List<FD_Items> getDataList(FD_EnvData env, long tableId) {
		List<FD_Items> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		FD_DB DB = new FD_DB();
		X_FD_Table table = new X_FD_Table(env, tableId);
		StringBuffer sql = new StringBuffer();
		try {
			sql.append("SELECT * FROM ").append(table.getFD_Table_Name()).append(" ");
			DB.connection(env);
			pstmt = DB.getConn().prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				FD_Items items = DB.createItems(env, tableId, rs);
				list.add(items);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.DBClose(pstmt, rs);
		}
		return list;
	}

}
