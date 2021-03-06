package com.cjean.daliytest.IO;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

class Rectangle implements Serializable {

	/**
	 * 先解释下Java中的对象序列化 在讨论transient之前，有必要先搞清楚Java中序列化的含义；
	 * 
	 * Java中对象的序列化指的是将对象转换成以字节序列的形式来表示，这些字节序列包含了对象的数据和信息，
	 * 一个序列化后的对象可以被写到数据库或文件中，也可用于网络传输，
	 * 一般当我们使用缓存cache（内存空间不够有可能会本地存储到硬盘）或远程调用rpc（网络传输）的时候，
	 * 经常需要让我们的实体类实现Serializable接口，目的就是为了让其可序列化。
	 * 
	 * 当然，序列化后的最终目的是为了反序列化，恢复成原先的Java对象，要不然序列化后干嘛呢，所以序列化后的字节序列都是可以恢复成Java对象的，
	 * 这个过程就是反序列化。
	 * 
	 * 关于transient关键字
	 * Java中transient关键字的作用，简单地说，就是让某些被修饰的成员属性变量不被序列化，这一看好像很好理解，就是不被序列化，
	 * 那么什么情况下，一个对象的某些字段不需要被序列化呢？如果有如下情况，可以考虑使用关键字transient修饰：
	 * 
	 * 1、类中的字段值可以根据其它字段推导出来，如一个长方形类有三个属性：长度、宽度、面积（示例而已，一般不会这样设计），那么在序列化的时候，
	 * 面积这个属性就没必要被序列化了；
	 * 
	 * 2、其它，看具体业务需求吧，哪些字段不想被序列化；
	 * 
	 * PS，记得之前看HashMap源码的时候，发现有个字段是用transient修饰的，我觉得还是有道理的，确实没必要对这个modCount字段进行序列化，
	 * 因为没有意义，modCount主要用于判断HashMap是否被修改（像put、remove操作的时候，modCount都会自增），对于这种变量，一开始可以为任何值，
	 * 0当然也是可以（new出来、反序列化出来、或者克隆clone出来的时候都是为0的），没必要持久化其值。
	 */
	private static final long serialVersionUID = 1710022455003682613L;
	private Integer width;
	private Integer height;
	private transient Integer area;

	public Rectangle(Integer width, Integer height) {
		this.width = width;
		this.height = height;
		this.area = width * height;
	}

	public void setArea() {
		this.area = this.width * this.height;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer(40);
		sb.append("width : ");
		sb.append(this.width);
		sb.append("\nheight : ");
		sb.append(this.height);
		sb.append("\narea : ");
		sb.append(this.area);
		return sb.toString();
	}
}

public class MyTransient {
	public static void main(String args[]) throws Exception {
		Rectangle rectangle = new Rectangle(3, 4);
		System.out.println("1.原始对象\n" + rectangle);
		ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream("rectangle"));
		// 往流写入对象
		o.writeObject(rectangle);
		o.close();

		// 从流读取对象
		ObjectInputStream in = new ObjectInputStream(new FileInputStream("rectangle"));
		Rectangle rectangle1 = (Rectangle) in.readObject();
		System.out.println("2.反序列化后的对象\n" + rectangle1);
		rectangle1.setArea();
		System.out.println("3.恢复成原始对象\n" + rectangle1);
		in.close();
	}
}
