package com.itheima.health.service;

import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.exception.MyException;
import com.itheima.health.pojo.CheckGroup;

import java.util.List;

public interface CheckGroupService {
    /*
    * 添加检查组
    * 添加检查组和检查项的中间表数据
    * */
    void add(CheckGroup checkGroup, Integer[] checkitemIds);
    /*
    * 分页查询
    * */
    PageResult<CheckGroup> findPage(QueryPageBean queryPageBean);
    /*
    * 根据id查询检查组数据
    * */
    CheckGroup findById(int id);
    /*
    根据检查组id去中间表查询检查项id
    * */
    List<Integer> findCheckItemIdsByCheckGroupId(int id);
    /*
    * 数据更新
    * */
    void update(CheckGroup checkGroup, Integer[] checkitemIds);

    /*
    * 根据id删除
    * */
    void deleteById(int id) throws MyException;
    /*
    * 查询所有
    * */
    List<CheckGroup> findAll();
}
