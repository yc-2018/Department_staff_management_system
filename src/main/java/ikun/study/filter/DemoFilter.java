//仰晨study 创建时间2023/4/23 23:58 星期日
package ikun.study.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;//过滤器
import java.io.IOException;
//@WebFilter(urlPatterns = "/*")  //对全部请求都进行拦截
public class DemoFilter implements Filter {
    @Override //初始化方法，只会被调用一次  也可以不重写因为默认实现了
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("init方法执行了");
    }

    @Override   //拦截到请求之后调用  ，会调用多次
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("Demo 拦截到了请求...放行前的逻辑");
        //放行
        filterChain.doFilter(servletRequest,servletResponse);

        System.out.println("Demo 拦截到了请求...放行后的逻辑");
    }

    @Override//销毁方法，只会被调用一次  也可以不重写因为默认实现了
    public void destroy() {
        System.out.println("destroy销毁方法执行了");
    }
}
