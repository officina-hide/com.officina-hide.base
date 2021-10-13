package com.officina_hide.fx.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.model.FD_DB;
import com.officina_hide.base.model.I_FD_ProcessParam;
import com.officina_hide.base.model.X_FD_ProcessParam;

/**
 * 処理変数情報一覧クラス[Process variable information list class]<br>
 * @author officina-hide.net
 * @version 1.00 新規作成
 * @since 2021/10/12 Ver. 1.00
 */
public class FD_Params extends FD_DB {

	/** 変数一覧 */
	List<FD_Param> params;
	
	/** 環境情報[Environment information] */
	private FD_EnvData env;
	
	/**
	 * コンストラクタ[Constructor]<br>
	 * @author officina-hide.net
	 * @since 2021/10/12 Ver. 1.00
	 * @param env 環境情報[Environment information]
	 * @param l 
	 */
	public FD_Params(FD_EnvData env, long processId) {
		this.env = env;
		//変数リスト初期化
		createParams(processId);
	}

	/**
	 * 変数リスト初期化[Variable list initialize]<br>
	 * @author officina-hide.net
	 * @since 2021/10/13 Ver. 1.00
	 * @param processId 処理情報ID[Process information ID]
	 */
	private void createParams(long processId) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		params = new ArrayList<>();
		try {
			sql.append("SELECT * FROM ").append(I_FD_ProcessParam.Table_Name).append(" ");
			sql.append("WHERE ").append(I_FD_ProcessParam.COLUMNNAME_FD_Process_ID).append(" = ? ");
			connection(env);
			pstmt = getConn().prepareStatement(sql.toString());
			pstmt.setLong(1, processId);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				X_FD_ProcessParam pp = new X_FD_ProcessParam(env,
						rs.getLong(I_FD_ProcessParam.COLUMNNAME_FD_ProcessParam_ID));
				params.add(new FD_Param(pp.getFD_ProcessParam_Name(), null, pp.getFD_TypeItem(env).getFD_TypeItem_Name()));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose(pstmt, rs);
		}
	}

	/**
	 * 処理変数セット[Process variable setting]<br>
	 * @author officina-hide.net
	 * @since 2021/10/13 Ver. 1.00
	 * @param paramName 変数名[Variable name]
	 * @param paramData 変数値[Variable data]
	 */
	public void setValue(String paramName, Object paramData) {
		for(FD_Param param : params) {
			if(param.getParamName().equals(paramName)) {
				param.setParamData(paramData);
				return;
			}
		}
		//エラー
		// TODO Log化する。(2021/10/13)
		System.out.println("Param not found!! : "+paramName);
	}

	/**
	 * オブジェクト型の変数値を返す。<br>
	 * Returns a variable value of object type.<br>
	 * @author officina-hide.net
	 * @since 2021/10/13 Ver. 1.00
	 * @param paramName 変数名[Variable name]
	 * @return 変数値[Variable data]
	 */
	public Object getObject(String paramName) {
		for(FD_Param param : params) {
			if(param.getParamName().equals(paramName)) {
				return param.getParamData();
			}
		}
		// TODO Log化する。(2021/10/13)
		System.out.println("Param not found!! : "+paramName);
		return null;
	}

}
