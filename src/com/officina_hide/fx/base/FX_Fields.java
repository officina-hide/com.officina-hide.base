package com.officina_hide.fx.base;

import java.util.ArrayList;
import java.util.List;

/**
 * 画面項目一覧[Screen item list]<br>
 * @author officina-hide.net
 * @version 1.00 新規作成
 * @since 2021/11/25 Ver. 1.00
 */
public class FX_Fields {

	private List<FX_FieldItem> fields;

	public List<FX_FieldItem> getFields() {
		if(fields == null) {
			fields = new ArrayList<>();
		}
		return fields;
	}

}
