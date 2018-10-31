import Vue from 'vue';
import App from './App.vue';
import router from './router/index';
import store from './store/index'
import MintUI from 'mint-ui';
import Execute from './libs/execute';
import './assets/css/main.css';
import './assets/css/mint-ui.css';

Vue.use(Execute);
Vue.use(MintUI);
window.vm=new Vue({
    el: '#app',
    store: store,
    router,
    render:h=>{
        return h(App)
    }
}).$mount("#app")