import localeLogin from '@/views/login/locale/en-US';
import localeTheme from '@/components/theme/locale/en-US';

import locale403 from '@/views/exception/403/locale/en-US';
import locale404 from '@/views/exception/404/locale/en-US';
import locale500 from '@/views/exception/500/locale/en-US';

import localeSettings from './en-US/settings';

import localeHttpError from './en-US/httpError';

export default {
  'menu.home': 'Monitoring page',
  'menu.work': 'workbench',
  'menu.exception': 'Exception',
  'menu.form': 'Form',
  'menu.visualization': 'Data Visualization',
  'navbar.docs': 'Docs',
  'navbar.action.locale': 'Switch to English',
  'messageBox.switchRoles': 'Switch Roles',
  'messageBox.userCenter': 'User Center',
  'messageBox.userSettings': 'User Settings',
  'messageBox.logout': 'Logout',
  'menu.cloud': 'Cloud service capability',
  ...localeTheme,
  ...localeSettings,
  ...localeLogin,
  ...locale403,
  ...locale404,
  ...locale500,
  ...localeHttpError,
};
