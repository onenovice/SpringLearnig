<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>获取集合参数实例</title>
</head>
<body>
    <form action="${pageContext.request.contextPath}/quick14" method="post">
        <%--表明是第几个User对象的username和age--%>
        <input type="text" name="userList[0].username"><br>
        <input type="text" name="userList[0].age"><br>
        <input type="text" name="userList[1].username"><br>
        <input type="text" name="userList[1].age">
        <input type="submit" name="提交">
    </form>
</body>
</html>
