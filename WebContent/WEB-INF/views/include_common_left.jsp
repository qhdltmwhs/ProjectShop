<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String sUserId = (String)session.getAttribute("sUserId");
%>
<script type="text/javascript">
	function login_message() {
		alert('로그인하세요');
		location.href = 'user_login_form.do';
	}
</script>
<p>
	<strong>메 뉴</strong>
</p>
<ul>
	<%if(sUserId == null){ %>
	<li><a href="user_write_form.do">회원가입</a></li>
	<li><a href="user_login_form.do">로그인</a></li>
	<%}else{ %>
	<li><a href="user_view.do"><%=sUserId %>님</a>&nbsp;<a href="user_logout_action.do">로그아웃</a></li>
	<%} %>
	<li><a href=""></a></li>
	<li><a href="shop_main.do">쇼핑몰메인</a></li>
	<%if(sUserId == null){ %>
	<li><a href="javascript:login_message();">장바구니</a></li>
	<%}else{ %>
	<li><a href="cart_view.do">장바구니</a></li>
	<li><a href="jumun_list.do">주문목록</a></li>
	<%} %>
	<li><a href="product_list.do">상품리스트</a></li>
	<li><a href=""></a></li>
	<li><a href="guest_list.do">방명록리스트</a></li>
	<li><a href="guest_write_form.do">방명록쓰기</a></li>
</ul>
