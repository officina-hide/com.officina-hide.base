package com.officina_hide.base.common;

import java.util.ArrayList;
import java.util.List;

import com.officina_hide.base.model.I_FD_DB;

/**
 * インポート情報クラス[Import information class]
 * @author officina-hide.net
 * @version 1.00 新規作成
 * @since 2022/02/17 Ver. 1.00
 */
public class FD_ImportClazz implements I_FD_DB {

	/** クラスリスト */
	List<FD_ImportData> importList = new ArrayList<>();
	
	/**
	 * インポートクラス追加[Add import class]
	 * @author officina-hide.net
	 * @since 2022/02/19 Ver. 1.00
	 * @param clazzName クラス名[Class name]
	 * @param clazzURI クラスURI[Class URI]
	 */
	public void addClazz(String clazzName, String clazzURI) {
		//登録済み確認
		if(isClazzExists(clazzName)) {
			//既にクラスリストに登録されているときは勝利を抜ける。
			return;
		}
		//クラスリスト追加
		FD_ImportData id = new FD_ImportData();
		id.setClazzName(clazzName);
		id.setClazzURI(clazzURI);
		importList.add(id);
	}

	/**
	 * インポート宣言文字列生成[Import declaration string generation]
	 * @author officina-hide.net
	 * @since 2022/02/21 Ver. 1.00
	 * @return インポート宣言文字列[Import declaration string]
	 */
	public String getImportDeclaration() {
		StringBuffer importStr = new StringBuffer();
		for(FD_ImportData id : importList) {
			importStr.append("import ").append(id.getClazzURI()).append(FD_SC).append(FD_LR);
		}
		return importStr.toString();
	}

	/**
	 * クラスリスト存在確認[Class list existence confirmation]
	 * @param clazzName クラス名[Class name]
	 * @return true - 存在する、false - 存在しない
	 */
	private boolean isClazzExists(String clazzName) {
		boolean chk = false;
		for(FD_ImportData cd : importList) {
			if(cd.getClazzName().equals(clazzName)) {
				chk = true;
				break;
			}
		}
		return chk;
	}

	/**
	 * インポート構成情報[Import configuration information]
	 * @author officina-hide.net
	 * @version 1.00 新規作成
	 * @since 2022/02/17 Ver. 1.00
	 */
	private class FD_ImportData {
		/** 項目 : クラス名 */
		private String clazzName;
		/** 項目 : クラスURI */
		private String clazzURI;
		public String getClazzName() {
			return clazzName;
		}
		public void setClazzName(String clazzName) {
			this.clazzName = clazzName;
		}
		public String getClazzURI() {
			return clazzURI;
		}
		public void setClazzURI(String clazzURI) {
			this.clazzURI = clazzURI;
		}
	}
}
