package com.cjean.lucene.test;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.StoredField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.junit.Test;

import com.cjean.lucene.dao.entity.Book;
import com.cjean.lucene.util.JdbcConntectUtil;

public class LuceneTest {
	
	private static JdbcConntectUtil jdbcConntectUtil;
	
	@Test
	public void testDelTheIndex() throws IOException {
		//1.创建分词器
		Analyzer analyzer = new StandardAnalyzer();
		//2.指定读入的索引库位置
		Directory directory = FSDirectory.open(FileSystems.getDefault().getPath("D:\\indexlucene"));
		//3.度索引库
		//3.1 配置写入操作
		IndexWriterConfig config = new IndexWriterConfig(analyzer);
		//4.写入索引库 ，并执行删除操作
		IndexWriter indexWriter = new IndexWriter(directory, config);
		long delNum = indexWriter.deleteDocuments(new Term("bookName","java"));
		System.out.println("删除索引数量："+delNum);
		indexWriter.close();
	}
	
	@Test
	public void testQueryLuceneIndex() throws IOException {
		//1.创建查询条件  查询对象
		Query query = new TermQuery(new Term("bookName","java"));
		
		//2.指定索引库的文件位置
		Directory directory = FSDirectory.open(FileSystems.getDefault().getPath("D:\\indexlucene"));
		//3、创建IndexReader对象 指定 读对象并指定 文件目录
		IndexReader indexReader = DirectoryReader.open(directory);
		//4、创建IndexSearcher对象 指定 读对象
		IndexSearcher searcher = new IndexSearcher(indexReader);
		//5.执行 搜索条件
		TopDocs topDocs= searcher.search(query,10);
		ScoreDoc[] scoreDocs = topDocs.scoreDocs;
		
		//6。返回搜索结果集
		for(ScoreDoc scoreDoc:scoreDocs) {
			int docID = scoreDoc.doc;
			Document doc = searcher.doc(docID);
			System.out.println("=========");
			System.out.println(""+doc.get("id"));
			System.out.println(""+doc.get("bookName"));
			System.out.println(""+doc.get("bookPeice"));
			System.out.println(""+doc.get("pic"));
			System.out.println(""+doc.get("bookDesc"));
		}
		
	//	indexReader.close();
	}
	
	@Test
	public void testCreateLuceneIndex() throws IOException {
		System.out.println("开始");
		jdbcConntectUtil = new JdbcConntectUtil();
		System.out.println(jdbcConntectUtil.toString());
		
		String searchSql = "select * from book;";
		List<Book> bookList = new ArrayList<Book>();
		try {
			ResultSet rs= jdbcConntectUtil.getSeacherData(searchSql);
			while(rs.next()) {
//				System.out.println(rs.getInt("id"));
//				System.out.println(rs.getString("name"));
//				System.out.println(rs.getFloat("price"));
//				System.out.println(rs.getString("pic"));
//				System.out.println(rs.getString("description"));
				Book book = new Book();
				book.setId(rs.getInt("id"));
				book.setName(rs.getString("name"));
				book.setPrice(rs.getFloat("price"));
				book.setPic(rs.getString("pic"));
				book.setDescription(rs.getString("description"));
				bookList.add(book);
			}
			jdbcConntectUtil.closeConnect();
			rs.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("结束");
//		jdbcConntectUtil = new JdbcConntectUtil("111", "222", "333", "444");
//		System.out.println(jdbcConntectUtil.toString());
		
		// 1.获取原始数据
		System.out.println("bookList.size()==="+bookList.size());
		
		if(null!=bookList&&bookList.size()>0) {
			// 2.将原始数据放进document以Filed形式对象中
			List<Document> docList = new ArrayList<Document>();
			Document doc;
			for(Book book:bookList) {
				doc = new Document();
				// 根据不同需求  创建不同的 Field对象
				// 关于Field 域的3要素     分词 索引  存储  
				//id---- 不分词  不索引  只存储
				Field bookId = new StoredField("id", book.getId());
				// 书籍名称  -- 分词 索引 存储
				Field bookName = new TextField("bookName", book.getName(), Store.YES);
				// 书籍价格-- 分词 索引 存储
				Field bookPrice = new TextField("bookPeice",book.getPrice().toString(),Store.YES);
				//数据照片   -- 分词 索引 存储
				Field bookPic = new StoredField("pic",book.getPic());
				// 书籍 描述， 分词  索引 不存储
				Field bookDesc = new TextField("bookDesc",book.getDescription(),Store.NO);
				
				doc.add(bookId);
				doc.add(bookName);
				doc.add(bookPrice);
				doc.add(bookPic);
				doc.add(bookDesc);
				
				docList.add(doc);
			}
			
			System.out.println("docList.size()==="+docList.size());
			//3. 对document对象的分词器进行粉刺（防止 分词不索引的情况出现）
			Analyzer anal = new StandardAnalyzer();
			//4.创建索引目录，  使用NIO形式
			Directory dir = FSDirectory.open(FileSystems.getDefault().getPath("D:\\indexlucene"));
			// 创建 索引配置
			IndexWriterConfig cofig = new IndexWriterConfig(anal);
			//5.将索引写进目录中
			IndexWriter writ = new IndexWriter(dir, cofig);
			for(Document docu:docList) {
				writ.addDocument(docu);
			}
			writ.close();
		}
	}
	

}
