package com.officina_hide.base.common;

import java.util.ArrayList;
import java.util.List;

/**
 * インポート情報クラス[Import information class]
 * @author officina-hide.net
 * @version 1.00 新規作成
 * @since 2022/02/17 Ver. 1.00
 */
public class FD_ImportClazz {

	/** クラスリスト */
	List<FD_ImportData> importList = new ArrayList<>();
	
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
