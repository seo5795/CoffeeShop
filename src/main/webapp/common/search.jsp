<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.select {
	text-align: center;
}
input {
vertical-align: middle;
    height: auto;
}
</style>
</head>
<body>
<form action="shop.do" method="post">
	<div class="search-area">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<span class="close-btn"><i class="fas fa-window-close"></i></span>
					<div class="search-bar">
						
							<div class="search-bar-tablecell">

								<h3>Search For:</h3>
								<select class="select" name="ccategory">
									<option value="cname">커피명</option>
									<option value="ccountry">나라명</option>
								</select> <input type="text" placeholder="Keywords" name="keyword">
								<button type="submit">
									Search <i class="fas fa-search"></i>
								</button>
							</div>
						
					</div>
				</div>
			</div>
		</div>
	</div>
	</form>
</body>
</html>