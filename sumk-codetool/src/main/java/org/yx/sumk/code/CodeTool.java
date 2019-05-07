package org.yx.sumk.code;

import java.util.Arrays;

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
	public static void generateDao(Class<?>... clzs) {
		SumkServer.start("nosoa", "nohttp");
		String output=AppInfo.get(BaseMaker.OUTPUT,null);
		if(output==null){
			SumkException.throwException("请在app.properties中设置"+BaseMaker.OUTPUT+"参数，这个是生成文件的存放目录。");
		}
		new AbstrDaoMaker(Arrays.asList(clzs)).exec();
		new DaoImplMaker(Arrays.asList(clzs)).exec();
		new DaoMaker(Arrays.asList(clzs)).exec();
	}

	public static void generateDBTable() {
		SumkServer.start("nosoa", "nohttp");
		new DBTableMaker().exec();
	}

}
