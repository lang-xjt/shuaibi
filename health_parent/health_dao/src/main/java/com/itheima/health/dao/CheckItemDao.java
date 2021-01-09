package com.itheima.health.dao;

import com.github.pagehelper.Page;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.pojo.CheckItem;

import java.util.List;

public interface CheckItemDao {
    /*
    * 查询
    * */
    List<CheckItem> findAll();
    /*
    * 添加
    * */
    void add(CheckItem checkItem);

    /*
    * 分页
    * */
    Page<CheckItem> findByCondition(String queryString);
    /*
    * 根据id查询数据
    * */
    CheckItem findByID(int id);
    /*
    * 跟新数据
    * */
    void update(CheckItem checkItem);
    /*
    * 统计检查项使用的次数
    * */
    int findCountByCheckItemId(int id);
    /*
    *
    * 根据id进行删除
    * */
    void deleteById(int id);
}
