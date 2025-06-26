<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
  <title>Title</title>
</head>
<body>
<div>
  <!-- 선택한 도시 이름 출력 -->
  <h2>${city}</h2>

  <!-- 날씨 설명과 아이콘 표시 -->
  오늘의 날씨:
  ${weather.weather[0].description}
  <img src="${iconUrl}"/>
</div>

<div>
  <!-- 온도와 습도 출력 -->
  온도: ${weather.main.temp}° /
  습도: ${weather.main.humidity}%
</div>
</body>
</html>