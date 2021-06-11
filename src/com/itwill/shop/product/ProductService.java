package com.itwill.shop.product;

import java.util.List;

public class ProductService {
	
	private ProductDao productDao;
	
	public ProductService() throws Exception {
		productDao = new ProductDao();
	}
	
	public Product selectByNo(int p_no) throws Exception {
		return productDao.selectByNo(p_no);
	}
	
	public List<Product> selectList() throws Exception {
		return productDao.selectList();
	}
}
