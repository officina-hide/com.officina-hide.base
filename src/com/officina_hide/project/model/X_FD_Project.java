package com.officina_hide.project.model;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.model.FD_DB;

public class X_FD_Project extends FD_DB implements I_FD_Project {

	public X_FD_Project (FD_EnvData env, long id) {
		createItemList(env, Table_Name);
		if(id > 0) {
			load(env, Table_Name, id, items);
		}
	}

}
