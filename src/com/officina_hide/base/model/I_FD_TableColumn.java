package com.officina_hide.base.model;

/**
 * テーブル項目情報インタフェース[Table item information interface class]<br>
 * @author officina-hide.com
 * @version 1.30
 * @since 2020/12/14
 */
public interface I_FD_TableColumn {

	/** テーブル項目情報 : テーブル名 */
	public final String Table_Name = "FD_TableColumn";
	/** テーブル項目情報 : テーブル表示名 */
	public final String NAME = "テーブル項目情報";
	/** テーブル項目情報 : テーブル説明 */
	public final String COMMENT = "テーブル項目を管理する為の情報";
	/** テーブル項目情報 : テーブル情報ID */
	public final int TABLE_ID = 102;
	
	//テーブル項目情報
	/** テーブル項目情報ID */
	public final String COLUMNNAME_FD_TableColumn_ID = Table_Name + "_ID";
	public final String NAME_FD_TableColumn_ID = "テーブル項目情報ID";
	public final String COMMENT_FD_TableColumn_ID = "テーブル項目情報を識別する為の情報ID";
	/** テーブル情報ID */
	public final String COLUMNNAME_FD_Table_ID = "FD_Table_ID";
	public final String NAME_FD_Table_ID = "テーブル情報ID";
	public final String COMMENT_FD_Table_ID = "テーブル項目を管理するテーブルの情報ID";
	/** テーブル項目名 */
	public final String COLUMNNAME_TableColumn_Name = "Talbe_Name";
	public final String NAME_TableColumn_Name = "テーブル項目名";
	public final String COMMENT_TableColumn_Name = "テーブル項目を識別する際に使用する名称";
	/** テーブル項目表示名 */
	public final String COLUMNNAME_FD_Name = "FD_Name";
	public final String NAME_FD_Name = "テーブル項目表示名";
	public final String COMMENT_FD_Name = "テーブル項目の論理名";
	/** テーブル項目説明 */
	public final String COLUMNNAME_FD_Comment = "FD_Comment";
	public final String NAME_FD_Comment = "テーブル項目説明";
	public final String COMMENT_FD_Comment = "テーブル項目の説明";
	/** テーブル項目属性ID */
	public final String COLUMNNAME_TableColumn_Type_ID = "TableColumn_Type_ID";
	public final String NAME_TableColumn_Type_ID = "テーブル項目属性ID";
	public final String COMMENT_TableColumn_Type_ID = "テーブル項目の属性を識別する為の情報ID（リファレンス情報ID）";
	/** テーブル項目桁数 */
	public final String COLUMNNAME_TableColumn_Size = "TableColumn_Size";
	public final String NAME_TableColumn_Size = "テーブル項目桁数";
	public final String COMMENT_TableColumn_Size = "テーブル項目の桁数（桁数管理対象外の時は0をセットする。）";
	/** 項目並び順 */
	public final String COLUMNNAME_Column_Sort_Order ="Column_Sort_Order";
	public final String NAME_Column_Sort_Order ="項目並び順";
	public final String COMMENT_Column_Sort_Order ="テーブル項目の一覧作成時に使用する並び順";
	/** 初期値 */
	public final String COLUMNNAME_Defualt_Data = "Defualt_Data";
	public final String NAME_Defualt_Data = "初期値";
	public final String COMMENT_Defualt_Data = "テーブル項目の初期値";
	/** Not Null必須判定 */
	public final String COLUMNNAME_Is_Null = "Is_Null";
	public final String NAME_Is_Null = "Not Null必須判定";
	public final String COMMENT_Is_Null = "テーブル項目がNotNull必須の時にYESとする。";
	public final String IS_NULL_Yes = "Y";
	public final String IS_NULL_No = "N";
	/** プライマリーKey判定 */
	public final String COLUMNNAME_Is_Primary = "Is_Primary";
	public final String NAME_Is_Primary = "プライマリーKey判定";
	public final String COMMENT_Is_Primary = "テーブル項目がPrimary　Keyの時にYESとする。";
	public final String IS_PRIMARY_Yes = "Y";
	public final String IS_PRIMARY_No = "N";
	/** 参照情報ID */
	public final String COLUMNNAME_FD_Reference_ID = I_FD_Reference.Table_Name + "_ID";
	public final String NAME_FD_Reference_ID = "参照用リファレンス情報ID";
	public final String COMMENT_FD_Reference_ID = "項目が使用するリスト等の管理をする為のリファレンス情報ID";
}
