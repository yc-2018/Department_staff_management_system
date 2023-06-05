package ikun.study.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import ikun.study.mapper.EmpMapper;
import ikun.study.pojo.Emp;
import ikun.study.pojo.PageBean;
import ikun.study.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;

    @Override
    public PageBean page(Integer page, Integer pageSize, String name, Short gender, LocalDate begin, LocalDate end) {
        // 1、获取总记录数
        // 2、获取分页查询结果列表
        // 3、封装在pageBean对象
        // return new PageBean(empMapper.count(), empMapper.page((page-1)*pageSize,pageSize));

        // 1、设置分页参数
        PageHelper.startPage(page, pageSize);
        // 2、执行查询
        List<Emp> list = empMapper.list(name,gender,begin,end);
        Page<Emp> p = (Page<Emp>) list;
        // 3、封装pageBean对象
        return new PageBean(p.getTotal(), p.getResult());
    }

    /**批量删除员工*/
    @Override
    public void delete(List<Integer> ids) {
        empMapper.delete(ids);
    }

    /**新增员工*/
    @Override
    public void save(Emp emp) {
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.insert(emp);
    }

    /**根据ID查询员工信息*/
    @Override
    public Emp getById(Integer id) {
        return empMapper.getById(id);
    }

    @Override
    public void update(Emp emp) {
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.update(emp);
    }

    @Override
    public Emp login(Emp emp) {
        return empMapper.getByUsernameAndPassword(emp);
    }
}
