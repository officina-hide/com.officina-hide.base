package com.officina_hide.fx.base;

import java.util.List;
import java.util.Optional;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.common.FD_WhereData;
import com.officina_hide.base.model.I_FD_Table;
import com.officina_hide.base.model.X_FD_Table;
import com.officina_hide.fx.model.Fx_TextArea;
import com.officina_hide.fx.model.Fx_ToolButtonArea;
import com.officina_hide.fx.model.I_Fx_View;
import com.officina_hide.fx.model.X_Fx_View;

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
import sun.jvm.hotspot.code.Location.Where;

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
	private X_Fx_View view;
	private static final String View_Name = "FX_FD_Table_View";
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
	
	/**
	 * 表示するテーブル情報のIDを保管する。[Store the ID of the table information to be displayed.]<br>
	 * @author officine-hide.net
	 * @param env 
	 * @since 1.00 2021/08/06
	 * @param id テーブル情報ID[Table information ID]
	 */
	public Fx_FD_Table_View(FD_EnvData env, Integer id) {
		this.env = env;
		tableId = id;
		/*
		 * 画面項目情報を取得する。
		 */
		FD_WhereData where = new FD_WhereData(I_Fx_View.COLUMNNAME_Fx_View_ID, 101);
		view = new X_Fx_View(env, where);
		
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
		table = new X_FD_Table(env, tableId);
		
		VBox root = new VBox(5);
		root.setPadding(new Insets(10, 10, 10, 10));
		//ボタン領域表示
		root.getChildren().add(tba.createNode());
		//タイトル
		root.getChildren().add(getTitle());
		//テーブル情報
		root.getChildren().add(tableName.createNode());
//		if(tableId > 0) {
//			root.getChildren().add(getText(I_FD_Table.COMMENT_FD_Table_Name, table.getFD_Table_Name()
//					,I_FD_Table.COLUMNNAME_FD_Table_Name , FX_ReadOnly));
//		} else {
//			root.getChildren().add(getText(I_FD_Table.COMMENT_FD_Table_Name, table.getFD_Table_Name()
//					,I_FD_Table.COLUMNNAME_FD_Table_Name , FX_TextField));
//		}
		root.getChildren().add(getText(I_FD_Table.COMMENT_FD_Name, table.getFD_Name()
				,I_FD_Table.COLUMNNAME_FD_Name , FX_TextField));
		root.getChildren().add(getTextArea(I_FD_Table.COMMENT_FD_Description, table.getFD_Description()));
		
		Scene scene = new Scene(root, 550, 300);
		stage.setScene(scene);
		stage.show();
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
//			text.setOnKeyTyped(event->{
//				TextField ttt = (TextField) event.getSource();
//				if(ttt.getText().equals(ttt.getUserData())){
//					saveButton.setDisable(true);
//				} else {
//					saveButton.setDisable(false);
//				}
//			});
		}
		
		return textBox;
	}

	/**
	 * タイトル項目生成[Title item generation]<br>
	 * @author officine-hide.net
	 * @since 1.00 2021/08/09
	 * @return タイトル表示用ノード[Title display node]
	 */
	private Node getTitle() {
		HBox titleBox = new HBox(5);
		Label title = new Label(View_Title);
		title.setFont(new Font("Meiryo UI", 12));
		titleBox.getChildren().add(title);
		return titleBox;
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
		
	}

	/**
	 * 入力チェック[Input check]<br>
	 * @author officine-hide.net
	 * @since 1.00 2021/08/19
	 * @return チェック結果 true - ok, false - error
	 */
	private boolean inputCheck() {
		//テーブル名必須入力チェック
		//テーブル名重複チェック
		
		return false;
	}
}
