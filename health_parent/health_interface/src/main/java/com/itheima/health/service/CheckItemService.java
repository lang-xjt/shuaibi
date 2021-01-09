package com.itheima.health.service;

import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.exception.MyException;
import com.itheima.health.pojo.CheckItem;

import java.util.List;

public interface CheckItemService {
    /*
    * 查询所有
    * */
    List<CheckItem> findAll();

    /*
     * 添加
    */
    void add(CheckItem checkItem);

    /*
    * 分页查询
    * */
    PageResult<CheckItem> findPage(QueryPageBean queryPageBean);
    /*
    * 根据id查询
    * */
    CheckItem findByID(int id);
    /*
    * 数据更新
    * */
    void update(CheckItem checkItem);
    /*
    * 根据id删除
    * */
    void deleteById(int id)  throws MyException;
}
