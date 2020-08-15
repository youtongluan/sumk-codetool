package org.yx.sumk.code;

import java.util.Arrays;

import org.yx.common.StartConstants;
import org.yx.conf.AppInfo;
import org.yx.db.sql.DBTableMaker;
import org.yx.exception.SumkException;
import org.yx.main.SumkServer;
import org.yx.sumk.code.make.AbstrDaoMaker;
import org.yx.sumk.code.make.BaseMaker;
import org.yx.sumk.code.make.DaoImplMaker;
import org.yx.sumk.code.make.DaoMaker;

public class CodeTool {
	// 从数据库生成dao,参数是pojo所在的包名
	public static void generateDao(boolean deleteBeforGenerate,Class<?>... clzs) {
		SumkServer.start("nosoa", "nohttp",StartConstants.NOSOA_ClIENT);
		String output=AppInfo.get(BaseMaker.OUTPUT,null);
		if(output==null){
			throw new SumkException(0,"请在app.properties中设置"+BaseMaker.OUTPUT+"参数，这个是生成文件的存放目录。");
		}
		new AbstrDaoMaker(Arrays.asList(clzs)).exec(deleteBeforGenerate);
		new DaoImplMaker(Arrays.asList(clzs)).exec(deleteBeforGenerate);
		new DaoMaker(Arrays.asList(clzs)).exec(deleteBeforGenerate);
	}
	
	public static void generateAbstrctDao(boolean deleteBeforGenerate,Class<?>... clzs) {
		SumkServer.start("nosoa", "nohttp",StartConstants.NOSOA_ClIENT);
		String output=AppInfo.get(BaseMaker.OUTPUT,null);
		if(output==null){
			throw new SumkException(0,"请在app.properties中设置"+BaseMaker.OUTPUT+"参数，这个是生成文件的存放目录。");
		}
		new AbstrDaoMaker(Arrays.asList(clzs)).exec(deleteBeforGenerate);
	}


	public static void generateDBTable() {
		SumkServer.start("nosoa", "nohttp",StartConstants.NOSOA_ClIENT);
		new DBTableMaker().exec();
	}

}
