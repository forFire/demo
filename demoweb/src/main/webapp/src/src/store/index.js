import Vue from 'vue';
import Vuex from 'vuex';
Vue.use(Vuex);

const store = new Vuex.Store({
    state: {
      'status':null //
    },
    mutations: {
      change_status (state,status) {
        state.status=status;
        console.log(state.status);
      }
    }
  })
  export default store;
//   store.commit('increment')