<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<!-- <title>회원가입</title> -->
<title><spring:message code="member.register" /></title>
</head>
<body>
	<%--  <p><strong>${formData.name}님</strong> 회원 가입을 완료했습니다.</p>
    <p><a href="<c:url value='/main'/>">[첫 화면 이동]</a></p> --%>
	<p>
		<spring:message code="register.done"
			arguments="${registerRequest.name},${registerRequest.email}" />
	</p>
	<p>
		<a href="<c:url value='/main'/>">[<spring:message code="go.main" />]
		</a>
	</p>
</body>
</html>
