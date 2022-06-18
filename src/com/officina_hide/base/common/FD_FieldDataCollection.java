package com.officina_hide.base.common;

import java.util.List;

import com.officina_hide.ui.model.FX_Field;
import com.officina_hide.ui.model.FX_View;
import com.officina_hide.ui.model.X_FX_Field;
import com.officina_hide.ui.model.X_FX_View;

/**
 * 画面項目コレクション[Screen item collection]<br>
 * @author officina-hide.net
 * @version 1.00 新規作成[New create]
 * @since 2022/06/17 Ver. 1.00
 */
public class FD_FieldDataCollection {

	/** 画面項目情報リスト */
	private List<FD_FIeldData> fieldDataList;
	
	/**
	 * 初期化[Initialize]
	 * 
	 * @param env 環境情報[Environment information]
	 * @param viewCode 画面コード
	 */
	public void initialize(FD_EnvData env, String viewCode) {
		//画面項目情報取得
		FX_View view = new FX_View(env);
		X_FX_View xview = new X_FX_View(env, view.getIDbyCode(viewCode));
		FX_Field field = new FX_Field(env);
		List<X_FX_Field> flist = field.getList(env, xview.getFX_View_ID());
		for(X_FX_Field fd : flist) {
			
		}
	}

}
