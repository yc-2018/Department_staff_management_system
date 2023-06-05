//仰晨study 创建时间2023/4/24 1:52 星期一
package ikun.study.config;

import ikun.study.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration //配置类
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private LoginInterceptor loginInterceptor;

    /**
     * 注册自己定义的拦截器LoginInterceptor
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)   //注册拦截器
                .addPathPatterns("/**")             //拦截全部路径    /*是一级路径/**是全部路径
                .excludePathPatterns("/login");     //放行/login路径

    }
}
