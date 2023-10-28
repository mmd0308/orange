import localeLogin from '@/views/login/locale/zh-CN';
import localeTheme from '@/components/theme/locale/zh-CN';

import systemPermission from '@/views/admin/system/permission/locale/zh-CN';
import systemDict from '@/views/admin/system/dict/locale/zh-CN';
import systemRecord from '@/views/admin/system/record/locale/zh-CN';
import systemInfo from '@/views/admin/system/info/locale/zh-CN';
import systemFile from '@/views/admin/system/file/locale/zh-CN';

import locale403 from '@/views/exception/403/locale/zh-CN';
import locale404 from '@/views/exception/404/locale/zh-CN';
import locale500 from '@/views/exception/500/locale/zh-CN';

import localeGlobal from './zh-CN/global';
import localeSettings from './zh-CN/settings';
import localeHttpError from './zh-CN/httpError';

export default {
  'menu.home': '监控页',
  'menu.work': '工作台',
  'menu.exception': '异常页',
  'menu.form': '表单页',
  'menu.visualization': '数据可视化',
  'menu.system.permission': '权限管理',
  'menu.system.permission.department': '部门管理',
  'menu.system.permission.user': '用户管理',
  'menu.system.permission.role': '角色管理',
  'menu.system.dict.type': '字典管理',
  'navbar.docs': '文档中心',
  'navbar.action.locale': '切换为中文',
  'messageBox.switchRoles': '切换角色',
  'messageBox.projectConfig': '项目配置',
  'messageBox.userCenter': '用户中心',
  'messageBox.userSettings': '用户设置',
  'messageBox.logout': '退出登录',
  'menu.cloud': '云服务能力展示',
  ...localeGlobal,
  ...localeTheme,
  ...localeSettings,
  ...localeLogin,
  ...locale403,
  ...locale404,
  ...locale500,
  ...localeHttpError,
  ...systemPermission,
  ...systemDict,
  ...systemRecord,
  ...systemInfo,
  ...systemFile
};
