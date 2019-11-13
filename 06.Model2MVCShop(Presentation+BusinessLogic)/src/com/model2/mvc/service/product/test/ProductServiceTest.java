package com.model2.mvc.service.product.test;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;



/*
 *	FileName :  UserServiceTest.java
 * �� JUnit4 (Test Framework) �� Spring Framework ���� Test( Unit Test)
 * �� Spring �� JUnit 4�� ���� ���� Ŭ������ ���� ������ ��� ���� �׽�Ʈ �ڵ带 �ۼ� �� �� �ִ�.
 * �� @RunWith : Meta-data �� ���� wiring(����,DI) �� ��ü ����ü ����
 * �� @ContextConfiguration : Meta-data location ����
 * �� @Test : �׽�Ʈ ���� �ҽ� ����
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration	(locations = {	"classpath:config/context-common.xml",
																	"classpath:config/context-aspect.xml",
																	"classpath:config/context-mybatis.xml",
																	"classpath:config/context-transaction.xml" })
public class ProductServiceTest {

	//==>@RunWith,@ContextConfiguration �̿� Wiring, Test �� instance DI
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;

	//@Test
	public void testAddProduct() throws Exception {
		System.out.println("���� ����");
		Product product = new Product();
		
		product.setProdName("����");
		product.setFileName("����");
		product.setManuDate("20121121");
		product.setPrice(Integer.parseInt("800000"));
		product.setProdDetail("����");
	
		
		productService.addProduct(product);
		//int prodNo = 10005;
		product = productService.getProduct(Integer.parseInt("10008"));
		

		//==> console Ȯ��
		System.out.println("===========product===:::::::"+product);
		
		//==> API Ȯ��
		//Assert.assertEquals(Integer.parseInt("10010"), product.getProdNo());
		Assert.assertEquals("���±�", product.getProdName());
		Assert.assertEquals("�����ؿ�", product.getProdDetail());
		Assert.assertEquals(Integer.parseInt("13231323"), product.getPrice());
		Assert.assertEquals("20191112", product.getManuDate());
		Assert.assertEquals(null, product.getFileName());
//		Assert.assertEquals("��ϳ�¥ ���ÿ��ÿ���", product.getRegDate());
	}
	
	//@Test
	public void testGetProduct() throws Exception {
		System.out.println("���� ��������");
		Product product = new Product();
		//==> �ʿ��ϴٸ�...
//		user.setUserId("testUserId");
//		user.setUserName("testUserName");
//		user.setPassword("testPasswd");
//		user.setSsn("1111112222222");
//		user.setPhone("111-2222-3333");
//		user.setAddr("��⵵");
//		user.setEmail("test@test.com");
		
		product = productService.getProduct((Integer.parseInt("10008")));

		//==> console Ȯ��
		//System.out.println(product);
		
		//==> API Ȯ��
		//Assert.assertEquals(Integer.parseInt("10008"), product.getProdNo());
		Assert.assertEquals("���±�", product.getProdName());
		Assert.assertEquals("�����ؿ�", product.getProdDetail());
		Assert.assertEquals(Integer.parseInt("13231323"), product.getPrice());
		Assert.assertEquals("20191112", product.getManuDate());
		Assert.assertEquals(null, product.getFileName());
		//Assert.assertEquals("��ϳ�¥ ���ÿ��ÿ���", product.getRegDate());

		Assert.assertNotNull(productService.getProduct((Integer.parseInt("10008"))));
		//Assert.assertNotNull(productService.getProduct((Integer.parseInt("22222"))));
		System.out.println(product);
	}
	
	//@Test
	 public void testUpdateProduct() throws Exception{
		 System.out.println("���� ������Ʈ");
		Product product = productService.getProduct((Integer.parseInt("10008")));
		Assert.assertNotNull(product);
		
		Assert.assertEquals("���±�", product.getProdName());
		Assert.assertEquals("�����ؿ�", product.getProdDetail());
		Assert.assertEquals(Integer.parseInt("13231323"), product.getPrice());
		Assert.assertEquals("20191112", product.getManuDate());
		Assert.assertEquals(null, product.getFileName());

		product.setProdName("�ٲ�");
		product.setProdDetail("�ٲ���������");
		product.setPrice(Integer.parseInt("20000"));
		product.setManuDate("11111111");
		product.setFileName("���������������");
		productService.updateProduct(product);
		
		product =productService.getProduct((Integer.parseInt("10008")));
		Assert.assertNotNull(product);
		
		//==> console Ȯ��
		//System.out.println(user);
			
		//==> API Ȯ��
		Assert.assertEquals("�ٲ�", product.getProdName());
		Assert.assertEquals("�ٲ���������", product.getProdDetail());
		Assert.assertEquals(Integer.parseInt("20000"), product.getPrice());
		Assert.assertEquals("11111111", product.getManuDate());
		Assert.assertEquals("���������������", product.getFileName());
		System.out.println("product :: " + product);
	 }
	
	 //==>  �ּ��� Ǯ�� �����ϸ�....
	//@Test
	 public void testGetProductList() throws Exception{
		 System.out.println("���� ����Ʈ�������");
	 	Search search = new Search();
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	Map<String,Object> map = productService.getProductList(search);
	 	
	 	List<Object> list = (List<Object>)map.get("list");
	 	Assert.assertEquals(3, list.size());
	 	
		//==> console Ȯ��
	 	System.out.println(list);
	 	
	 	Integer totalCount = (Integer)map.get("totalCount");
	 	System.out.println("total  ::  " + totalCount);
	 	
	 	System.out.println("=======================================");
	 	
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	search.setSearchCondition("0");
	 	search.setSearchKeyword("");
	 	map = productService.getProductList(search);
	 	
	 	list = (List<Object>)map.get("list");
	 	Assert.assertEquals(3, list.size());
	 	
	 	//==> console Ȯ��
	 	System.out.println(list);
	 	
	 	totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 }
	 
	 //@Test
	 public void testGetProductListByProdNo() throws Exception{
		 System.out.println("���� ��ȣ�� ��������");
	 	Search search = new Search();
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	search.setSearchCondition("0");
	 	search.setSearchKeyword("10008");
	 	Map<String,Object> map = productService.getProductList(search);
	 	
	 	List<Object> list = (List<Object>)map.get("list");
	 	Assert.assertEquals(1, list.size());
	 	
		//==> console Ȯ��
	 	System.out.println(list);
	 	
	 	Integer totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 	
	 	System.out.println("=======================================");
	 	
	 	search.setSearchCondition("0");
	 	search.setSearchKeyword(""+System.currentTimeMillis());
	 	map = productService.getProductList(search);
	 	
	 	list = (List<Object>)map.get("list");
	 	Assert.assertEquals(0, list.size());
	 	
		//==> console Ȯ��
	 	System.out.println(list);
	 	
	 	totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 }
	 
	 //@Test
	 public void testGetProductListByProductName() throws Exception{
		 System.out.println("���� �̸����ΰ�������");
	 	Search search = new Search();
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	search.setSearchCondition("1");
	 	search.setSearchKeyword("���±�");
	 	Map<String,Object> map = productService.getProductList(search);
	 	
	 	List<Object> list = (List<Object>)map.get("list");
	 	Assert.assertEquals(0, list.size());
	 	
		//==> console Ȯ��
	 	System.out.println(list);
	 	
	 	Integer totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 	
	 	System.out.println("=======================================");
	 	
	 	search.setSearchCondition("1");
	 	search.setSearchKeyword(""+System.currentTimeMillis());
	 	map = productService.getProductList(search);
	 	
	 	list = (List<Object>)map.get("list");
	 	Assert.assertEquals(0, list.size());
	 	
		//==> console Ȯ��
	 	System.out.println(list);
	 	
	 	totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 }	 
	 
	 //@Test
		 public void testGetProductListByPrice() throws Exception{
			 System.out.println("���� �̸����ΰ�������");
		 	Search search = new Search();
		 	search.setCurrentPage(1);
		 	search.setPageSize(3);
		 	search.setSearchCondition("2");
		 	search.setSearchKeyword("20000");
		 	Map<String,Object> map = productService.getProductList(search);
		 	
		 	List<Object> list = (List<Object>)map.get("list");
		 	Assert.assertEquals(2, list.size());
		 	
			//==> console Ȯ��
		 	System.out.println(list);
		 	
		 	Integer totalCount = (Integer)map.get("totalCount");
		 	System.out.println(totalCount);
		 	
		 	System.out.println("=======================================");
		 	
		 	search.setSearchCondition("2"); 
		 	search.setSearchKeyword(""+System.currentTimeMillis());
		 	map = productService.getProductList(search);
		 	
		 	list = (List<Object>)map.get("list");
		 	Assert.assertEquals(0, list.size());
		 	
			//==> console Ȯ��
		 	System.out.println(list);
		 	
		 	totalCount = (Integer)map.get("totalCount");
		 	System.out.println(totalCount);
		 }	 
}