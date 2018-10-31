<template>
    <div class="page f-v">
        <div class="f-1 content-info">
            <div class="scroll rule t-i-3 p-t-2 p-b-2 font-2">
                <div class="font-0 t-c t-i-0" style="font-weight: 600;color: #ffe07b">中国石化-中交兴路柴油专用联名卡办卡须知</div>
                <img class="init_img" src="../../assets/images/init-bg06.jpg" style="display:block;" alt="">
                <div class="p-l-r">
                    <p style="text-indent: 0px;" class="m-t-2">尊敬的用户：</p>
                    <p>中国石化携手中交兴路隆重推出柴油专用联名卡（以下简称联名卡）。该卡执行一卡一车、限车号、设密码、限加柴油的原则。</p>
                    <p class="t-i-0 m-t">1．联名卡申办：您可通过“车旺大卡”手机客户端、车旺官网（www.95155.com）申办、“中国石化中交兴路柴油专用卡”微信服务号，审核通过后凭身份证、预约号及办卡所需资料到中国石化所属预约售卡网点领取卡片。</p>
                    <p class="t-i-0 m-t">2．联名卡充值：您可通过中交兴路线上平台申请贷款充值，详情可咨询中交兴路客服400-096-6666；或通过中国石化加油卡网上营业厅（www.sinopecsales.com）、加油卡售卡充值网点进行充值，详情可咨询中国石化客服95105888/95105988。</p>
                    <p class="t-i-0 m-t">3．联名卡优惠：您持联名卡可持续参与联名卡营销活动。第五期营销活动时间为2018年4月1日至2018年6月30日，2018年1月1日-3月31日持联名卡累计消费超过2000升的老用户，可享受叠加优惠 0.5%；优惠规则如下：</p>
                    <ul class="t-c t-i-0 p-t-b">
                        <li class="h4 b-t b-l-r f-h line-h-4">
                            <div class=" f-1 w0 f-v f-p-c">消费量阶梯</div>
                            <div class="f-1 w0 f-v f-p-c">优惠率</div>
                            <div class="f-1 w0 f-v f-p-c">叠加优惠率</div>
                        </li>
                        <li class="h4 b-t b-l-r f-h line-h-4">
                            <div class=" f-1 w0 f-v f-p-c">1000升以下</div>
                            <div class="f-1 w0 f-v f-p-c">2%</div>
                            <div class="f-1 w0 f-v f-p-c">2.5%</div>
                        </li>
                        <li class="h4 b-t b-l-r f-h line-h-4">
                            <div class=" f-1 w0 f-v f-p-c line-h-2">1000(含)<br>至 2000升</div>
                            <div class="f-1 w0 f-v f-p-c">4%</div>
                            <div class="f-1 w0 f-v f-p-c">4.5%</div>
                        </li>
                        <li class="h4 b-t b-l-r f-h line-h-4">
                            <div class=" f-1 w0 f-v f-p-c line-h-2">2000(含)<br>至 3000升</div>
                            <div class="f-1 w0 f-v f-p-c">6%</div>
                            <div class="f-1 w0 f-v f-p-c">6.5%</div>
                        </li>
                        <li class="h4 b-t b-l-r f-h line-h-4">
                            <div class=" f-1 w0 f-v f-p-c line-h-2">3000(含)<br>至 5000升</div>
                            <div class="f-1 w0 f-v f-p-c">8%</div>
                            <div class="f-1 w0 f-v f-p-c">8.5%</div>
                        </li>
                        <li class="h4 b-t-b b-l-r f-h line-h-4">
                            <div class=" f-1 w0 f-v f-p-c ">5000升(含)以上</div>
                            <div class="f-1 w0 f-v f-p-c">10%</div>
                            <div class="f-1 w0 f-v f-p-c">10.5%</div>
                        </li>
                    </ul>
                    <p>（1）第五期营销活动实行季度累进制，按累计消费量对应的阶梯及优惠率计算、返还优惠；</p>
                    <p>（2）中国石化各省市公司设置免惠最低价，当单笔柴油消费单价低于免惠最低价时，该笔交易参与消费量梯度累进，但不享受积分优惠返利。 点击查看各省市免惠最低价，本数据仅供参考，具体价格请以当地石化公布执行为准。</p>
                    <div class="mh_list w bf b-t">
                        <div class="f-h h4 line-h-4 b-b" v-for="it in list" @click="look_price(it)" :key='it'>
                            <p class="f-1 h4 line-h-4 p-l font-2">{{it.name}}</p>
                            <p class="flex-v flex-j-c p-r">
                                <img src="../../assets/images/arrow.jpg" alt="" class=" display-block" style="width: 0.8rem;height:1rem;">
                            </p>
                        </div>
                    </div>
                    <div class="fixed_dialog" v-show="is_mh_show" @click="close_dialog">
                        <div class="fixed_dialog_c">
                            <p class="h4 line-h-4 font-2 t-c">{{detail.name}} 免惠最低价</p>
                            <p class="p font-2 orange_c t-c bf">
                                <span class="font-bold font1">{{detail.price}} </span>元
                            </p>
                            <!--<img src="../images/input_del.png" alt="" style="position: absolute;top: -30px;right: 0;width: 2rem">-->
                        </div>
                    </div>
                    <p>（3）持联名卡加油后，优惠金额一般于48小时内返还到加油卡账户的“积分账户”，在您圈存积分后，可在发卡省联网加油站使用积分加油。</p>
                    <p>（4）联名卡营销活动将持续进行，越早办理越早受益，老客户享有更多特权。</p>
                    <p class="t-i-0 m-t">4．联名卡开票：</p>
                    <p>（1）现场充值开普票：在售卡充值网点充值时，可现场开具普通发票；</p>
                    <p>（2）网上充值开普票：在线上平台对联名卡充值，可在充值成功24小时后（部分省市48小时后）的90天内，携联名卡前往发卡省中国石化售卡充值网点开具普通发票；</p>
                    <p>（3）增值税发票开具：联名卡客户待加油消费后，按发卡省管理要求再开具增值税专用发票。</p>
                    <p>具体开票事宜以发卡当地税务管理机关相关要求为准。</p>
                </div>
            </div>
    
        </div>
        <img src="../../assets/images/kq-btn.png" alt="" class="w display-block fixed_img" @click="go_e" v-if="is_show">
    </div>
</template>

<script>
    import util from '../../libs/util';
    export default {
        data() {
            return {
                list: [],
                is_show: true,
                detail: {},
                is_mh_show: false
            }
        },
        mounted() {
            this.init_data();
            this.list = [{
                    name: '北京',
                    price: '6.2'
                },
                {
                    name: '天津',
                    price: '5.5'
                },
                {
                    name: '河北',
                    price: '5.5'
                },
                {
                    name: '山西',
                    price: '5.5'
                },
                {
                    name: '河南',
                    price: '5.85'
                },
                {
                    name: '山东',
                    price: '5.19'
                },
                {
                    name: '上海',
                    price: '6.2'
                },
                {
                    name: '江苏',
                    price: '6.2'
                },
                {
                    name: '浙江',
                    price: '6.2'
                },
                {
                    name: '福建',
                    price: '6.1'
                },
                {
                    name: '安徽',
                    price: '5.65'
                },
                {
                    name: '江西',
                    price: '5.5'
                },
                {
                    name: '湖北',
                    price: '6.2'
                },
                {
                    name: '湖南',
                    price: '6.2'
                },
                {
                    name: '广东',
                    price: '6.2'
                },
                {
                    name: '广西',
                    price: '6.2'
                },
                {
                    name: '贵州',
                    price: '6.2'
                },
                {
                    name: '云南',
                    price: '6.2'
                },
                {
                    name: '海南',
                    price: '6.2'
                },
                {
                    name: '黑龙江',
                    price: '5.25'
                },
                {
                    name: '吉林',
                    price: '5.25'
                },
                {
                    name: '辽宁',
                    price: '5'
                },
                {
                    name: '陕西',
                    price: '6.2'
                },
                {
                    name: '新疆',
                    price: '5.8'
                },
                {
                    name: '内蒙',
                    price: '5.5'
                },
                {
                    name: '青海',
                    price: '6.2'
                },
                {
                    name: '宁夏',
                    price: '6'
                },
                {
                    name: '甘肃',
                    price: '6'
                },
                {
                    name: '四川',
                    price: '6.2'
                },
                {
                    name: '重庆',
                    price: '6.2'
                },
                {
                    name: '西藏',
                    price: '6.2'
                },
            ]
        },
        methods: {
            init_data() {
                util.ajax.post("/newspokespeople/isBindMobile", {}).then(e => {
                    console.log(e);
                    //true已绑定 false未绑定
                    if (e.data) {
                        this.is_show = false;
                    } else {
                        this.is_show = true;
                    }
                })
            },
            look_price(a) {
                this.is_mh_show = true;
                this.detail = a;
            },
            go_e() {
                this.$router.push({
                    name: 'login'
                })
            },
            close_dialog() {
                this.is_mh_show = false;
            }
        }
    }
</script>

<style>
    @import './rule.css';
</style>
