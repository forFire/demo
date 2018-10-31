'use strict';
define(['vue','tools','base64'], function(vue,Tools,base64) {
	var $ = Tools.$;
	var id,desc;
	var flag = 1;
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
			el:".result",
			data:{
				goodsinfo:{},
				imgUrl:""				
			},
			computed: {
			},
			methods: {	       
			}
		})
	}
	function init(){
		Tools.action({'eventName': '20181111XsmsDhjgPv', 'actionType':'【PvUv】', 'pageName': '2017卡豆兑换结果炫耀一下页','actionName': '2017卡豆兑换结果炫耀一下页pv'});
		id = Tools.getUrlParam("id");		
		datavue();
	 	goodsinfo();
	}
	function share(){
		var targetUrl =location.protocol + '//' + location.host + '/mall-mobile-api/exchange/shareindex.html?id='+id;		
    	Tools.postToken('/hb-mobile-api/hb20170315/addShareDynamic', {desc:desc,logo:vm.imgUrl,targetUrl:targetUrl}, function(r){
    		if(r.head.status == "0"){  
    			window.h5_native.execNative("startNativePage",{"code":"109","args":{}});  
    			if(Tools.isIOS()){
    				setTimeout(function(){
						window.h5_native.execNative("closeWindow",{"code":"","args":{}});						
					},300);
    			} 			
	    		flag = 1;
			}else{
				Tools.showMsg(r.head.errorMessage);
				flag = 1;
			}
		});
		
    }
    function goodsinfo(){
    	Tools.postToken('/mall-mobile-api/mallApi/goodsInfoNew', {goodsId:id}, function(r){
    		if(r.head.status == "0"){     			
	    		vm.goodsinfo = r.body;	
	    		vm.imgUrl = r.body.imageList[0];
	    		desc = "讲真，我买这个没花钱~我刚刚用"+r.body.peasNum+"卡豆兑换了"+r.body.name;	    		
	    		$('.goodsinfo').removeClass('hide');
			}else{
				Tools.showMsg(r.head.errorMessage);
			}
		});
    }
	function bindEvent(){
		var click_event = "ontouchstart" in  document.documentElement ? "tap" : "click";
		$("body").on(click_event,".btn",function(){
			  if(flag==0) return;
			  Tools.action({'eventName': '20181111DhjgXyyx', 'actionType':'【Event】', 'pageName': '2017卡豆兑换结果炫耀一下页', 'actionName': '2017卡豆兑换炫耀一下按钮'});
		      flag = 0;
			  share();						
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
