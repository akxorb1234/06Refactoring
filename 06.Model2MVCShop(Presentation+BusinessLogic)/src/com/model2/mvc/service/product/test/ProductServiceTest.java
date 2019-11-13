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
 * ㅇ JUnit4 (Test Framework) 과 Spring Framework 통합 Test( Unit Test)
 * ㅇ Spring 은 JUnit 4를 위한 지원 클래스를 통해 스프링 기반 통합 테스트 코드를 작성 할 수 있다.
 * ㅇ @RunWith : Meta-data 를 통한 wiring(생성,DI) 할 객체 구현체 지정
 * ㅇ @ContextConfiguration : Meta-data location 지정
 * ㅇ @Test : 테스트 실행 소스 지정
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration	(locations = {	"classpath:config/context-common.xml",
																	"classpath:config/context-aspect.xml",
																	"classpath:config/context-mybatis.xml",
																	"classpath:config/context-transaction.xml" })
public class ProductServiceTest {

	//==>@RunWith,@ContextConfiguration 이용 Wiring, Test 할 instance DI
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;

	//@Test
	public void testAddProduct() throws Exception {
		System.out.println("여긴 에드");
		Product product = new Product();
		
		product.setProdName("마탴");
		product.setFileName("마탴");
		product.setManuDate("20121121");
		product.setPrice(Integer.parseInt("800000"));
		product.setProdDetail("마탬");
	
		
		productService.addProduct(product);
		//int prodNo = 10005;
		product = productService.getProduct(Integer.parseInt("10008"));
		

		//==> console 확인
		System.out.println("===========product===:::::::"+product);
		
		//==> API 확인
		//Assert.assertEquals(Integer.parseInt("10010"), product.getProdNo());
		Assert.assertEquals("마태규", product.getProdName());
		Assert.assertEquals("저렴해요", product.getProdDetail());
		Assert.assertEquals(Integer.parseInt("13231323"), product.getPrice());
		Assert.assertEquals("20191112", product.getManuDate());
		Assert.assertEquals(null, product.getFileName());
//		Assert.assertEquals("등록날짜 오늘오늘오늘", product.getRegDate());
	}
	
	//@Test
	public void testGetProduct() throws Exception {
		System.out.println("여긴 가져오기");
		Product product = new Product();
		//==> 필요하다면...
//		user.setUserId("testUserId");
//		user.setUserName("testUserName");
//		user.setPassword("testPasswd");
//		user.setSsn("1111112222222");
//		user.setPhone("111-2222-3333");
//		user.setAddr("경기도");
//		user.setEmail("test@test.com");
		
		product = productService.getProduct((Integer.parseInt("10008")));

		//==> console 확인
		//System.out.println(product);
		
		//==> API 확인
		//Assert.assertEquals(Integer.parseInt("10008"), product.getProdNo());
		Assert.assertEquals("마태규", product.getProdName());
		Assert.assertEquals("저렴해요", product.getProdDetail());
		Assert.assertEquals(Integer.parseInt("13231323"), product.getPrice());
		Assert.assertEquals("20191112", product.getManuDate());
		Assert.assertEquals(null, product.getFileName());
		//Assert.assertEquals("등록날짜 오늘오늘오늘", product.getRegDate());

		Assert.assertNotNull(productService.getProduct((Integer.parseInt("10008"))));
		//Assert.assertNotNull(productService.getProduct((Integer.parseInt("22222"))));
		System.out.println(product);
	}
	
	//@Test
	 public void testUpdateProduct() throws Exception{
		 System.out.println("여긴 업데이트");
		Product product = productService.getProduct((Integer.parseInt("10008")));
		Assert.assertNotNull(product);
		
		Assert.assertEquals("마태규", product.getProdName());
		Assert.assertEquals("저렴해요", product.getProdDetail());
		Assert.assertEquals(Integer.parseInt("13231323"), product.getPrice());
		Assert.assertEquals("20191112", product.getManuDate());
		Assert.assertEquals(null, product.getFileName());

		product.setProdName("바뀜");
		product.setProdDetail("바뀌이이이임");
		product.setPrice(Integer.parseInt("20000"));
		product.setManuDate("11111111");
		product.setFileName("삼미지삼미지삼미지");
		productService.updateProduct(product);
		
		product =productService.getProduct((Integer.parseInt("10008")));
		Assert.assertNotNull(product);
		
		//==> console 확인
		//System.out.println(user);
			
		//==> API 확인
		Assert.assertEquals("바뀜", product.getProdName());
		Assert.assertEquals("바뀌이이이임", product.getProdDetail());
		Assert.assertEquals(Integer.parseInt("20000"), product.getPrice());
		Assert.assertEquals("11111111", product.getManuDate());
		Assert.assertEquals("삼미지삼미지삼미지", product.getFileName());
		System.out.println("product :: " + product);
	 }
	
	 //==>  주석을 풀고 실행하면....
	//@Test
	 public void testGetProductList() throws Exception{
		 System.out.println("여긴 리스트가쟈오기");
	 	Search search = new Search();
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	Map<String,Object> map = productService.getProductList(search);
	 	
	 	List<Object> list = (List<Object>)map.get("list");
	 	Assert.assertEquals(3, list.size());
	 	
		//==> console 확인
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
	 	
	 	//==> console 확인
	 	System.out.println(list);
	 	
	 	totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 }
	 
	 //@Test
	 public void testGetProductListByProdNo() throws Exception{
		 System.out.println("여긴 번호로 가져오기");
	 	Search search = new Search();
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	search.setSearchCondition("0");
	 	search.setSearchKeyword("10008");
	 	Map<String,Object> map = productService.getProductList(search);
	 	
	 	List<Object> list = (List<Object>)map.get("list");
	 	Assert.assertEquals(1, list.size());
	 	
		//==> console 확인
	 	System.out.println(list);
	 	
	 	Integer totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 	
	 	System.out.println("=======================================");
	 	
	 	search.setSearchCondition("0");
	 	search.setSearchKeyword(""+System.currentTimeMillis());
	 	map = productService.getProductList(search);
	 	
	 	list = (List<Object>)map.get("list");
	 	Assert.assertEquals(0, list.size());
	 	
		//==> console 확인
	 	System.out.println(list);
	 	
	 	totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 }
	 
	 //@Test
	 public void testGetProductListByProductName() throws Exception{
		 System.out.println("여긴 이름으로가져오기");
	 	Search search = new Search();
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	search.setSearchCondition("1");
	 	search.setSearchKeyword("마태규");
	 	Map<String,Object> map = productService.getProductList(search);
	 	
	 	List<Object> list = (List<Object>)map.get("list");
	 	Assert.assertEquals(0, list.size());
	 	
		//==> console 확인
	 	System.out.println(list);
	 	
	 	Integer totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 	
	 	System.out.println("=======================================");
	 	
	 	search.setSearchCondition("1");
	 	search.setSearchKeyword(""+System.currentTimeMillis());
	 	map = productService.getProductList(search);
	 	
	 	list = (List<Object>)map.get("list");
	 	Assert.assertEquals(0, list.size());
	 	
		//==> console 확인
	 	System.out.println(list);
	 	
	 	totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 }	 
	 
	 //@Test
		 public void testGetProductListByPrice() throws Exception{
			 System.out.println("여긴 이름으로가져오기");
		 	Search search = new Search();
		 	search.setCurrentPage(1);
		 	search.setPageSize(3);
		 	search.setSearchCondition("2");
		 	search.setSearchKeyword("20000");
		 	Map<String,Object> map = productService.getProductList(search);
		 	
		 	List<Object> list = (List<Object>)map.get("list");
		 	Assert.assertEquals(2, list.size());
		 	
			//==> console 확인
		 	System.out.println(list);
		 	
		 	Integer totalCount = (Integer)map.get("totalCount");
		 	System.out.println(totalCount);
		 	
		 	System.out.println("=======================================");
		 	
		 	search.setSearchCondition("2"); 
		 	search.setSearchKeyword(""+System.currentTimeMillis());
		 	map = productService.getProductList(search);
		 	
		 	list = (List<Object>)map.get("list");
		 	Assert.assertEquals(0, list.size());
		 	
			//==> console 확인
		 	System.out.println(list);
		 	
		 	totalCount = (Integer)map.get("totalCount");
		 	System.out.println(totalCount);
		 }	 
}