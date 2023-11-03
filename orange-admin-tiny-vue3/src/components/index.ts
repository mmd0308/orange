import { App } from 'vue';
import Breadcrumb from './breadcrumb/index.vue';
import DictTag from './dict-tag/index.vue'
import SvgIcon from './svg-icon/index.vue'
import Screenfull from './screenfull/Index.vue';
import ToggleLanguage from './language/index.vue';

export default {
  install(Vue: App) {
    Vue.component('Breadcrumb', Breadcrumb);
    Vue.component('DictTag', DictTag)
    Vue.component('SvgIcon', SvgIcon)
    Vue.component('Screenfull', Screenfull)
    Vue.component('ToggleLanguage', ToggleLanguage)
  },
};
