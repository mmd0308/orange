import { createApp } from 'vue';
import * as echarts4 from 'echarts4';
import globalComponents from '@/components';
import dict from '@/utils/dict'
import DictTag from '@/components/DictTag/index.vue'
import { Modal } from '@opentiny/vue';

import 'virtual:svg-icons-register'
import SvgIcon from '@/components/svg-icon/index.vue'

import router from './router';
import store from './store';
import i18n from './locale';
import directive from './directive';
import { setupProdMockServer } from './mockProdServer';
import './mock';
import App from './App.vue';
import '@/api/interceptor';
import '@/assets/style/index.less';
// eslint-disable-next-line import/extensions
import 'echarts4/map/js/china.js';
import chinaMap from './assets/chaina.json';


if (import.meta.env.VITE_USE_MOCK) setupProdMockServer();
echarts4.registerMap('china', chinaMap);
const app = createApp(App);

app.use(router);
app.use(store);
app.use(i18n({ locale: 'zhCN' }));
app.use(globalComponents);
app.use(directive);

app.mount('#app');


// 挂在全局组件
app.component('DictTag', DictTag)
app.component('SvgIcon', SvgIcon)

app.config.globalProperties.$dict = dict
app.config.globalProperties.$modal = Modal