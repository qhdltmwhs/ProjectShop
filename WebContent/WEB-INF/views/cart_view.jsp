<%@page import="java.text.DecimalFormat"%>
<%@page import="com.itwill.shop.cart.Cart"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	List<Cart> cartList = (List<Cart>) request.getAttribute("cartList");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>쇼핑몰 관리</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel=stylesheet href="css/styles.css" type="text/css">
<link rel=stylesheet href="css/shop.css" type="text/css">
<style type="text/css" media="screen">
</style>
<script type="text/javascript">
	function cart_delete(){
		document.delete_cart_form.method='POST';
		document.delete_cart_form.action='cart_delete_action.do';
		document.delete_cart_form.submit();
	}
	function view_cart_form_submit(){
		document.view_cart_form.method='POST';
		document.view_cart_form.action='jumun_create_form.do';
		document.view_cart_form.submit();
	}
</script>
</head>
<body bgcolor=#FFFFFF text=#000000 leftmargin=0 topmargin=0
	marginwidth=0 marginheight=0>
	<form name="delete_cart_form"></form>
	<form name="view_cart_form">
		<input type="hidden" name="buyType" value="cart">
	</form>
	
	<!-- container start-->
	
	<div id="container">
		<!-- header start -->
		<div id="header">
			<!-- include_common_top.jsp start-->
			<jsp:include page="include_common_top.jsp"/>
			<!-- include_common_top.jsp end-->
		</div>
		<!-- header end -->
		<!-- navigation start-->
		<div id="navigation">
			<!-- include_common_left.jsp start-->
			<jsp:include page="include_common_left.jsp"/>
			<!-- include_common_left.jsp end-->
		</div>
		<!-- navigation end-->
		<!-- wrapper start -->
		<div id="wrapper">
			<!-- content start -->

			<!-- include_content.jsp start-->
			<div id="content">
				<table border=0 cellpadding=0 cellspacing=0>
					<tr>
						<td><br />
							<table style="padding-left: 10px" border=0 cellpadding=0
								cellspacing=0>
								<tr>
									<td bgcolor="f4f4f4" height="22">&nbsp;&nbsp;<b>쇼핑몰 -
											장바구니 보기</b></td>
								</tr>
							</table> 
							<!--form-->
							<!-- 
							<form name="f" method="post">
							 -->
							<div id='f'>
							<table  align=center border=1 width=80% cellspacing=0
									bordercolordark=#FFFFFF bordercolorlight=#4682b4>
									<tr>
										<td width=40 height=25 bgcolor=#4682b4 align=center class=t1><font
											color=#FFFFFF>이미지</font></td>
										<td width=250 height=25 bgcolor=#4682b4 align=center class=t1><font
											color=#FFFFFF>강아지 이름</font></td>
										<td width=112 height=25 bgcolor=#4682b4 align=center class=t1><font
											color=#FFFFFF>수 량</font></td>
										<td width=166 height=25 bgcolor=#4682b4 align=center class=t1><font
											color=#FFFFFF>가 격</font></td>
										<td width=50 height=25 bgcolor=#4682b4 align=center class=t1><font
											color=#FFFFFF>비 고</font></td>
									</tr>
									
									<%
									int tot = 0;
									for(Cart cart : cartList) { 
										tot += cart.getCart_qty() * cart.getProduct().getP_price();
									%>
									<!-- cart item start -->
									<tr>
										<td width=40 height=26 align=center class=t1><img src='image/<%=cart.getProduct().getP_image() %>' width="40" height="28"/></td>
										<td width=250 height=26 align=center class=t1><a href='product_detail.do?p_no=<%=cart.getProduct().getP_no() %>'><%=cart.getProduct().getP_name() %></a></td>
										<td width=112 height=26 align=center class=t1><%=cart.getCart_qty() %></td>
										<td width=166 height=26 align=center class=t1><%=new DecimalFormat("##,###").format(cart.getCart_qty() * cart.getProduct().getP_price()) %></td>
										<td width=50 height=26 align=center class=t1>
											<form action="cart_delete_item_action.do" method="post">
												<input type="hidden" name="cart_no" value="<%=cart.getCart_no() %>">
												<input type="submit" value="삭제">
											</form>
										</td>
									</tr>
									<!-- cart item end -->
									<%} %>
									
									<tr>
										<td width=640 colspan=5 height=26 class=t1>
											<p align=right>
												<font color=#FF0000>총 금액 : <%=new DecimalFormat("##,###").format(tot) %> 원
												</font>
											</p>
										</td>
									</tr>
								</table>
							
							</div>
							<!-- 
							</form> 
							 -->
							 
							<br />
							
							<table style="padding-left: 10px" border="0" cellpadding="0" cellspacing="1" width="590">
								<tr>
									<td align=center>&nbsp;&nbsp;
									<a href="javascript:cart_delete();" class=m1>장바구니 비우기</a>&nbsp;&nbsp;
									<a href="product_list.do" class=m1>계속 구경하기</a>&nbsp;&nbsp;
									<a href="javascript:view_cart_form_submit();" class=m1>주문하기[주문폼]</a>

									</td>
								</tr>
							</table></td>
					</tr>
				</table>
			</div>
			<!-- include_content.jsp end-->
			<!-- content end -->
		</div>
		<!--wrapper end-->
		<div id="footer">
			<!-- include_common_bottom.jsp start-->
			<jsp:include page="include_common_bottom.jsp"/>
			<!-- include_common_bottom.jsp end-->
		</div>
	</div>
	<!--container end-->
</body>
</html>