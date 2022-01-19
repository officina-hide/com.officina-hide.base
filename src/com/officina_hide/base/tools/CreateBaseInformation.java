package com.officina_hide.base.tools;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.common.FD_WhereData;
import com.officina_hide.base.model.FD_Column;
import com.officina_hide.base.model.FD_DB;
import com.officina_hide.base.model.FD_DataDictionary;
import com.officina_hide.base.model.FD_Login;
import com.officina_hide.base.model.FD_Numbering;
import com.officina_hide.base.model.FD_Process;
import com.officina_hide.base.model.FD_ProcessParam;
import com.officina_hide.base.model.FD_Reference;
import com.officina_hide.base.model.FD_Table;
import com.officina_hide.base.model.FD_TableReference;
import com.officina_hide.base.model.FD_Type;
import com.officina_hide.base.model.FD_TypeITem_Param;
import com.officina_hide.base.model.FD_TypeItem;
import com.officina_hide.base.model.FD_User;
import com.officina_hide.base.model.I_FD_Column;
import com.officina_hide.base.model.I_FD_DB;
import com.officina_hide.base.model.I_FD_DataDictionary;
import com.officina_hide.base.model.I_FD_Numbering;
import com.officina_hide.base.model.I_FD_Table;
import com.officina_hide.base.model.X_FD_Numbering;

/**
 * 基盤情報構築クラス<br>
 * Basic information construction class.<br>
 * <p>基盤情報には以下の情報があります。<br>
 * ・テーブル関連情報（テーブル、カラム等データベースを構築するうえで必要な情報）</p>
 * <p>The basic information includes the following information.<br>
 * ・Table-related information (information necessary for building a database such as tables and columns)</p>
 * 
 * @author officina-hide.net
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
		 * ※ここからは汎用の構築手順で行う。
		 * 7. ユーザー情報テーブル構築、システムユーザー登録
		 * 8. ログイン情報テーブル構築
		 * 9. 処理情報テーブル構築
		 * 10-1. 処理変数情報テーブル構築
		 * 10-2. 処理変数用種別登録
		 * 11-1. 参照情報テーブル構築
		 * 11-2. テーブル参照情報テーブル構築
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
		//7.
		FD_User user = new FD_User();
		user.createTable(env);
		user.add(env, SYSTEM_USER_ID, SYSTEM_USER, SYSTEM_USER_PASSWORD,
				SYSTEM_USER_NAME, SYSTEM_USER_DESCRIPTION);
		//8.
		FD_Login login = new FD_Login();
		login.createTable(env);
		//9.
		FD_Process process = new FD_Process();
		process.createTable(env);
		//10-1.
		FD_ProcessParam pp = new FD_ProcessParam();
		pp.createTable(env);
		//10-2.
		addParamTypeInformation(env);
		//11-1.
		FD_Reference rf = new FD_Reference();
		rf.createTable(env);
		rf.createReferenceType(env);
		//11-2.
		FD_TableReference tr = new FD_TableReference();
		tr.createTable(env);
//		//12.
//		FD_Reference_Param refParam = new FD_Reference_Param();
//		refParam.createTable(env);
	}
	/**
	 * テーブル項目用属性情報登録[Attribute information registration for table items]<br>
	 * @author officina-hide.net
	 * @since 2021/09/22
	 * @param env 環境情報[Environment information]
	 */
	private void addTableTypeInformation(FD_EnvData env) {
		FD_Type type = new FD_Type();
		long typeID = type.add(env, 0, FD_Column_Type, "テーブル項目属性", "テーブル項目の属性を管理する。");
		FD_TypeItem typeItem = new FD_TypeItem();
		FD_TypeITem_Param typeItemParam = new FD_TypeITem_Param();
		long typeItemID = typeItem.add(env, 0, FD_ITEM_ID, typeID, "情報ID", "情報を識別するID(Classはlong)");
		typeItemParam.add(env, 0, "SQL_String", typeItemID, "CreateSQL用文字列", "int unsigned", null);
		typeItemID = typeItem.add(env, 0, FD_ITEM_String, typeID, "文字列", "単行の文字列(ClassはString)");
		typeItemParam.add(env, 0, "SQL_String", typeItemID, "CreateSQL用文字列", "varchar(@size@) binary", null);
		typeItemID = typeItem.add(env, 0, FD_ITEM_Text, typeID, "複数行文字列", "複数行の文字列(ClassはString)");
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
		typeItemID = typeItem.add(env, 0, FD_ITEM_Amount, typeID, "金額", "金額を扱う(ClassはBigdecimal)");
		typeItemParam.add(env, 0, "SQL_String", typeItemID, "CreateSQL用文字列", "Bigint", null);
	}

	/**
	 * 処理変数用属性情報登録[Attribute information registration for processing variables]<br>
	 * @author officina-hide.net
	 * @since 2021/10/12 Ver. 1.00
	 * @param env 環境情報[Environment information]
	 */
	private void addParamTypeInformation(FD_EnvData env) {
		FD_Type type = new FD_Type();
		FD_TypeItem typeItem = new FD_TypeItem();
		long typeID = type.add(env, 0, FD_Param_Type, NAME_FD_Param_Type, COMMENT_FD_Param_Type);
		typeItem.add(env, 0, FD_Param_Object, typeID, NAME_FD_Param_Object, COMMENT_FD_Param_Object);
	}

	/**
	 * 機能開発用の採番再設定[Renumbering for function development]
	 * @author officina-hide.net
	 * @since 2021/10/02 Ver.1.00
	 * @param env 環境情報[Environment information]
	 */
	public void reNumber(FD_EnvData env) {
		//採番情報
		FD_WhereData where = new FD_WhereData(I_FD_Numbering.COLUMNNAME_FD_Table_ID, I_FD_Numbering.Table_ID);
		X_FD_Numbering num = new X_FD_Numbering(env, where);
		num.setFD_InitialNumber(100001);
		num.setFD_CurrentNumber(0);
		num.save(env);
		//辞書情報
		where = new FD_WhereData(I_FD_Numbering.COLUMNNAME_FD_Table_ID, I_FD_DataDictionary.Table_ID);
		X_FD_Numbering num_dd = new X_FD_Numbering(env, where);
		num_dd.setFD_InitialNumber(100001);
		num_dd.setFD_CurrentNumber(0);
		num_dd.save(env);
		//テーブル項目情報
		where = new FD_WhereData(I_FD_Numbering.COLUMNNAME_FD_Table_ID, I_FD_Column.Table_ID);
		X_FD_Numbering num_column = new X_FD_Numbering(env, where);
		num_column.setFD_InitialNumber(100001);
		num_column.setFD_CurrentNumber(0);
		num_column.save(env);
	}
}
