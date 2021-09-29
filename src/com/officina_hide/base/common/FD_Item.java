package com.officina_hide.base.common;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.officina_hide.base.model.FD_DB;
import com.officina_hide.base.model.I_FD_TypeITem_Param;
import com.officina_hide.base.model.X_FD_Column;
import com.officina_hide.base.model.X_FD_TypeItem;
import com.officina_hide.base.model.X_FD_TypeItem_Param;

/**
 * 汎用項目クラス[Generic item class]<br>
 * @author officine-hide.com
 * @version 1.00
 * @since 2021/04/23
 */
public class FD_Item {

	/** 項目種別 */
	public static final String ITEM_TYPE_Integer = "integer";
	public static final String ITEM_TYPE_String = "String";
	
	/** テーブル項目情報ID */
	private long FD_Column_ID;
	/** テーブル項目情報 */
	private X_FD_Column FD_Column;
	/** 項目名[item name] */
	private String name;
	/** 項目種別[item type] */
	private String type;
	/** 項目種別情報ID */
	private long FD_ItemType_ID;
	/** 項目種別情報 */
	private X_FD_TypeItem FD_TypeItem;
	/** 項目種別設定情報 */
	private List<X_FD_TypeItem_Param> paramList;
	/** 項目サイズ[item size] */
	private int size;
	/** 項目設定値[item setting value] */
	private Object data;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public Object getData() {
		return this.data;
	}
	public long getFD_ItemType_ID() {
		return FD_ItemType_ID;
	}
	public void setFD_ItemType_ID(long fD_ItemType_ID) {
		FD_ItemType_ID = fD_ItemType_ID;
	}
	public X_FD_TypeItem getFD_TypeItem(FD_EnvData env) {
		if(FD_TypeItem == null) {
			if(getFD_ItemType_ID() == 0) {
				return null;
			}
			FD_TypeItem = new X_FD_TypeItem(env, getFD_ItemType_ID());
			paramList = getParamList(env);
		}
		return FD_TypeItem;
	}
	
	/**
	 * 属性項目設定情報一覧取得[Get attribute item setting information list]<br>
	 * @author officine-hide.net
	 * @since 1.00 2021/09/28
	 * @param env 環境情報[Environment information]
	 * @return 属性項目設定情報一覧[attribute item setting information list]
	 */
	private List<X_FD_TypeItem_Param> getParamList(FD_EnvData env) {
		List<X_FD_TypeItem_Param> list = new ArrayList<>();
		FD_DB DB = new FD_DB();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		try {
			sql.append("SELECT * FROM ").append(I_FD_TypeITem_Param.Table_Name).append(" ");
			sql.append("WHERE ").append(I_FD_TypeITem_Param.COLUMNNAME_FD_TypeItem_ID).append(" = ? ");
			DB.connection(env);
			pstmt = DB.getConn().prepareStatement(sql.toString());
			pstmt.setLong(1, getFD_ItemType_ID());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				list.add(new X_FD_TypeItem_Param(env,
						rs.getLong(I_FD_TypeITem_Param.COLUMNNAME_FD_TypeItem_Param_ID)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.DBClose(pstmt, rs);
		}
		return list;
	}
	
	/**
	 * 属性項目設定値取得[Get attribute item setting value]<br>
	 * @author officine-hide.net
	 * @since 1.00 2021/09/29
	 * @param env 環境情報[Environment information]
	 * @param paramName 属性項目設定名[Attribute item setting name]
	 * @return 属性項目設定値[Attribute item setting value]
	 */
	public String getParamValue(FD_EnvData env, String paramName) {
		if(paramList == null) {
			getFD_TypeItem(env);
		}
		for(X_FD_TypeItem_Param param : paramList) {
			if(param.getFD_TypeItemParamName().equals(paramName)) {
				return param.getFD_Value();
			}
		}
		return null;
	}
	public long getFD_Column_ID() {
		return FD_Column_ID;
	}
	public void setFD_Column_ID(long fD_Column_ID) {
		FD_Column_ID = fD_Column_ID;
	}
	public X_FD_Column getFD_Column(FD_EnvData env) {
		if(FD_Column == null) {
			FD_Column = new X_FD_Column(env, getFD_Column_ID());
		}
		return FD_Column;
	}
}
