package com.officina_hide.fx.process;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Optional;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.fx.base.FX_FieldItem;
import com.officina_hide.fx.base.FX_Fields;
import com.officina_hide.fx.model.I_FX_ToolBar;
import com.officina_hide.fx.model.X_FX_Tab;
import com.officina_hide.fx.model.X_FX_Toolbar;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

/**
 * ツールバー処理[Toolbar processing]<br>
 * @author officina-hide.net
 * @version 新規作成
 * @since 2021/12/07 Ver. 1.00
 */
public class TB_Process implements I_FX_ToolBar {

	/**
	 * 処理実行[Processing execute]<br>
	 * @author officina-hide.net
	 * @since 2021/12/07 Ver. 1.00
	 * @param env 環境情報[Environment information]
	 * @param toolBar ツールバー情報[ToolBar information]
	 * @param fields 画面項目情報[Screen item information]
	 * @param tab タブ情報[Tab information]
	 */
	public void execute(FD_EnvData env, X_FX_Toolbar toolBar, FX_Fields fields, X_FX_Tab tab) {
		/*
		 * 保存ボタンが押されたときの処理<br>
		 * TODO 別クラス化予定
		 */
		if(toolBar.getFD_Name().equals(NAME_TB_Save)) {
			//情報の保存
			saveProcess(env, fields, tab);
			//検索結果の再表示
			
		}
		
		/*
		 * 「新規」ボタンクリック時処理
		 */
		if(toolBar.getFD_Name().equals(NAME_TB_New)) {
			//項目クリア
			for(FX_FieldItem item : fields.getFields()) {
				switch(item.getFieldTypeName()) {
				case FD_Field_SimpleText:
					TextField text = (TextField) item.getFieldItem();
					text.clear();
					break;
				}
			}
		}
	}

	/**
	 * ツールバー保管処理[On the toolbar-save process]<br>
	 * @author officina-hide.net
	 * @since 2022/01/06 Ver. 1.00
	 * @param env 環境情報[Environment information]
	 * @param fields 画面項目情報[Screen item information]
	 * @param tab タブ情報[Tab information]
	 */
	private void saveProcess(FD_EnvData env, FX_Fields fields, X_FX_Tab tab) {
		// TODO 必須登録等の実装については後で追加する。 2021/01/11 
		/*
		 * 1. 登録確認メッセージ
		 * 2. 登録処理
		 * 3. 登録完了メッセージ
		 * 4. 画面の更新(未実装)
		 */
		//1.
		Alert confirmMsg = new Alert(AlertType.CONFIRMATION, "保存しますか");
		Optional<ButtonType> rs = confirmMsg.showAndWait();
		if(rs.get().equals(ButtonType.CANCEL)) {
			System.out.println("キャンセルされました。");
			return;
		}
		//タブ情報の対象テーブルのI/Oクラスを取得する。
		try {
			// TODO パッケージURIについては別途検討 2021/01/13
			Class<?> IOClazz = Class.forName("com.officina_hide.account.model.X_"+tab.getFD_Table(env).getFD_Table_Name());
			Constructor<?> constructor = IOClazz.getConstructor(FD_EnvData.class, int.class);
			Object IOInstance = constructor.newInstance(env, 0);
			for(FX_FieldItem item : fields.getFields()) {
				String columnName = item.getField().getFD_Column(env).getFD_DataDictionary().getFD_DataDictionary_Name();
				Method setData = IOInstance.getClass().getMethod("set"+columnName, String.class);
				TextField text = (TextField) item.getFieldItem();
				setData.invoke(IOInstance, text.getText());
			}
			Method saveData = IOInstance.getClass().getMethod("save", FD_EnvData.class);
			saveData.invoke(IOInstance, env);		
		} catch (ClassNotFoundException | IllegalArgumentException | NoSuchMethodException |
				SecurityException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		
		//3.
		Alert completeMsg = new Alert(AlertType.INFORMATION, "保存しました。");
		completeMsg.showAndWait();
	}

}
