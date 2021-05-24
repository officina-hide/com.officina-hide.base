package com.officina_hide.fx.base;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import com.officina_hide.base.model.FD_DB;
import com.officina_hide.base.model.X_FD_Table;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
	/** DB操作クラス */
	private FD_DB DB = new FD_DB();
	
	@Override
	public void start(Stage stage) throws Exception {
		VBox root = new VBox(5);
		root.setPadding(new Insets(10, 10, 10, 10));
		
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
		
		root.getChildren().addAll(fileSelect);
		Scene scene = new Scene(root, 500, 200);
		stage.setScene(scene);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.show();
	}

	/**
	 * XMLファイル選択[XML file selection]<br>
	 * @author officina-hide.net
	 * @param stage 
	 * @since 1.00 2021/05/22
	 */
	private void fileSelect(Stage stage) {
		//ファイル選択ダアログ表示[File selection Daalog display]
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
			//テーブル情報取得
			X_FD_Table table = new X_FD_Table(xmlData);
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
	}

}
