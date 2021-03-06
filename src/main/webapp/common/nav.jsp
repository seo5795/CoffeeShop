<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="top-header-area" id="sticker">
		<div class="container">
			<div class="row">
				<div class="col-lg-12 col-sm-12 text-center">
					<div class="main-menu-wrap">
						<!-- logo -->
						<div class="site-logo">
							<a href="main.do">
								<img src="assets/img/logo.png" alt="">
							</a>
						</div>
						<!-- logo -->

						<!-- menu start -->
						<nav class="main-menu">
							<ul>
								
								
								<li><a href="shop.do">Coffee</a>
									<ul class="sub-menu">
										<li><a href="shop.do">Shop</a></li>
										<c:choose>
											<c:when test="${mName!=null}">
											<!-- 로그인 했을 시 결제 버튼 생성 -->
										<li><a href="checkout.do">결제</a></li>
											</c:when>
										</c:choose>
												
										<c:choose>
											<c:when test="${mRank==1}">
											<!-- session에서 설정한 사용자 권한 -->
										<li><a href="insertCoffee.jsp">상품등록</a></li>
											</c:when>
										</c:choose>
										
										
									</ul>
								</li>
								<!-- 로그인 태그 -->
								<tag:login />
								<li>
									<div class="header-icons">
										<a class="shopping-cart" href="cart.do"><i class="fas fa-shopping-cart"></i></a>
										<a class="mobile-hide search-bar-icon" href="#"><i class="fas fa-search"></i></a>
									</div>
								</li>
							</ul>
						</nav>
						<a class="mobile-show search-bar-icon" href="#"><i class="fas fa-search"></i></a>
						<div class="mobile-menu"></div>
						<!-- menu end -->
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>