package com.officina_hide.project_ad.fx;

import com.officina_hide.project_ad.model.I_FD_Project;
import com.officina_hide.ui.model.I_FX_View;

/**
 * プロジェクト情報画面インターフェースクラス[Project screen information interface class]<br>
 * @author officina-hide.net
 * @version 1.00 新規作成[New create]
 * @since 2022/04/07 Ver. 1.00
 */
public interface I_FX_Project extends I_FD_Project {

	public final String FV_View_Data = 
			I_FX_View.COLUMNNAME_FX_View_ID + ":" + "101" + ","
			+ I_FX_View.COLUMNNAME_FX_View_Name + ":" + "FX_Projext" + ","
			+ I_FX_View.COLUMNNAME_FD_Name + ":" + "画面情報";
}
