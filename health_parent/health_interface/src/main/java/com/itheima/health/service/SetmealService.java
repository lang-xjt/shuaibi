package com.itheima.health.service;

import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.pojo.Setmeal;

import java.util.List;

public interface SetmealService {
    /*
     * 添加套餐
     * setmeal 添加套餐的内容
     * checkgroupIds 选中的检查组
     * */

    void add(Setmeal setmeal, Integer[] checkgroupIds);
    /*
    *  /*
     *
     * 分页查询
     * */
    PageResult<Setmeal> findPage(QueryPageBean queryPageBean);
    /*
     * 根据id查询
     * */
    Setmeal findById(int id);
    /*
     * 回显钩选中的检查组，发送请求，通过套餐id查询选中的检查组id，绑定到checkgroupIds
     * */
    List<Integer> findCheckGroupIdsBySetmealId(int id);
    /*
     * 更新数据
     *
     * */
    void update(Setmeal setmeal, Integer[] checkgroupIds);
}
