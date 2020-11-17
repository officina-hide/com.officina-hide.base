package com.officina_hide.base.tools;

import java.util.Date;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.model.FD_DB;
import com.officina_hide.base.model.I_FD_Log;
import com.officina_hide.base.model.I_FD_Numbering;
import com.officina_hide.base.model.I_FD_Process;
import com.officina_hide.base.model.I_FD_Table;
import com.officina_hide.fx.tools.FXView;
import com.officina_hide.project.tools.FDProject;
import com.officina_hide.project.tools.FDTask;

/**
 * パッケージベース環境構築クラス<br>
 * @author officine-hide.com
 * @version 1.20
 * @since 2020/11/17
 */
public class CreateBaseEnvironment {
	
	//プロセスID
	private final static int ThisProcess_ID = 101;

	/**
	 * 構築処理
	 * @author officine-hide.com
	 * @since 1.20 2020/11/17
	 * @param env 環境情報
	 */
	public void create(FD_EnvData env) {
		//プロセス情報IDセット
		env.setActiveProcessID(ThisProcess_ID);
		//開始時刻保存
		Date startDate = new Date();

		//ログ情報構築
		FDLog log = new FDLog();
		log.createTable(env);
		//プロセス情報構築
		FDProcess process = new FDProcess();
		process.createTable(env);
		
		//プロセス情報登録
		process.addData(env, ThisProcess_ID,  CreateBaseEnvironment.class.getSimpleName(), startDate);

		//テーブル情報構築
		FDTable table = new FDTable();
		table.createTable(env);
		//先行構築テーブルのテーブル情報登録
		table.addData(env, I_FD_Log.TABLE_ID, I_FD_Log.Table_Name, I_FD_Log.NAME, I_FD_Log.COMMENT);
		table.addData(env, I_FD_Process.TABLE_ID, I_FD_Process.Table_Name, I_FD_Process.NAME, I_FD_Process.COMMENT);

		//採番情報構築
		FDNumbering num = new FDNumbering();
		num.createTable(env);
		num.addData(env,I_FD_Table.TABLE_ID, I_FD_Table.TABLE_ID, 0, 1000001, 0, null);
		num.addData(env, I_FD_Process.TABLE_ID, I_FD_Process.TABLE_ID, 0, 1000001, 0, null);
		
		//テーブル項目情報構築
		FDTableColumn column = new FDTableColumn();
		column.createTable(env);

		//リファレンス情報構築
		FDReference ref = new FDReference();
		ref.createDBTable(env);
		//テーブル項目情報に必要なリファレンスを登録する。
		ref.addColumnTypeReference(env);

		//先行したテーブルのテーブル項目情報登録
		table.addTableColumn(env);
		column.addTableColumn(env);
		num.addTableColumn(env);
		ref.addTableColumn(env);
		log.addTabeColumn(env);
		process.addTableColumn(env);

		//ユニーク制約情報構築
		FDUniqueIndex uidx = new FDUniqueIndex();
		uidx.createTable(env);

		//採番情報のユニーク制約を登録する。
		FDUniqueColumn uclm = new FDUniqueColumn();
		uclm.createTable(env);
		//採番情報にユニーク制約付与
		int uiId = uidx.addData(env, 0, I_FD_Numbering.TABLE_ID
				, I_FD_Numbering.Unique_Index_Name, I_FD_Numbering.Unique_Index_FD_Name);
		uclm.addData(env, 0, uiId, column.getColumnId(env, I_FD_Numbering.TABLE_ID, I_FD_Numbering.COLUMNNAME_FD_Table_ID));
		uclm.addData(env, 0, uiId, column.getColumnId(env, I_FD_Numbering.TABLE_ID, I_FD_Numbering.COLUMNNAME_FD_TableColumn_ID));
		uclm.addData(env, 0, uiId, column.getColumnId(env, I_FD_Numbering.TABLE_ID, I_FD_Numbering.COLUMNNAME_Numbering_Key));
		uidx.createUniqueKey(env, uiId);

		//Fx画面情報構築
		FXView view = new FXView();
		view.createTable(env);

		//プロジェクト情報構築
		FDProject project = new FDProject();
		project.createTable(env);
		
		//タスク情報構築
		FDTask task = new FDTask();
		task.createTable(env);
		
		//終了メッセージ
		FD_DB DB = new FD_DB();
		DB.addLog(env, I_FD_Log.LOGTYPE_Info_ID, "パッケージベース環境の構築完了【CreateBaseEnvironment】");
		//プロセス終了登録
		process.endProcess(env, new Date());

	}

}
