package cn.hengzq.orange.admin.starter.mybatis.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.logging.stdout.StdOutImpl;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.boot.autoconfigure.AutoConfiguration;

/**
 * 自定义SqlSessionFactory 省略yml中 mybatis plus的配置
 *
 * @author 程序员橙子
 */
@Slf4j
@AutoConfiguration
public class SqlSessionFactoryAutoConfiguration {

    public SqlSessionFactoryAutoConfiguration(SqlSessionFactory sqlSessionFactory) {
        sqlSessionFactory.getConfiguration().setLogImpl(StdOutImpl.class);
        // 注册自定TypeHandler
        sqlSessionFactory.getConfiguration().getTypeHandlerRegistry().register("cn.hengzq.orange.admin.starter.mybatis.handler");
        if (log.isDebugEnabled()) {
            log.debug("init {} complete.", this.getClass().getSimpleName());
        }
    }
}
