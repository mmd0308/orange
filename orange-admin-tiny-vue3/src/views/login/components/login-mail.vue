<template>
  <div class="login-form-container">
    <tiny-form ref="loginFormMail" :model="loginMail" class="login-form" :rules="rules" validate-type="text"
      label-width="0" size="medium">
      <tiny-form-item prop="mailname" size="medium">
        <tiny-input v-model="loginMail.mailname" :placeholder="$t('login.form.mailName.placeholder')">
        </tiny-input>
      </tiny-form-item>

      <tiny-form-item prop="mailpassword" size="medium">
        <tiny-input v-model="loginMail.mailpassword" type="password" show-password
          :placeholder="$t('login.form.mailpassword.placeholder')">
        </tiny-input>
      </tiny-form-item>

      <div class="login-form-options">
        <tiny-checkbox>{{ $t('login.form.rememberPassword') }}</tiny-checkbox>
        <div>
          <tiny-link type="primary">
            {{ $t('login.form.forgetPassword') }}
          </tiny-link>
          <tiny-link type="primary" class="divide-line">|</tiny-link>
          <tiny-link type="primary" @click="typeChange">
            {{ $t('login.form.registration') }}
          </tiny-link>
        </div>
      </div>
      <tiny-form-item size="medium">
        <tiny-button type="primary" class="login-form-btn" :loading="loading" @click="handleSubmit">{{
          $t('login.form.login') }}</tiny-button>
      </tiny-form-item>
    </tiny-form>
  </div>
</template>

<script lang="ts" setup>
import { inject, ref, reactive, computed } from 'vue';
import { useRouter } from 'vue-router';
import { useI18n } from 'vue-i18n';
import { useUserStore } from '@/store';

const { proxy } = getCurrentInstance() as any

const router = useRouter();
const { t } = useI18n();
const loading = ref(false)
const userStore = useUserStore();
const loginFormMail = ref();

const rules = computed(() => {
  return {
    mailname: [
      {
        required: true,
        message: t('login.form.mailName.errMsg'),
        trigger: 'change',
      },
    ],
    mailpassword: [
      {
        required: true,
        message: t('login.form.mailpassword.errMsg'),
        trigger: 'change',
      },
    ],
  };
});

const loginMail = reactive({
  mailname: 'admin',
  mailpassword: 'eeX00Wzl3zXFB+4QsmpgYX7zChzxqde/M8w21o3PKifqmMzjIiG8fH+j34iJcGoCt3iZS/aJ5l2q9QztIwVhKRDz3VhQEP0NClbT7sKECg5DPK98gxGlnTUYJ3w8fMF91gXdSA6BrwEdRtlNfSiNeL9OXiyua964ITohLzFMMzE=',
  rememberPassword: true,
});

// 切换模式
const handle: any = inject('handle');
const typeChange = () => {
  handle(true);
};

function handleSubmit() {
  loginFormMail.value?.validate(async (valid: boolean) => {
    if (!valid) {
      return;
    }
    loading.value = true
    try {
      await userStore.login({
        username: loginMail.mailname,
        password: loginMail.mailpassword,
      });
      proxy.$modal.message({
        message: t('login.form.login.success'),
        status: 'success',
      });
      router.push({ path: `${import.meta.env.VITE_CONTEXT}dashboard`, });
    } catch (err) {
      proxy.$notify({
        type: 'error',
        title: t('login.tip.right'),
        message: t('login.tip.mail'),
        position: 'top-right',
        duration: 2000,
        customClass: 'my-custom-cls',
      });
    } finally {
      loading.value = false
    }
  });
}
</script>

<style lang="less" scoped>
.login-form-container {
  margin-top: 5%;
}

.login-form {
  margin-left: 6%;

  &-container {
    width: 320px;
  }

  &-options {
    display: flex;
    align-items: center;
    justify-content: space-between;
    margin-bottom: 20px;
    font-size: 12px;
  }

  &-btn {
    display: block;
    width: 100%;
    max-width: 100%;
  }
}

.divide-line {
  margin: 0 5px;
}

// responsive
@media (max-width: @screen-ms) {
  .login-form {
    margin-left: 5%;

    &-container {
      width: 240px;
    }
  }
}
</style>