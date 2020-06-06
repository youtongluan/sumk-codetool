package org.yx.sumk.code.make;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Map;

import org.yx.conf.AppInfo;
import org.yx.log.Log;
import org.yx.sumk.code.util.FileUtil;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public abstract class BaseMaker {
	public static final String OUTPUT="sumk.code.output";
	protected Configuration cfg;
	protected File destDir;
	protected static final String baseDir = AppInfo.get(OUTPUT);

	public BaseMaker() {
		this.cfg = ConfigHolder.getConfiguration();
		this.destDir = new File(baseDir);
		FileUtil.checkDir(this.destDir);
		Log.get("sumk.code").info("代码生成的路径：{}",this.destDir);
	}

	protected void outPut(String src, File dest, Map<String, Object> root) throws IOException, TemplateException {
		Template temp = cfg.getTemplate(src);
		FileUtil.checkNewFile(dest);
		Writer out = new OutputStreamWriter(new FileOutputStream(dest), "UTF-8");
		temp.process(root, out);
		out.flush();
		out.close();

	}

	protected void print(String src, Map<String, Object> root) throws IOException, TemplateException {
		Template temp = cfg.getTemplate(src);
		Writer out = new OutputStreamWriter(System.out);
		temp.process(root, out);
		out.flush();
	}

	public abstract void exec(boolean needClear);

	/*
	 * 其实是拿到包的上一级.<br> 如clz=com.youtl.temp.model.house 就返回com.youtl.temp
	 */
	protected String getPackage(Class<?> clz) {
		String pack = clz.getPackage().getName();
		return pack.substring(0, pack.lastIndexOf("."));
	}

}
