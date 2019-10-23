lucene是什么 
	* Lucene是apache下面一个开源的全文检索的引擎工具包 ,他是用java或其他语言编写的全文检索的工具包
可以简单理解为实现了全文检索的类库 .

全文检索的概念 
	全文检索就是首先要把查询的目标对象提取出来 ,构造一套索引 ,然后我们在应用的时候通过查询索引来得到目标文档
	
	1: 先建立索引  2: 查询索引得到目标对象

lucene有什么作用
	* 搜索引擎  baidu.com
	* 电商平台的站内搜索 
	
lucene怎么用 (包含哪些知识点)
	* lucene全文检索的流程
		构造索引  : 采集原始数据(文件系统/数据库/网上的数据/手工录入)
			 -> 构建lucene识别的文档对象 -> 分析文档对象(分词,分析) -> 创建索引 
			 	 
		搜索索引获取目标文档 :  用户通过搜索界面输入查询目标  -> 创建索引查询 -> 搜索器去索引库搜索 -> 渲染结果
		
	* lucene的入门程序  某网站的图书搜索功能
		1 : lucene开发环境搭建 
			官网 : https://lucene.apache.org/
			最新版本 6.6.0  本次课程版本 6.5 
			
		2 : analysis/common文件家下面的 分析器的jar包 lucene-analyzers-common-6.5.0.jar
			core文件下面的核心包  lucene-core-6.5.0.jar
			queryparse下面的查询器 	 lucene-queryparser-6.5.0.jar
			引入junit测试包和mysql驱动包
			
		3 : 将准备好的数据导入到数据库 ,并进行查询测试
		
		4: 开始构建索引
		
		5: 通过索引查询目标对象
		详见代码 LuceneTest.java
		
javaee框架/分布式 / 大数据/
			
			
		
		
			
		
		
		

