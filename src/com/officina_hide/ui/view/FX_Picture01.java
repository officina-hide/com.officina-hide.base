package com.officina_hide.ui.view;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * 写真情報保存[Save photo information]<br>
 * 【メモ】
 * ファイル情報(FD_File)
 * ファイル属性情報(FD_FileAttribute)
 * 画像情報(FD_Picture)
 * 画像属性情報(FD_PictureAttribute)
 * @author officina-hide.net
 * @version 1.00 新規作成[Create new]
 * @since 2022/04/16 Ver. 1.00
 */
public class FX_Picture01 extends Application {

	/** 選択ファイル */
	private File selectFile;
	/** 画面項目 : ファイル名 */
	private TextField fileName;
	/** 画面項目 : 画像表示 */
	private ImageView iv;
	
	@Override
	public void start(Stage stage) throws Exception {
		VBox root = new VBox(5);
		root.setPadding(new Insets(5, 5, 5, 5));
		
		HBox fileBox = new HBox(5);
		fileBox.setAlignment(Pos.CENTER_LEFT);
		root.getChildren().add(fileBox);
		Label fileLabel = new Label("写真ファイル");
		fileName = new TextField();
		Button fileSelectButton = new Button("選択");
		fileSelectButton.setOnAction(event->{
			selectFileButton(stage);
			fileName.setText(selectFile.getName());
			try {
				Metadata  metadata = ImageMetadataReader.readMetadata(selectFile);
				for (Directory directory : metadata.getDirectories()) {
					for (Tag tag : directory.getTags()) {
						System.out.println(tag.getTagName()+":"+tag.getDirectoryName()+":"+tag.getDescription());
					}
				}
				System.out.println(Files.readAttributes(selectFile.toPath(), "*"));
				System.out.println(Files.readAttributes(selectFile.toPath(), "lastAccessTime").get("lastAccessTime").toString());
				Image image = new Image(new FileInputStream(selectFile));
				iv.setImage(image);
			} catch (IOException | ImageProcessingException e) {
				e.printStackTrace();
			}
		});
		fileBox.getChildren().addAll(fileLabel, fileName, fileSelectButton);
		
		//写真表示
		HBox picBox = new HBox(5);
		root.getChildren().add(picBox);
		iv = new ImageView();
		iv.setFitWidth(100);
		iv.setFitHeight(100);
		picBox.getChildren().add(iv);
		
		Scene scene = new Scene(root, 400, 200);
		stage.setScene(scene);
		stage.show();
	}

	/**
	 * ファイル選択ボタン処理[File selection button processing]<br>
	 * @author officina-hide.net
	 * @param stage ステージ[stage]
	 * @since 2022/04/17 Ver. 1.00
	 */
	private void selectFileButton(Stage stage) {
		FileChooser fc = new FileChooser();
		selectFile = fc.showOpenDialog(stage);
	}

	public static void main(String[] args) {
		launch(args);
	}

}
