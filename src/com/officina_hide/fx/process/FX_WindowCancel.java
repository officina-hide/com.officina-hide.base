package com.officina_hide.fx.process;

import com.officina_hide.fx.model.FD_Params;

import javafx.stage.Stage;

/**
 * FX画面キャンセル処理[FX screen cancellation process]<br>
 * @author officina-hide.net
 * @version 1.00 新規作成
 * @since 2021/10/11 Ver. 1.00
 */
public class FX_WindowCancel {

	public void execute(FD_Params params) {
		System.out.println("Window Close");
		//画面を閉じる[Close window]
		Stage stage = (Stage) params.getObject("stage");
		stage.close();
	}
}
