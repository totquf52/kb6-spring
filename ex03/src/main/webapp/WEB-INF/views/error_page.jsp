<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="false" import="java.util.*"%>

<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
  <title>Insert title here</title>
</head>
<body>
  <h4><c:out value="${exception.getMessage()}"/></h4>
  <ul>
<%--    예외의 전체 스택 트레이스를 순회하며 출력--%>
    <c:forEach items="${exception.getStackTrace()}" var="stack">
      <li><c:out value="${stack}"/></li>
    </c:forEach>
  </ul>
</body>
</html>
