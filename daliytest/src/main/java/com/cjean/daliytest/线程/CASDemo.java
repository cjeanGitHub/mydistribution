package com.cjean.daliytest.�߳�;

import java.util.concurrent.atomic.AtomicInteger;

public class CASDemo {
	//��� Ʊ��5
	private static AtomicInteger atomicInteger = new AtomicInteger(5);

	public static void main(String[] args) {

		for(int i=0;i<5;i++) {
			new Thread(()->{
				//ÿ���̻߳�ȡһ��Ʊ
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Integer tol = atomicInteger.get();
				Integer left = atomicInteger.decrementAndGet();
				if(left<1) {
					return;
				}
				System.out.println(Thread.currentThread().getName()+"��ȡ��һ��Ʊ");

				System.out.println("��ʣ��Ʊ��(left)��"+(left));
				System.out.println("��ʣ��Ʊ��(tol)��"+(tol));
				System.out.println("��ʣ��Ʊ��(tol-left)��"+(tol-left));
			}).start();
		}
	}

}
