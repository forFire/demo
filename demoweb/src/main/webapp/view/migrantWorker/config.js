'use strict';
// var isLocal = /test/.test(location.host);
// var isDev = /test|localhost/.test(location.host);
// var Domain = isLocal ? '' : (isDev ? (location.protocol + '//test-appapis.95155.com') : (location.protocol + '//h5.95155.com'));


var isLocal = /localhost1/.test(location.host);
var isDev = /test|localhost/.test(location.host);
var Domain = isLocal ? '' : (isDev ? (location.protocol + '//test-appapis.95155.com') : (location.protocol + '//h5.95155.com'));
define({
  cnzz_id: '1254000558',
  domain: Domain
  //domain: "http://192.168.162.77:80"
});
