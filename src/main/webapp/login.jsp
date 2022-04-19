<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title><spring:message code="message.login.title" /></title>

<!-- favicon -->
	<link rel="shortcut icon" type="image/png" href="assets/img/favicon.png">
<!-- Place favicon.ico in the root directory -->

<!-- Google Web Fonts by JHS -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Gothic+A1:wght@600&display=swap" rel="stylesheet">

<!-- jquery -->
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>

<link rel="stylesheet" href="assets/css/style.css">

   
<style>
*{
   font-family: 'Gothic A1', sans-serif;
}


.input-group {
 
    position: absolute;
    width: 280px;
    transition: .5s;
}



.text-dark {
   color: #343a40 !important;
}

.name {
   text-align: center;
}
.submit{
   margin-top: 20px;
   margin-bottom: 20px;
   background: #78b455;
   font-size: 14px;
}

.btn2{
   margin-left: 40px;
}

.register, .findPw{
   text-decoration: none;
   font-size: 14px;
}
.register:hover{
   text-decoration:underline;
}
.findPw:hover{
   text-decoration:underline;
}

</style>
</head>
<body>


   <div class="wrap">

      <div class="form-wrap">
         <div class="image">
            <a href="index.jsp"><img src="assets/img/logo.png" alt="logo" style="width: 45px;"></a>
         </div>

         <br>
         <div class="name">
            <h1><spring:message code="message.login.title" /></h1>
         </div>
			<div style="text-align:center"><a href="login.do?lang=ko">한국어</a> | <a href="login.do?lang=en">English</a></div>
         <form id="login" action="login.do" class="input-group" method="post">
            <input type="text" class="input-field" name="mid"
               placeholder="<spring:message code="message.login.user" /> ID" required> <input
               type="password" class="input-field" name="mpw" placeholder="<spring:message code="message.login.pw" />"
               required>
            <!-- <div>
               <input type="checkbox" class="checkbox">Remember Password
            </div> -->
            <br><br>
            
            <div style="text-align: center">
               <button class="submit"><spring:message code="message.login.login" /></button>
            </div>
            <div style="text-align:center">
               <a href="register.do" class="register"><spring:message code="message.login.sign" /></a>
            </div>
         </form>
      </div>
      </div>
   
</body>
</html>