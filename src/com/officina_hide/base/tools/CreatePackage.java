package com.officina_hide.base.tools;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.model.FD_DB;
import com.officina_hide.base.model.I_FD_Log;

/**
 * パッケージ構築クラス<br>
 * <p>本パッケージを初期状態から構築するための機能を提供する。<br>
 * 将来的には、ウィザード形式でシステムが構築できるようにしていく。</p>
 * @author officine-hide.com
 * @version 1.00 新規作成
 * @version 1.20 ユニーク制約情報構築
 * @since 2020/10/08
 * @deprecated 2020/11/17 CreateBaseEnvironmentに移管
 */
public class CreatePackage {

	private final static int ThisProcess_ID = 101;
	
	public static void main(String[] args) {
		/*
		 * 2020/10/29
		 * ログ情報にプロセス情報を追加する。<br>
		 * 本処理(CreatePackage)を1つのプロセスとする。(プロセス番号は101とする。)<br>
		 * これにより次の処理でプロセス単位での情報の消去を可能とする。<br>
		 * ※ここでの検討、他の情報にも追加プロセスが必要な場合は、共通化を検討する。<br>
		 */
		/*
		 * ユニーク制約情報の追加
		 */
		
		//開始時刻保存
		Date startDate = new Date();
		//環境情報のPathを設定する。
		FD_EnvData env = null;
		try {
			String propPath = new File(".").getCanonicalPath()+"\\data\\base.properties";
			//環境情報の取込
			env = new FD_EnvData(propPath);
		} catch (IOException e) {
			e.printStackTrace();
		}
		//プロセス情報IDセット
		env.setActiveProcessID(ThisProcess_ID);
		
//		//ログ情報構築
		FDLog log = new FDLog();
//		log.createTable(env);
		//プロセス情報構築
		FDProcess process = new FDProcess();
//		process.createTable(env);
//		process.addData(env, ThisProcess_ID,  CreatePackage.class.getSimpleName(), startDate);
		//テーブル情報構築
		FDTable table = new FDTable();
//		table.createTable(env);
//		table.addData(env, I_FD_Table.TABLE_ID, I_FD_Table.Table_Name, I_FD_Table.NAME, I_FD_Table.COMMENT);
//		table.addData(env, I_FD_Log.TABLE_ID, I_FD_Log.Table_Name, I_FD_Log.NAME, I_FD_Log.COMMENT);
//		table.addData(env, I_FD_Process.TABLE_ID, I_FD_Process.Table_Name, I_FD_Process.NAME, I_FD_Process.COMMENT);
		//採番情報構築
		FDNumbering num = new FDNumbering();
//		num.createTable(env);
//		table.addData(env, I_FD_Numbering.TABLE_ID, I_FD_Numbering.Table_Name
//				, I_FD_Numbering.NAME, I_FD_Numbering.COMMENT);
//		num.addData(env,I_FD_Table.TABLE_ID, I_FD_Table.TABLE_ID, 0, 1000001, 0, null);
//		num.addData(env,I_FD_Numbering.TABLE_ID, I_FD_Numbering.TABLE_ID, 0, 1000001, 0, null);
//		num.addData(env, I_FD_Process.TABLE_ID, I_FD_Process.TABLE_ID, 0, 1000001, 0, null);
		//リファレンス情報構築
		FDReference ref = new FDReference();
//		ref.createDBTable(env);
//		ref.addColumnTypeReference(env);
		
		//テーブル項目情報構築
		FDTableColumn column = new FDTableColumn();
//		column.createTable(env);
//		table.addData(env, I_FD_TableColumn.TABLE_ID, I_FD_TableColumn.Table_Name
//				, I_FD_TableColumn.NAME, I_FD_TableColumn.COMMENT);
//		num.addData(env,I_FD_TableColumn.TABLE_ID, I_FD_TableColumn.TABLE_ID, 0, 1000001, 0, null);
//		//テーブル項目情報登録
//		table.addTableColumn(env);
//		column.addTableColumn(env);
//		num.addTableColumn(env);
//		ref.addTableColumn(env);
//		log.addTabeColumn(env);
//		process.addTableColumn(env);
	
		//ユニーク制約情報構築
		FDUniqueIndex uidx = new FDUniqueIndex();
//		uidx.createTable(env);
//		FDUniqueColumn uclm = new FDUniqueColumn();
//		uclm.createTable(env);
//		//採番情報にユニーク制約付与
//		int uiId = uidx.addData(env, 0, I_FD_Numbering.TABLE_ID
//				, I_FD_Numbering.Unique_Index_Name, I_FD_Numbering.Unique_Index_FD_Name);
//		uclm.addData(env, 0, uiId, column.getColumnId(env, I_FD_Numbering.TABLE_ID, I_FD_Numbering.COLUMNNAME_FD_Table_ID));
//		uclm.addData(env, 0, uiId, column.getColumnId(env, I_FD_Numbering.TABLE_ID, I_FD_Numbering.COLUMNNAME_FD_TableColumn_ID));
//		uclm.addData(env, 0, uiId, column.getColumnId(env, I_FD_Numbering.TABLE_ID, I_FD_Numbering.COLUMNNAME_Numbering_Key));
//		uidx.createUniqueKey(env, uiId);
		
		process.endProcess(env, new Date());
		FD_DB DB = new FD_DB();
		DB.addLog(env, I_FD_Log.LOGTYPE_Info_ID, "ベース情報の構築完了【CreatePackage】");
		
	}

}
