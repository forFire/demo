'use strict';
var loading = 1
define(['vue', 'tools', 'base64', 'iscroll'], function (vue, Tools, base64, IScroll) {
    var $ = Tools.$;
    var vm, swiper1, swiper2, swiper1_copy;
    var isPulled = false; // 拉动标记
    var page = 1;
    var timestamp;
    var loadinit = false;
    var myScroll;
    var mydate = new Date().getTime();
    try {
        var _mobile = JSON.parse(utf8to16(base64decode(window.daka.getMobileApp())));
        window.sessionStorage.setItem('USERID', _mobile.userId);
        window.sessionStorage.setItem('TOKEN', _mobile.tokenId);
        window.sessionStorage.setItem('APPVERSION', _mobile.appVersion);
        window.sessionStorage.setItem('LOGINNAME', _mobile.userName);
        window.sessionStorage.setItem('systemVersion', _mobile.systemVersion);
        window.sessionStorage.setItem('SUPPORTNATIVEPAY', _mobile.supportNativePay);
    } catch (e) {
        console.log('未获取到用户信息');
        var _mobile = {
            userId: "5687acec-fc1f-401c-bde9-9cf7c298a1ad",
            tokenId: "203A9D9FC08638E7ADA0B8832536A7D5",
            userName: '袁彬彬'
        };
        window.sessionStorage.setItem('USERID', _mobile.userId);
        window.sessionStorage.setItem('TOKEN', _mobile.tokenId);
        window.sessionStorage.setItem('LOGINNAME', _mobile.userName);
    }

    function init() {
        datavue();
        // initSwiper();
        gettimelist();
        burial_point("20181111XsmsindexPv");
        monitorSwiper_copy();//控制黑色头是否隐藏
    }

    function monitorSwiper_copy() {
        window.onscroll = function () {
            var top = document.body.scrollTop || document.documentElement.scrollTop;//兼容写法
            var headerHeight = $('.header-wrapper').height();
            console.log("滚动的高度 == " + headerHeight);
            if (top > headerHeight) {
                $('#swiper1_copy').removeClass('hide01');
            } else {
                $('#swiper1_copy').addClass('hide01');
            }
        }
    }

    function datavue() {
        vm = new vue({
            el: ".index",
            data: {
                baseinfo: {},
                teamlist: [],
                useravatar: "",
                isjoin: "",
                myteamid: "",
                mymap: {},
                toplist: {},
                list: {},
                nomore: false,
                cvtype: "2",
                vtopicId: "20180611001",
                shopid: '9d774ff9-4cd9-4c5a-926e-46c1db830a60',// 商品id
            },

            computed: {},

            methods: {
                close_rule: function () {
                    $('#rule').addClass('hide');
                },
                rushuy: function (e) {
                    console.log("抢购。。。。。");
                    toGoodsInfo(e);
                },
                open_rule: function () {
                    $('#rule').removeClass('hide');
                    // window.h5_native.execNative("notificationset", "", function (e) {
                    //     console.log("得到的值==" + e);
                    //     alert("回调的值==" + e);
                    // });
                },

                // 时间转换
                inittime: function (e) {
                    return transdatatotime(e);
                },

                getstatus: function (e) {
                    return e == '0' ? "已开抢" : (e == '1' ? "疯抢中" : "即将开抢");
                },

                swiperclick: function (index) {
                    console.log("点击的pos ===" + index);
                    // setCurrentSlide($("#swiper1 .swiper-slide").eq(index), index);
                    // setCurrentSlide($("#swiper1_copy .swiper-slide").eq(index), index);
                    swiper1.slideTo(index, 500, false);
                    swiper2.slideTo(index, 500, false);

                    initPreNextData(index);
                    swiper1_copy.slideTo(index, 500, false);
                },

                exchangeclick: function (buyStatus, goodsid) {
                    console.log("点击的status="+buyStatus+",商品id="+goodsid);
                    toGoodsInfo(goodsid);
                    setTimeout(function () {
                        burial_point(buyStatus == 0 ? "20181111XsmsLjqd" : (buyStatus == 1 ? "20181111XsmsYqg" : "20181111XsmsJjkq"));
                    }, 300);
                }
            }
        });
    }

    // 时间转换
    function transdatatotime(e) {
        var time = new Date(parseInt(e));
        var y = time.getFullYear();
        var m = time.getMonth() + 1;
        var d = time.getDate();
        var h = time.getHours();
        var mm = time.getMinutes();
        var s = time.getSeconds();
        return h + ":" + "00";
    }

    function controlscroll() {
        if (myScroll) {
            myScroll.refresh();
            return;
        }

        myScroll = new IScroll('#MyScroller', {
            click: iScrollClick(),
            probeType: 3,
            mouseWheel: true,
            preventDefault: false,
            scrollbars: true,
            fadeScrollbars: true,
        });

        myScroll.on('scroll', function () {
            var height = myScroll.y,
                bottomHeight = myScroll.maxScrollY - height + 50;
            // 隐藏发布按钮
            console.log("滚动的距离 ==" + height);
            // 控制下拉显示
            /*if (height >= 20) {
                $(".scroller-pullDown").removeClass("hide");
                isPulled = true;
                return;
            } else if (height < 20 && height >= 0) {
                $(".scroller-pullDown").addClass("hide");
                return;
            }
            if (myScroll.y > 5) {
                isPulled = true;
                console.log("开始加载更多。。。。");
                load_more();
            }

            //  控制上拉显示
            if (bottomHeight >= 20) {
                isPulled = true;
                $(".scroller-pullUp").removeClass("hide");
                return;
            } else if (bottomHeight < 20 && bottomHeight >= 0) {
                $(".scroller-pullUp").addClass("hide");
                return;
            }*/
        })

        myScroll.on('scrollEnd', function () { // 滚动结束
            // 显示发布按钮
            // setTimeout(function () {
            //     showHideBtn(0);
            // }, 50);

            // console.log("isPulled == "+isPulled+",myscroll是否大于0="+myScroll.y >= 0);

            // console.log("轮动结束。。。。" + isPulled);

            // if (isPulled) { // 如果达到触发条件，则执行加载
            //     isPulled = false;
            //     if (myScroll.y <= myScroll.maxScrollY) {
            //         if (!$("#PullUp").hasClass("scroller-pullUp")) {
            //             $("#PullUp").addClass("scroller-pullUp");
            //         }
            //         console.log("加载更多。。。。");
            //         load_more();
            //     }
            // }
            //
            // if (myScroll.y >= 0) {
            //     page = 1;
            //     timestamp = '';
            //     if (!$("#PullUp").hasClass("scroller-pullUp")) {
            //         $("#PullUp").addClass("scroller-pullUp");
            //     }
            //     console.log("下拉刷新。。。。。");
            //     // vm.list = [];
            //     list();
            //     loading = false;
            //     vm.nomore = false;
            // }
            // setTimeout(function () {
            //     myScroll.refresh();
            // }, 300);
        });
        // document.addEventListener('touchmove', function (e) { e.preventDefault(); }, false);
        // document.addEventListener('touchstart', function (event) {
        //     // 判断默认行为是否可以被禁用
        //     if (event.cancelable) {
        //         // 判断默认行为是否已经被禁用
        //         if (!event.defaultPrevented) {
        //             event.preventDefault();
        //         }
        //     }
        // }, false);
    }

    //修复iscroll在iosbug
    function iScrollClick() {
        if (/iPhone|iPad|iPod|Macintosh/i.test(navigator.userAgent)) return false;
        if (/Chrome/i.test(navigator.userAgent)) return (/Android/i.test(navigator.userAgent));
        if (/Silk/i.test(navigator.userAgent)) return false;
        if (/Android/i.test(navigator.userAgent)) {
            var s = navigator.userAgent.substr(navigator.userAgent.indexOf('Android') + 8, 3);
            return parseFloat(s[0] + s[3]) < 44 ? false : true
        }
    }

    function initSwiper() {

        function setCurrentSlide(ele) {
            // $("#swiper1 .swiper-slide").removeClass("selected");
            // $("#swiper1_copy .swiper-slide").removeClass("selected");
            // ele.addClass("selected");
        }

        swiper1 = new Swiper('#swiper1', {
            slidesPerView: 5,//数量
            paginationClickable: true,//点击分页器的指示点分页器会控制Swiper切换。
            centeredSlides: true, //而不是默认状态下的居左
            spaceBetween: 10,
            loop: false,
            watchSliderProgress: true,
            initialSlide: 0,
            observer: true,
            on: {
                slideChangeTransitionEnd: function () { //回调函数，swiper从一个slide过渡到另一个slide结束时执行。
                    var index = this.activeIndex;
                    // console.log("当前滑动的index = " + index)
                    setCurrentSlide($("#swiper1 .swiper-slide").eq(index), index);
                    setCurrentSlide($("#swiper1_copy .swiper-slide").eq(index), index);
                    swiper1.slideTo(index, 500, false);
                    swiper2.slideTo(index, 500, false);

                    initPreNextData(index);
                    swiper1_copy.slideTo(index, 500, false);
                }
            },

        });

        swiper1.slides.each(function (index, val) {
            var ele = $(this);
            ele.on("click", function () {
                setCurrentSlide(ele);
                console.log("点击的index == " + index);
                swiper1.slideTo(index, 500, false);
                swiper2.slideTo(index, 500, false);
                initPreNextData(index);
                swiper1_copy.slideTo(index, 500, false);
            });
        });

        swiper1_copy = new Swiper('#swiper1_copy', {
            slidesPerView: 5,
            paginationClickable: true,
            centeredSlides: true,
            spaceBetween: 10,
            loop: false,
            watchSliderProgress: true,
            observer: true,
            on: {
                slideChangeTransitionEnd: function () {
                    var index = this.activeIndex;
                    console.log("当前滑动的index = " + index)
                    setCurrentSlide($("#swiper1 .swiper-slide").eq(index), index);
                    setCurrentSlide($("#swiper1_copy .swiper-slide").eq(index), index);
                    swiper1.slideTo(index, 500, false);
                    swiper2.slideTo(index, 500, false);
                    swiper1_copy.slideTo(index, 500, false);
                }
            }
        });

        swiper1_copy.slides.each(function (index, val) {
            var ele = $(this);
            ele.on("click", function () {
                setCurrentSlide(ele);
                swiper1.slideTo(index, 500, false);
                swiper2.slideTo(index, 500, false);
                swiper1_copy.slideTo(index, 500, false);
            });
        });

        swiper2 = new Swiper('.swiper2', {
            direction: 'horizontal',
            loop: false,
            autoHeight: true,
            initialSlide: 0,
            observer: true,
            on: {
                slideChangeTransitionEnd: function () {
                    var n = this.activeIndex;
                    // console.log("滑动的pos = " + n);
                    setCurrentSlide($("#swiper1 .swiper-slide").eq(n));
                    swiper1.slideTo(n, 500, false);
                    initPreNextData(n);
                    swiper1_copy.slideTo(n, 500, false);
                }
            }
        });

        // 根据当前时间选择移动的位置
        var pos = getpos();
        console.log("获取的当前位置 == "+pos);
        var number = Number(pos);

        swiper1.slideTo(number, 500, true);
        swiper2.slideTo(number, 500, false);
        swiper1_copy.slideTo(number, 500, false);

        if (number == 0)
            setCurrentSlide($(".swiper1 .swiper-slide").eq(number));
        initPreNextData(pos);
    }

    // 获取上一个、下一条数据的值
    function initPreNextData(pos) {
        if (isEmpety(vm.toplist))
            return;
        list(pos);

        var provpos = pos - 1;
        var nextpps = pos + 1;
        if (provpos > 0 && provpos < vm.toplist.length) {
            list(provpos);
        }

        if (nextpps > 0 && nextpps < vm.toplist.length) {
            list(nextpps);
        }
    }

    // 是否为空
    function isEmpety(e) {
        return (e && e != '' && e != null && e != undefined) ? false : true;
    }

    function getpos() {
        // 获取当前的位置--
        // status -- 0:已开抢，1：疯抢中，2：即将开抢
        // var timestamp = (new Date()).valueOf();
        var pos =0;
        for (var i = 0; i < vm.toplist.length; i++) {
            // console.log("当前时间==" + timestamp + "----" + vm.toplist[i].limitTime);
            console.log("当前的状态 == "+vm.toplist[i].status);
            if (vm.toplist[i].status =='1') {
                console.log("当前返回的位置==" + i);
                return i;
            }else if(vm.toplist[i].status == '0'){
                pos = i;
            } else {
                console.log("不是当前时间。。。。");
            }
        }
        return pos;
    }

    // 获取时间列表
    function gettimelist() {
        Tools.postToken('/mall-mobile-api/mallApi/timeLimit/listTimes', {}, function (r) {
            if (r.head.status == "0") {
                if (null != r.body.data) {
                    var length = r.body.data.length;
                    if (length > 0) {
                        vm.toplist = r.body.data;
                        console.log("解析的时间列表个数 == " + vm.toplist.length);
                        // vm.$set(this,vm.toplist,r.body.data);
                        // vm.$set(vm.toplist,r.body.data);// map更新方式
                        initSwiper();
                        // monitorSwiper_copy();//控制黑色头是否隐藏
                    }
                }

                $(".circle").addClass("hide");
                $(".list").removeClass("hide");
                loadinit = false;
            } else {
                Tools.showMsg(r.head.errorMessage);
                loadinit = false;
            }
        });
    }

    //首屏列表
    function list(e) {
        // if (loadinit) return;
        // loadinit = true;
        // /hb-mobile-api/hbOut/videos
        if (!isEmpety(vm.mymap[e])) {
            console.log("有值则不请求。。。。。" + e);
            return;
        }

        Tools.postToken('/mall-mobile-api/mallApi/timeLimit/getGoodsByTime', {
            limitTime: vm.toplist[e].limitTime,
        }, function (r) {
            if (r.head.status == "0") {
                if (null != r.body.data) {
                    // vm.loading = true;
                    var length = r.body.data.length;
                    if (length > 0) {
                        timestamp = r.body.data[length - 1].timestamp;
                        if (length < 10) {
                            noMoreView();
                        }
                        vm.list = [];
                        setTimeout(function () {
                            vm.list = r.body.data;
                        }, 50);

                        if (!isEmpety(vm.mymap)) {
                            if (isEmpety(vm.mymap[e])) {
                                console.log("不存在则更新。。。。");
                                vm.$set(vm.mymap, e, r.body.data);// map更新方式
                            } else {
                                console.log("已经存在，不需要更新。。。");
                            }
                        }
                    }
                    else {
                        noMoreView();
                    }
                } else {
                    noMoreView()
                }

                setTimeout(function () {
                    controlscroll();
                }, 300);

                $(".circle").addClass("hide");
                $(".list").removeClass("hide");
                loadinit = false;
            } else {
                Tools.showMsg(r.head.errorMessage);
                loadinit = false;
            }
        });
    }

    function noMoreView() {
        $(".scroller-pullUp").addClass("hide");
        $(".scroller-pullUp").removeClass("scroller-pullUp");
        vm.nomore = true;
    }

    function bindEvent() {
        var click_event = "ontouchstart" in document.documentElement ? "tap" : "click";
        $("body").on(click_event, ".a", function () {
            var pageurl = location.protocol + '//' + location.host + '/hb-mobile-api/goodvoices/list.html';
            window.h5_native.execNative("startNativePage", {
                "code": "998",
                "url": pageurl,
                "args": {"pageUrl": pageurl, "URL": pageurl}
            });
        });

        // $("body").on(click_event, ".rushbuy-btn", function () {
        //     toGoodsInfo(vm.shopid);
        //     try {
        //         var buyStatus = $(this).attr('buyStatus');
        //         console.log("立即抢购。。。。。状态==" + buyStatus);
        //         var status = window.daka.getnotifistatus();// 1-已设置，其他未设置
        //         alert(status);
        //
        //         window.h5_native.execNative("notificationset", {}, function (e) {
        //             console.log("得到的值==" + e);//{"getnotifistatus":0}
        //             alert("回调的值==" + e.getnotifistatus);
        //         });
        //     } catch (e) {
        //         console.log("抛出的异常 == " + e.toString());
        //     }
        //
        //     // 埋点
        //     setTimeout(function () {
        //         burial_point(buyStatus == 0 ? "20181111XsmsLjqd" : (buyStatus == 1 ? "20181111XsmsYqg" : "20181111XsmsJjkq"));
        //     }, 300);
        // });
    }

    // 进入查看详情
    function toGoodsInfo(id) {
        Tools.action({
            'eventName': 'goodspeas' + id,
            'actionType': '【Event】',
            'pageName': '2017卡豆兑换列表首页',
            'actionName': '2017卡豆兑换商品单击事件'
        });
        window.location.href = "goodsinfo.html?id=" + id + "&mydate=" + mydate;
    }

    // 埋点公用方法
    function burial_point(e) {
        Tools.action({'eventName': e, 'actionType': '【PvUv】', 'pageName': '2018双11秒杀活动', 'actionName': '2018双11秒杀活动'});
    }

    (function () {
        bindEvent();
    })();
    //对外接口
    return {
        init: init,
    }
});
