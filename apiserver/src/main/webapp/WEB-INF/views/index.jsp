<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!-- Spring Security JSP 태그 사용 선언 -->

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>

<h1>환영합니다.</h1>

<sec:authorize access="isAnonymous()">
    <!-- 로그인하지 않은 사용자만 볼 수 있는 영역 -->
    <a href="/security/login">로그인</a>
</sec:authorize>

<sec:authorize access="isAuthenticated()">
    <!-- 로그인한 사용자만 볼 수 있는 영역 -->

    <sec:authentication property="principal.username"/>
    <!-- 현재 로그인한 사용자의 username 출력 -->

    <form action="/security/logout" method="post">
        <!-- 로그아웃은 POST 방식으로 처리해야 CSRF 보호 가능 -->

        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
        <!-- CSRF 공격 방지를 위한 토큰 전달 -->

        <input type="submit" value="로그아웃"/>
    </form>
</sec:authorize>

</body>
</html>