package ikun.study.service;

import ikun.study.pojo.Dept;

import java.util.List;

/**
 * 部门管理
 */
public interface DeptService {
    /**
     * 查询全部数据
     * */
    List<Dept> list();


    /**
     * 删除部门
     * */
    void delete(Integer id) throws Exception;


    /**
     * 新增部门
     * */
    void add(Dept dept);

    /**
     * 根据id查询部门
     * */
    Dept getDeptById(Dept dept);

    /**
     * 修改部门名字
     * */
    void update(Dept dept);
}
