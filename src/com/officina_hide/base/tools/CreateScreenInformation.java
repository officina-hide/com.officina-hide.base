package com.officina_hide.base.tools;

import java.util.Date;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.model.FD_ReferenceGroup;
import com.officina_hide.ui.model.FX_Field;
import com.officina_hide.ui.model.FX_View;

/**
 * 画面情報生成[Screen information generation]<br>
 * 画面の構築に;:必要な情報群を構築する。
 * ・画面情報 FX_View 画面タイトルやサイズ等の全体表示に関する情報
 * @author officina-hide.net
 * @version 1.00 新規作成[New create]
 * @since 2022/06/02 Ver. 1.00
 */
public class CreateScreenInformation implements I_CreateScreenInformation {

	/** 項目 : 環境情報[Environment information] */
	private FD_EnvData env;

	/**
	 * コンストラクター[Constructor]<br>
	 * @author officina-hide.net
	 * @since 2022/06/02 Ver. 1.00
	 * @param env 環境情報[Environment information]
	 */
	public CreateScreenInformation(FD_EnvData env) {
		this.env = env;
	}

	/**
	 * 構築実行[Build execution]<br>
	 * @author officina-hide.net
	 * @since 2022/06/02 Ver. 1.00
	 */
	public void execute() {
		//開始メッセージ[Start message]
		System.out.println("画面情報構成 開始 : "+new Date());
		//基本情報更新
		//参照情報に画面項目に関する情報を登録する。
		FD_ReferenceGroup rg = new FD_ReferenceGroup(env);
		rg.add(Entry_FD_ReferenceGroup_FieldType);
		
		//画面情報
		FX_View view = new FX_View(env);
		view.createTable();
		//画面項目情報
		FX_Field field = new FX_Field(env);
		field.createTable();
	
		//完了メッセージ[Complete message]
		System.out.println("画面情報構成 完了 : "+new Date());
	}


}
