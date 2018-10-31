'use strict';
define(['vue', 'tools', 'base64'], function (vue, Tools, base64) {
    var $ = Tools.$;
    var id;
    var flag = 1;
    var pageNum = 1;
    var _mobile;
    var isReceiving;
    var mydate = new Date().getTime();
    var vm;
    try {
        var _mobile = JSON.parse(utf8to16(base64decode(window.daka.getMobileApp())));
        window.sessionStorage.setItem('USERID', _mobile.userId);
        window.sessionStorage.setItem('TOKEN', _mobile.tokenId);
        window.sessionStorage.setItem('LOGINNAME', _mobile.userName);
        window.sessionStorage.setItem('LOGINPHONE', _mobile.mobileNo);
    } catch (e) {
        console.log('未获取到用户信息');
    }

    function datavue() {
        vm = new vue({
            el: ".goodsinfo",
            data: {
                goodsinfo: {},
                imgUrl: "",
                isbuyed:"",// 是否已经抢购过该商品,1-是，0：否
                buyStatus: "0",//抢购状态0:立即购抢；1：已抢光,2:即将开始
            },
            computed: {},
            methods: {}
        })
    }

    function init() {
        burial_point("20181111XsmsSpxqPv");
        id = Tools.getUrlParam("id");
        console.log("获取到的id==" + id);
        datavue();
        goodsinfo();
    }

    // 埋点公用方法
    function burial_point(e) {
        Tools.action({'eventName': e, 'actionType': '【PvUv】', 'pageName': '2018双11秒杀活动', 'actionName': '2018双11秒杀活动'});
    }

    function placeOrder() {
        Tools.postToken('/mall-mobile-api/mallApi/buyer/placeOrderNew', {
            goodsId: id,
            receiverName: _mobile.userName,
            receiverPhone: _mobile.mobileNo,
            num: "1"
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

    function goodsinfo() {
        Tools.postToken('/mall-mobile-api/mallApi/timeLimit/getKillTimeGoodsInfo', {goodsId: id}, function (r) {
            if (r.head.status == "0") {
                vm.goodsinfo = r.body;
                vm.imgUrl = r.body.imageUrl;
                if (r.body.isReceiving) {
                    isReceiving = r.body.isReceiving;
                }
                if (r.body.peastotal < r.body.peasNum) {
                    // $('#btn').addClass('graybtn');
                    $('#btn').addClass('null');
                    // $('#btn').text("立即充值卡豆");
                }
                vm.isbuyed = r.body.isbuyed;
                $('.goodsinfo').removeClass('hide');
            } else {
                Tools.showMsg(r.head.errorMessage);
            }
        });
    }

    function bindEvent() {
        var click_event = "ontouchstart" in document.documentElement ? "tap" : "click";
        $("body").on(click_event, "#btn", function () {
            console.log("是否已购买 == "+vm.isbuyed);
            if(vm.isbuyed =="1"){
                Tools.showMsg("您已购兑换过此商品");
                return;
            }
            if (!$(this).hasClass('null')) {
                // 兑换提示
                burial_point("20181111XsmsKkdPv");
                $('#mask01').removeClass('hide');
            } else {
                // 卡豆不足提示
                $('#mask02').removeClass('hide');
                burial_point("20181111XsmsKdbzPv");
                }

            setTimeout(function () {
                burial_point(vm.buyStatus = '0' ? "20181111SpxqLjqd" : (vm.buyStatus = '2' ? "20181111SpxqJjkq" : "20181111SpxqYqg"));
            },300);
        })
        $("body").on(click_event, ".l", function () {
            $('.mask').addClass('hide');
            //20181111KkdQx
            if (!$(this).hasClass('null')) {
                // 兑换提示
                burial_point("20181111KkdQx");
            } else {
                // 卡豆不足提示
                burial_point("20181111KdbzQx");
            }
        })
        $("body").on(click_event, "#r01", function () {
            if (isReceiving == "1") {
                if (flag == 0) return;
                Tools.action({
                    'eventName': 'surepeas',
                    'actionType': '【Event】',
                    'pageName': '2017卡豆兑换详情页',
                    'actionName': '2017卡豆兑换详情页确定按钮'
                });
                flag = 0
                placeOrder();
            } else {
                window.location.href = "getaddressinfo.html?id=" + id + "&mydate=" + mydate;
            }
            burial_point("20181111KKdQr");
        })
        $("body").on(click_event, "#r02", function () {
            window.h5_native.execNative("startNativePage", {"code": "329", "args": {}});
            burial_point("20181111KdbzQcz");
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
