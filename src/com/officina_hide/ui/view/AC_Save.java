package com.officina_hide.ui.view;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.Optional;

import com.officina_hide.base.common.FD_ColumnDataCollection;
import com.officina_hide.base.common.FD_EnvData;
import com.officina_hide.base.common.FD_FIeldData;
import com.officina_hide.base.common.FD_FieldDataCollection;
import com.officina_hide.base.model.FD_DB;
import com.officina_hide.base.model.FD_Numbering;
import com.officina_hide.base.model.X_FD_Table;
import com.officina_hide.ui.model.I_FP_Album;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

/**
 * 保存処理クラス[Save process class]<br>
 * TODO パッケージについては仮保管 2022/06/17
 * @author officina-hide.net
 * @version 1.00 新規作成[New create]
 * @since 2022/06/17 Ver. 1.00
 */
public class AC_Save extends FD_DB {

	/**
	 * 保存処理[save process]<br>
	 * @author officina-hide.net
	 * @since 2022/07/04 Ver. 1.00
	 * @param env
	 * @param event
	 * @param fdc 
	 */
	public void execute(FD_EnvData env, ActionEvent event, FD_FieldDataCollection fdc) {
		//確認メッセージ
		Alert alert = new Alert(AlertType.CONFIRMATION, "保存しますか？");
		Optional<ButtonType> bt = alert.showAndWait();
		if(bt.isEmpty() == true || bt.get().getText().equals(ButtonType.CANCEL.getText())) {
			return;
		}
		//保存処理
		// TODO 新規追加のみで作成中(2022/07/07)
		//追加対象テーブル取得
		X_FD_Table table = new X_FD_Table(env, fdc.getTableId());
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO ").append(table.getFD_Table_Code()).append(" SET (");
		FD_ColumnDataCollection columns = new FD_ColumnDataCollection();
		columns.initialize(env, table.getFD_Table_ID());
		//情報ID採番
		FD_Numbering num = new FD_Numbering(env);
		columns.setValue(I_FP_Album.COLUMNNAME_FP_Album_ID, num.getNewId(table.getFD_Table_ID()));
		columns.setValue(I_FP_Album.COLUMNNAME_FD_Created, new Date());
		for(FD_FIeldData fd : fdc.getFieldDataList()) {
			TextField text = (TextField) fd.getFieldItem();
			columns.setValue(fd.getFieldData().getFX_Field_Code(), text.getText());
		}
		//新規追加処理（仮）2022/07/21
		System.out.println(columns.getInsertSQL(table.getFD_Table_Code()));
		PreparedStatement pstmt = null;
		try {
			connection(env);
			pstmt = getConn().prepareStatement(columns.getInsertSQL(table.getFD_Table_Code()));
			int rs = pstmt.executeUpdate();
			if(rs == 0) {
				System.out.println("Error!! not insert : "+pstmt.toString());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose(pstmt, null);
		}
		
		//完了メッセージ
	}

}
