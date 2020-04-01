import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import '@/plugins/preloader'
import Api from '@/api'
import Antd from 'ant-design-vue';
import 'ant-design-vue/dist/antd.css';

require('@/utils/register-assets')
require('@/utils/register-components')

Vue.use(Antd);
Vue.use(Api)
Vue.config.productionTip = false

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
