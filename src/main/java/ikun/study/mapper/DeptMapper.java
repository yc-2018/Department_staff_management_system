package ikun.study.mapper;

import ikun.study.pojo.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 部门管理
 */
@Mapper
public interface DeptMapper {
    /**
     * 查询全部部门数据
     * */
    @Select("select * from dept")
    List<Dept> list();

    /**删除部门*/
    @Delete("delete from dept where id=#{id}")
    void deleteById(Integer id);

    /**新增部门*/
    @Insert("insert into dept(name, create_time, update_time) values (#{name},#{createTime},#{updateTime})")
    void insert(Dept dept);

    /**根据id查询*/
    @Select("select * from dept where id=#{id}")
    Dept select(Dept dept);

    @Update("update dept set name = #{name} where id = #{id}")
    void update(Dept dept);

}
