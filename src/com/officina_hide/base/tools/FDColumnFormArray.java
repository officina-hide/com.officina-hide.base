package com.officina_hide.base.tools;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.officina_hide.base.common.FD_DB_Utility;
import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.model.FD_DB;
import com.officina_hide.base.model.I_FD_ColumnFormArray;
import com.officina_hide.base.model.I_FD_Log;

/**
 * 書式配列情報クラス<br>
 * @author officina-hide.com
 * @version 1.21
 * @since 2020/11/21
 */
public class FDColumnFormArray extends FD_DB implements I_FD_ColumnFormArray {

	/**
	 * 書式配列情報テーブル構築<br>
	 * @author officina-hide.com
	 * @since 1.21 2020/11/21
	 * @param env 環境情報
	 */
	public void createTable(FD_EnvData env) {
		addLog(env, I_FD_Log.LOGTYPE_Info_ID, NAME + "テーブル構築開始");

		//テーブル情報登録
		FDTable table = new FDTable();
		table.addData(env, TABLE_ID, Table_Name, NAME, COMMENT);
		
		//テーブル項目情報登録
		FDTableColumn column = new FDTableColumn();
		column.addData(env, 0, TABLE_ID, COLUMNNAME_FD_ColumnFormArray_ID, NAME_FD_ColumnFormArray_ID, COMMENT_FD_ColumnFormArray_ID
				, COLUMNTYPE_ID_FD_Information_ID, "0", 0, 10, "Y", "Y");
		column.addData(env, 0, TABLE_ID, COLUMNNAME_FD_ColumnForm_ID, NAME_FD_ColumnForm_ID, COMMENT_FD_ColumnForm_ID
				, COLUMNTYPE_ID_FD_Information_ID, "0", 0, 20, "N", "N");
		column.addData(env, 0, TABLE_ID, COLUMNNAME_Array_Number, NAME_Array_Number, COMMENT_Array_Number
				, COLUMNTYPE_ID_FD_Number, "0", 0, 30, "N", "N");
		column.addData(env, 0, TABLE_ID, COLUMNNAME_FormType_ID, NAME_FormType_ID, COMMENT_FormType_ID
				, COLUMNTYPE_ID_FD_Information_ID, "0", 0, 40, "N", "N");
		column.addData(env, 0, TABLE_ID, COLUMNNAME_FD_Value, NAME_FD_Value, COMMENT_FD_Value
				, COLUMNTYPE_ID_FD_Text, null, 100, 40, "N", "N");
		column.addData(env, 0, TABLE_ID, COLUMNNAME_FD_Code, NAME_FD_Code, COLUMNNAME_FD_Code
				, COLUMNTYPE_ID_FD_Information_ID, "0", 0, 60, "N", "N");
		
		FD_DB_Utility dutil = new FD_DB_Utility();
		dutil.baseColumnEntry(env, TABLE_ID);
		
		//テーブル構築
		createDBTable(env, TABLE_ID);
		
		//採番情報登録
		FDNumbering num = new FDNumbering();
		num.addData(env, TABLE_ID, TABLE_ID, 0, 1000001);
		
		//関連リファレンス情報登録
		FDReference ref = new FDReference();
		ref.addData(env, FORMTYPE_ID_FixText_ID, FORMTYPE_REF_NAME_FixText, FORMTYPE_NAME_FixText);
		ref.addData(env, FORMTYPE_ID_ConnextText_ID, FORMTYPE_REF_NAME_ConnextText, FORMTYPE_NAME_ConnextText);
		ref.addData(env, FORMTYPE_ID_Numbering_ID, FORMTYPE_REF_NAME_Numbering, FORMTYPE_NAME_Numbering);
		
		//接続文字列用リファレンスリスト情報登録
		FDReferenceList refList = new FDReferenceList();
		
		addLog(env, I_FD_Log.LOGTYPE_Info_ID, NAME + "テーブル構築完了");
	}

	/**
	 * 固定文字列登録<br>
	 * @author officina-hide.com
	 * @since 1.21 2020/11/23
	 * @param env 環境情報
	 * @param columnFormId 項目書式情報ID
	 * @param text 固定文字列
	 */
	public void addFixText(FD_EnvData env, int columnFormId, String text) {
		X_FD_ColumnFormArray cfa = new X_FD_ColumnFormArray(env);
		cfa.setValueByName(env, COLUMNNAME_FD_ColumnFormArray_ID, 0);
		cfa.setValueByName(env, COLUMNNAME_FD_ColumnForm_ID, columnFormId);
		cfa.setValueByName(env, COLUMNNAME_Array_Number, getNewArrayNumber(env, columnFormId));
		cfa.setValueByName(env, COLUMNNAME_FormType_ID, I_FD_ColumnFormArray.FORMTYPE_ID_FixText_ID);
		cfa.setValueByName(env, COLUMNNAME_FD_Value, text);
		cfa.save(env);
	}

	/**
	 * 接続文字列登録<br>
	 * @author officina-hide.com
	 * @since 1.21 2020/11/23
	 * @param env 環境情報
	 * @param columnFormId 項目書式情報ID
	 * @param code 接続文字列（コード）
	 */
	public void addConnectText(FD_EnvData env, int columnFormId, String code) {
		X_FD_ColumnFormArray cfa = new X_FD_ColumnFormArray(env);
		cfa.setValueByName(env, COLUMNNAME_FD_ColumnFormArray_ID, 0);
		cfa.setValueByName(env, COLUMNNAME_FD_ColumnForm_ID, columnFormId);
		cfa.setValueByName(env, COLUMNNAME_Array_Number, getNewArrayNumber(env, columnFormId));
		cfa.setValueByName(env, COLUMNNAME_FormType_ID, I_FD_ColumnFormArray.FORMTYPE_ID_ConnextText_ID);
		
		/*
		 * 接続文字列からリファレンスリストを抽出する。
		 * 
		 */
		
		
		cfa.save(env);
	}

	/**
	 * 新規書式配列番号取得<br>
	 * @author officina-hide.com
	 * @since 1.21 2020/11/23
	 * @param env 環境情報
	 * @param columnFormId 項目書式情報ID
	 * @return 書式配列番号
	 */
	private int getNewArrayNumber(FD_EnvData env, int columnFormId) {
		int arrayNo = 1;
		Statement stmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		try {
			sql.append("SELECT MAX(").append(I_FD_ColumnFormArray.COLUMNNAME_Array_Number).append(")").append(" ")
				.append("FROM ").append(I_FD_ColumnFormArray.Table_Name).append(" ");
			sql.append("WHERE ").append(I_FD_ColumnFormArray.COLUMNNAME_FD_ColumnForm_ID).append(" = ").append(columnFormId);
			connection(env);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql.toString());
			if(rs.next()) {
				arrayNo = rs.getInt("MAX("+I_FD_ColumnFormArray.COLUMNNAME_Array_Number+")") + 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(stmt, rs);
		}
		return arrayNo;
	}

}
