package org.wuancake;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @author Ericheel
 * @Description: 启动
 * @date 2018/6/1118:21
 */
public class SpringBootStarterApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(AdminBackApplication.class);
    }
}
