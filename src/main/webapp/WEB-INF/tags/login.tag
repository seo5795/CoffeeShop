<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:choose>
	<c:when test="${mName!=null}">
		<!-- session에서 설정한 사용자 mname -->
		<li class="current-list-item"><a href="logout.do">로그아웃</a></li>
	</c:when>
	<c:otherwise>

		<li class="current-list-item"><a href="login.do">로그인</a>
			<ul class="sub-menu">
				<li><a href="login.jsp">로그인</a></li>
				<li><a href="register.jsp">회원가입</a></li>
			</ul>
		</li>

	</c:otherwise>

</c:choose>

