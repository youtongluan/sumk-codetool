package org.yx.sumk.code.util;

import java.io.File;
import java.io.IOException;

public class MyFile {
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
}
