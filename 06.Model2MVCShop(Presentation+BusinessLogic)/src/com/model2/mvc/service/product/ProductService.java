package com.model2.mvc.service.product;

import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;



public interface ProductService {
	
	public void addProduct(Product product) throws Exception;							//상품추가

	public Product getProduct(int prodNo) throws Exception;									//상품정보
	
	public void updateProduct(Product product) throws Exception;						//상품변경
	
	public Map<String, Object> getProductList(Search search) throws Exception;			//상품조회
	
}