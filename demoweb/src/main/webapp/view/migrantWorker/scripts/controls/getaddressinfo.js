'use strict';
define(['tools', 'base64', "allcity"], function (Tools, base64, allcity) {
    var $ = Tools.$;
    var id;
    var flag = 1;
    var reg_name = /^[A-z]+$|^[\u4E00-\u9FA5]+$/;
    var reg_mobile = /^0{0,1}(13[0-9]|15[0-9]|176|177|178|147|145|170|18[0-9])[0-9]{8}$/;
    var reg_address = /^[\u4e00-\u9fa5a-zA-Z0-9，\-,\/,\|,\$,\+,\%,\&,\',\(,\),\*,\x20-\x2f,\x3a-\x40,\x5b-\x60,\x7b-\x7e,\x80-\xff,\u3000-\u3002,\u300a,\u300b,\u300e-\u3011,\u2014,\u2018,\u2019,\u201c,\u201d,\u2026,\u203b,\u25ce,\uff01-\uff5e,\uffe5]+$/;
    var _mobile, nameval, mobileVal, detailsval;
    var name_format = "请输入正确的姓名";
    var name_empty = "请输入姓名";
    var mobile_empty = "请输入手机号";
    var mobile_format = "请输入正确的手机号";
    var ischose = false;
    var mydate = new Date().getTime();
    var a = "北京市", b;
    try {
        var _mobile = JSON.parse(utf8to16(base64decode(window.daka.getMobileApp())));
        window.sessionStorage.setItem('USERID', _mobile.userId);
        window.sessionStorage.setItem('TOKEN', _mobile.tokenId);
        window.sessionStorage.setItem('LOGINNAME', _mobile.userName);
        window.sessionStorage.setItem('LOGINPHONE', _mobile.mobileNo);
        window.sessionStorage.setItem('NICKNAME', _mobile.nickName);
    } catch (e) {
        console.log('未获取到用户信息');
        // var _mobile = {
        //     userId: "5687acec-fc1f-401c-bde9-9cf7c298a1ad",
        //     tokenId: "DC662A212364051726BA3E0E60956BE6",
        //     userName: '袁彬彬'
        // };
        // window.sessionStorage.setItem('USERID', _mobile.userId);
        // window.sessionStorage.setItem('TOKEN', _mobile.tokenId);
        // window.sessionStorage.setItem('LOGINNAME', _mobile.userName);
    }

    function init() {
        $('#userName').val(_mobile.nickName);
        $('#phone').val(_mobile.mobileNo);
        if (window.localStorage.getItem('ANDRESS')) {
            $("#details").val(window.localStorage.getItem('ANDRESS'));
        }
        if (window.localStorage.getItem('CHOSE')) {
            ischose = true;
            $(".chose").text(window.localStorage.getItem('CHOSE'));
        }
        Tools.action({
            'eventName': 'getaddressinfopeas',
            'actionType': '【PvUv】',
            'pageName': '2017卡豆兑换收货详情页',
            'actionName': '2017卡豆兑换收货详情页pv'
        });
        id = Tools.getUrlParam("id");
        province();
    }

    function placeOrder() {
        var receiverName = $('#userName').val();
        var receiverPhone = $('.phone').val();
        var address = $('.chose').text() + $("#details").val().trim();
        Tools.postToken('/mall-mobile-api/mallApi/buyer/placeOrderNew', {
            goodsId: id,
            receiverName: receiverName,
            receiverPhone: _mobile.mobileNo,
            num: "1",
            address: address
        }, function (r) {
            if (r.head.status == "0") {
                window.location.href = "result.html?id=" + id + "&mydate=" + mydate;
                flag = 1;
            } else {
                Tools.showMsg(r.head.errorMessage);
                flag = 1;
            }
        });
    }

    function checkname() {
        nameval = $("#userName").val().trim();
        if (nameval != '') {
            if (reg_name.test(nameval)) {
                if (/^[A-z]+$/.test(nameval)) {
                    if (nameval.length >= 4 && nameval.length <= 20) {
                        return true;
                    } else {
                        Tools.showMsg(name_format);
                        return false;
                    }
                } else {
                    if (nameval.length >= 2 && nameval.length <= 10) {
                        return true;
                    } else {
                        Tools.showMsg(name_format);
                        return false;
                    }
                }
            }
            Tools.showMsg(name_format);
            return false;
        } else {
            Tools.showMsg(name_empty);
            return false;
        }
    }

    function checkphone() {
        mobileVal = $("#phone").val();
        if (mobileVal != '') {
            if (reg_mobile.test(mobileVal)) {
                return true;
            }
            Tools.showMsg(mobile_format);
            return false;
        } else {
            Tools.showMsg(mobile_empty);
            return false;
        }
    }

    function checkchose() {
        if (ischose == true) {
            var choseval = $('.chose').text();
            window.localStorage.setItem('CHOSE', choseval);
            return true
        } else {
            Tools.showMsg("请选择所在地区");
            return false
        }
    }

    function checkdetails() {
        detailsval = $("#details").val().trim();
        if (detailsval != '') {
            window.localStorage.setItem('ANDRESS', detailsval);
            if (reg_address.test(detailsval)) {
                if (detailsval.length >= 1 && detailsval.length <= 150) {
                    return true;
                } else {
                    Tools.showMsg("请输入合法的地址");
                    return false;
                }
            }
            Tools.showMsg("请输入合法的地址");
            return false;
        } else {
            Tools.showMsg("请输入详细地址");
            return false;
        }
    }

    function province() {
        var str = "";
        for (var i = 0; i < myarea.length; i++) {
            str += '<li class="basic-line otherline" arr="' + i + '" code="' + myarea[i].code + '">' + myarea[i].cityName + '</li>';
        }
        $("#classa_div").append(str);
        setTimeout(function () {
            var strstart = "";
            $(".basic-line").eq(0).addClass('ac');
            for (var i in (myarea[0].children)) {
                var city = (myarea[0].children)[i].cityName;
                strstart += '<li class="basic-line1">' + city + '</li>';
            }
            $("#classb_div").append(strstart);
        }, 300);
    }

    function bindEvent() {
        var click_event = "ontouchstart" in document.documentElement ? "tap" : "click";
        $("body").on(click_event, ".pseudo", function () {
            $(".zonewrap").removeClass("hide");
        });
        $("body").on(click_event, ".basic-line1", function () {
            b = $(this).text();
            $(".chose").html(a + b);
            $(".chose").css({"color": "#242424"});
            $(".zonewrap").addClass('hide');
            ischose = true;
        });
        $("body").on(click_event, ".otherline", function () {
            $('.basic-line').removeClass('ac');
            $(this).addClass('ac');
            $('.ztxtwrap').text($(this).text());
            var str = "";
            for (var i in (myarea[$(this).attr('arr')].children)) {
                var city = (myarea[$(this).attr('arr')].children)[i].cityName;
                str += '<li class="basic-line1">' + city + '</li>';
            }
            $("#classb_div").html("");
            $("#classb_div").append(str);
            a = $(this).text();
        })
        $("body").on(click_event, "#sure", function () {
            if (checkname() && checkphone() && checkchose() && checkdetails()) {
                if (flag == 0) return;
                Tools.action({
                    'eventName': 'getaddresssure',
                    'actionType': '【Event】',
                    'pageName': '2017卡豆兑换收货详情页',
                    'actionName': '2017卡豆兑换收货详情页确定按钮'
                });
                flag = 0
                placeOrder();
            }
        })
    }

    (function () {
        bindEvent();
    })();
    //对外接口
    return {
        init: init
    }
});
