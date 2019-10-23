package com.cjean.cjeanmvc.test;

import java.io.File;

public class FIleScanTest {

	public static void main(String[] args) {
		
		ScannFile("D:\\Apowersoft\\ApowerMirror");
		
		
	}
	public static void ScannFile(String path) {
		File file = new File(path);
		for(File f:file.listFiles()) {
			if(f.isDirectory()) {
				String newPath = path+f.separator+f.getName();
				ScannFile(newPath);
			}else {
				System.out.println("文件的路径以及名称==="+path+">>:"+f.getName());
			}

		}
	}
	
}
