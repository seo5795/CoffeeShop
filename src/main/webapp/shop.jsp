<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description"
	content="Responsive Bootstrap4 Shop Template, Created by Imran Hossain from https://imransdesign.com/">

<!-- title -->
<title>커피저장소 | 상품목록</title>

<!-- favicon -->
<link rel="shortcut icon" type="image/png" href="assets/img/favicon.png">
<!-- google font -->
<link
	href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,700"
	rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css?family=Poppins:400,700&display=swap"
	rel="stylesheet">
<!-- fontawesome -->
<link rel="stylesheet" href="assets/css/all.min.css">
<!-- bootstrap -->
<link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
<!-- owl carousel -->
<link rel="stylesheet" href="assets/css/owl.carousel.css">
<!-- magnific popup -->
<link rel="stylesheet" href="assets/css/magnific-popup.css">
<!-- animate css -->
<link rel="stylesheet" href="assets/css/animate.css">
<!-- mean menu css -->
<link rel="stylesheet" href="assets/css/meanmenu.min.css">
<!-- main style -->
<link rel="stylesheet" href="assets/css/main.css">
<!-- responsive -->
<link rel="stylesheet" href="assets/css/responsive.css">

</head>
<body>

	<!--PreLoader-->
	<div class="loader">
		<div class="loader-inner">
			<div class="circle"></div>
		</div>
	</div>
	<!--PreLoader Ends-->

	<!-- header -->
	<jsp:include page="common/nav.jsp" />
	<!-- end header -->

	<!-- search area -->
	<jsp:include page="common/search.jsp" />
	<!-- end search arewa -->

	<!-- breadcrumb-section -->
	<div class="breadcrumb-section breadcrumb-bg">
		<div class="container">
			<div class="row">
				<div class="col-lg-8 offset-lg-2 text-center">
					<div class="breadcrumb-text">
						<p>Fresh and Organic</p>
						<h1>Shop</h1>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- end breadcrumb section -->

	<!-- products -->
	<div class="product-section mt-150 mb-150">
		<div class="container">

			<div class="row">
				<div class="col-md-12">
					<div class="product-filters">


						<ul>
							<!-- 구독 리스트 공간 -->
							<h3>나의 구독보기</h3>
							<a href="shop.do"><li>All</li></a>
							<a href="shop.do?keyword=${mId}&ccategory=join"><li>구독리스트</li></a>
							<br>
							<hr>
							<br>
							<form action="shop.do" mehtod="post">
								<input type="hidden" value="cprice" name="ccategory">
								<h3>가격검색</h3>
								<input type="number" name="cprice" min="0" max="100000" required>
								~ <input type="number" name="cprice2" min="0" max="100000" required>
								<input type="submit" value="검색">
							</form>

						</ul>


					</div>
				</div>
			</div>

			<div class="row" style="justify-content: center">
				<c:choose>
					<c:when test="${list == null or fn:length(list)==0 }">
						<c:choose>
							<c:when test="${mName==null}">
								<div style="text-align: center">
									<h3>로그인 후 이용해 주세요😊😊😊</h3>
								</div>
							</c:when>
							<c:otherwise>
								<div style="text-align: center">
									<h3>찾으시는 상품이 없습니다😥😥😥</h3>
								</div>
							</c:otherwise>
						</c:choose>
					</c:when>

					<c:otherwise>
						<c:forEach var="cp" items="${list}">
							<div class="col-lg-4 col-md-6 text-center">
								<div class="single-product-item">
									<div class="product-image">
										<a href="singleProduct.do?cid=${cp.cid}"><img
											src="${cp.cpic}" alt=""></a>
									</div>
									<h3>${cp.cname}</h3>
									<p class="product-price">
										<span>Per Kg</span>${cp.cprice}
									</p>
									<a href="cart.do?cid=${cp.cid}&number=1" class="cart-btn"><i
										class="fas fa-shopping-cart"></i> Add to Cart</a>
								</div>
							</div>
						</c:forEach>
					</c:otherwise>
				</c:choose>

			</div>



			<div class="row">
				<div class="col-lg-12 text-center">
					<div class="pagination-wrap">
						<ul>
							<!-- 1. 이전 버튼 활성화 -->
							<c:if test="${pageVO.prev }">
								<li><a
									href="shop.do?pageNum=${pageVO.startPage - 1 }&amount=${pageVO.amount}&ccategory=${cvo.ccategory}&keyword=${cvo.keyword}&cprice=${cvo.cprice}&cprice2=${cvo.cprice2}">이전</a></li>
							</c:if>

							<!-- 2. 페이지번호 처리 -->
							<c:forEach var="num" begin="${pageVO.startPage }"
								end="${pageVO.endPage }">
								<li class="${pageVO.pageNum eq num ? 'active' : '' }"><a
									href="shop.do?pageNum=${num}&amount=${pageVO.amount}&ccategory=${cvo.ccategory}&keyword=${cvo.keyword}&cprice=${cvo.cprice}&cprice2=${cvo.cprice2}">${num}</a></li>
							</c:forEach>

							<!-- 3. 다음버튼 활성화 여부 -->
							<c:if test="${pageVO.next }">
								<li><a
									href="shop.do?pageNum=${pageVO.endPage + 1 }&amount=${pageVO.amount}&ccategory=${cvo.ccategory}&keyword=${cvo.keyword}&cprice=${cvo.cprice}&cprice2=${cvo.cprice2}">다음</a></li>
							</c:if>
						</ul>
					</div>
				</div>
			</div>

		</div>
	</div>
	<!-- end products -->


	<!-- footer -->
	<jsp:include page="common/footer.jsp" />
	<!-- end footer -->

	<!-- copyright -->
	<jsp:include page="common/copyright.jsp" />
	<!-- end copyright -->

	<!-- jquery -->
	<script src="assets/js/jquery-1.11.3.min.js"></script>
	<!-- bootstrap -->
	<script src="assets/bootstrap/js/bootstrap.min.js"></script>
	<!-- count down -->
	<script src="assets/js/jquery.countdown.js"></script>
	<!-- isotope -->
	<script src="assets/js/jquery.isotope-3.0.6.min.js"></script>
	<!-- waypoints -->
	<script src="assets/js/waypoints.js"></script>
	<!-- owl carousel -->
	<script src="assets/js/owl.carousel.min.js"></script>
	<!-- magnific popup -->
	<script src="assets/js/jquery.magnific-popup.min.js"></script>
	<!-- mean menu -->
	<script src="assets/js/jquery.meanmenu.min.js"></script>
	<!-- sticker js -->
	<script src="assets/js/sticker.js"></script>
	<!-- main js -->
	<script src="assets/js/main.js"></script>

</body>
</html>