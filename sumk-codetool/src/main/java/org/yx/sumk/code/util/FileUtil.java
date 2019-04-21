package org.yx.sumk.code.util;

import java.io.File;
import java.io.IOException;

public class FileUtil {
	public static void checkNewFile(File file) throws IOException {
		if (!file.exists()) {
			if (file.getName().indexOf(".") > 0) {
				checkDir(file.getParentFile());
				file.createNewFile();
			} else {
				file.mkdirs();
			}
		} else if (file.isFile()) {
			file.delete();
			file.createNewFile();
		}
	}

	public static void checkDir(File dir) {
		if (!dir.isDirectory())
			dir.mkdirs();
	}

	/**
	 * 删除dir目录下的文件和空文件夹
	 */
	public static void clearDir(File path) {
		if (!path.exists()) {
			path.mkdir();
		}
		File[] fs = path.listFiles();
		if (null != fs) {
			for (int i = 0; i < fs.length; i++) {
				fs[i].delete();
			}
		}
	}
}
