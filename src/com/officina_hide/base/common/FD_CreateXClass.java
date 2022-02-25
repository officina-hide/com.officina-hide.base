package com.officina_hide.base.common;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.officina_hide.base.model.I_FD_DB;
import com.officina_hide.base.tools.FD_ClazzUtil;

/**
 * Xクラス生成[X class generation]
 * @author officina-hide.net
 * @version 1.00 新規作成
 * @since 2022/02/17 Ver. 1.00
 */
public class FD_CreateXClass implements I_FD_DB {

	/** ソース */
	private StringBuffer source = new StringBuffer();
	private StringBuffer clazz_src = new StringBuffer();
	/** インポート */
	FD_ImportClazz importClazz = new FD_ImportClazz();
	/** テーブル名 */
	private String tableName;

	/**
	 * I/Oクラス生成[I / O class generation]
	 * @author officina-hide.net
	 * @since 2022/02/25 Ver. 1.00
	 * @param env 環境情報[Environment information]
	 * @param tableName テーブル名[Table name]
	 * @param classpath クラスパス[Class path]
	 */
	public void createIO(FD_EnvData env, String tableName, String classpath) {
		String path = new File(".").getAbsolutePath()+"\\"+classpath.replaceAll("/", "\\\\");
		//パッケージ宣言
		setPackage(classpath);
		//クラス宣言
		setTableName(tableName);
		setClazz();
		try {
			File file = new File(path+"\\X_" + tableName +".java");
			FileOutputStream fo = new FileOutputStream(file);
			fo.write(getClazzSource().getBytes());
			fo.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * パッケージ宣言生成[Package declaration generation]
	 * @author officina-hide.net
	 * @since 2022/02/17 Ver. 1.00
	 * @param packageUri パッケージURI[Package URI]
	 */
	public void setPackage(String packageUri) {
		source.append("package ").append(packageUri).append(FD_SC).append(FD_LR);
		source.append(FD_LR);
	}

	/**
	 * クラス宣言生成[Class declaration generation]
	 * @author officina-hide.net
	 * @since 2022/02/17 Ver. 1.00.
	 */
	public void setClazz() {
		clazz_src.append("public class ").append("X_").append(tableName).append(" ")
			.append("extends FD_DB").append(" ")
			.append("implements ").append("I_").append(tableName).append(" {").append(FD_LR);
		clazz_src.append(FD_LR);
		
		//コンストラクター宣言
		clazz_src.append(setConstructor());
		
		clazz_src.append(FD_LR);
		clazz_src.append("}").append(FD_LR);
		
		importClazz.addClazz("FD_DB", FD_DB_ImportUri);
		
	}

	/**
	 * クラス全ソース取得[Get all source for class]
	 * @author officina-hide.net
	 * @since 2022/02/17 Ver. 1.00
	 * @return ソース文字列[Source strings]
	 */
	public String getClazzSource() {
		StringBuffer wk = new StringBuffer();
		wk.append(source).append(getImportSource()).append(FD_LR).append(clazz_src);
		return wk.toString();
	}

	/**
	 * コンストラクター宣言生成[Constructor declaration generation]
	 * @author officina-hide.net
	 * @since 2022/02/21 Ver. 1.00
	 * @return コンストラクター文字列[Constructor String]
	 */
	private String setConstructor() {
		FD_ClazzUtil cutil = new FD_ClazzUtil();
		StringBuffer conStr = new StringBuffer();
		conStr.append(cutil.tab(1)).append("public X_").append(tableName).append(" (")
			.append("FD_EnvData env, long id")
			.append(")").append(" {").append(FD_LR);
		importClazz.addClazz("FD_EnvData", "com.officina_hide.base.common.FD_EnvData");
		
		conStr.append(cutil.tab(2)).append("createItemList(env, Table_Name)").append(FD_SC).append(FD_LR);
		
		conStr.append(cutil.tab(2)).append("if(id > 0) {").append(FD_LR);
		conStr.append(cutil.tab(3)).append("load(env, Table_Name, id, items)").append(FD_SC).append(FD_LR);
		conStr.append(cutil.tab(2)).append("}").append(FD_LR);
		
		conStr.append(cutil.tab(1)).append("}").append(FD_LR);
		return conStr.toString();
	}

	/**
	 * インポート宣言文字列取得[Get import declaration string]
	 * @author officina-hide.net
	 * @since 2022/02/21 Ver. 1.00
	 * @return インポート宣言文字列[Import declaration string]
	 */
	private String getImportSource() {
		String importStr = importClazz.getImportDeclaration();
		return importStr;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	
}
