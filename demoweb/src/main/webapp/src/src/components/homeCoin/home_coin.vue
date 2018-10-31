<template>
  <div class="profit_record">
    <div class="w bf h5 flex-h c2">
      <div class="flex-1 b-t" @click="click_tab(1)" :class="status==1?'profit_record_hot':''">
        <div class="h5 line-h-5 font-2 t-c flex-h flex-j-c">
          <p>已到账订单</p>
        </div>
      </div>
      <div class="flex-1 b-t" @click="click_tab(0)" :class="status==0?'profit_record_hot':''">
        <div class="h5 line-h-5 font-2 t-c flex-h flex-j-c">
          <p>未到账订单</p>
          <p class="w2 h5" v-if="status==0" @click="show_info"><span class="sp_i">?</span></p>
        </div>
      </div>
      <div class="flex-1 b-t" @click="click_tab(2)" :class="status==2?'profit_record_hot':''">
        <div class="h5 line-h-5 font-2 t-c flex-h flex-j-c">
          <p>不计奖励订单</p>
        </div>
      </div>
    </div>
    <div class="contenter">
      <div class="page-infinite-wrapper" ref="wrapper" :style="{ height: wrapperHeight + 'px' }">
        <ul class="page-infinite-list" v-if="list.length>0" v-infinite-scroll="loadMore" infinite-scroll-disabled="loading" infinite-scroll-distance="20">
          <div class="cont_1 b-b b-t">
            <div class="w bf p flex-h b-b" v-for="it in list" :key="it.id">
              <div class="flex-2">
                <p class="font-1 h3 line-h-3">
                  {{it.realName}}
                  <span v-if="it.tranType==0">单卡领卡</span>
                  <span v-else-if="it.tranType==1">开通垫款加油服务</span>
                  <span v-else-if="it.tranType==2">首次使用垫款加油服务</span>
                  <span v-else-if="it.tranType==3">主副卡消费</span>
                </p>
                <p class="font-2 c2">{{format_d(it.time,'yyyy-MM-dd hh:mm:ss')}} {{it.vehicleNo}}</p>
              </div>
              <div class="p-l flex-v flex-j-c">
                <p class="t-r font-0 c4 bold"> +{{it.money}}元</p>
              </div>
            </div>
          </div>
        </ul>
        <ul v-else>
          <div class="w m-t-3 t-c">
            <img src="../../assets/images/null_bg.png" alt="" class="w5 h5">
            <p class="h4 line-h-4 font-2 c2">您还没有订单记录</p>
          </div>
        </ul>
        <p v-show="loading&&!null_data" class="page-infinite-loading">
          <mt-spinner type="fading-circle"></mt-spinner>
          加载中...
        </p>
        <p v-if="null_data&&page>1" class="page-infinite-loading">
          兄弟，真的没有了...
        </p>
      </div>
    </div>
  </div>
</template>

<script>
  import Vue from 'vue';
  import {
    MessageBox,
    Toast,
    InfiniteScroll
  } from 'mint-ui';
  import {
    format
  } from "../../libs/tools";
  import util from '../../libs/util';
  Vue.use(InfiniteScroll);
  export default {
    data() {
      return {
        // list1: [{}],
        // list2: [{}, {}],
        // list3: [{}, {}, {}],
        page: 1,
        status: 1, //0未到账，1已到账，2不计奖励
        list: [],
        loading: false,
        allLoaded: false,
        wrapperHeight: 0,
        page: 0,
        null_data: false,
      }
    },
    mounted() {
      // this.init_data()
      this.loadMore();
    },
    methods: {
      init_data() {
        var that = this,
          _p = {
            status: that.status,
            page: that.page,
            pageSize: 20,
          };
        util.ajax.post("/newspokespeople/getSpokesTransactions", _p).then(e => {
          if (e.code == 200) {
            console.log(that.page);
            if (that.page == 1) {
              that.list = e.data.list;
            } else {
              that.list = that.list.concat(e.data.list);
            }
            if (that.list.length < 20) {
              that.null_data = true;
              return false;
            }
            setTimeout(() => {
              that.loading = false;
            }, 1000);
          }
        })
      },
      click_tab(a) {
        // status: 0未到账，1已到账，2不计奖励
        if (a == 0) {
          this.setCnzz({
            type: "e",
            content: "未到账订单"
          });
        } else if (a == 1) {
          this.setCnzz({
            type: "e",
            content: "已到账订单"
          });
        } else if (a == 2) {
          this.setCnzz({
            type: "e",
            content: "不计奖励订单"
          });
        }
        this.status = a;
        this.page = 0;
        this.null_data = false;
        this.list = [];
        this.loadMore();
      },
      show_info() {
        MessageBox.alert(
          '<div class="font-2 t-l line-h-2 p-t">' +
          '什么情况下会存在未到账订单？ <br>' +
          '1.  单卡提交办卡，但是还没有领卡。 <br>' +
          '2.  主副卡提交办卡但是还没有领卡或领卡后还没有产生消费。 <br>' +
          '3.  开通垫款加油服务的申请已经提交，但是审核尚未通过，或者该用户名下还没有可用的柴油专用卡。 <br>' +
          '4.  首次使用垫款加油服务的申请已经提交，但是用户名下还没有可用的柴油专用卡，无法充值到账。您需要设置一张银行卡作为提现的收款账户。' +
          '</div>', '');
      },
      format_d(a, b) {
        if (a) {
          return format(a, b);
        }
      },
      loadMore() {
        this.loading = true;
        this.page++;
        this.init_data();
  
      }
    }
  }
</script>

<style lang='less'>
  @import 'profit-record.less';
</style>
