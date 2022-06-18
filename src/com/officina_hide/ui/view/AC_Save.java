package com.officina_hide.ui.view;

import java.util.Optional;

import com.officina_hide.base.common.FD_EnvData;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

/**
 * 保存処理クラス[Save process class]<br>
 * TODO パッケージについては仮保管 2022/06/17
 * @author officina-hide.net
 * @version 1.00 新規作成[New create]
 * @since 2022/06/17 Ver. 1.00
 */
public class AC_Save {

	/**
	 * 保存処理
	 * @param env
	 * @param event
	 */
	public void execute(FD_EnvData env, ActionEvent event) {
		//確認メッセージ
		Alert alert = new Alert(AlertType.CONFIRMATION, "保存しますか？");
		Optional<ButtonType> bt = alert.showAndWait();
		if(bt.isEmpty() == true || bt.get().getText().equals(ButtonType.CANCEL.getText())) {
			return;
		}
		//保存処理
		
		//完了メッセージ
	}

}
