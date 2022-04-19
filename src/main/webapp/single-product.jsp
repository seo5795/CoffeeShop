<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description"
	content="Responsive Bootstrap4 Shop Template, Created by Imran Hossain from https://imransdesign.com/">

<!-- title -->
<title>커피저장소 | 상세보기</title>

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

<style type="text/css">
.cart-btn {
	font-family: 'Poppins', sans-serif;
	display: inline-block;
	background-color: #F28123;
	color: #fff;
	padding: 10px 20px;
	border-radius: 50px;
	transition: 0.3s;
}

a.bordered-btn {
	color: black;
}
</style>
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
						<p>See more Details</p>
						<h1>Single Product</h1>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- end breadcrumb section -->

	<!-- single product -->
	<div class="single-product mt-150 mb-150">
		<div class="container">
			<div class="row">
				<div class="col-md-5">
					<div class="single-product-img">
						<img src="${data.cpic}" alt="상품준비중입니다">
					</div>
				</div>
				<div class="col-md-7">
					<div class="single-product-content">
						<h3>${data.cname}</h3>
						<p class="single-product-pricing">
							<span>Per Kg</span> ${data.cprice}원 <span>재고: ${data.cnum}</span>
						</p>
						<strong>${data.ccontent}</strong> <br> <br>
						<div class="single-product-form">
							<form action="cart.do?cid=${data.cid}" id="cartFrom" name="cart"
								method="post">
								<input type="number" placeholder="1" name="number" max="5"
									min="1" required>
								<button class="cart-btn" type="submit">
									<i class="fas fa-shopping-cart"></i> 장바구니
								</button>
								<!-- 로그인 한 회원들만 구독버튼 활성화 -->
								<c:if test="${mName!=null}">
									<c:if test="${sdata.mid == null}">
									<a href="subscribe.do?cid=${data.cid}&mid=${mId}"
												class="bordered-btn">구독하기</a>
									</c:if>
									<c:if test="${sdata.mid != null}">
									<a href="delsubscribe.do?cid=${data.cid}&mid=${mId}"
													class="bordered-btn">구독취소</a>
									</c:if>
										
								</c:if>

							</form>

						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- end single product -->

	<!-- more products -->
	<div class="more-products mb-150">
		<div class="container">
			<div class="row">
				<div class="col-lg-8 offset-lg-2 text-center">
					<div class="section-title">
						<h3>
							<span class="orange-text">${data.ccountry}</span> 상품들
						</h3>
						<p>고객님이 검색하신 제품과 같은 나라의 제품입니다.</p>
					</div>
				</div>
			</div>

			<div class="row">
				<!-- 나라가 같은 커피리스트 3개씩-->
				<c:forEach begin="0" end="2" var="cp" items="${datas}">
					<div class="col-lg-4 col-md-6 text-center">
						<div class="single-product-item">
							<div class="product-image">
								<a href="singleProduct.do?cid=${cp.cid}"><img src="${cp.cpic}"
									alt="상품준비중입니다."></a>
							</div>
							<h3>${cp.cname}</h3>
							<p class="product-price">
								<span>Per Kg</span> ${cp.cprice}원
							</p>
							<a href="cart.jsp" class="cart-btn"><i
								class="fas fa-shopping-cart"></i> 장바구니</a>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
	<!-- end more products -->


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

	<script type="text/javascript">
	
	document.cart.onsubmit=function(){
		var number=document.querySelector('[name="number"]').value;
		console.log(number);
		console.log(${data.cnum})
		if(number>${data.cnum}){
			alert('수량이 올바르지 않습니다.');
			return false;
		}
	}
	</script>
</body>
</html>