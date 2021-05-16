<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ajax</title>
<%--    涉及静态资源访问权限--%>
    <script src="${pageContext.request.contextPath}/js/jquery-3.6.js"></script>
    <script>
        var userList = new Array();
        userList.push({username:"zhangsan",age:18});
        userList.push({username:"lisi",age:20});

        $.ajax({
            type:"POST",
            url:"${pageContext.request.contextPath}/quick15",
            data:JSON.stringify(userList),
            contentType:"application/json;charset=utf-8"
        })
    </script>
</head>
<body>

</body>
</html>
