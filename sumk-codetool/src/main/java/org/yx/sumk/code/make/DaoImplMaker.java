package org.yx.sumk.code.make;

import java.util.List;

import org.yx.sumk.code.Infos.SimpleTableInfo;

public class DaoImplMaker extends AbstractMaker{

	public DaoImplMaker(List<Class<?>> clzs) {
		super(clzs);
	}

	@Override
	protected String outputPath() {
		return "dao/impl";
	}

	@Override
	protected String outputFileName(SimpleTableInfo tb) {
		return tb.getClassName() + "DaoImpl.java";
	}

	@Override
	protected String ftlName() {
		return "daoImpl.ftl";
	}
	
}
