package com.itwill.shop.jumun;

import java.util.ArrayList;
import java.util.List;

import com.itwill.shop.cart.Cart;
import com.itwill.shop.cart.CartDao;
import com.itwill.shop.product.Product;
import com.itwill.shop.product.ProductDao;
import com.itwill.shop.user.User;
import com.itwill.shop.user.UserDao;

public class JumunService {
	
	private JumunDao jumunDao;
	private CartDao cartDao;
	private ProductDao productDao;
	private UserDao userDao;
	
	public JumunService() {
		jumunDao = new JumunDao();
		cartDao = new CartDao();
		productDao = new ProductDao();
		userDao = new UserDao();
	}
	
	/*
	 * Cart에서 주문
	 */
	public int createJumun(String sUserId) throws Exception {
		List<Cart> cartList = cartDao.cartList(sUserId);
		List<JumunDetail> jumunDetailList = new ArrayList<JumunDetail>();
		User user = cartList.get(0).getUser();
		int j_price = 0;
		for (Cart cart : cartList) {
			//int jd_no, int jd_qty, int j_no, Product product
			jumunDetailList.add(new JumunDetail(0, cart.getCart_qty(), 0, cart.getProduct()));
			j_price += cart.getCart_qty() * cart.getProduct().getP_price();
		}
		
		String j_desc = jumunDetailList.get(0).getProduct().getP_name() + "외 "+ jumunDetailList.size() +"종";
		Jumun jumun = new Jumun(0, j_desc, null, j_price, user, jumunDetailList);
		jumunDao.insert(jumun);
		cartDao.deleteCart(sUserId);
		return 0;
	}
	
	/*
	 * 상품에서 직접주문
	 */
	public int createJumun(String sUserId, int p_no, int jd_qty) throws Exception {
		Product product = productDao.selectByNo(p_no);
		User user = userDao.selectUserById(sUserId);
		
		List<JumunDetail> jumunDetailList = new ArrayList<JumunDetail>();
		jumunDetailList.add(new JumunDetail(0, jd_qty, 0, product));
		String j_desc = jumunDetailList.get(0).getProduct().getP_name() +"외 0종";
		int j_price = jumunDetailList.get(0).getJd_qty() * jumunDetailList.get(0).getProduct().getP_price();
		Jumun jumun = new Jumun(0, j_desc, null, j_price, user, jumunDetailList);
		
		jumunDao.insert(jumun);
		return 0;
	}
	
	/*
	 * 주문전체리스트 (특정사용자)
	 */
	public List<Jumun> jumunList(String sUserId) throws Exception {
		return jumunDao.selectAllByUserId(sUserId);
	}
	
	/*
	 * 주문한개의 주문상세보기 (특정사용자)
	 */
	public Jumun jumunDetail(String sUserId, int j_no) throws Exception {
		return jumunDao.selectOneByUserIdNj_no(sUserId, j_no);
	}
	
	/*
	 * 주문한개삭제 (특정사용자, 해당주문번호)
	 */
	public int deleteJumun(int j_no) throws Exception {
		return jumunDao.deleteByj_no(j_no);
	}
}
