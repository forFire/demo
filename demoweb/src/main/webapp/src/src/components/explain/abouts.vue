<template>
    <div class="invite">
        <div v-if="notice_content">
            <marquee class="box__marquee" @click="notice_link_turn" direction="left" behavior="scroll" scrollamount="5" scrolldelay="200">
                {{notice_content}}
            </marquee>
        </div>
        <div class="daka_update_dialog flex-v flex-j-c flex-a-c" v-show="show_dialog" @click="close_show_dialog">
            <img src="../../assets/images/daka_update_dialog.png" alt="">
        </div>
        <div class="invite-bg" @click='close_toast'>
            <div>
                <div class="invite-c">
                    <div>
                        <img :src="img_url" alt="">
                    </div>
                    <p class="line-h-3 h3 bold t-c font-0 m-t" v-show="isWx">
                        长按发送给好友
                    </p>
                    <p class="line-h-3 h3 c2 t-c font-2">
                        {{time}}前有效，重新进入会更新
                    </p>
                </div>
                <div class="invite-evnt">
                    <div @click="open_layer"></div>
                    <div @click="turn_mj"></div>
                </div>
            </div>
        </div>
        <!-- <div class="invite-aside">
                        <div class="f-1 f-h">
                            <p class="f-1 p-l font-2 bold w4">下一单收益</p>
                            <p class="f-1 money-icon">奖金+10</p>
                            <p class="p-r c2 font-3">剩余3天过期</p>
                        </div>
                    </div> -->
        <div class="w" style="height:5.5rem"></div>
        <base-footer class="fixed-footer">
        </base-footer>
    </div>
</template>

<script>
    import {
        Toast
    } from 'mint-ui';
    import BaseFooter from "../common/BaseFooter.vue";
    import util from '../../libs/util';
    import {
        format,
        getCookie,
        isWeChat,
        dakaDyrUrl,
trim
    } from '../../libs/tools';
    let instance;
    export default {
        data() {
            return {
                notice_content: '',
                notice_link: null,
                show_dialog: false,
                img_url: null,
                time: null,
                isWx:isWeChat()
                // popupVisible:true
            };
        },
        components: {
            BaseFooter
        },
        mounted() {
            // if (window.daka) {
            //     this.$router.replace({
            //         name: "daka_dyr"
            //     })
            // }
            this.init_data();
            this.get_notice();
            this.handle_dialog();
            this.setCnzz({
                type: "p",
                content: "邀请好友"
            });
        },
        methods: {
            init_data() {
                var that = this,
                user_id=isWeChat()?getCookie("spokes_userId"):header_params.userId,
                user_mobile=isWeChat()?getCookie("spokes_mobile"):header_params.mobile;
                that.img_url = "/vchat/qr/getQr?type=2&flag=4&param=spokes_" + user_id + "_" + user_mobile;
                that.time = format(new Date().getTime() + 1000 * 60 * 60 * 24 * 29, 'yyyy-MM-dd')
    
            },
            get_notice() {
                var that = this;
                util.ajax.post("/newspokespeople/getSpokesAd").then(e => {
                    if (e.data.length > 0) {
                        that.notice_content = e.data[0].content;
                        that.notice_link = e.data[0].link;
                    }
    
                })
            },
            handle_dialog() {
                if (!isWeChat() && !localStorage.getItem("dialog_is")) {
                    this.show_dialog = true;
                }
            },
            turn_mj() {
                this.setCnzz({
                    type: "e",
                    content: "高收益秘籍"
                });
                if (instance) instance.close();
                this.$router.push({
                    name: 'mj'
                });
            },
            open_layer() {
                this.setCnzz({
                    type: "e",
                    content: "邀请好友说明问号"
                });
                if (instance) instance.close();
                instance = Toast({
                    message: '邀请好友扫描专属二维码关注或进入"中国石化中交兴路柴油专用卡"公众号，好友办卡、消费、开通和使用垫款加油服务，代言人均可获得对应收益',
                    position: 'top',
                    duration: 5000
                });
                window.instance = instance;
            },
            close_toast() {
                if (instance) instance.close();
            },
            notice_link_turn() {
                if (this.notice_link) location.href = this.notice_link;
            },
            close_show_dialog() {
                this.show_dialog = false;
                localStorage.setItem("dialog_is", 1);
            }
        }
    };
</script>

<style lang="less">
    @import "invite.less";
</style>


