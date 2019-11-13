package com.model2.mvc.service.product;

import java.util.List;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;

public interface ProductDao {

	
	 //C
	public void addProduct(Product product) throws Exception;
	
	//R
	public Product getProduct(int prodNo) throws Exception;
	
	//U
	public void updateProduct(Product product) throws Exception;
	
	//리스트 불러오기
	public List<Product> getProductList(Search search) throws Exception;		//상품조회

	// 게시판 Page 처리를 위한 전체Row(totalCount)  return
	public int getTotalCount(Search search) throws Exception ;
}
