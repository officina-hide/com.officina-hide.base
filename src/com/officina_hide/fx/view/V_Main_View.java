package com.officina_hide.fx.view;

import java.util.ArrayList;
import java.util.List;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.common.FD_Items;
import com.officina_hide.base.common.FD_WhereData;
import com.officina_hide.base.model.FD_Table;
import com.officina_hide.base.model.I_FD_DB;
import com.officina_hide.base.model.X_FD_Table;
import com.officina_hide.fx.model.FX_Field;
import com.officina_hide.fx.model.FX_Menu;
import com.officina_hide.fx.model.I_FX_Field;
import com.officina_hide.fx.model.I_FX_Tab;
import com.officina_hide.fx.model.X_FX_Field;
import com.officina_hide.fx.model.X_FX_Menu;
import com.officina_hide.fx.model.X_FX_Tab;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Separator;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class V_Main_View extends Application implements I_FD_DB {

	/** 環境情報 */
	private FD_EnvData env;
	/** メイン(右) */
	private VBox rightBox;
	/** タブエリア */
	private TabPane rightTP;
	
	public V_Main_View(FD_EnvData env) {
		this.env = env;
	}

	@Override
	public void start(Stage stage) throws Exception {
		VBox root = new VBox(5);
		root.setPadding(new Insets(5, 5, 5, 5));
		//トップエリア設定[Top area setting]
		setTopArea(root);
		//セパレーター
		root.getChildren().add(new Separator());
		//メインエリア設定[Main area setting]
		setMainArea(root);
		
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.showAndWait();
	}

	/**
	 * トップエリア設定[Top area setting]<br>
	 * @author officina-hide.net
	 * @since 2021/11/02 Ver. 1.00
	 * @param root ルート[Root]
	 */
	private void setTopArea(VBox root) {
		HBox topBox = new HBox(5);
		root.getChildren().add(topBox);
		
		//メニューボタン設定
		topBox.getChildren().add(createMenuButton());
	}

	/**
	 * メインエリア設定[Main area setting]<br>
	 * @author officina-hide.net
	 * @since 2021/11/02 Ver. 1.00
	 * @param root ルート[Root]
	 */
	private void setMainArea(VBox root) {
		//セパレート領域設定
		SplitPane sp = new SplitPane();
		root.getChildren().add(sp);
		sp.setPrefHeight(400);
		sp.setPrefWidth(800);
		
		//左エリア
		VBox leftBox = new VBox(5);
		//右エリア
		rightBox = new VBox(5);
		rightTP = new TabPane();
		rightBox.getChildren().add(rightTP);
		
		sp.getItems().addAll(leftBox, rightBox);
		sp.setDividerPositions(0.2, 0.8);
	}

	/**
	 * メニューボタン設定[Menu button setting]<br>
	 * @author officina-hide.net
	 * @since 2021/11/02 Ver. 1.00
	 * @return メニューボタン[Menu button]
	 */
	private MenuButton createMenuButton() {
		MenuButton mb = new MenuButton("メニュー");
		FX_Menu menu = new FX_Menu();
		List<X_FX_Menu> mlist = menu.getMenuList(env);
		for(X_FX_Menu fm : mlist) {
			MenuItem mi = new MenuItem(fm.getFD_Name());
			mi.setUserData(fm);
			mi.setOnAction(event->{
				openView(event);
			});
			mb.getItems().add(mi);
		}
		return mb;
	}

	/**
	 * 画面表示[Screen open]<br>
	 * @author officina-hide.net
	 * @since 2021/11/02 Ver. 1.00
	 * @param event イベント情報[Event information]
	 */
	private void openView(ActionEvent event) {
		MenuItem item = (MenuItem) event.getTarget();
		X_FX_Menu mn = (X_FX_Menu) item.getUserData();
		VBox tbBox = new VBox(5);
		tbBox.setPadding(new Insets(5, 5, 5, 5));
		Tab tb = new Tab(mn.getFD_Name(), tbBox);
		rightTP.getTabs().add(tb);
		rightTP.getSelectionModel().select(rightTP.getTabs().size() - 1);	
		
		//テーブル・カラムのメニューが呼び出されたと仮定
		long viewId = mn.getFX_Target_ID();
		FD_WhereData where = new FD_WhereData(I_FX_Tab.COLUMNNAME_FX_View_ID, viewId);
		X_FX_Tab tab = new X_FX_Tab(env, where);
		FX_Field field = new FX_Field();
		where = new FD_WhereData(I_FX_Field.COLUMNNAME_FX_Tab_ID, tab.getFX_Tab_ID());
		List<X_FX_Field> flist = field.getList(env, where);
		FX_FieldCollection fdlist = new FX_FieldCollection();
		for(X_FX_Field fd : flist) {
			FX_FieldItem fi = new FX_FieldItem();
			fdlist.getItems().add(fi);
			fi.setFieldName(fd.getFx_Field_Name());
			fi.setFieldType(fd.getFD_TypeItem(env).getFD_TypeItem_Name());
			
			HBox fieldBox = new HBox(5);
			fieldBox.setAlignment(Pos.CENTER_LEFT);
			tbBox.getChildren().add(fieldBox);
			
			Label label = new Label(fd.getFD_Name());
			fieldBox.getChildren().add(label);
			switch(fi.getFieldType()) {
			case FD_Field_SimpleText:
				TextField textField = new TextField("");
				fieldBox.getChildren().add(textField);
				fi.setFieldItem(textField);
				break;
			case FD_Field_Text:
				TextArea textArea = new TextArea("");
				textArea.setPrefRowCount(3);
				textArea.setPrefColumnCount(40);
				fieldBox.getChildren().add(textArea);
				fi.setFieldItem(textArea);
				break;
			}
		}
		//テーブル情報の一覧リストを取得する。
		FD_Table table = new FD_Table();
		List<X_FD_Table> tlist = table.getList(env);
		if(tlist.size() > 0) {
			//1件目のテーブル情報を画面セット
			setData(tlist.get(0), fdlist);
		}
	}

	/**
	 * データセット[Data setting]<br>
	 * @author officina-hide.net
	 * @since 2021/11/06 Ver. 1.00
	 * @param table テーブル情報[Table information]
	 * @param fdlist 画面項目情報[Screen item information]
	 */
	private void setData(X_FD_Table table, FX_FieldCollection fdlist) {
		FD_Items items = table.getItems(); 
		for(FX_FieldItem item : fdlist.getItems()) {
			switch(item.getFieldType()) {
			case FD_Field_SimpleText:
				TextField textField = (TextField) item.getFieldItem();
				textField.setText(items.getStringData(item.getFieldName()));
				break;
			case FD_Field_Text:
				TextArea textAres = (TextArea) item.getFieldItem();
				textAres.setText(items.getStringData(item.getFieldName()));
				break;
			}
		}
		
		
	}

	/**
	 * 画面項目用情報クラス[Information class for screen items]<br>
	 * TODO 汎用化予定(2021/11/04)
	 * @author officina-hide.net
	 * @version 1.00 新規作成
	 * @since 2021/11/04 Ver. 1.00
	 */
	private class FX_FieldCollection {
		private List<FX_FieldItem> items;

		public List<FX_FieldItem> getItems() {
			if(items == null) {
				items = new ArrayList<>();
			}
			return items;
		}
	}
	
	/**
	 * 画面項目クラス[Screen item class]<br>
	 * TODO 汎用化予定(2021/11/04)
	 * @author officina-hide.net
	 * @version 1.00 新規作成
	 * @since 2021/11/04 Ver. 1.00
	 */
	private class FX_FieldItem {
		/** 項目名 */
		private String fieldName;
		/** 項目 */
		private Object fieldItem;
		/** 項目属性 */
		private String fieldType;

		public String getFieldName() {
			return fieldName;
		}
		public void setFieldName(String fieldName) {
			this.fieldName = fieldName;
		}
		public Object getFieldItem() {
			return fieldItem;
		}
		public void setFieldItem(Object fieldItem) {
			this.fieldItem = fieldItem;
		}
		public String getFieldType() {
			return fieldType;
		}
		public void setFieldType(String fieldType) {
			this.fieldType = fieldType;
		}
	}
}
