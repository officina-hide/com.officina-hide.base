package com.officina_hide.base.model;

/**
 * 処理変数情報クラス[Process variable information class]<br>
 * @author officina-hide.net
 * @version 1.00 新規作成
 * @since 2021/10/13 Ver. 1.00
 */
public class FD_Param {

	/** 変数名 */
	private String paramName;
	/** 変数値 */
	private Object paramData;
	/** 変数属性 */
	private String paramType;
	
	/**
	 * コンストラクタ[Constructor]<br>
	 * @author officina-hide.net
	 * @since 2021/10/13 Ver. 1.00
	 * @param paramName 変数名[Variable name]
	 * @param paranData 変数値[Variable data]
	 * @param typeName 属性項目名[Attribute item name]
	 */
	public FD_Param(String paramName, Object paranData, String typeName) {
		setParamName(paramName);
		setParamData(paranData);
		setParamType(typeName);
	}

	public String getParamName() {
		return paramName;
	}
	public void setParamName(String paramName) {
		this.paramName = paramName;
	}
	public Object getParamData() {
		return paramData;
	}
	public void setParamData(Object paramData) {
		this.paramData = paramData;
	}
	public String getParamType() {
		return paramType;
	}
	public void setParamType(String paramType) {
		this.paramType = paramType;
	}
}
