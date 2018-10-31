<template>
    <div id="app">
        <div class="box">
            <transition :name="transitionName">
                <router-view class="view"></router-view>
            </transition>
        </div>
    </div>
</template>

<script>
    import util from "./libs/util";
    import axios from "axios";
    import './libs/cnzz';
    export default {
        data() {
            return {
                transitionName: ''
            };
        },
        mounted() {
            this.initCnzz();
            if (window.daka) {
                this.$router.replace({
                    name: "daka_dyr"
                })
            }
        },
        methods: {
    
        }
    };
</script>


