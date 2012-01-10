package com.vance.yim;

public class RootPathUtil {

	private static String rootPath;

	public static void setRootPath(String path) {
		if (path != null) {
			rootPath = path;
		}
	}
	
	public static String getRootPath(){
		return rootPath;
	}

}
