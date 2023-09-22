/* eslint-disable prefer-template */
import { createRouter, createWebHistory } from 'vue-router';
import NProgress from 'nprogress';
import { AdminBaseLayout } from '@/layout'
import appRoutes from './routes';

import setupPermissionGuard from './permission';

NProgress.configure({ showSpinner: false });

const constantRoutes = [
  {
    path: '/',
    redirect: `${import.meta.env.VITE_CONTEXT}login`,
  },
  {
    path: import.meta.env.VITE_CONTEXT,
    redirect: { path: `${import.meta.env.VITE_CONTEXT}login` },
  },
  {
    path: import.meta.env.VITE_CONTEXT + 'login',
    name: 'login',
    component: () => import('@/views/login/index.vue'),
    meta: {
      requiresAuth: false,
    },
  },
  {
    path: import.meta.env.VITE_CONTEXT,
    component: AdminBaseLayout,
    redirect: `${import.meta.env.VITE_CONTEXT}dashboard`,
    children: [
      {
        path: 'dashboard',
        name: 'Dashboard',
        component: () => import('@/views/admin/dashboard/index.vue'),
      }
    ]
  },
  {
    name: 'root',
    path: import.meta.env.VITE_CONTEXT,
    component: AdminBaseLayout,
    children: appRoutes,
  },
  {
    path: import.meta.env.VITE_CONTEXT + ':pathMatch(.*)*',
    name: 'notFound',
    component: () => import('@/views/not-found/index.vue'),
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes: constantRoutes,
  scrollBehavior() {
    return { top: 0 };
  },
});

setupPermissionGuard(router);

export default router;
