package org.yx.sumk.code.Infos;

import java.io.File;
import java.io.FilenameFilter;

public class JavaFilter implements FilenameFilter {

	public boolean accept(File dir, String name) {
		return name.endsWith(".java");
	}

}
