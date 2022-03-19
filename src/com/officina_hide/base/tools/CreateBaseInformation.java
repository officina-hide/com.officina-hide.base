package com.officina_hide.base.tools;

import com.officina_hide.base.common.FD_EnvData;

/**
 * ��Տ�񐶐��N���X[Infrastructure information generation class]<br>
 * @author officina-hide.net
 * @version 1.50 �V�K�쐬
 * @since 2022/03/19 Ver. 1.50
 */
public class CreateBaseInformation {

	/** ���� : �����[Environment information] */
	private FD_EnvData env = new FD_EnvData();
	
	/**
	 * �R���X�g���N�^[Constructor]
	 * @author officina-hide.net
	 * @since 2022/03/19 Ver. 1.50
	 * @param env �����[Environment information]
	 */
	public CreateBaseInformation(FD_EnvData env) {
		this.env = env;
	}

}
