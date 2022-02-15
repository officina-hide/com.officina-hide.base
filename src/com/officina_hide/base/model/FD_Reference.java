package com.officina_hide.base.model;

import java.util.List;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.common.FD_WhereData;

/**
 * 参照情報クラス[Reference information class]<br>
 * @author officina-hide.net
 * @version 1.00 新規作成
 * @since 2021/12/10 Ver. 1.00
 */
public class FD_Reference extends FD_DB implements I_FD_Reference {

	/**
	 * 参照情報テーブル構築[Generate reference information table]<br>
	 * @author officina-hide.net
	 * @since 2021/12/10 Ver. 1.00
	 * @param env 環境情報[Environment information]
	 */
	public void createTable(FD_EnvData env) {
		//テーブル登録
		FD_Table table = new FD_Table();
		table.add(env, Table_ID, Table_Name, Table_Disp_Name, Table_Comment);
		//採番情報登録
		FD_Numbering num = new FD_Numbering();
		num.add(env, Table_ID, Table_ID, 100001, 0);
		//辞書情報登録
		FD_DataDictionary dd = new FD_DataDictionary();
		dd.add(env, 0, COLUMNNAME_FD_Reference_ID, NAME_FD_Reference_ID, COMMENT_FD_Reference_ID);
		dd.add(env, 0, COLUMNNAME_FD_Reference_Name, NAME_FD_Reference_Name, COMMENT_FD_Reference_Name);
		dd.add(env, 0, COLUMNNAME_FD_ReferenceType_ID, NAME_FD_ReferenceType_ID, COMMENT_FD_ReferenceType_ID);
		//テーブル項目情報登録
		FD_Column column = new FD_Column();
		column.add(env, 0, Table_ID, COLUMNNAME_FD_Reference_ID, FD_ITEM_ID, 0, false, true, "0");
		column.add(env, 0, Table_ID, COLUMNNAME_FD_Reference_Name, FD_ITEM_String, 100, false, false, null);
		column.add(env, 0, Table_ID, COLUMNNAME_FD_ReferenceType_ID, FD_ITEM_ID, 0, true, false, null);
		addCommonColumn(env, Table_ID);
		//テーブル削除
		dropTable(env, Table_Name);
		//テーブル生成
		createTable(env, Table_Name);
	}

	/**
	 * 参照情報登録[Reference information registration]<br>
	 * @author officina-hide.net
	 * @since 2021/12/13 Ver. 1.00
	 * @param env 環境情報[Environment information]
	 */
	public void createReferenceType(FD_EnvData env) {
		/*
		 * 基盤として必要な参照情報を登録する。
		 * 1. 参照情報用の属性情報を登録
		 */
		FD_Type type = new FD_Type();
		long typeId = type.add(env, 0, FD_Reference_Type, "参照情報", "参照情報で参照方法の種別を管理する。");
		FD_TypeItem typeItem = new FD_TypeItem();
		typeItem.add(env, 0, FD_Reference_Table, typeId, NAME_FD_Reference_Table, COMMENT_FD_Reference_Table);
	}

	/**
	 * 情報登録[Information entry]<br>
	 * @author officina-hide.net
	 * @since 2021/12/14 Ver. 1.00
	 * @param env 環境情報[Environment information]
	 * @param refrenceId 参照情報ID[Reference information ID]
	 * @param referenceName 参照情報名[Reference information name]
	 * @param refereceType 参照情報種別[Reference information type]
	 * @return 参照情報ID[Reference ID]
	 */
	public long add(FD_EnvData env, int refrenceId, String referenceName, String refereceType) {
		X_FD_Reference ref = new X_FD_Reference(env, 0);
		ref.setFD_Reference_ID(refrenceId);
		ref.setFD_Reference_Name(referenceName);
		FD_TypeItem type = new FD_TypeItem();
		ref.setFD_ReferenceType_ID(type.getTypeItemID(env, FD_Reference_Type, refereceType));
		ref.setFD_Group_ID(env.getActionUserID());
		ref.save(env); 
		return ref.getFD_Reference_ID();
	}

	/**
	 * 情報ID取得[Information ID acquisition]
	 * 引数の名称を持つ情報IDを抽出する。もし、対象の情報IDが無い時はメッセージを出力して0を返す。<br>
	 * Extract the information ID with the name of the argument.<br>
	 * If there is no target information ID, a message is output and 0 is returned.<br>
	 * @author officina-hide.net
	 * @since 2022/02/14 Ver. 1.00
	 * @param env 環境情報[Environment information]
	 * @param name 名称(テーブル名+Nameを持つ項目)[Name (item with table name + Name)]
	 * @return 情報ID[Information ID]
	 */
	public long getIdByName(FD_EnvData env, String name) {
		FD_WhereData where = new FD_WhereData(COLUMNNAME_FD_Reference_Name, name);
		List<Integer> ids = getAllId(Table_Name, where, env);
		if(ids.size() == 1) {
			return ids.get(0);
		}
		return 0;
	}

}
