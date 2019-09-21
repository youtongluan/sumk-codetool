/**
 * Copyright (C) 2016 - 2030 youtongluan.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 		http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.yx.sumk.code.make;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.yx.db.sql.ColumnMeta;
import org.yx.db.sql.PojoMeta;
import org.yx.db.sql.PojoMetaHolder;
import org.yx.log.Log;
import org.yx.sumk.code.Infos.IdInfo;
import org.yx.sumk.code.Infos.SimpleTableInfo;
import org.yx.sumk.code.util.FileUtil;
import org.yx.sumk.code.util.StringUtils;

public abstract class AbstractMaker extends BaseMaker {
	protected List<Class<?>> clzs = new ArrayList<>();

	public AbstractMaker(List<Class<?>> clzs) {
		this.clzs = clzs;
	}

	/*
	 * 存放生成文件的文件夹，前后不需要文件分隔符
	 */
	protected abstract String outputPath();

	/*
	 * 生成的文件名
	 * 
	 */
	protected abstract String outputFileName(SimpleTableInfo tb);

	protected abstract String ftlName();

	@Override
	public void exec() {
		FileUtil.clearDir(new File(baseDir, outputPath()));// 清空文件
		for (Class<?> clz : clzs) {
			PojoMeta pm = PojoMetaHolder.getPojoMeta(clz);
			if (pm == null) {
				Log.get(this.getClass()).error("{}没有使用@Table注解，或者{}不在sumk.ioc扫描的包路径下", clz.getName(), clz.getName());
				continue;
			}
			SimpleTableInfo tb = new SimpleTableInfo();
			tb.setClassName(clz.getSimpleName());// 获取类的名字
			tb.setJavaPackage(clz.getPackage().getName());// 获取类所在的package
			ColumnMeta[] ids = pm.getPrimaryIDs();
			// if(ids==null || ids.length==0){
			// Log.get(this.getClass()).error("{}没有使用设置主键，不能自动生成",clz.getName());
			// continue;
			// }
			List<IdInfo> idInfos = new ArrayList<IdInfo>();
			for (ColumnMeta cm : ids) {
				IdInfo idinfo = new IdInfo();
				idinfo.setId(cm.getFieldName());
				idinfo.parseType(cm.field.getType());
				String newStr = StringUtils.capFirst(cm.field.getName());
				idinfo.setSetid("set" + newStr);
				idinfo.setGetid("get" + newStr);
				idInfos.add(idinfo);
			}
			tb.setIdInfos(idInfos);
			String minfirst1 = clz.getSimpleName().substring(0, 1).toLowerCase();
			String minrest1 = clz.getSimpleName().substring(1, clz.getSimpleName().length());
			String minnewStr1 = new StringBuffer(minfirst1).append(minrest1).toString();
			tb.setMinclassname(minnewStr1);
			try {
				this.output(tb);
				Log.get(this.getClass()).info(tb.getClassName() + "已经生成了");
			} catch (Exception e) {
				Log.printStack("sumk.code",e);
			}
		}
		Log.get(this.getClass()).info(this.getClass().getSimpleName() + "处理完了!");
	}

	protected void output(SimpleTableInfo tb) throws Exception {
		Map<String, Object> root = new HashMap<>();
		root.put("tb", tb);
		root.put("ClassName", tb.getClassName());
		root.put("javaPackage", tb.getPackageName());
		root.put("idInfos", tb.getIdInfos());
		int idNum = tb.getIdInfos() == null ? 0 : tb.getIdInfos().size();
		if (idNum == 1) {
			root.put("id", tb.getIdInfos().get(0).getId());
			root.put("idtype", tb.getIdInfos().get(0).getIdtype());
			root.put("rawidtype", tb.getIdInfos().get(0).getRawIdType());
			root.put("setid", tb.getIdInfos().get(0).getSetid());
			root.put("getid", tb.getIdInfos().get(0).getGetid());
		}

		root.put("classname", tb.getMinclassname());
		root.put("module", tb.getPackageName().substring(0, tb.getPackageName().lastIndexOf(".")));
		Path path = Paths.get(baseDir, this.outputPath(), this.outputFileName(tb));
		this.outPut(ftlName(), path.toFile(), root);
	}

}
