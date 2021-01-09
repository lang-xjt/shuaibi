package com.itheima.health.dao;

import com.github.pagehelper.Page;
import com.itheima.health.pojo.Setmeal;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SetmealDao {
    /*
     * 添加套餐
     * setmeal 添加套餐的内容
     *
     * */
    void add(Setmeal setmeal);
    /*
    * 往套餐和检查组的中间表添加关系
    * */
    void addSetmealIdCheckgroupId(@Param("setmealId") Integer setmealId, @Param("checkgroupId") Integer checkgroupId);
    /*
     *
     * 分页查询
     * */
    Page<Setmeal> findByCondition(String queryString);
    /*
     * 根据id查询
     * */
    Setmeal findById(int id);
    /*
     * 回显钩选中的检查组，发送请求，通过套餐id查询选中的检查组id，绑定到checkgroupIds
     * */
    List<Integer> findCheckGroupIdsBySetmealId(int id);
    /*
     * 更新套餐数据
     *
     * */
    void update(Setmeal setmeal);

    /*
    * 删除套餐和检查组的关系
    * */
    void deleteSetmealCheckGroup(Integer id);
}
