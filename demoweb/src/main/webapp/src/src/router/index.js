import Vue from 'vue';
import VueRouter from 'vue-router';
import routes from './router';
import {title} from '../libs/tools';
import { Indicator,MessageBox } from 'mint-ui';
Vue.use(VueRouter);
const RouterConfig={
    // mode: 'history',
    base: __dirname,
    routes: routes
 }
 const router=new VueRouter(RouterConfig);
router.beforeEach((to,from,next)=>{
    //loading=start
    Indicator.open({ spinnerType: 'fading-circle'});
    title(to.meta.title)
    next();
})
router.afterEach((to,from)=>{
    //components=invite:[close invite dialog]
    if (window.instance) window.instance.close();
    setTimeout(() => {
        Indicator.close();
        MessageBox.close();
    }, 200);
   //loading=end
//    window.scrollTo(0,0);
})
export default router;