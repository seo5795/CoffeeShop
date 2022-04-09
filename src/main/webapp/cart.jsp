<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description"
	content="Responsive Bootstrap4 Shop Template, Created by Imran Hossain from https://imransdesign.com/">

<!-- title -->
<title>커피저장소 | 장바구니</title>

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
#select {
	border: 1px solid #ddd;
	border-radius: 5px;
	padding: 10px;
	width: 100px;
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
						<h1>Cart</h1>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- end breadcrumb section -->

	<!-- cart -->
	<div class="cart-section mt-150 mb-150">
		<div class="container">
			<div class="row">
				<div class="col-lg-8 col-md-12">
					<div class="cart-table-wrap">
						<table class="cart-table">
							<thead class="cart-table-head">
								<tr class="table-head-row">
									<th class="product-remove"></th>
									<th class="product-image">Product Image</th>
									<th class="product-name">Name</th>
									<th class="product-price">Price</th>
									<th class="product-quantity">Quantity</th>
									<th class="product-total">Total</th>
								</tr>
							</thead>
							<tbody>

								<c:forEach var="cart" items="${cartlist}" varStatus="st">
									<tr class="table-body-row">
										<td class="product-remove"><a
											href="cartRemove.do?cid=${cart.cid}"><i
												class="far fa-window-close"></i></a></td>
										<td class="product-image"><img src="${cart.cpic}"
											alt="상품이미지"></td>
										<td class="product-name">${cart.cname}</td>
										<td class="product-price">${cart.cprice}</td>
										<td class="product-quantity">
											<form action="cartupdate.do" class="update">
											<input type="hidden" value="${cart.cid}" name="cid" method="post">
												<select name="number" onchange="javascript:myListener(this);" id="${st.index}">

													<option value="1"
														<c:if test="${cart.number == 1}">selected</c:if>>1</option>
													<option value="2"
														<c:if test="${cart.number == 2}">selected</c:if>>2</option>
													<option value="3"
														<c:if test="${cart.number == 3}">selected</c:if>>3</option>
													<option value="4"
														<c:if test="${cart.number == 4}">selected</c:if>>4</option>
													<option value="5"
														<c:if test="${cart.number == 5}">selected</c:if>>5</option>
												</select>

											</form>
										</td>
										<td class="product-total">${cart.subtotal}</td>
									</tr>
								</c:forEach>

							</tbody>
						</table>
					</div>
				</div>

				<div class="col-lg-4">
					<div class="total-section">
						<table class="total-table">
							<thead class="total-table-head">
								<tr class="table-total-row">
									<th>Total</th>
									<th>Price</th>
								</tr>
							</thead>
							<tbody>
								<tr class="total-data">
									<td><strong>Subtotal: </strong></td>
									<td>${total}원</td>
								</tr>
								<tr class="total-data">
									<td><strong>Shipping: </strong></td>
									<td>3000원</td>
								</tr>
								<tr class="total-data">
									<td><strong>Total: </strong></td>
									<td>${total+3000}원</td>
								</tr>
							</tbody>
						</table>
						<div class="cart-buttons">
							<a href="checkout.jsp?total=${total}" class="boxed-btn black" onclick="f1();">결제하기</a>
							<!-- <button class="boxed-btn black" type="submit" form="checkout">결제하기
								</button> -->
						</div>
					</div>


				</div>
			</div>
		</div>
	</div>
	<!-- end cart -->

	<!-- logo carousel -->
	<div class="logo-carousel-section">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<div class="logo-carousel-inner">
						<div class="single-logo-item">
							<img src="assets/img/company-logos/1.png" alt="">
						</div>
						<div class="single-logo-item">
							<img src="assets/img/company-logos/2.png" alt="">
						</div>
						<div class="single-logo-item">
							<img src="assets/img/company-logos/3.png" alt="">
						</div>
						<div class="single-logo-item">
							<img src="assets/img/company-logos/4.png" alt="">
						</div>
						<div class="single-logo-item">
							<img src="assets/img/company-logos/5.png" alt="">
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- end logo carousel -->

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
		var form = document.querySelectorAll(".update");
		var index = document.querySelectorAll(".index");
		
		function myListener(obj) {
			console.log(form);
			console.log(obj.id);
			alert(obj.value);
			form[obj.id].submit();			
		};
		
		var checkout=document.querySelector("#check");
		function f1(){
			var mname="${mName}";
			
			if(mname==null||mname==""){
				alert('로그인 후 이용해 주세요!');
				event.preventDefault();
			}
		}
	</script>

</body>
</html>