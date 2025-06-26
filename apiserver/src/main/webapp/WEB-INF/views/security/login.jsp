<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
</head>
<h1>login</h1>
<%--로그인 실패 시 전달되는 파라미터--%>
${param.error}
<form name='f' action='/security/login' method='POST'>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

    <c:if test="${param.error != null}">
        <div style="color: red">사용자 ID 또는 비밀번호가 틀립니다.</div>
    </c:if>

    <table>
        <tr>
            <td>User:</td>
            <td><input type='text' name='username' value=''></td>
        </tr>
        <tr>
            <td>Password:</td>
            <td><input type='password' name='password'/></td>
        </tr>
        <tr>
            <td colspan='2'>
                <input name="submit" type="submit" value="Login"/>
            </td>
        </tr>
    </table>
</form>
</body>
</html>