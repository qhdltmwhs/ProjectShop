package com.itwill.shop.cart;

import java.util.List;

public class CartService {
	
	private CartDao cartDao;
	
	public CartService() {
		cartDao = new CartDao();
	}
	
	/*
	 * 카트에 담기 (추가 or 수정)
	 */
	public int addCart(String sUserId, int p_no, int cart_qty) throws Exception {
		boolean existProduct = cartDao.isExistProduct(sUserId, p_no);
		if(existProduct) {
			return cartDao.update(sUserId, p_no, cart_qty);
		}else {
			return cartDao.add(sUserId, p_no, cart_qty);
		}
	}
	
	/*
	 * 로그인한 유저 카트목록보기
	 */
	public List<Cart> cartList(String sUserId) throws Exception {
		return cartDao.cartList(sUserId);
	}
	
	/*
	 * 로그인한 유저 카드 아이템 1개 삭제 (By cart_no)
	 */
	public int deleteCartByNo(int cart_no) throws Exception {
		return cartDao.deleteCartByNo(cart_no);
	}
	
	/*
	 * 로그인한 유저 카드 비우기 (By userId)
	 */
	public int emptyCartByUserId(String sUserId) throws Exception {
		return cartDao.deleteCart(sUserId);
	}
		
}
