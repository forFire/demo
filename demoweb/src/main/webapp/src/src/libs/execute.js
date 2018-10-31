//立即执行代码
import {
    isWeChat
} from './tools';
const Execute = {};
Execute.install = function (Vue, options) {
     //cnzz init
    Vue.prototype.initCnzz = () => {
        var _czc = window._czc || [];
        _czc.push(["_setAccount", "1254000558"]);
    }
    Vue.prototype.setCnzz = a => {
        _czc.push(["_trackEvent", a.type == "p" ? "PVUV" : "Event", isWeChat() ? '代言人_wx' : '代言人_app', a.content]);
    }
}
export default Execute;