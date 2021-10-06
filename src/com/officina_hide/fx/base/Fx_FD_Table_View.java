package com.officina_hide.fx.base;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.common.FD_WhereData;
import com.officina_hide.base.model.FD_DB;
import com.officina_hide.base.model.I_FD_Table;
import com.officina_hide.base.model.X_FD_Table;
import com.officina_hide.fx.model.Fx_TextArea;
import com.officina_hide.fx.model.Fx_ToolButtonArea;
import com.officina_hide.fx.model.I_FX_Field;
import com.officina_hide.fx.model.I_FX_View;
import com.officina_hide.fx.model.X_Fx_Field;
import com.officina_hide.fx.model.X_FX_View;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * テーブル情報単票画面[Table information display screen]<br>
 * @author officine-hide.net
 * @version 1.00
 * @since 2021/08/06
 */
public class Fx_FD_Table_View extends Application {

	/** 環境情報 */
	private FD_EnvData env;
	/** 画面基本情報 */
	private X_FX_View view;
	private static final String View_Name = "FX_FD_Table_View";
	/** 画面項目リスト */
	private List<X_Fx_Field> fields;
	/** テーブル情報ID */
	private int tableId;
	/** テーブル情報 */
	private X_FD_Table table;
	/** 画面タイトル */
	private static final String View_Title = "テーブル情報";
	/** テキスト表示モード */
	private static final Boolean FX_ReadOnly = true;
	private static final Boolean FX_TextField = false;
	/** ボタン領域情報 */
	Fx_ToolButtonArea tba;
	/** テーブル名情報 */
	Fx_TextArea tableName;
	/** ダイアログメッセージ */
	StringBuffer message = new StringBuffer();
	/** DBクラス */
	private FD_DB DB = new FD_DB();
	
	/**
	 * 表示するテーブル情報のIDを保管する。[Store the ID of the table information to be displayed.]<br>
	 * @author officine-hide.net
	 * @param env 
	 * @since 1.00 2021/08/06
	 * @param id テーブル情報ID[Table information ID]
	 */
	public Fx_FD_Table_View(FD_EnvData env, Integer id) {
		this.env = env;
		this.tableId = id;
		/*
		 * 画面項目情報を取得する。
		 */
//		FD_WhereData where = new FD_WhereData(I_Fx_View.COLUMNNAME_Fx_View_ID, 100);
		view = new X_FX_View(env, 100);
//		fields = getFieldList(view.getF_View_ID());
		
		tba = new Fx_ToolButtonArea();
		try {
			tba.getButtonData(Fx_ToolButtonArea.Fx_Disp_Button).setActive(false);
			tba.getButtonData(Fx_ToolButtonArea.Fx_Save_Button).setClazz(this);
			tba.getButtonData(Fx_ToolButtonArea.Fx_Save_Button).setMethod(this.getClass().getMethod("saveSelected", ActionEvent.class));
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		tableName = new Fx_TextArea();
	}

	@Override
	public void start(Stage stage) throws Exception {
		//テーブル情報取得
		System.out.println(tableId);
		table = new X_FD_Table(env, tableId);
		
		VBox root = new VBox(5);
		root.setPadding(new Insets(10, 10, 10, 10));
		//ボタン領域表示
		root.getChildren().add(tba.createNode());
		//項目情報セット
		root.getChildren().add(getItem(fields));
//		//テーブル情報
//		root.getChildren().add(tableName.createNode());
//		if(tableId > 0) {
//			root.getChildren().add(getText(I_FD_Table.COMMENT_FD_Table_Name, table.getFD_Table_Name()
//					,I_FD_Table.COLUMNNAME_FD_Table_Name , FX_ReadOnly));
//		} else {
//			root.getChildren().add(getText(I_FD_Table.COMMENT_FD_Table_Name, table.getFD_Table_Name()
//					,I_FD_Table.COLUMNNAME_FD_Table_Name , FX_TextField));
//		}
//		root.getChildren().add(getText(I_FD_Table.COMMENT_FD_Name, table.getFD_Name()
//				,I_FD_Table.COLUMNNAME_FD_Name , FX_TextField));
//		root.getChildren().add(getTextArea(I_FD_Table.COMMENT_FD_Description, table.getFD_Description()));
		
		Scene scene = new Scene(root, 550, 300);
		stage.setScene(scene);
		stage.setTitle(view.getFD_Name());
		stage.show();
	}

	/**
	 * 項目領域を生成する。[Generate an item area.]<br>
	 * @author officine-hide.net
	 * @param fileds 画面項目リスト[screen item list]
	 * @since 1.00 2021/08/30
	 * @return 項目領域[Item Area]
	 */
	private Node getItem(List<X_Fx_Field> fileds) {
		VBox itemArea = new VBox(5);
		for(X_Fx_Field field : fileds) {
			HBox rowArea = new HBox(5);
			itemArea.getChildren().add(rowArea); 
			//項目のラベルをセット
//			Label title = new Label(field.getFX_Field_Name());
//			title.setFont(new Font("Meiryo UI", 12));
//			title.setPrefWidth(100);
//			title.setAlignment(Pos.CENTER_RIGHT);
//			rowArea.getChildren().add(title);
			//テーブル項目の属性を取得する。
//			System.out.println(field.getFD_Column().getFD_Column_Name());
//			switch(field.getFD_Column().getFD_Type_ID()) {
//			case 101:	// TODO テキスト項目　クラス化予定
//				TextField text = new TextField(table.getItems().getStringData(field.getFD_Column().getFD_Column_Name()));
//				rowArea.getChildren().add(text);
//				break;
//			case 102:	// TODO 複数行項目 クラス化予定
//				TextArea textArea = new TextArea(table.getItems().getStringData(field.getFD_Column().getFD_Column_Name()));
//				rowArea.getChildren().add(textArea);
//			}
			
		}
		return itemArea;
	}

	/**
	 * テキスト領域生成[Text area generation]<br>
	 * @author officine-hide.net
	 * @since 1.00 2021/08/10
	 * @param labelName ラベル表示文字列[Label display string]
	 * @param textData テキスト情報[Text information]
	 * @return テキスト領域ノード[Text area node]
	 */
	private Node getTextArea(String labelName, String textData) {
		HBox textAreaBox = new HBox(5);
		Label label = new Label(labelName);
		label.setPrefWidth(100);
		label.setPadding(new Insets(3, 3, 3, 3));
		label.setAlignment(Pos.CENTER_RIGHT);
		label.setFont(new Font("Meiryo UI", 12));
		textAreaBox.getChildren().add(label);
		
		TextArea text = new TextArea(textData);
		text.setFont(new Font("Meiryo UI", 12));
		text.setPrefRowCount(3);
		text.setPrefColumnCount(30);
		textAreaBox.getChildren().add(text);
		
		return textAreaBox;
	}

	/**
	 * テキスト項目生成[Text item generation]<br>
	 * @author officine-hide.net
	 * @since 1.00 2021/08/09
	 * @param labelName ラベル表示文字列[Label display string]
	 * @param textData テキスト情報[Text information]
	 * @param readOnly 参照判定[Reference judgment
	 * 	true - Label(ReadOnly), false - TextField 
	 * @return テキスト項目ノード[Text item node]
	 */
	private Node getText(String labelName, String textData, String columnId, Boolean readOnly) {
		HBox textBox = new HBox(5);
		Label label = new Label(labelName);
		label.setPrefWidth(100);
		label.setPadding(new Insets(3, 3, 3, 3));
		label.setAlignment(Pos.CENTER_RIGHT);
		label.setFont(new Font("Meiryo UI", 12));
		textBox.getChildren().add(label);
		
		if(readOnly == true) {
			Label text = new Label(textData);
			text.setPadding(new Insets(3, 3, 3, 10));
			text.setPrefWidth(200);
			text.setAlignment(Pos.CENTER_LEFT);
			text.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY
					, new CornerRadii(2), new Insets(0))));
			text.setFont(new Font("Meiryo UI", 12));
			textBox.getChildren().add(text);
		} else {
			TextField text = new TextField(textData);
			textBox.getChildren().add(text);
			text.setPrefWidth(200);
			text.setId(columnId);
			text.setUserData(textData);
		}
		
		return textBox;
	}

	/**
	 * 保存ボタン選択処理[Save button selection process]<br>
	 * @author officine-hide.net
	 * @since 1.00 2021/08/18
	 * @param event イベント情報[event information]
	 */
	public void saveSelected(ActionEvent event) {
		System.out.println("save selected!!");
		//入力チェック
		if(inputCheck() == false) {
			Alert dialog = new Alert(AlertType.ERROR);
			dialog.showAndWait();
			return;
		}
		
		Alert dialog = new Alert(AlertType.CONFIRMATION);
		dialog.setHeaderText("テーブル情報を保存しますか？");
		if(dialog.showAndWait().get().equals(ButtonType.OK) == false) {
			//「OK」がクリックされなかった時は、保存処理を抜ける
			return;
		}
		System.out.println(table.getFD_Table_ID());
	}

	/**
	 * 入力チェック[Input check]<br>
	 * @author officine-hide.net
	 * @since 1.00 2021/08/19
	 * @return チェック結果 true - ok, false - error
	 */
	private boolean inputCheck() {
		boolean chk = true;
		//テーブル名必須入力チェック
		//テーブル名重複チェック
		
		return chk;
	}

	/**
	 * Fx画面項目情報リスト生成[Fx screen item information list generation]<br>
	 * @author officine-hide.net
	 * @param viewId 
	 * @since 1.00 2021/08/28
	 * @return Fx画面項目情報リスト[Fx screen item information list]
	 */
	private List<X_Fx_Field> getFieldList(int viewId) {
		List<X_Fx_Field> list = new ArrayList<>();
		Statement stmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		try {
			sql.append("SELECT * FROM ").append(I_FX_Field.Table_Name).append(" ");
//			sql.append("WHERE ").append(I_FX_Field.COLUMNNAME_FX_View_ID).append(" = ").append(viewId).append(" ");
			DB.connection(env);
			stmt = DB.getConn().createStatement();
			rs = stmt.executeQuery(sql.toString());
			while(rs.next()) {
				list.add(new X_Fx_Field(env, rs.getInt(I_FX_Field.COLUMNNAME_FX_Field_ID)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} DB.DBClose(stmt, rs);
		return list;
	}
}
