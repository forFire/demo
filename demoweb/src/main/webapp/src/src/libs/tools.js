export const isWeChat= () => {
    const ua = window.navigator.userAgent.toLowerCase();
    if (ua.match(/MicroMessenger/i) == 'micromessenger') {
        return 1;
    } else {
        return 0;
    }
}
export const isAndroidOrIos = () => {
    var u = navigator.userAgent;
    if (u.indexOf('Android') > -1 || u.indexOf('Adr') > -1) {
        return 0;
    } else if (!!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/)) {
        return 1;
    } else {
        return -1;
    }
}
// util.format('yyyy-MM-dd hh:mm:ss')
export const format = function (date,fmt) {
    let da=new Date(date);
    var o = {
        "M+": da.getMonth() + 1, //月份
        "d+": da.getDate(), //日
        "h+": da.getHours(), //小时
        "m+": da.getMinutes(), //分
        "s+": da.getSeconds(), //秒
        "q+": Math.floor((da.getMonth() + 3) / 3), //季度
        "S": da.getMilliseconds() //毫秒
    };
    if (/(y+)/.test(fmt))
        fmt = fmt.replace(RegExp.$1, (da.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt))
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}

export const trim = function () {
    return this.replace(/^\s+/, '').replace(/\s+$/, '');
}
export const setCookie = (cname, cvalue, exdays) => {
    var d = new Date();
    d.setTime(d.getTime() + ((exdays || 7) * 24 * 60 * 60 * 1000));
    var expires = "expires=" + d.toGMTString();
    document.cookie = cname + "=" + cvalue + "; " + expires;
}
export const getCookie = cname => {
    var name = cname + "=";
    var ca = document.cookie.split(';');
    for (var i = 0; i < ca.length; i++) {
        var c = ca[i].trim();
        if (c.indexOf(name) == 0) return c.substring(name.length, c.length);
    }
    return "";
}
export const title = title => {
    window.document.title = title;
};
