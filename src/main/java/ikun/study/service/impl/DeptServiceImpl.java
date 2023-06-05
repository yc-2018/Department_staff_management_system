package ikun.study.service.impl;

import ikun.study.mapper.DeptMapper;
import ikun.study.mapper.EmpMapper;
import ikun.study.pojo.Dept;
import ikun.study.pojo.DeptLog;
import ikun.study.service.DeptLogService;
import ikun.study.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptMapper deptMapper;
    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private DeptLogService deptLogService;

    @Override
    public List<Dept> list() {
        return deptMapper.list();
    }

    /**
     * Transactional:Spring事务管理（类，接口，方法都可以用（推荐，因为简单的增删改查都是不需要的））（下面的数据库方法 要么全部成功(自动提交)要么全部失败（自动回滚）)
     * <p>
     * `@Transactional(rollbackFor)` 是 Spring 框架中用来配置事务回滚行为的注解，其中 `rollbackFor` 属性指定了哪些异常需要回滚事务。[(<a href="https://www.marcobehler.com/guides/spring-transaction-management-transactional-in-depth">[1]</a>)][(<a href="https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/transaction/annotation/Transactional.html">[2]</a>)][(<a href="https://www.jianshu.com/p/c5988db897fc">[3]</a>)][(<a href="https://www.baeldung.com/transaction-configuration-with-jpa-and-spring">[4]</a>)]
     * 默认情况下，`@Transactional` 注解只会在遇到运行时异常或 Error 时回滚事务。但是对于一些非运行时异常，默认是不回滚事务的。而通过 `rollbackFor` 属性，我们可以指定一些特定的异常类型，使得其发生时事务也会回滚。需要注意的是，`rollbackFor` 属性默认值为空数组，表示不指定任何需要回滚的异常类型。如果设置为某个异常类型的 Class 对象，表示只有该异常发生时才会回滚事务；如果设置为多个异常类型的 Class 对象数组，表示这些异常发生时都会回滚事务。
     */
    @Transactional(rollbackFor = Exception.class)   //默认情况下只有运行时异常才会被回滚 rollbackFor指定所有异常都回滚
    @Override
    public void delete(Integer id) throws Exception {
        try {
            deptMapper.deleteById(id);      //根据ID删除部门

            int i = 1/0;
            //if (true) {throw new Exception("ikun:错错错是我的错");}

            empMapper.deleteByDeptId(id);   //根据部门id删除该部门下的员工信息
        } finally {
            DeptLog deptLog = new DeptLog();
            deptLog.setCreateTime(LocalDateTime.now());
            deptLog.setDescription("执行了解散部门操作,这次解散的是:"+id+"号部门");
            deptLogService.insert(deptLog);
        }

    }

    @Override
    public void add(Dept dept) {
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.insert(dept);
    }

    @Override
    public Dept getDeptById(Dept dept) {
        return deptMapper.select(dept);
    }

    @Override
    public void update(Dept dept) {
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.update(dept);
    }
}
