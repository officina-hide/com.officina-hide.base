package com.officina_hide.project.tools;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.model.FD_DB;
import com.officina_hide.base.model.I_FD_ColumnForm;
import com.officina_hide.base.model.I_FD_ColumnFormArray;
import com.officina_hide.base.model.I_FD_Log;
import com.officina_hide.base.model.I_FD_Reference;
import com.officina_hide.base.model.I_FD_ReferenceList;
import com.officina_hide.base.model.X_FD_ColumnForm;
import com.officina_hide.base.tools.FDNumbering;
import com.officina_hide.base.tools.FDTable;
import com.officina_hide.base.tools.FDTableColumn;
import com.officina_hide.project.model.I_FD_Project;
import com.officina_hide.project.model.I_FD_Task;
import com.officina_hide.project.model.X_FD_Project;
import com.officina_hide.project.model.X_FD_Task;

/**
 * タスク情報クラス<br>
 * @author officine-hide.com
 * @version 1.20
 * @since 2020/11/09
 */
public class FDTask extends FD_DB implements I_FD_Task {

	public void createTable(FD_EnvData env) {
		addLog(env, I_FD_Log.LOGTYPE_Info_ID, NAME+"テーブル構築開始");
		
		//テーブル情報登録
		FDTable table = new FDTable();
		table.addData(env, TABLE_ID, Table_Name, NAME, COMMENT);
		//テーブル項目情報登録
		FDTableColumn column = new FDTableColumn();
		column.addData(env, 0, TABLE_ID, COLUMNNAME_FD_Task_ID, NAME_FD_Task_ID, COMMENT_FD_Task_ID
				, COLUMNTYPE_ID_FD_Information_ID, "0", 0, 10, "Y", "Y");
		column.addData(env, 0, TABLE_ID, COLUMNNAME_FD_Project_ID, COLUMNNAME_FD_Project_ID, COMMENT_FD_Project_ID
				, COLUMNTYPE_ID_FD_Information_ID, "0", 0, 20, "N", "N");
		column.addData(env, 0, TABLE_ID, COLUMNNAME_Task_Number, NAME_Task_Number, COMMENT_Task_Number
				, COLUMNTYPE_ID_FD_Text, null, 32, 30, "N", "N");
		
		column.addData(env, 0, TABLE_ID, COLUMNNAME_FD_Process_ID, NAME_FD_Process_ID, COMMENT_FD_Process_ID
				, COLUMNTYPE_ID_FD_Information_ID, null, 0, 900, "N", "N");
		column.addData(env, 0, TABLE_ID, COLUMNNAME_FD_CREATE, NAME_FD_CREATE, COMMENT_FD_CREATE
				, COLUMNTYPE_ID_FD_Date, null, 0, 910, "N", "N");
		column.addData(env, 0, TABLE_ID, COLUMNNAME_FD_CREATED, NAME_FD_CREATED, COMMENT_FD_CREATED
				, COLUMNTYPE_ID_FD_Information_ID, null, 0, 920, "N", "N");
		column.addData(env, 0, TABLE_ID, COLUMNNAME_FD_UPDATE, NAME_FD_UPDATE, COMMENT_FD_UPDATE
				, COLUMNTYPE_ID_FD_Date, null, 0, 930, "N", "N");
		column.addData(env, 0, TABLE_ID, COLUMNNAME_FD_UPDATED, NAME_FD_UPDATED, COMMENT_FD_UPDATED
				, COLUMNTYPE_ID_FD_Information_ID, null, 0, 940, "N", "N");
		//テーブル構築
		createDBTable(env, TABLE_ID);
		//採番情報登録
		FDNumbering num = new FDNumbering();
		num.addData(env, TABLE_ID, TABLE_ID, 0, 1000001);

		addLog(env, I_FD_Log.LOGTYPE_Info_ID, NAME+"テーブル構築完了");
	}

	/**
	 * タスク情報登録<br>
	 * @author officine-hide.com
	 * @since 1.20 2020/11/09
	 * @param env 環境情報
	 * @param projectId プロジェクト情報ID
	 */
	public void addData(FD_EnvData env, int projectId) {
		X_FD_Task task = new X_FD_Task(env);
		task.setValueByName(env, COLUMNNAME_FD_Task_ID, 0);
		task.setValueByName(env, COLUMNNAME_FD_Process_ID, projectId);
		System.out.println(getTaskNumber(env, projectId));
	}

	/**
	 * タスク情報追加<br>
	 * @author officine-hide.com
	 * @since 1.21 2020/11/26
	 * @param env 環境情報
	 * @param projectId プロジェクト情報ID
	 */
	public void createTask(FD_EnvData env, int projectId) {
		X_FD_Task task = new X_FD_Task(env);
		task.setValueByName(env, COLUMNNAME_FD_Task_ID, 0);
		task.setValueByName(env, COLUMNNAME_FD_Project_ID, projectId);
		task.setValueByName(env, COLUMNNAME_Task_Number, getTaskNumber(env, projectId));
		task.save(env);
	}

	/**
	 * タスク伝票番号生成<br>
	 * @author officine-hide.com
	 * @since 1.20 2020/11/18
	 * @param env 環境情報
	 * @param projectId プロジェクト情報ID
	 * @return タスク伝票番号
	 */
	private String getTaskNumber(FD_EnvData env, int projectId) {
		/*
		 * ・プロジェクト情報かに項目様式情報を取得する。
		 * ・項目様式情報から書式配列を取得する。
		 * ・書式配列に対して該当する情報をセットする。（採番情報）
		 */
//		X_FD_Project pro = new X_FD_Project(env, projectId);
//		X_FD_ColumnForm form = new X_FD_ColumnForm(env, pro.getintOfValue(I_FD_Project.COLUMNNAME_Task_Number_Key_ID));

		Statement stmt = null;
		ResultSet rs = null;
		
		StringBuffer sql = new StringBuffer();
		try {
			sql.append("SELECT ")
				.append("r.").append(I_FD_Reference.COLUMNNAME_Reference_Name).append(" ").append("formType").append(",")
				.append("cfa.").append(I_FD_ColumnFormArray.COLUMNNAME_FD_Value).append(" ").append("fixText").append(",")
				.append("rl.").append(I_FD_ReferenceList.COLUMNNAME_FD_Code).append(" ").append("connectText").append(",")
				.append("cfa.").append(I_FD_ColumnFormArray.COLUMNNAME_FD_Length).append(" ").append("length").append(" ")
				.append("FROM ").append(I_FD_ColumnFormArray.Table_Name).append(" ").append("cfa").append(" ");
			sql.append("LEFT JOIN ").append(I_FD_ColumnForm.Table_Name).append(" ").append("cf").append(" on ")
				.append("cf.").append(I_FD_ColumnForm.COLUMNNAME_FD_ColumnForm_ID).append(" = ")
				.append("cfa.").append(I_FD_ColumnFormArray.COLUMNNAME_FD_ColumnForm_ID).append(" ");
			sql.append("LEFT JOIN ").append(I_FD_Project.Table_Name).append(" ").append("pj").append(" on ")
				.append("pj.").append(I_FD_Project.COLUMNNAME_Task_Number_Key_ID).append(" = ")
				.append("cf.").append(I_FD_ColumnForm.COLUMNNAME_FD_ColumnForm_ID).append(" ");
			sql.append("LEFT JOIN ").append(I_FD_ReferenceList.Table_Name).append(" rl ").append(" on ")
				.append("rl.").append(I_FD_ReferenceList.COLUMNNAME_FD_ReferenceList_ID).append(" = ")
				.append("cfa.").append(I_FD_ColumnFormArray.COLUMNNAME_FD_Code).append(" ");
			sql.append("LEFT JOIN ").append(I_FD_Reference.Table_Name).append(" r ").append(" on ")
				.append("r.").append(I_FD_Reference.COLUMNNAME_FD_Reference_ID).append(" = ")
				.append("cfa.").append(I_FD_ColumnFormArray.COLUMNNAME_FormType_ID).append(" ");
			sql.append("WHERE ").append("pj.").append(I_FD_Project.COLUMNNAME_FD_Project_ID).append(" = ").append(projectId).append(" ");
			sql.append("ORDER BY ").append("cfa.").append(I_FD_ColumnFormArray.COLUMNNAME_Array_Number).append(" ");

			connection(env);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql.toString());
			
			List<Map<String, String>> formList = new ArrayList<>();
			StringBuffer keyText = new StringBuffer();
			while(rs.next()) {
				Map<String, String> map = new HashMap<>();
				map.put("formType", rs.getString("formType"));
				switch(rs.getString("formType")) {
				case I_FD_ColumnFormArray.FORMTYPE_REF_NAME_FixText:
					map.put("form", rs.getString("fixText"));
					keyText.append(rs.getString("fixText"));
					break;
				case I_FD_ColumnFormArray.FORMTYPE_REF_NAME_ConnextText:
					map.put("form", rs.getString("connectText"));
					keyText.append(rs.getString("connectText"));
					break;
				case I_FD_ColumnFormArray.FORMTYPE_REF_NAME_Numbering:
					char[] formatChar = new char[rs.getInt("length")];
					Arrays.fill(formatChar, '0');
					map.put("form", String.valueOf(formatChar));
					DecimalFormat df = new DecimalFormat(String.valueOf(formatChar));
					keyText.append(df.format(0));
					break;
				}
				formList.add(map);
			}
			//Keyによる採番処理
			int keyNo = getNewID(env, Table_Name, keyText.toString());
			
//			StringBuffer numText = new StringBuffer();
//			while(rs.next()) {
//				switch(rs.getString("formType")) {
//				case I_FD_ColumnFormArray.FORMTYPE_REF_NAME_FixText:
//					numText.append(rs.getString("fixText"));
//					break;
//				case I_FD_ColumnFormArray.FORMTYPE_REF_NAME_ConnextText:
//					numText.append(rs.getString("connectText"));
//					break;
//				case I_FD_ColumnFormArray.FORMTYPE_REF_NAME_Numbering:
//					char[] formatChar = new char[rs.getInt("length")];
//					Arrays.fill(formatChar, '0');
//					DecimalFormat df = new DecimalFormat(String.valueOf(formatChar));
//					break;
//				}
//			}
			
//			System.out.println(numText.toString());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(stmt, rs);
		}
		
		return null;
	}

}
