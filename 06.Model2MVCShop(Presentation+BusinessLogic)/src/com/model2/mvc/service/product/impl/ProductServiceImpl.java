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

	//�ʵ�
	@Autowired
	@Qualifier("productDaoImpl")
	private ProductDao productDao;
	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	public ProductServiceImpl() {
		System.out.println("=====���δ�Ʈ ���� ���� ����Ʈ ������=====");
	}

	@Override
	public void addProduct(Product product) throws Exception {
		 productDao.addProduct(product);
		 System.out.println("���� ���δ�Ʈ");
		
	}

	@Override
	public Product getProduct(int prodNo) throws Exception {
		System.out.println("�����δ�Ʈ");
		return productDao.getProduct(prodNo);
	}

	@Override
	public void updateProduct(Product product) throws Exception {
		productDao.updateProduct(product);
		System.out.println("������Ʈ ���δ�Ʈ");
		
	}

	@Override
	public Map<String, Object> getProductList(Search search) throws Exception {
		List<Product> list = productDao.getProductList(search);
		int totalCount = productDao.getTotalCount(search);
		
		Map<String , Object> map = new HashMap<String,Object>();
		map.put("list",list);
		map.put("totalCount", new Integer(totalCount));
		System.out.println("�����δ�Ʈ ����Ʈ@@");
		return map;
	}
	
	
}