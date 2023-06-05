//仰晨study 创建时间2023/4/23 18:39 星期日
package ikun.study.controller;

import ikun.study.pojo.Emp;
import ikun.study.pojo.Result;
import ikun.study.service.EmpService;
import ikun.study.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class LoginController {
    @Autowired
    private EmpService empService;

    @PostMapping("/login")
    public Result login(@RequestBody Emp emp) {
        log.info("用户登录:{}",emp);
        Emp e = empService.login(emp);
        //登录成功，生成令牌，下发令牌
        if (e != null) {
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", e.getId());
            claims.put("name", e.getName());
            claims.put("username", e.getUsername());

            String jwt = JwtUtils.generateJwt(claims);  //jwt包涵当前登录员工信息
            return Result.success(jwt);

        }
        //登录失败，返回错误信息
        return Result.error("账号或密码错误");
    }
}
