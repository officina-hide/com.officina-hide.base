package com.officina_hide.base.common;

import com.officina_hide.base.model.I_FD_DB;

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
	/** 項目 : 情報種別 */
	private String type;
	
	/**
	 * 条件句用文字列生成[String generation for conditional clause]<br>
	 * @author officina-hide.net
	 * @since 2022/04/12 Ver. 1.00
	 * @return 条件句用文字列[Condition clause strings]
	 */
	public String getWhereString() {
		StringBuffer where = new StringBuffer();
		where.append(name).append(" = ");
		switch(type) {
		case I_FD_DB.FD_Item_String:
			where.append(I_FD_DB.FD_SQ).append(value).append(I_FD_DB.FD_SQ).append(" ");
			break;
		}
		return where.toString();
	}

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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
