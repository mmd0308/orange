import { createApp } from 'vue';
import * as echarts4 from 'echarts4';
import globalComponents from '@/components';
import dict from '@/utils/dict'

// Icon 
import 'virtual:svg-icons-register'

// 引入 opentiny 组件
import { Modal, Notify } from '@opentiny/vue';
import { setupOpentiny } from '@/plugins/opentiny'

import router from './router';

// API接口拦截
import '@/api/interceptor';

import store from './store';
import i18n from './locale';

import App from './App.vue';
import '@/assets/style/index.less';
// eslint-disable-next-line import/extensions
import 'echarts4/map/js/china.js';
import chinaMap from './assets/chaina.json';

echarts4.registerMap('china', chinaMap);
const app = createApp(App);

app.use(router);
app.use(store);
app.use(i18n({ locale: 'zhCN' }));
app.use(globalComponents);

setupOpentiny(app)

app.config.globalProperties.$dict = dict
app.config.globalProperties.$modal = Modal
app.config.globalProperties.$notify = Notify

app.mount('#app');