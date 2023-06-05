package ikun.study.service;

import ikun.study.pojo.Emp;
import ikun.study.pojo.PageBean;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

/**
 * 员工管理
 */
public interface EmpService {
    /**分页查询*/
    PageBean page(Integer page, Integer pageSize,String name, Short gender,LocalDate begin, LocalDate end);

    /**批量删除员工*/
    void delete(List<Integer> ids);

    /**新增员工*/
    void save(Emp emp);

    /**根据ID查询员工信息*/
    Emp getById(Integer id);

    /**更新员工数据*/
    void update(Emp emp);

    /**用户登录*/
    Emp login(Emp emp);
}
