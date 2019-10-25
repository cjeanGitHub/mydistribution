package com.cjean.daliytest.IO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *  �Ӵ��̶�ȡһ���ļ����ڴ��У��ٴ�ӡ������̨
 * @author chu_c
 *
 */
public class Test_04 {
	
	/**
	 * ���ļ�
	 * @param file
	 */
	public void readFile(File file) {
		if(file.isFile()) {
			try(FileInputStream fis = new FileInputStream(file);) {
				int length = (int)file.length();
				byte[] b = new byte[length];
				int len = 0;
				StringBuffer sbf = new StringBuffer();
				
				System.out.println(file.getParent()+"\\copyFile.txt");
				File copyFile = new File(file.getParent()+"\\copyFile.txt");
				FileOutputStream fos = new FileOutputStream(copyFile);
				while( (len = fis.read(b)) != -1) {
					//���ļ�����д���ڴ���
					sbf.append(new String(b,0,len));
					//���ڴ��е��ļ�����д�� io�ļ���
					fos.write(b, 0, len);
					fos.flush();
				}
				fos.close();
				//���ڴ��е��ļ�����д������̨��
				System.out.println(sbf.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}

	public static void main(String[] args) {
		Test_04 test_04 = new Test_04();
		File file = new File("D:\\workspaces\\distribution\\daliytest\\src\\main\\java\\com\\cjean\\daliytest\\IO\\Test_04.java");
		System.out.println("��ʼ");
		test_04.readFile(file);
		System.out.println("����");
	}

}
