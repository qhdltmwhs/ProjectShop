package com.itwill.shop.product.test;

import java.util.List;

import com.itwill.shop.product.Product;
import com.itwill.shop.product.ProductDao;

public class ProductDaoTestMain {

	public static void main(String[] args) throws Exception {
		ProductDao productDao = new ProductDao();
		
		System.out.println("#######[ Select ]#######");
		Product findProduct1 = productDao.selectByNo(1);
		Product findProduct2 = productDao.selectByNo(2);
		System.out.println(findProduct1);
		System.out.println(findProduct2);
		
		System.out.println("#####[ SelectMany ]#####");
		List<Product> findProductList = productDao.selectList();
		System.out.println(findProductList);
	}

}
