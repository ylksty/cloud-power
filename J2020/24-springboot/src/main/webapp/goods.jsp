<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <title>SpringBoot+MyBatis+JSP</title>
</head>
<body style="margin: 45px;">

${goods.id}  -- ${goods.name}

<%--可以加载该图片，其实这个图片也跨域了--%>
<img src="https://www.baidu.com/img/flexible/logo/pc/result.png"/>

<script type="text/javascript">
    /*$(function() {
        $.ajax({
            type: "GET",
            url: "http://192.168.0.104:8080/web/test",
            dataType: "text",
            success: function(data){
                alert(data);
            }
        });
    })*/

    $.ajax({
        type:"GET",
        url:"http://192.168.0.104:8080/24-springboot/web/jsonp",
        dataType : "jsonp", // 返回的数据类型，设置为JSONP方式
        jsonp : 'callback', //指定一个查询参数名称来覆盖默认的 jsonp 回调参数名 callback
        jsonpCallback: 'handleResponse', //设置回调函数名

        success: function(response, status, xhr){
            console.log('状态为：' + status + ',状态是：' + xhr.statusText);
            console.log(response);
        },

        error: function (XMLHttpRequest, textStatus, errorThrown) {
            // 状态码
            console.log(XMLHttpRequest.status);
            // 状态
            console.log(XMLHttpRequest.readyState);
            // 错误信息
            console.log(textStatus);
            alert(errorThrown);
        }
    });

    //回调方法
    function handleResponse(response){
        console.log(response);
        alert(response);
    }
</script>

<img src="${pageContext.request.contextPath}/changsha.jpg"/>

<img src="${pageContext.request.contextPath}/chengdu.jpg"/>
</body>
</html>