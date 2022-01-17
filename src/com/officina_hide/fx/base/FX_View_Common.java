package com.officina_hide.fx.base;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.common.FD_Item;
import com.officina_hide.base.common.FD_Items;
import com.officina_hide.base.common.FD_WhereData;
import com.officina_hide.base.model.FD_DB;
import com.officina_hide.base.model.I_FD_DB;
import com.officina_hide.base.model.I_FD_TableReference;
import com.officina_hide.base.model.X_FD_Table;
import com.officina_hide.base.model.X_FD_TableReference;
import com.officina_hide.fx.model.FX_Field;
import com.officina_hide.fx.model.FX_Menu;
import com.officina_hide.fx.model.FX_ToolBar;
import com.officina_hide.fx.model.I_FX_Field;
import com.officina_hide.fx.model.I_FX_Tab;
import com.officina_hide.fx.model.X_FX_Field;
import com.officina_hide.fx.model.X_FX_Menu;
import com.officina_hide.fx.model.X_FX_Tab;
import com.officina_hide.fx.model.X_FX_Toolbar;
import com.officina_hide.fx.model.X_FX_View;
import com.officina_hide.fx.process.TB_Process;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
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
		FX_Fields fields = new FX_Fields();
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
		
		//対象情報リスト生成
		List<FD_Items> dlist = getDataList(env, tab.getFD_Table_ID());
		fields.setRecordCount(dlist.size());
		
		// ヘッダー情報のセット
		tabBox.getChildren().add(fields.getHeadBox());
		
		/*
		 * ツールバー表示
		 */
		HBox toolBox = createToolbarBox(env, fields, tab);
		tabBox.getChildren().add(toolBox);

		FX_Field field = new FX_Field();
		where = new FD_WhereData(I_FX_Field.COLUMNNAME_FX_Tab_ID, tab.getFX_Tab_ID());
		List<X_FX_Field> flist = field.getList(env, where);
		for(X_FX_Field fd : flist) {
			FX_FieldItem fitem = new FX_FieldItem();
			fields.getFields().add(fitem);
			fitem.setField(fd);
			fitem.setFieldTypeName(fd.getFD_TypeItem(env).getFD_TypeItem_Name());
			HBox fieldBox = new HBox(5);
			tabBox.getChildren().add(fieldBox);
			Label lebel = new Label(fd.getFD_Name());
			fieldBox.getChildren().add(lebel);
			switch(fitem.getFieldTypeName()) {
			case FD_Field_SimpleText:
				TextField textField = new TextField("");
				fieldBox.getChildren().add(textField);
				fitem.setFieldItem(textField);
				break;
			case FD_Field_Date:
				DatePicker dateField = new DatePicker();
				fieldBox.getChildren().add(dateField);
				fitem.setFieldItem(dateField);
				break;
			case FD_Field_List:
				ComboBox<String> combo = new ComboBox<>();
				if(fd.getFD_Reference(env).getFD_ReferenceType(env).getFD_TypeItem_Name().equals(FD_Reference_Table)) {
					//テーブル参照情報取得
					FD_WhereData trWhere = new FD_WhereData(I_FD_TableReference.COLUMNNAME_FD_Reference_ID, fd.getFD_Reference_ID());
					X_FD_TableReference tr = new X_FD_TableReference(env, trWhere);
					//テーブルのFD_NameとIDのMapを生成する。
					StringBuffer sql = new StringBuffer();
					sql.append("SELECT * FROM ").append(tr.getFD_Table(env).getFD_Table_Name()).append(" ");
					FD_DB DB = new FD_DB();
					PreparedStatement pstmt = null;
					ResultSet rs = null;
					try {
						DB.connection(env);
						pstmt = DB.getConn().prepareStatement(sql.toString());
						rs = pstmt.executeQuery();
						while(rs.next()) {
							combo.getItems().add(rs.getString("FD_Name"));
						}
					} catch (SQLException e) {
						e.printStackTrace();
					} finally {
						DB.DBClose(pstmt, rs);
					}		
					fieldBox.getChildren().add(combo);
					fitem.setFieldItem(combo);
				}
				break;
			}
		}
		
		/*
		 * 情報セット
		 * TODO 個別メソッド化予定(2021/11/30)
		 */
		if(dlist.size() > 0) {
			FD_Items dt = dlist.get(0);
			for(FX_FieldItem fitem : fields.getFields()) {
				for(FD_Item ditem : dt.getItems()) {

					if(ditem.getName().equals(fitem.getField().getFD_Column(env).getFD_DataDictionary().getFD_DataDictionary_Name())) {
						//項目セット
						switch(fitem.getFieldTypeName()) {
						case FD_Field_SimpleText:
							TextField text = (TextField) fitem.getFieldItem();
							text.setText((String) ditem.getData());
							break;
						}
					}
				}
			}
		}
	}

	/**
	 * ツールバー設定[Toolbar settings]<br>
	 * @author officina-hide.net
	 * @since 2021/12/02 Ver. 1.00
	 * @param env 環境情報[Environment information]
	 * @param fields 画面項目情報[Screen item information]
	 * @param tab タブ情報
	 * @return ツールバー[Toolbar]
	 */
	private HBox createToolbarBox(FD_EnvData env, FX_Fields fields, X_FX_Tab tab) {
		//ツールバー領域構築
		HBox toolBox = new HBox(5);
		//ToolBar情報取得
		FX_ToolBar toolBar = new FX_ToolBar();
		List<X_FX_Toolbar> tlist = toolBar.getList(env);
		for(X_FX_Toolbar tb : tlist) {
			Button button = new Button(tb.getFD_Name());
			button.setOnMouseClicked(event->{
				TB_Process tp = new TB_Process();
				tp.execute(env, tb, fields, tab);
			});
			toolBox.getChildren().add(button);
		}
		return toolBox;
	}

	/**
	 * テーブルデータ取得[Table data acquisition]<br>
	 * @author officina-hide.net
	 * @since 2021/11/25 Ver. 1.00
	 * @param env 環境情報[Environment information]
	 * @param tableId テーブル情報ID[Table information ID]
	 * @return テーブル情報付テーブル項目リスト[Table item list with table information]
	 */
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
