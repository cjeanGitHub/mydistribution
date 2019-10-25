package com.cjean.daliytest.IO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *  从磁盘读取一个文件到内存中，再打印到控制台
 * @author chu_c
 *
 */
public class Test_04 {
	
	/**
	 * 读文件
	 * @param file
	 */
	public void readFile(File file) {
		if(file.isFile()) {
			try {
				FileInputStream fileInputStream = new FileInputStream(file);
				int length = (int)file.length();
				byte[] b = new byte[length];
				int read = fileInputStream.read(b);
				int len = 0;
				while() {
					
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}

	public static void main(String[] args) {

	}

}
