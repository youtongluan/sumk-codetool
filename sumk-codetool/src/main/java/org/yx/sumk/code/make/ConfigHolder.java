package org.yx.sumk.code.make;

import java.io.IOException;
import java.io.InputStream;

import org.yx.log.Log;
import org.yx.util.IOUtil;

import freemarker.cache.ByteArrayTemplateLoader;
import freemarker.template.Configuration;

public class ConfigHolder {
	protected static Configuration cfg = new Configuration(Configuration.VERSION_2_3_20);
	static {
		cfg.setTemplateLoader(new ByteArrayTemplateLoader());
		cfg.setDefaultEncoding("UTF-8");
	}

	public static Configuration getConfiguration() {
		return cfg;
	}

	public static void putTemplate(String name) throws IOException {
		InputStream in = ConfigHolder.class.getClassLoader().getResourceAsStream("template/" + name);
		if (in == null) {
			Log.get("sumk.config").error("模板" + name + "找不到");
			System.exit(-1);
		}
		byte[] bs = IOUtil.readAllBytes(in, true);
		ByteArrayTemplateLoader.class.cast(cfg.getTemplateLoader()).putTemplate(name, bs);
	}
}
