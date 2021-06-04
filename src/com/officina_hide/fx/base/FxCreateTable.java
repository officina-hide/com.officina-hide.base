package com.officina_hide.fx.base;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.model.FD_DB;
import com.officina_hide.base.model.X_FD_Table;
import com.officina_hide.base.sql.FD_sql;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * テーブル生成[Table generation]<br>
 * テーブル生成用XMLファイルを指定し、内容チェック後テーブルを生成する。<br>
 * Specify the XML file for table generation, and generate the table after checking the contents.
 * @author officina-hide.net
 * @since 1.00 2021/05/22
 */
public class FxCreateTable extends Application {

	/** XMLファイルPath */
	private TextField xmlFileName;
	/** テーブル名 */
	private TextField tableNameText;
	/** テーブル生成ボタン */
	private Button createButton;
	/** DB操作クラス */
	private FD_DB DB = new FD_DB();
	/** 環境情報 */
	private FD_EnvData env = new FD_EnvData();
	/** テーブル情報 */
	private X_FD_Table table;
	/** SQLクラス */
	private FD_sql sqlUtil = new FD_sql(); 
	
	/**
	 * インスタンス時に、環境情報をセットする。<br>
	 * Set environment information at the time of instance.<br>
	 * @author officine-hide.com
	 * @since 1.00 2021/05/24
	 * @param env 環境情報[Environment Information]
	 */
	public FxCreateTable(FD_EnvData env) {
		this.env = env;
	}

	@Override
	public void start(Stage stage) throws Exception {
		VBox root = new VBox(5);
		root.setPadding(new Insets(10, 10, 10, 10));

		//画面設定
		initDisp(root, stage);
		
		Scene scene = new Scene(root, 500, 200);
		stage.setScene(scene);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.show();
	}

	/**
	 * 画面初期設定[Screen initial settings]<br>
	 * @author officine-hide.net
	 * @since  1.00 2021/05/27
	 * @param root ルート[root]
	 * @param stage ステージ[stage]
	 */
	private void initDisp(VBox root, Stage stage) {
		//ファイル選択
		HBox fileSelect = new HBox(5);
		fileSelect.setAlignment(Pos.CENTER_LEFT);
		xmlFileName = new TextField();
		xmlFileName.setPrefWidth(350);
		Button fileButton = new Button("選択");
		fileButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				fileSelect(stage);
			}
		});
		fileSelect.getChildren().addAll(xmlFileName, fileButton);
		
		//テーブル名
		HBox tableNameBox = new HBox(5);
		tableNameBox.setAlignment(Pos.CENTER_LEFT);
		Label tableNameLabel = new Label("テーブル名");
		tableNameText = new TextField("");
		tableNameText.setEditable(false);
		tableNameBox.getChildren().addAll(tableNameLabel, tableNameText);
		
		//ライン
		Separator separator = new Separator(Orientation.HORIZONTAL);
		
		//ボタン領域
		HBox buttonArea = new HBox(5);
		buttonArea.setAlignment(Pos.CENTER_RIGHT);
		createButton = new Button("テーブル作成");
		createButton.setDisable(true);
		createButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
					createTable();
			}
		});
		buttonArea.getChildren().addAll(createButton);
		
		root.getChildren().addAll(fileSelect, tableNameBox, separator, buttonArea);
	}

	/**
	 * テーブル構築[Table construction]<br>
	 * @author officine-hide.net
	 * @since 1.00 2021/05/29
	 */
	protected void createTable() {
		/*
		 * 1.生成確認
		 * 2.構築済みの場合は削除
		 * 3.新たにテーブルを生成
		 * 4.テーブル情報の登録
		 * 5.完了メッセージ
		 */
		
		//生成確認
		Alert confirmwid = new Alert(AlertType.CONFIRMATION);
		Optional<ButtonType> result = confirmwid.showAndWait();
		if(result.isPresent() && result.get().equals(ButtonType.CANCEL)) {
			return;
		}
		//既登録分削除
		table.createTable(env);
	}

	/**
	 * XMLファイル選択[XML file selection]<br>
	 * @author officina-hide.net
	 * @param stage 
	 * @since 1.00 2021/05/22
	 */
	private void fileSelect(Stage stage) {
		//ファイル選択ダアログ表示[File selection Daalog display]
		// TODO 初期表示DirをEnvに入れる。(環境プロパティから取得する）
		FileChooser fileChooser = new FileChooser();
		fileChooser.setInitialDirectory(new File("./document"));
		File file = fileChooser.showOpenDialog(stage);
		if(file == null) {
			return;
		}
		/*
		 * 選択したファイルPathを表示用テキスト項目にセットする。
		 * Set the selected file path in the display text item.
		 */
		xmlFileName.setText(file.getAbsolutePath());
		/*
		 * XMLファイルの内容をチェックする。
		 * Check the contents of the XML file.
		 */
		checkXmlFile(file);
		//テーブル構築ボタンを活性化する。
		createButton.setDisable(false);
	}

	/**
	 * テーブル生成用XMLファイルチェック[Checking XML file for table generation]<br>
	 * @author officina-hide.net
	 * @since 1.00 2021/05/22
	 * @param file テーブル生成用XMLファイル[XML file for table generation]
	 */
	private void checkXmlFile(File file) {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(file);
			Element xmlData = document.getDocumentElement();
			//テーブル情報生成
			table = new X_FD_Table(xmlData);
			//テーブル情報セット
			tableNameText.setText(table.getFD_Table_Name());
			
			
			
//			//データベース接続
//			DB.connection(env);
//			//テーブル削除用SQL生成
//			String sql = sqlUtil.createSqlStatement(env, FD_sql.DELETE_TABLE, table.getItems());
//			System.out.println(sql);
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
	}

}
