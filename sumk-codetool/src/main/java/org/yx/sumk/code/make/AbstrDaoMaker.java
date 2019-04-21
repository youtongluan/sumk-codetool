package org.yx.sumk.code.make;

import java.util.List;

import org.yx.sumk.code.Infos.SimpleTableInfo;

public class AbstrDaoMaker extends AbstractMaker {

	public AbstrDaoMaker(List<Class<?>> clzs) {
		super(clzs);
	}

	@Override
	protected String outputPath() {
		return "dao/abstr";
	}

	@Override
	protected String outputFileName(SimpleTableInfo tb) {
		return "Abstract" + tb.getClassName() + "Dao.java";
	}

	@Override
	protected String ftlName() {
		return "abstr.ftl";
	}

}
