//仰晨study 创建时间2023/4/24 1:00 星期一
package ikun.study.filter;

import com.alibaba.fastjson.JSONObject;
import ikun.study.pojo.Result;
import ikun.study.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**过滤器*/
@Slf4j
//@WebFilter("/*")
public class LoginCheckFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        //1.获取请求URL
        String url = req.getRequestURI();
        log.info("请求的URL为:{}",url);

        //2.判断请求头是否包含登录login，如果包含，说明是登录操作，放行
        if (url.contains("login")) {
            log.info("登录操作,放行");
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }
        //3.获取请求头中的令牌（token）.
        String jwt = req.getHeader("token");

        //4.判断令牌是否存在，如果未存在 就返回错误结果（未登录）
        if (!StringUtils.hasLength(jwt)) {
            log.info("请求头token为空,返回未登录数据");
            Result error = Result.error("NOT_LOGIN");
            //手动转换--》对象-》json   -=》阿里巴巴fastJSON
            String notLogin = JSONObject.toJSONString(error);
            resp.getWriter().write(notLogin);
            return;
        }

        //5.解析token 如果解析失败就返回错误结果（未登录）
        try {
            JwtUtils.parseJWT(jwt);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("解析令牌失败,返回未登录错误信息");
            Result error = Result.error("NOT_LOGIN");
            //手动转换--》对象-》json   -=》阿里巴巴fastJSON
            String notLogin = JSONObject.toJSONString(error);
            resp.getWriter().write(notLogin);
            return;
        }

        //6.放行
        log.info("令牌合法,放行");
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
