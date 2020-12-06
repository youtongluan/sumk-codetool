package org.yx.sumk.code.make;

import java.util.List;

import org.yx.sumk.code.Infos.SimpleTableInfo;

public class ConstMaker extends AbstractMaker {

	public ConstMaker(List<Class<?>> clzs) {
		super(clzs);
	}

	@Override
	protected String outputPath() {
		return "constant";
	}

	@Override
	protected String outputFileName(SimpleTableInfo tb) {
		return tb.getClassName() + "Const.java";
	}

	@Override
	protected String ftlName() {
		return "const.ftl";
	}

}
