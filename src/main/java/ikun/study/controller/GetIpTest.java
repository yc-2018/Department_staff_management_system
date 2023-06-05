//仰晨study 创建时间2023/5/19 11:52 星期五
package ikun.study.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController("/text")
public class GetIpTest {

    @GetMapping
    public String ikun(HttpServletRequest request) {
        return request.getRemoteAddr();
    }


}
