package org.yx.sumk.code.make;

import java.io.IOException;
import java.io.InputStream;

import org.yx.log.Log;
import org.yx.util.StreamUtil;

import freemarker.cache.ByteArrayTemplateLoader;
import freemarker.template.Configuration;

public class ConfigHolder {
	protected static Configuration cfg = new Configuration(Configuration.VERSION_2_3_20);
	static {
		init();
	}

	public static Configuration getConfiguration() {
		return cfg;
	}

	private static void putTemplate(ByteArrayTemplateLoader tl, String name) throws IOException {
		InputStream in = ConfigHolder.class.getClassLoader().getResourceAsStream("template/" + name);
		if (in == null) {
			Log.get("sumk.config").error("模板" + name + "找不到");
			System.exit(-1);
		}
		byte[] bs = StreamUtil.readAllBytes(in, true);
		tl.putTemplate(name, bs);
	}

	private static void init() {
		try {
			ByteArrayTemplateLoader tl = new ByteArrayTemplateLoader();
			putTemplate(tl, "abstr.ftl");
			putTemplate(tl, "daoImpl.ftl");
			putTemplate(tl, "dao.ftl");
			cfg.setTemplateLoader(tl);
			cfg.setDefaultEncoding("UTF-8");
		} catch (Exception e) {
			Log.printStack("sumk.code",e);
		}
	}
}
