package cn.hengzq.orange.admin.starter.common.message;

import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.context.support.ResourceBundleMessageSource;

import java.util.Locale;
import java.util.ResourceBundle;


public class GlobalMessageSource extends ResourceBundleMessageSource {

    private static final GlobalMessageSource instance = new GlobalMessageSource();

    public static GlobalMessageSource getInstance() {
        return instance;
    }

    public GlobalMessageSource() {
        setDefaultEncoding("UTF-8");
        setBasename("cn.hengzq.orange.admin.starter.common.global");
    }

    @Override
    public ResourceBundle getResourceBundle(String basename, Locale locale) {
        return super.getResourceBundle(basename, locale);
    }

    public static MessageSourceAccessor getAccessor() {
        return new MessageSourceAccessor(getInstance());
    }
}
