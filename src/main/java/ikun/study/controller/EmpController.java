package ikun.study.controller;

import ikun.study.anno.Log;
import ikun.study.pojo.Emp;
import ikun.study.pojo.PageBean;
import ikun.study.pojo.Result;
import ikun.study.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * 员工管理Controller
 */
@Slf4j
@RestController
@RequestMapping("/emps")
public class EmpController {
    @Autowired
    private EmpService empService;

    /**<a href="http://demo.org:8080/emps">分页查询</a>*/
    @GetMapping
    public Result parse(@RequestParam(defaultValue = "1") Integer page,     //前端没给就设置默认值1 @RequestParam(defaultValue = "1")
                        @RequestParam(defaultValue = "10") Integer pageSize,
                        String name, Short gender,
                        @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,    //要想成功接收前端请求过来的日期参数 就要加这个注解
                        @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end) {
        log.info("分页查询,参数:{},{},{},{},{},{}",page,pageSize,name,gender,begin,end);
        //调用service分页查询
        PageBean pageBean = empService.page(page,pageSize,name,gender,begin,end);
        return Result.success(pageBean);
    }

    @Log
    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable List<Integer> ids) {
        log.info("删除员工:{}",ids);

        empService.delete(ids);
        return Result.success();
    }

    @Log
    @PostMapping
    public Result save(@RequestBody Emp emp) {
        log.info("新增员工:{}",emp);
        empService.save(emp);
        return Result.success();
    }



    /**<a href="http://demo.org:8080/emps/3">查询员工信息（回显）</a>*/
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        log.info("根据ID查询员工信息,id:{}",id);
        Emp emp = empService.getById(id);
        return Result.success(emp);
    }

    /**
     * 更新员工信息
     */
    @Log
    @PutMapping
    public Result update(@RequestBody Emp emp) {
        empService.update(emp);
        return Result.success();
    }
}
