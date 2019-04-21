package org.yx.sumk.code.make;

import java.util.List;

import org.yx.sumk.code.Infos.SimpleTableInfo;

public class DaoMaker extends AbstractMaker {

	public DaoMaker(List<Class<?>> clzs) {
		super(clzs);
	}

	@Override
	protected String outputPath() {
		return "dao";
	}

	@Override
	protected String outputFileName(SimpleTableInfo tb) {
		return tb.getClassName() + "Dao.java";
	}

	@Override
	protected String ftlName() {
		return "dao.ftl";
	}

}
