package com.officina_hide.project.model;

/**
 * プロジェクト情報インターフェース<br>
 * @author officine-hide.com
 * @version 1.20
 * @since 2020/11/06
 */
public interface I_FD_Project {

	/** プロジェクト情報 : テーブル名 */
	public final String Table_Name = "FD_Project";
	/** プロジェクト情報 : テーブル表示名 */
	public final String NAME = "プロジェクト情報";
	/** プロジェクト情報 : テーブル説明 */
	public final String COMMENT = "プロジェクトを管理する為の情報";
	/** プロジェクト情報 : テーブル情報ID */
	public final int TABLE_ID = 301;
	
	/** プロジェクト情報ID */
	public final String COLUMNNAME_FD_Project_ID = Table_Name + "_ID";
	public final String NAME_FD_Project_ID = "プロジェクト情報ID";
	public final String COMMENT_FD_Project_ID = "プロジェクト情報を識別する為の情報ID";
	/** プロジェクト名 */
	public final String COLUMNNAME_Project_Name = "Project_Name";
	public final String NAME_Project_Name = "プロジェクト名";
	public final String COMMENT_Project_Name = "プロジェクトの識別名";
	/** プロジェクト表示名 */
	public final String COLUMNNAME_FD_Name = "FD_Name";
	public final String NAME_FD_Name = "プロジェクト表示名";
	public final String COMMENT_FD_Name = "プロジェクトの論理名称";
}
