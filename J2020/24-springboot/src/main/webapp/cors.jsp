<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <title>CORS测试</title>
</head>

<body style="margin: 45px;">

<%--可以加载该图片，其实这个图片也跨域了--%>
<img src="https://www.baidu.com/img/flexible/logo/pc/result.png"/>

<script type="text/javascript">
    $(function() {
        $.ajax({
            type: "GET",
            url: "http://192.168.0.104:80/test",
            dataType: "text",
            success: function(data){
                alert(data);
            }
        });
    })
</script>

</body>
</html>