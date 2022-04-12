<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description"
	content="Responsive Bootstrap4 Shop Template, Created by Imran Hossain from https://imransdesign.com/">

<!-- title -->
<title>커피저장소 | 결제정보</title>

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
<style>
.boxed-btn {
	font-family: 'Poppins', sans-serif;
	display: inline-block;
	background-color: #F28123;
	color: #fff;
	padding: 10px 20px;
	border-radius: 50px;
	transition: 0.3s;
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
						<p>Fresh and Organic</p>
						<h1>Check Out Product</h1>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- end breadcrumb section -->

	<!-- check out section -->
	<div class="checkout-section mt-150 mb-150">
		<div class="container">
			<div class="row">
				<div class="col-lg-8">
					<div class="checkout-accordion-wrap">
						<div class="accordion" id="accordionExample">
							<div class="card single-accordion">
								<div class="card-header" id="headingOne">
									<h5 class="mb-0">
										<button class="btn btn-link" type="button"
											data-toggle="collapse" data-target="#collapseOne"
											aria-expanded="true" aria-controls="collapseOne">
											Billing Address</button>
									</h5>
								</div>

								<div id="collapseOne" class="collapse show"
									aria-labelledby="headingOne" data-parent="#accordionExample">
									<div class="card-body">
										<div class="billing-address-form">
											<form action="buy.do?">
												<p>
													<input type="text" value="${mName}">
												</p>
												<p>
													<input type="email" placeholder="Email">
												</p>
												<p>
													<input type="text" placeholder="Address">
												</p>
												<p>
													<input type="tel" placeholder="Phone">
												</p>
												<p>
													<textarea name="bill" id="bill" cols="30" rows="10"
														placeholder="Say Something"></textarea>
												</p>
											</form>
										</div>
									</div>
								</div>
							</div>

						</div>

					</div>
				</div>

				<div class="col-lg-4">
					<div class="order-details-wrap">

						<table class="order-details">
							<thead>
								<tr>
									<th>Your order Details</th>
									<th>Price</th>
								</tr>
							</thead>
							<tbody class="order-details-body">
								<tr>
									<td>Product</td>
									<td>Total</td>
								</tr>
								<c:forEach var="cart" items="${cartlist}" varStatus="st">
									<tr>
										<td>${cart.cname}</td>
										<td>${cart.subtotal}원</td>
									</tr>
								</c:forEach>
							</tbody>
							<tbody class="checkout-details">

								<tr>
									<td>합계</td>
									<td>${total}원</td>
								</tr>
								<tr>
									<td>포인트</td>
									<td>${mPoint}포인트</td>
								</tr>
								<tr>
									<td>배송비</td>
									<td>3000원</td>
								</tr>
								<tr>
									<td>Total</td>
									<td>${total+3000}원</td>
								</tr>


							</tbody>
						</table>
						<form method="post" action="pay.do" name="pay">
							<input type="hidden" name="mpoint" value="${mPoint-total-3000}">
							<input type="submit" class="boxed-btn" value="결제하기">
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- end check out section -->

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
	var submit=document.querySelectorAll(".submit");
	
		document.pay.onsubmit=function(){
			
		var mPoint=${mPoint};
		var total=${total};
		var size = ${fn:length(cartlist)};
		console.log(size);
		console.log("111"+size);
		if(size==0){
			alert('결제할 상품이 없습니다.');
			return false;
		}
		
		if(parseInt(total)>parseInt(mPoint)){
			alert('포인트가 부족합니다!');
			return false;
		}
		
    
		};

	</script>
</body>
</html>