package ikun.study.controller;

import ikun.study.anno.Log;
import ikun.study.pojo.Dept;
import ikun.study.pojo.Result;
import ikun.study.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 部门管理Controller
 */
@Slf4j // == private static Logger log = LoggerFactory.getLogger(DeptController.class);
@RestController
@RequestMapping("/depts")
public class DeptController {

    @Autowired
    private DeptService deptService;

    /**<a href="http://demo.org:8080/depts">查询全部部门数据</a>
     * 只允许get请求 其他请求方法报405*/
//    @RequestMapping(value = "/depts",method = RequestMethod.GET)
    @GetMapping
    public Result list() {
        log.info("查询全部部门数据");
        List<Dept> deptList = deptService.list();

        return Result.success(deptList);
    }

    /**
     * 删除部门数据
     */
    @Log
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) throws Exception {
        log.info("根据id删除部门:{}",id);
        //调用service删除部门
        deptService.delete(id);
        return Result.success();
    }

    /**新增部门*/
    @Log
    @PostMapping
    public Result add(@RequestBody Dept dept) {
        log.info("新增部门:{}",dept);
        // 调用service新增部门
        deptService.add(dept);
        return Result.success();
    }

    /**
     * 根据ID查询(自习完成
     */
    @GetMapping("/{id}")
    public Result getDeptById(Dept dept) {      //算了应该是Integer类型的
        log.info("根据ID:{}查询部门",dept);
        Dept dept1 = deptService.getDeptById(dept);
        return Result.success(dept1);
    }

    /**
     * 修改部门名字
     */
    @Log
    @PutMapping
    public Result updateDept(@RequestBody Dept dept) {
        log.info("修改部门{}",dept);
        deptService.update(dept);
        return Result.success();
    }
}
