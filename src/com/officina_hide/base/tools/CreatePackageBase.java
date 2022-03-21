package com.officina_hide.base.tools;

import com.officina_hide.base.common.FD_EnvData;

/**
 * プロジェクトの基盤情報を構築する。<br>
 * Build basic information for the project.<br>
 * 引数 : 1-処理レベル<br>
 * @author officina-hide.net
 * @version 1.50 新規作成
 * @since 2022/03/19 Ver. 1.50
 */
public class CreatePackageBase {

	/** 環境情報[Environment information] */
	private static FD_EnvData env;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//環境情報取得[Environmental information acquisition]
		env = new FD_EnvData("FD_Project.prop");
//		try {
//			//暗号Key作成
//			KeyGenerator keyGen = KeyGenerator.getInstance("AES");
//			keyGen.init(128);
//			SecretKey key = keyGen.generateKey();
//			Cipher cipher = Cipher.getInstance("AES");
//			cipher.init(Cipher.ENCRYPT_MODE, key);
//			String text = "DBPassword,fdadminqAz*01";
//			byte[] btext = text.getBytes();
//			byte[] eText = cipher.doFinal(btext);
//			System.out.println(new String(eText));
//			
//			cipher.init(Cipher.DECRYPT_MODE, key);
//			byte[] dText = cipher.doFinal(eText);
//			System.out.println(new String(dText));
//			
//		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
//			e.printStackTrace();
//		}
		
		//処理レベルを引数から取得する。
		env.setRunLevel(args[0]);
		
		//基盤情報生成
		CreateBaseInformation cbi = new CreateBaseInformation(env);
		cbi.execute();
	}

}
