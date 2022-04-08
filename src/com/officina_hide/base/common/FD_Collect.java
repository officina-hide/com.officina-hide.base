package com.officina_hide.base.common;

/**
 * 収蔵要素[Collection item]<br>
 * @author officina-hide.net
 * @version 1.50 新規作成
 * @since 2022/03/23 Ver. 1.50
 */
public class FD_Collect {

	/** 項目 : 情報名[Information name] */
	private String name;
	/** 項目 : 情報値[Information data] */
	private String value;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
}
