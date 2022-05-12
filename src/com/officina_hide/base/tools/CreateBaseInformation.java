package com.officina_hide.base.tools;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.model.FD_Column;
import com.officina_hide.base.model.FD_Numbering;
import com.officina_hide.base.model.FD_Reference;
import com.officina_hide.base.model.FD_ReferenceGroup;
import com.officina_hide.base.model.FD_Table;
import com.officina_hide.base.model.I_FD_Column;
import com.officina_hide.base.model.I_FD_Numbering;
import com.officina_hide.base.model.I_FD_ReferenceGroup;
import com.officina_hide.base.model.I_FD_Table;

/**
 * 基盤情報生成クラス[Infrastructure information generation class]<br>
 * @author officina-hide.net
 * @version 1.50 新規作成
 * @since 2022/03/19 Ver. 1.50
 */
public class CreateBaseInformation {

	/** 項目 : 環境情報[Environment information] */
	private FD_EnvData env;
	
	/**
	 * コンストラクタ[Constructor]
	 * @author officina-hide.net
	 * @since 2022/03/19 Ver. 1.50
	 * @param env 環境情報[Environment information]
	 */
	public CreateBaseInformation(FD_EnvData env) {
		this.env = env;
	}

	/**
	 * 生成実行[Generation execution].<br>
	 * <p>1. ID採番用テーブル構築、情報登録[ID numbering table construction, information registration]</p>
	 * @author officina-hide.com
	 * @since 2022/03/19 Ver. 1.50
	 */
	public void execute() {
		/*
		 * テーブルをインターフェースクラス内のSQL文で生成する。
		 * 採番情報の登録（採番情報IDは固定値を使用）→基本情報テーブルの固定値は一覧で管理する。
		 */
		//採番情報生成
		FD_Numbering num = new FD_Numbering(env);
		num.createTable();
		//テーブル情報生成
		FD_Table table = new FD_Table(env);
		table.createTable();
		//テーブル項目情報生成
		FD_Column column = new FD_Column(env);
		column.createTable();
		//参照情報生成
		FD_Reference ref = new FD_Reference(env);
		ref.createTable();
		//参照グループ情報生成
		FD_ReferenceGroup rfg = new FD_ReferenceGroup(env);
		rfg.createTable();
		//参照グループ情報 : テーブル項目グループ登録
		long rfg_Id = rfg.add(I_FD_ReferenceGroup.Entry_FD_ReferenceGroup_ColumnType);
		/*
		 * 採番情報を登録する。
		 * 採番情報IDは基本情報の場合、テーブル情報IDと同様とする。
		 */
		num.add(I_FD_Table.Entry_FD_Number);
		num.add(I_FD_Column.Entry_FD_Number);
		num.add(I_FD_Numbering.Entry_FD_Number);
		table.add(I_FD_Table.Entry_FD_Table);
	}

}
