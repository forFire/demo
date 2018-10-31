import axios from 'axios';
import header_params from './config';
import { Indicator,Toast } from 'mint-ui';
let util = {

};
window.header_params=header_params();
const ajaxUrl = process.env.NODE_ENV == "development" ?
    'http://127.0.0.1:8888' : 'http://lmk.95155.com';



/*
 **--axios handle config--
 */

util.ajax = axios.create({
    baseURL: "/activity-api/",
    timeout: 60000,
    responseType: 'json', //default
    headers:header_params(),
    //post split joint params
    // data: null,
    //get split joint params
    // params: params
})
util.ajax.interceptors.request.use(function (config) {
    //在请求发出之前进行一些操作
    // console.log("start");
    Indicator.open({ spinnerType: 'fading-circle'});
    config.headers=header_params();
    if(config.method=='post'){
        config.params=null
    } else if(config.method=='get'){
        config.datas=null;
    }
    // console.log(config);
    if(config.url.indexOf('vchat')>-1){
        config.baseURL='/'
    }
    return config;
}, function (err) {
    //Do something with request error
    return Promise.reject(error);
})
util.ajax.interceptors.response.use(function (config) {
    //在请求发出之前进行一些操作
    // console.log("end");
    Indicator.close();
    // console.log(config)
    if(config.data.code!=200){
        Toast(config.data.message)
    }
    if(config.data.code==-10){
        window.vm.$router.push({name:"follow_dyr"});
    }
    return config.data;
}, function (err) {
    Indicator.close();
    Toast('网络异常或请求失败');
    //Do something with request error
    return Promise.reject(error);
})
export default util;