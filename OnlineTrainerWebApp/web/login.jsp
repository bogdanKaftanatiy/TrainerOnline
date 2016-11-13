<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login page</title>
    <link href="<c:url value="/resources/css/login_style.css" />" rel="stylesheet">
</head>
<body>
<form action="j_security_check" method="post" class="loginForm">
    <input name="j_username" placeholder="Username" type="text"><br>
    <input name="j_password" placeholder="Password" type="password"><br>
    <input type="submit" value="Login">
    <p class="createAccount">Not registered? <a href="${pageContext.request.contextPath}/registration.xhtml">Create an account</a></p>
</form>
</body>
</html>
