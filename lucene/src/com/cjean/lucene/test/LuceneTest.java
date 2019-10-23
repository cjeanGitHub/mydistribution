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
		//1.�����ִ���
		Analyzer analyzer = new StandardAnalyzer();
		//2.ָ�������������λ��
		Directory directory = FSDirectory.open(FileSystems.getDefault().getPath("D:\\indexlucene"));
		//3.��������
		//3.1 ����д�����
		IndexWriterConfig config = new IndexWriterConfig(analyzer);
		//4.д�������� ����ִ��ɾ������
		IndexWriter indexWriter = new IndexWriter(directory, config);
		long delNum = indexWriter.deleteDocuments(new Term("bookName","java"));
		System.out.println("ɾ������������"+delNum);
		indexWriter.close();
	}
	
	@Test
	public void testQueryLuceneIndex() throws IOException {
		//1.������ѯ����  ��ѯ����
		Query query = new TermQuery(new Term("bookName","java"));
		
		//2.ָ����������ļ�λ��
		Directory directory = FSDirectory.open(FileSystems.getDefault().getPath("D:\\indexlucene"));
		//3������IndexReader���� ָ�� ������ָ�� �ļ�Ŀ¼
		IndexReader indexReader = DirectoryReader.open(directory);
		//4������IndexSearcher���� ָ�� ������
		IndexSearcher searcher = new IndexSearcher(indexReader);
		//5.ִ�� ��������
		TopDocs topDocs= searcher.search(query,10);
		ScoreDoc[] scoreDocs = topDocs.scoreDocs;
		
		//6���������������
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
		System.out.println("��ʼ");
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
		
		System.out.println("����");
//		jdbcConntectUtil = new JdbcConntectUtil("111", "222", "333", "444");
//		System.out.println(jdbcConntectUtil.toString());
		
		// 1.��ȡԭʼ����
		System.out.println("bookList.size()==="+bookList.size());
		
		if(null!=bookList&&bookList.size()>0) {
			// 2.��ԭʼ���ݷŽ�document��Filed��ʽ������
			List<Document> docList = new ArrayList<Document>();
			Document doc;
			for(Book book:bookList) {
				doc = new Document();
				// ���ݲ�ͬ����  ������ͬ�� Field����
				// ����Field ���3Ҫ��     �ִ� ����  �洢  
				//id---- ���ִ�  ������  ֻ�洢
				Field bookId = new StoredField("id", book.getId());
				// �鼮����  -- �ִ� ���� �洢
				Field bookName = new TextField("bookName", book.getName(), Store.YES);
				// �鼮�۸�-- �ִ� ���� �洢
				Field bookPrice = new TextField("bookPeice",book.getPrice().toString(),Store.YES);
				//������Ƭ   -- �ִ� ���� �洢
				Field bookPic = new StoredField("pic",book.getPic());
				// �鼮 ������ �ִ�  ���� ���洢
				Field bookDesc = new TextField("bookDesc",book.getDescription(),Store.NO);
				
				doc.add(bookId);
				doc.add(bookName);
				doc.add(bookPrice);
				doc.add(bookPic);
				doc.add(bookDesc);
				
				docList.add(doc);
			}
			
			System.out.println("docList.size()==="+docList.size());
			//3. ��document����ķִ������з۴̣���ֹ �ִʲ�������������֣�
			Analyzer anal = new StandardAnalyzer();
			//4.��������Ŀ¼��  ʹ��NIO��ʽ
			Directory dir = FSDirectory.open(FileSystems.getDefault().getPath("D:\\indexlucene"));
			// ���� ��������
			IndexWriterConfig cofig = new IndexWriterConfig(anal);
			//5.������д��Ŀ¼��
			IndexWriter writ = new IndexWriter(dir, cofig);
			for(Document docu:docList) {
				writ.addDocument(docu);
			}
			writ.close();
		}
	}
	

}
