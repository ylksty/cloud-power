var buyObject = {

    //一个属性
    contextPath : "",

    //一个属性
    url : {
        //购买接口地址
        buyURL : function () {
            return buyObject.contextPath + "/cloud/goods/";
        },
    },

    //函数
    func : {
        //提交购买请求
        submitBuy : function (id, buyNum) {
            $.ajax({
                url : buyObject.url.buyURL() + id,
                type: "POST",
                dataType : "json",
                data : {
                    "buyNum" : buyNum
                },
                success : function(responseMessage) {
                    if (responseMessage.statusCode == 0) {
                        $("#buyTip").html("<span style='color:blue;'>" + responseMessage.statusMessage + "，3秒后将进入支付页面</span>");
                        window.setTimeout(function(){
                                window.location.href="http://www.alipay.com";
                            },3000);
                    } else {
                        $("#buyTip").html("<span style='color:red;'>" + responseMessage.statusMessage + "</span>");
                    }
                }
            });
        }
    }
}