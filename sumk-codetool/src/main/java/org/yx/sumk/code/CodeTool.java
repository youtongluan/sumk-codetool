package org.yx.sumk.code;

import java.util.Arrays;

import org.yx.db.sql.DBTableMaker;
import org.yx.main.SumkServer;
import org.yx.sumk.code.make.AbstrDaoMaker;
import org.yx.sumk.code.make.DaoImplMaker;
import org.yx.sumk.code.make.DaoMaker;

public class CodeTool {
	// 从数据库生成dao,参数是pojo所在的包名
	public static void generateDao(Class<?>... clzs) {
		SumkServer.start("nosoa", "nohttp");
		new AbstrDaoMaker(Arrays.asList(clzs)).exec();
		new DaoImplMaker(Arrays.asList(clzs)).exec();
		new DaoMaker(Arrays.asList(clzs)).exec();
	}

	public static void generateDBTable() {
		SumkServer.start("nosoa", "nohttp");
		new DBTableMaker().exec();
	}

}
