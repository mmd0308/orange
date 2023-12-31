import type { App } from 'vue'

import {
  Container as TinyContainer, Layout as TinyLayout,
  Breadcrumb as TinyBreadcrumb, BreadcrumbItem as TinyBreadcrumbItem,
  Drawer as TinyDrawer, Modal as TinyModal, Popover as TinyPopover,
  TreeMenu as TinyTreeMenu,
  Row as TinyRow, Col as TinyCol,
  Grid as TinyGrid, GridColumn as TinyGridColumn, GridToolbar as TinyGridToolbar, Pager as TinyPager,
  Form as TinyForm, FormItem as TinyFormItem,
  Input as TinyInput, Select as TinySelect, Option as TinyOption, Numeric as TinyNumeric,
  Search as TinySearch,
  Checkbox as TinyCheckbox, Radio as TinyRadio,
  DatePicker as TinyDatePicker,
  UserHead as TinyUserHead,
  Button as TinyButton, ActionMenu as TinyActionMenu, Switch as TinySwitch,
  Tag as TinyTag, Link as TinyLink,
  Tabs as TinyTabs, TabItem as TinyTabItem,
  Collapse as TinyCollapse, CollapseItem as TinyCollapseItem,
  Dropdown as TinyDropdown, DropdownMenu as TinyDropdownMenu, DropdownItem as TinyDropdownItem
} from '@opentiny/vue';

const components = [
  TinyContainer, TinyLayout,
  TinyBreadcrumb, TinyBreadcrumbItem,
  TinyDrawer, TinyModal, TinyPopover,
  TinyTreeMenu,
  TinyRow, TinyCol,
  TinyGrid, TinyGridColumn, TinyGridToolbar, TinyPager,
  TinyForm, TinyFormItem,
  TinyInput, TinySelect, TinyOption, TinyNumeric,
  TinySearch,
  TinyCheckbox, TinyRadio,
  TinyDatePicker,
  TinyUserHead,
  TinyButton, TinyActionMenu, TinySwitch,
  TinyTag, TinyLink,
  TinyTabs, TinyTabItem,
  TinyCollapse, TinyCollapseItem,
  TinyDropdown, TinyDropdownMenu, TinyDropdownItem
]

export const setupOpentiny = (app: App) => {
  components.forEach((component) => {
    app.component(component.name, component)
  })
}