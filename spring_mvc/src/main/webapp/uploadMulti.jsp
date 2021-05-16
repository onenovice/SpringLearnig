<%--
  Created by IntelliJ IDEA.
  User: Jay
  Date: 2021/5/16
  Time: 18:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>多文件上传</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/quick23" method="post" enctype="multipart/form-data">
    名称<input type="text" name="username"/><br/>
    文件1<input type="file" name="uploadFiles"/><br/>
    文件2<input type="file" name="uploadFiles"/><br/>
    文件3<input type="file" name="uploadFiles"/><br/>
    <input type="submit" name="提交">
</form>
</body>
</html>
