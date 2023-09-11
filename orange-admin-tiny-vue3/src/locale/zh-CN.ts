import localeLogin from '@/views/login/locale/zh-CN';
import localeTheme from '@/components/theme/locale/zh-CN';


import systemPermission from '@/views/admin/system/permission/locale/zh-CN';


import localeSearchTable from '@/views/list/search-table/locale/zh-CN';

import localeStepForm from '@/views/form/step/locale/zh-CN';
import localeBaseForm from '@/views/form/base/locale/zh-CN';

import localeDetailForm from '@/views/profile/detail/locale/zh-CN';

import locale403 from '@/views/exception/403/locale/zh-CN';
import locale404 from '@/views/exception/404/locale/zh-CN';
import locale500 from '@/views/exception/500/locale/zh-CN';

import localekanban from '@/views/board/locale/zh-CN';

import localeHello from '@/views/cloud/hello/locale/zh-CN';

import localeContracts from '@/views/cloud/contracts/locale/zh-CN';

import localeGlobal from './zh-CN/global';

import localeSettings from './zh-CN/settings';

import localeHttpError from './zh-CN/httpError';

export default {
  'menu.board': '看板',
  'menu.home': '监控页',
  'menu.work': '工作台',
  'menu.list': '列表页',
  'menu.exception': '异常页',
  'menu.form': '表单页',
  'menu.profile': '详情页',
  'menu.profile.detail': '基础详情页',
  'menu.visualization': '数据可视化',
  'menu.system.permission': '权限管理',
  'menu.system.permission.department': '部门管理',
  'menu.system.permission.user': '用户管理',
  'menu.system.permission.role': '角色管理',
  'navbar.docs': '文档中心',
  'navbar.action.locale': '切换为中文',
  'messageBox.switchRoles': '切换角色',
  'messageBox.userCenter': '用户中心',
  'messageBox.userSettings': '用户设置',
  'messageBox.logout': '退出登录',
  'menu.cloud': '云服务能力展示',
  ...localeGlobal,
  ...localeTheme,
  ...localeSettings,
  ...localeLogin,
  ...localeSearchTable,
  ...localeStepForm,
  ...localeBaseForm,
  ...locale403,
  ...locale404,
  ...locale500,
  ...localeDetailForm,
  ...localekanban,
  ...localeHello,
  ...localeContracts,
  ...localeHttpError,
  ...systemPermission,
};
