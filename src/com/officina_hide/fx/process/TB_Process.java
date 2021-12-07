package com.officina_hide.fx.process;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.fx.model.X_FX_Toolbar;

/**
 * ツールバー処理[Toolbar processing]<br>
 * @author officina-hide.net
 * @version 新規作成
 * @since 2021/12/07 Ver. 1.00
 */
public class TB_Process {

	/**
	 * 処理実行[Processing execute]<br>
	 * @author officina-hide.net
	 * @since 2021/12/07 Ver. 1.00
	 * @param env 環境情報[Environment information]
	 * @param toolBar ツールバー情報[ToolBar information]
	 */
	public void execute(FD_EnvData env, X_FX_Toolbar toolBar) {
		System.out.println(toolBar.getFD_Name()+"クリック");
	}

}
