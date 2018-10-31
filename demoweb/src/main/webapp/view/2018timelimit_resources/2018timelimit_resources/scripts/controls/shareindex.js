'use strict';
define(['vue','tools','base64'], function(vue,Tools,base64) {
	var $ = Tools.$;
	var id;
	var mydate = new Date().getTime();
	var vm;
	try{
		var _mobile=JSON.parse(utf8to16(base64decode(window.daka.getMobileApp())));
		window.sessionStorage.setItem('USERID',_mobile.userId);
		window.sessionStorage.setItem('TOKEN',_mobile.tokenId);
		window.sessionStorage.setItem('LOGINNAME',_mobile.userName);
		window.sessionStorage.setItem('LOGINPHONE',_mobile.mobileNo);
		}catch(e){
	    console.log('未获取到用户信息');
	}
	 function datavue(){
		vm = new vue({
			el:".shareindex",
			data:{
				goodsinfo:{},
				imgUrl:"",
				shopId:""				
			},
			computed: {
			},
			methods: {
			}
		})
	}
	function init(){
		// Tools.action({'eventName': 'shareinfopeas', 'actionType':'【PvUv】', 'pageName': '2017卡豆兑换卡友圈跳转页','actionName': '2017卡豆兑换卡友圈跳转页pv'});
		id = Tools.getUrlParam("id");		
		datavue();
	 	goodsinfo();
	}
    function goodsinfo(){
    	Tools.postToken('/mall-mobile-api/mallApi/goodsInfoNew', {goodsId:id}, function(r){
    		if(r.head.status == "0"){     			
	    		vm.goodsinfo = r.body;	
	    		vm.imgUrl = r.body.imageList[0];
	    		vm.shopId = r.body.shopId;
	    		if(r.body.peastotal<r.body.peasNum){
	    			$('#btn').addClass('graybtn');
	    			$('#btn').text("卡豆不足");
	    		}		    		
	    		$('.shareindex').removeClass('hide');
			}else{
				Tools.showMsg(r.head.errorMessage);
			}
		});
    }

	function bindEvent(){
		console.log("我也要兑换。。。。。。跳转到详情。。。");
		var click_event = "ontouchstart" in  document.documentElement ? "tap" : "click";
		$("body").on(click_event,".btn",function(){
			Tools.action({'eventName': 'iwantpeas', 'actionType':'【Event】', 'pageName': '2017卡豆兑换卡友圈跳转页', 'actionName': '2017卡豆兑换我也要兑换按钮'});
			window.location.href = "index.html?shopId="+vm.shopId+"&mydate="+mydate;	
		})
	}
	(function(){
	    bindEvent();
	})();
	//对外接口
	return {
		init:init
	}
});
