package com.model2.mvc.service.product.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductDao;
import com.model2.mvc.service.product.ProductService;

@Service("productServiceImpl")
public class ProductServiceImpl implements ProductService {

	//필드
	@Autowired
	@Qualifier("productDaoImpl")
	private ProductDao productDao;
	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	public ProductServiceImpl() {
		System.out.println("=====프로덕트 서비스 임플 디폴트 생성자=====");
	}

	@Override
	public void addProduct(Product product) throws Exception {
		 productDao.addProduct(product);
		 System.out.println("에드 프로덕트");
		
	}

	@Override
	public Product getProduct(int prodNo) throws Exception {
		System.out.println("겟프로덕트");
		return productDao.getProduct(prodNo);
	}

	@Override
	public void updateProduct(Product product) throws Exception {
		productDao.updateProduct(product);
		System.out.println("업데이트 프로덕트");
		
	}

	@Override
	public Map<String, Object> getProductList(Search search) throws Exception {
		List<Product> list = productDao.getProductList(search);
		int totalCount = productDao.getTotalCount(search);
		
		Map<String , Object> map = new HashMap<String,Object>();
		map.put("list",list);
		map.put("totalCount", new Integer(totalCount));
		System.out.println("겟프로덕트 리스트@@");
		return map;
	}
	
	
}