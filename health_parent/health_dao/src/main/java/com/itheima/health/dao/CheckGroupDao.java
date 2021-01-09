package com.itheima.health.dao;

import com.github.pagehelper.Page;
import com.itheima.health.pojo.CheckGroup;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CheckGroupDao {
     /*
     添加检查组
     * */
    void add(CheckGroup checkGroup);
    /*
    *  添加检查组和检查项的关键
    * */
    void addCheckGroupCheckItem(@Param("checkGroupId") Integer checkGroupId, @Param("checkitemId") Integer checkitemId);
    /*
    *  分页查询
    * */
    Page<CheckGroup> findByCondition(String queryString);
    /*
    *  根据id查询
    * */
    CheckGroup findById(int id);
    /*
    *  根据检查组id去中间表查询检查项id
    * */
    List<Integer> findCheckItemIdsByCheckGroupId(int id);
    /*
     * 数据更新
     * */
    void update(CheckGroup checkGroup);

    /*
    * <!--删除检查组和检查项中间表数据-->
    * */
    void deleteCheckGroupCheckItem(Integer id);
    /*
    * <!--查询检查项是否被套餐使用-->
    * */
    int findSetmealCountByCheckGroupId(int id);
    /*
     *  根据id删除
     * */
    void deleteById(int id);

    List<CheckGroup> findAll();
}
