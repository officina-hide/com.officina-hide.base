package com.officina_hide.base.tools;

import com.officina_hide.base.common.FD_EnvData;

/**
 * �v���W�F�N�g�̊�Տ����\�z����B<br>
 * Build basic information for the project.<br>
 * ���� : 1-�������x��<br>
 * @author officina-hide.net
 * @version 1.50 �V�K�쐬
 * @since 2022/03/19 Ver. 1.50
 */
public class CreatePackageBase {

	/** �����[Environment information] */
	private static FD_EnvData env;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//�������x������������擾����B
		env.setRunLevel(args[0]);
		
		//��Տ�񐶐�
		CreateBaseInformation cbi = new CreateBaseInformation(env);
	}

}
