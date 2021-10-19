package com.officina_hide.fx.view;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.model.FD_DB;
import com.officina_hide.fx.model.I_FX_Menu;
import com.officina_hide.fx.model.X_FX_Menu;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class V_MainMenu extends Application {

	/** 環境情報[Environment information] */
	private FD_EnvData env;
	
	/**
	 * コンストラクタ[Constructor]
	 * @param env 環境情報[Environment information]
	 */
	public V_MainMenu(FD_EnvData env) {
		this.env = env;
	}

	@Override
	public void start(Stage stage) throws Exception {
		VBox root = new VBox(5);
		root.setPadding(new Insets(10, 10, 10, 10));
		/*
		 * 1. メニュー情報を抽出し、ボタンとして画面表示する。(Fuse 1. シンプルにボタンを縦並びにする。)
		 */
		//1.
		List<X_FX_Menu> mlist = getMenuList(env);
		for(X_FX_Menu menu : mlist) {
			Button button = new Button(menu.getFD_Name());
			button.setFont(new Font("Meiryo UI", 12));
			button.setOnMouseClicked(event->{
				System.out.println("clicked!!");
			});
			root.getChildren().add(button);
		}
		
		Scene scene = new Scene(root, 300, 500);
		stage.setScene(scene);
		stage.showAndWait();
	}

	/**
	 * メニュー一覧生成[Menu list generate]<br>
	 * @author officina-hide.net
	 * @since 2021/10/19 Ver. 1.00
	 * @param env 環境情報[Environment information]
	 * @return メニュー一覧[Menu list]
	 */
	private List<X_FX_Menu> getMenuList(FD_EnvData env) {
		List<X_FX_Menu> list = new ArrayList<>();
		FD_DB DB = new FD_DB();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		try {
			sql.append("SELECT * FROM ").append(I_FX_Menu.Table_Name).append(" ");
			DB.connection(env);
			pstmt = DB.getConn().prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				list.add(new X_FX_Menu(env, rs.getLong(I_FX_Menu.COLUMNNAME_FX_Menu_ID)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.DBClose(pstmt, rs);
		}
		return list;
	}

}
