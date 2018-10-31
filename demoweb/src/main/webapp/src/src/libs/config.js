import base64 from './base64';
import {isWeChat,getCookie,setCookie} from './tools';
let common_param={};
const source=isWeChat()?'lmkspokeswechat':"lmkspokesapp";//1:wx 0:app
const moke_param={
    token: "3wx_15B02145249A7A1F7BF15AFF47E2AB1A",
    // userId: "6ddc9556-05cf-45f2-a62c-6efc0808951e",
    userId: "c13630f9-0952-45e2-810b-6a5e6c0044f1",
    openId: 'ohrfawyy_H-7NQoMidq52g9d7Twk',
    mobile: '18973927020',
    source:source
};
const get_usr_info=()=>{
    const vx=isWeChat();
    // console.log(vx);
    if (vx) {
        if (getCookie("spokes_openId")) {
            /*common need param*/
            common_param.token = getCookie("spokes_token");
            common_param.userId = getCookie("spokes_userId");
            common_param.openId = getCookie("spokes_openId");
            common_param.mobile = getCookie("spokes_mobile");
            common_param.source = source;
        } else if(/localhost/.test(location.href)){
            common_param = moke_param;
        }else{
    
        }
    } else{
        if(window.daka){
            const _str = base64.utf8to16(base64.base64decode(window.daka.getMobileApp()));
                const _mobile = JSON.parse(_str);
                const userId = _mobile.uaaId;
                const token = _mobile.tokenId;
                const mobileNo = _mobile.mobileNo;
                common_param.token = token;
                common_param.userId = userId;
                common_param.mobile = mobileNo;
                common_param.source = source;
        }else if(/localhost/.test(location.href)){
            common_param = moke_param;
        }else{
    
        }
       
    }
    return common_param;
}

export default get_usr_info;
