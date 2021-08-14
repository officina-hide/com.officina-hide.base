package com.officina_hide.fx.base;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.model.I_FD_Table;
import com.officina_hide.base.model.X_FD_Table;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
	/** テーブル情報ID */
	private int tableId;
	/** 画面タイトル */
	private static final String View_Title = "テーブル情報";
	/** テキスト表示モード */
	private static final Boolean FX_ReadOnly = true;
	private static final Boolean FX_TextField = false;
	/** 保存ボタン表示 */
	private static final String Fx_Save_Button = "保存";
	
	/**
	 * 表示するテーブル情報のIDを保管する。[Store the ID of the table information to be displayed.]<br>
	 * @author officine-hide.net
	 * @param env 
	 * @since 1.00 2021/08/06
	 * @param id テーブル情報ID[Table information ID]
	 */
	public Fx_FD_Table_View(FD_EnvData env, int id) {
		this.env = env;
		tableId = id;
	}

	@Override
	public void start(Stage stage) throws Exception {
		//テーブル情報取得
		X_FD_Table table = new X_FD_Table(env, tableId);
		
		VBox root = new VBox(5);
		root.setPadding(new Insets(10, 10, 10, 10));
		//ボタン領域
		root.getChildren().add(getButton());
		//タイトル
		root.getChildren().add(getTitle());
		//テーブル情報
		root.getChildren().add(getText(I_FD_Table.COMMENT_FD_Table_Name, table.getFD_Table_Name(), FX_ReadOnly));
		root.getChildren().add(getText(I_FD_Table.COMMENT_FD_Name, table.getFD_Name(), FX_TextField));
		root.getChildren().add(getTextArea(I_FD_Table.COMMENT_FD_Description, table.getFD_Description()));
		
		Scene scene = new Scene(root, 550, 300);
		stage.setScene(scene);
		stage.show();
	}

	/**
	 * ボタン領域生成[Button area generation]<br>
	 * @author officine-hide.net
	 * @since 1.00 2021/08/10
	 * @return ボタン領域ノード[Button area node]
	 */
	private Node getButton() {
		HBox buttonArea = new HBox(5);
		
		//保存ボタン
		Button saveButton = new Button(Fx_Save_Button);
		saveButton.setDisable(true);
		buttonArea.getChildren().add(saveButton);
		
		return buttonArea;
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
	private Node getText(String labelName, String textData, Boolean readOnly) {
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
			text.setPrefWidth(200);
//			text.setOnKeyTyped(event->{
//				System.out.println(event.getCharacter()+":"+event.getCode().toString()+":"+event.getText());
//			});
			textBox.getChildren().add(text);
			text.setOnKeyPressed(event->{
				TextField ttt = (TextField) event.getSource();
				System.out.println(ttt.getText());
			});
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

}
