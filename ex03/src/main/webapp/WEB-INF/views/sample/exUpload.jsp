<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>파일 업로드 폼</title>
</head>
<body>
<%--enctype 파일 업로드 필수 설정--%>
<form action="/sample/exUploadPost" method="post"
      enctype="multipart/form-data" accept-charset="UTF-8">

<%--    여러 파일 업로드시 동일한 이름으로 배열 처리 가능--%>
    <div><input type="file" name="files" /></div>
    <div><input type="file" name="files" /></div>
    <div><input type="file" name="files" /></div>
    <div><input type="file" name="files" /></div>
    <div><input type="file" name="files" /></div>

    <div>
        <input type="submit" value="제출" />
    </div>
</form>
</body>
</html>