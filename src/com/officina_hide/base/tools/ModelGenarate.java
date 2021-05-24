package com.officina_hide.base.tools;

import com.officina_hide.base.common.FD_EnvData;

/**
 * I/Oクラス生成[I / O class generation]<br>
 * @author officina-hide.com
 * @version 1.00
 * @since 2021/05/10
 */
public class ModelGenarate {

	/** 環境情報[Environment Information]  */
	private static FD_EnvData env = new FD_EnvData();
	/** 生成用ソース種別[Source type for generation] */
	private static int generateSoueceType;
	private static final int SOURCE_XML = 1;	//XMLファイル
	
	public static void main(String[] args) {
		/* 生成用のソースをXMLとする。 */
		generateSoueceType = SOURCE_XML;
		/* モデルの保存先をセット */
		env.setGeneratePath("./src/com/officina_hide/base/model");
		
		switch(generateSoueceType) {
		case SOURCE_XML:
			ModelGenerateByXML mbx = new ModelGenerateByXML();
		}
	}

}
