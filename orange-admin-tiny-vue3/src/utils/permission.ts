
import { useUserStore } from '@/store';

export const hasPermission = (resources: API.Button[]) => {
  const userStore = useUserStore()
  if (!userStore.buttonPermissions) {
    return false;
  }
  return resources.filter(button => {
    return userStore.buttonPermissions.some(item => {
      return button.permission.includes(item)
    })
  });
}