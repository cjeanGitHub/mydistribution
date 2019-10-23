package com.cjean.daliytest.线程;

import java.util.concurrent.atomic.AtomicInteger;

public class CASDemo {
	//库存 票数5
	private static AtomicInteger atomicInteger = new AtomicInteger(5);

	public static void main(String[] args) {

		for(int i=0;i<5;i++) {
			new Thread(()->{
				//每个线程获取一张票
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
				System.out.println(Thread.currentThread().getName()+"获取了一张票");

				System.out.println("还剩余票数(left)："+(left));
				System.out.println("还剩余票数(tol)："+(tol));
				System.out.println("还剩余票数(tol-left)："+(tol-left));
			}).start();
		}
	}

}
