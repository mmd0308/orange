package com.hzqing.orange.admin.starter.common.message;

import cn.hutool.core.util.StrUtil;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import java.util.Locale;

public class LocalMessage {

    private static final MessageSource messageSource;

    static {
        ReloadableResourceBundleMessageSource bundleMessageSource = new ReloadableResourceBundleMessageSource();
        bundleMessageSource.setDefaultEncoding("UTF-8");
        bundleMessageSource.setUseCodeAsDefaultMessage(true);
        bundleMessageSource.setCacheSeconds(3600);
        bundleMessageSource.setBasenames(new String[]{"classpath:i18n/messages"});
        messageSource = bundleMessageSource;
    }

    /**
     * 根据错误编码 获取消息详情
     */
    public static String getErrMsg(String code) {
        return getMessage(code, null, LocaleContextHolder.getLocale());
    }

    /**
     * 获取指定语言的Message
     *
     * @param code 消息编码
     */
    public static String getMessage(String code, Object[] params, Locale locale) {
        if (locale == null) {
            locale = Locale.CHINESE;
        }
        if (StrUtil.isBlank(code)) {
            return StrUtil.EMPTY;
        }
        String result = messageSource.getMessage(code, params, locale);
        if (code.equals(result)) {
            return StrUtil.EMPTY;
        }
        return result;
    }

}
