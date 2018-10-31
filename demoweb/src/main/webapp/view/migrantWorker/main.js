"use strict";

requirejs.config({
    baseUrl: "scripts",
    paths: {
        "config": "../config",
        "zepto": "libs/zepto",
        "dot": "libs/doT",
        "tools": "common/tools",
        "tools01": "common/tools01",
        "wxshare": "common/wxshare",
        "jweixin": "https://res.wx.qq.com/open/js/jweixin-1.0.0",
        "vue": "libs/vue.dev",
        "base64": "libs/base64",
        "jquery": "libs/jquery",
        "easing": "libs/easing",
        "md5": "libs/md5",
        "sha1": "libs/sha1",
        "bridge": "libs/bridge",
        "swiper": "libs/swiper",
        "iscroll": "libs/iscroll-probe",
        "cnzz": "libs/cnzz",
        "index": "controls/index",
        "outindex": "controls/outindex",
        "myteam": "controls/myteam",
        "choice": "controls/choice",
        "rule": "controls/rule",
        "myproduction": "controls/myproduction",
        "download": "controls/download",
        "more": "controls/more",
        "list": "controls/list",
        "video": "controls/video",
        "video01": "controls/video01",
        "outsuccess": "controls/outsuccess",
        "crunchies": "controls/crunchies",

        // 商品详情有关
        "goodsinfo": "controls/goodsinfo",
        "getaddressinfo": "controls/getaddressinfo",
        "result": "controls/result",
        "shareindex":  "controls/shareindex",
        "allcity":"libs/allcity"
    },
    shim: {
        "zepto": {
            exports: "$"
        },
        "tools": {
            deps: ["zepto"]
        },
        "tools01": {
            deps: ["jquery"]
        },
        "easing": {
            deps: ["jquery"]
        },
        "cnzz": {
            exports: "_czc"
        }
    },
    urlArgs: function (name, url) {
        var version = new Date().getTime();
        if (url.indexOf('http') !== -1) return '';
        version = (this.verMap && this.verMap[name]) || version;
        return 'v=' + version;
    }
});

require([document.body.id], function (page) {
    return page.init();
});
