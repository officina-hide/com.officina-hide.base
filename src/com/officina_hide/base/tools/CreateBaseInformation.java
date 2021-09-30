package com.officina_hide.base.tools;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.model.FD_Column;
import com.officina_hide.base.model.FD_DB;
import com.officina_hide.base.model.FD_DataDictionary;
import com.officina_hide.base.model.FD_Numbering;
import com.officina_hide.base.model.FD_Table;
import com.officina_hide.base.model.FD_Type;
import com.officina_hide.base.model.FD_TypeITem_Param;
import com.officina_hide.base.model.FD_TypeItem;
import com.officina_hide.base.model.I_FD_Column;
import com.officina_hide.base.model.I_FD_DB;
import com.officina_hide.base.model.I_FD_DataDictionary;
import com.officina_hide.base.model.I_FD_Numbering;
import com.officina_hide.base.model.I_FD_Table;

/**
 * 基盤情報構築クラス<br>
 * Basic information construction class.<br>
 * <p>基盤情報には以下の情報があります。<br>
 * ・テーブル関連情報（テーブル、カラム等データベースを構築するうえで必要な情報）</p>
 * <p>The basic information includes the following information.<br>
 * ・Table-related information (information necessary for building a database such as tables and columns)</p>
 * 
 * @author officine-hide.com
 * @version 1.00 新規作成
 * @version 2.00 構築手順の全面見直し
 * @since 2021/03/31
 */
public class CreateBaseInformation implements I_FD_DB {

	//環境情報の取得
	FD_EnvData env = new FD_EnvData();

	public CreateBaseInformation(FD_EnvData env) {
		this.env = env;
	}

	/**
	 * 構築実行[Build execution]<br>
	 * 本メソッドでは必要なテーブル、情報の登録を順次実行する。
	 * @since 1.00 2021/03/31
	 * @since 2.00 2021/09/15
	 */
	public void execute() {
		/*
		 * 1. ID採番用テーブル構築、情報登録
		 * 2. 辞書情報テーブル構築、情報登録
		 * 3. テーブル情報構築、情報登録
		 * 4. 属性情報構築、情報登録
		 * 5-1. 属性項目情報構築、情報登録
		 * 5-2. 属性設定値、情報登録
		 * 6-1. テーブル項目用属性登録 
		 * 6-2. テーブル項目情報構築、情報登録
		 */
		FD_DB DB = new FD_DB();
		//1.
		FD_Numbering num = new FD_Numbering();
		num.createTable(env);
		num.addData(env, I_FD_Numbering.Table_Name);
		//2.
		FD_DataDictionary dd = new FD_DataDictionary();
		dd.createTable(env);
		dd.addData(env, I_FD_Numbering.Table_Name);
		DB.addData(env, I_FD_DataDictionary.Table_Name);
		num.addData(env, I_FD_DataDictionary.Table_Name);
		dd.addData(env, I_FD_DataDictionary.Table_Name);
		//3.
		FD_Table table = new FD_Table();
		table.createTable(env);
		table.addData(env, I_FD_Numbering.Table_Name);
		table.addData(env, I_FD_DataDictionary.Table_Name);
		num.addData(env, I_FD_Table.Table_Name);
		dd.addData(env, I_FD_Table.Table_Name);
		table.addData(env, I_FD_Table.Table_Name);
		//4.
		FD_Type type = new FD_Type();
		type.createTable(env);
		type.addData(env, I_FD_Numbering.Table_Name);
		type.addData(env, I_FD_DataDictionary.Table_Name);
		type.addData(env, I_FD_Table.Table_Name);
		//5-1.
		FD_TypeItem typeItem = new FD_TypeItem();
		typeItem.createTable(env);
		typeItem.addData(env, I_FD_Numbering.Table_Name);
		typeItem.addData(env, I_FD_DataDictionary.Table_Name);
		typeItem.addData(env, I_FD_Table.Table_Name);
		//5-2.
		FD_TypeITem_Param typeItemParam = new FD_TypeITem_Param();
		typeItemParam.createTable(env);
		typeItemParam.addData(env, I_FD_Numbering.Table_Name);
		typeItemParam.addData(env, I_FD_DataDictionary.Table_Name);
		typeItemParam.addData(env, I_FD_Table.Table_Name);
		//6-1.
		addTableTypeInformation(env);
		//6.
		FD_Column column = new FD_Column();
		column.createTable(env);
		column.addData(env, I_FD_Numbering.Table_Name);
		column.addData(env, I_FD_DataDictionary.Table_Name);
		column.addData(env, I_FD_Table.Table_Name);
		num.addData(env, I_FD_Column.Table_Name);
		dd.addData(env, I_FD_Column.Table_Name);
		table.addData(env, I_FD_Column.Table_Name);
		type.addData(env, I_FD_Column.Table_Name);
		typeItem.addData(env, I_FD_Column.Table_Name);
		column.addData(env, I_FD_Column.Table_Name);
		typeItemParam.addData(env, I_FD_Column.Table_Name);
	}
	/**
	 * テーブル項目用属性情報登録[Attribute information registration for table items]<br>
	 * @author officine-hide.net
	 * @since 2021/09/22
	 * @param env 環境情報[Environment information]
	 */
	private void addTableTypeInformation(FD_EnvData env) {
		FD_Type type = new FD_Type();
		long typeID = type.add(env, 0, FD_Column_Type, "テーブル項目属性", "テーブル項目の属性を管理する。");
		FD_TypeItem typeItem = new FD_TypeItem();
		FD_TypeITem_Param typeItemParam = new FD_TypeITem_Param();
		long typeItemID = typeItem.add(env, 0, FD_Item_ID, typeID, "情報ID", "情報を識別するID(Classはlong)");
		typeItemParam.add(env, 0, "SQL_String", typeItemID, "CreateSQL用文字列", "int unsigned", null);
		typeItemID = typeItem.add(env, 0, FD_Item_String, typeID, "文字列", "単行の文字列(ClassはString)");
		typeItemParam.add(env, 0, "SQL_String", typeItemID, "CreateSQL用文字列", "varchar(@size@)", null);
		typeItemID = typeItem.add(env, 0, FD_Item_Text, typeID, "複数行文字列", "複数行の文字列(ClassはString)");
		typeItemParam.add(env, 0, "SQL_String", typeItemID, "CreateSQL用文字列", "text", null);
		typeItemID = typeItem.add(env, 0, FD_ITEM_Date, typeID, "日付", "日付(ClassはCalendar)");
		typeItemParam.add(env, 0, "SQL_String", typeItemID, "CreateSQL用文字列", "datetime", null);
		typeItemID = typeItem.add(env, 0, FD_ITEM_BigInt, typeID, "拡大整数", "最大長の整数(Classはlong)");
		typeItemParam.add(env, 0, "SQL_String", typeItemID, "CreateSQL用文字列", "bigint", null);
		typeItemID = typeItem.add(env, 0, FD_ITEM_Unsigned_BigInt, typeID, "拡大正整数", "最大長の符号無し整数(Classはlong)");
		typeItemParam.add(env, 0, "SQL_String", typeItemID, "CreateSQL用文字列", "bigint unsigned", null);
		typeItemID = typeItem.add(env, 0, FD_ITEM_Int, typeID, "整数", "整数(Classはint)");
		typeItemParam.add(env, 0, "SQL_String", typeItemID, "CreateSQL用文字列", "int", null);
		typeItemID = typeItem.add(env, 0, FD_ITEM_Unsigned_Int, typeID, "正整数", "符号無し整数(Classはint)");
		typeItemParam.add(env, 0, "SQL_String", typeItemID, "CreateSQL用文字列", "int unsigned", null);
		typeItemID = typeItem.add(env, 0, FD_ITEM_YES_NO, typeID, "正整数", "判定値 Yはtrue、Nはfalse (Classはboolean)");
		typeItemParam.add(env, 0, "SQL_String", typeItemID, "CreateSQL用文字列", "enum('Y','N')", null);
	}
}
