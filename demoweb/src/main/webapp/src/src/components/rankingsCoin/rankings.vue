<template>
    <div class="login_el">
        <div class="f-1 bf">
            <div class="login-logo p-t-3">
    
            </div>
            <div class="login-input p2">
                <div class="h4 line-h-4 b-b mobile">
                    <input class="font-1" type="tel" placeholder="请输入手机号" v-model.trim="mobile" @input="checkRules(1)">
                </div>
                <div class="h3 line-h-3 c6">
                    <span v-if="!rules.param1&&rules.if_input1" class="font-2">手机号码格式错误</span>
                </div>
                <div v-if="show_img_code">
                    <div class="rela f-h f-1 h4">
                        <div class="h4 line-h-4 b-b pwd f-1">
                            <input class="font-1" type="tel" placeholder="请填写右侧验证码" v-model.trim="imgCode" @input="checkRules(2)">
                        </div>
                        <div class="p-l-2">
                            <img :src="img_code_shape" alt="" class="login-image_code" @click="updataImgCode">
                        </div>
                    </div>
                    <div class="h3 line-h-3 c6">
                        <span v-if="!rules.param2&&rules.if_input2" class="font-2">输入4位验证码</span>
                    </div>
                </div>
                <div class="rela f-h f-1 h4">
                    <div class="h4 line-h-4 b-b pwd f-1">
                        <input class="font-1" type="tel" placeholder="请填写收到的验证码" v-model.trim="smsCode" @input="checkRules(3)">
                    </div>
                    <div class="p-l-2">
                        <p class="hot-btn w10" v-if="!if_time_load" @click="send_sms_code">获取验证码</p>
                        <p class="border-btn w10 c4 b-c-1" v-else>再次获取({{timer}}s)</p>
                    </div>
                </div>
                <div class="h3 line-h-3 c6">
                    <span v-if="!rules.param3&&rules.if_input3" class="font-2">输入6位验证码</span>
                </div>
                <div class="btn-1 border-r-5" @click="login" v-if="if_submit">
                    立即加入
                </div>
                <div class="btn-2 border-r-5" @click="login" v-else>
                    立即加入
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    import {
        Toast
    } from 'mint-ui';
    import util from '../../libs/util';
    export default {
        data() {
            return {
                mobile: null,
                imgCode: null,
                img_code_shape: '/weChatDriver/verifyCode/getPicVerifyCode',
                smsCode: null,
                timer: 60,
                if_time_load: false, // 是否倒计时时间
                if_send_code: false, // 是否发送验证码
                if_submit: false,
                rules: {
                    if_input1: false, //监测输入内容，true输入，false null
                    if_input2: false,
                    if_input3: false,
                    param1: false, //正则验证结果，true通过，false未通过
                    param2: false,
                    param3: false
                },
                imgCode_url: null, //拼图验证码
                show_img_code: false, //是否需要显示图片验证码
            }
        },
        methods: {
            initData() {
                Toast('success');
            },
            checkRules(a) {
                this.p_rule = a;
                new Promise(this.checkForm);
            },
            checkForm: function(resolve, reject) {
                var rul = this.rules,
                    p = this.p_rule;
                // this.if_submit = true;
                // return false;
                if (!p || (p && p == 1)) {
                    if (!this.mobile || (this.mobile && !/^(1[0-9]{2})\d{8}$/i.test(this.mobile))) {
                        if (!this.mobile) {
                            rul.if_input1 = false;
                        } else {
                            rul.if_input1 = true;
                        }
                        rul.param1 = false;
                    } else {
                        rul.param1 = true;
                    }
                }
                if (this.show_img_code && (!p || (p && p == 2))) {
                    if (!this.imgCode || (this.imgCode && !/^\d{4}$/i.test(this.imgCode))) {
                        if (!this.imgCode) {
                            rul.if_input2 = false;
                        } else {
                            rul.if_input2 = true;
                        }
                        rul.param2 = false;
                    } else {
                        rul.param2 = true;
                    }
                }
                if (!p || (p && p == 3)) {
                    if (!this.smsCode || (this.smsCode && !/^\d{6}$/i.test(this.smsCode))) {
                        if (!this.smsCode) {
                            rul.if_input3 = false;
                        } else {
                            rul.if_input3 = true;
                        }
                        rul.param3 = false;
                    } else {
                        rul.param3 = true;
                    }
                }
    
                //正则验证通过 && 发送成功验证码
                if (rul.param1 && rul.param3 && (!this.show_img_code||(this.show_img_code && rul.param2)) && this.if_send_code) {
                    this.if_submit = true;
                    resolve(true);
                } else {
                    this.if_submit = false;
                    resolve(false);
                }
            },
            updataImgCode() {
                this.img_code_shape = "/weChatDriver/verifyCode/getPicVerifyCode?v=" + new Date().getTime()
            },
            //发送验证码
            send_sms_code() {
                var that = this;
                if (!that.mobile) return Toast('请输入手机号！');
                if (!this.mobile || (this.mobile && !/^(1[0-9]{2})\d{8}$/i.test(this.mobile))) {
                    return Toast('请输入正确的手机号！');
                }
                if (this.show_img_code) {
                    if (!that.imgCode) return Toast('请输入图片验证码！');
                    if (!this.imgCode || (this.imgCode && !/^\d{4}$/i.test(this.imgCode))) {
                        return Toast('请输入4位图片验证码！');
                    }
                }
    
                var url = "/newspokespeople/getValidateCode",
                    _p = {
                        mobile: that.mobile,
                        // picVerifyCode:that.imgCode
                    };
                util.ajax.post(url, _p).then(r => {
                    if (r.code == 200) {
                        Toast('短信验证码发送成功!');
                        that.load_timer();
                    }
                })
            },
            load_timer() {
                var that = this;
                that.if_time_load = true;
                that.if_send_code = true;
                setTimeout(function() {
                    that.timer--;
                    if (that.timer == 0) {
                        that.if_time_load = false;
                        that.timer = 60;
                        return false;
                    } else {
                        that.load_timer();
                    }
                }, 1000)
            },
            login() {
                var that = this,
                    url = "/vchat/spokesperson/registerOrLogin",
                    _p = {
                        mobile: that.mobile,
                        validateCode: that.smsCode,
                    };
    
                new Promise(this.checkForm).then(function(e) {
                    if (e) {
                        util.ajax.post(url, _p).then(function(r) {
                            if (r.code == 200) {
                                // that.$router.replace({name:"login_end"});
                                location.href='/dyr-wx/index.html#/login_end';
                            }
                        })
                    }
                });
            }
        }
    };
</script>

<style lang="less">
    @import "login.less";
</style>


