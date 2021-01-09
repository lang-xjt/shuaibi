package com.itheima.health.service.impl;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.StringUtil;
import com.itheima.health.dao.CheckItemDao;
import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.exception.MyException;
import com.itheima.health.pojo.CheckItem;
import com.itheima.health.service.CheckItemService;
import org.springframework.beans.factory.annotation.Autowired;

import java.awt.*;
import java.util.List;

/**
 * @Date 2021/1/5
 */
@Service
//@Service发布服务  注意：使用的是阿里的包
public class CheckItemServiceImpl implements CheckItemService {
    //Autowired注入对象
    @Autowired
    private CheckItemDao checkItemDao;
    /*
    *  查询所有
    * */
    @Override
    public List<CheckItem> findAll() {
        return checkItemDao.findAll();
    }
  /*
  * 添加
  * */
    @Override
    public void add(CheckItem checkItem) {
        checkItemDao.add(checkItem);
    }


    /*
    * 分页查询
    * */
    @Override
    public PageResult<CheckItem> findPage(QueryPageBean queryPageBean) {
        //第二种，Mapper接口方式的调用，推荐这种使用方式。
        PageHelper.startPage(queryPageBean.getCurrentPage(), queryPageBean.getPageSize());
        // 模糊查询 拼接 %
        // 判断是否有查询条件
        if(StringUtils.isNotEmpty(queryPageBean.getQueryString())){
            // 有查询条件，拼接%
            queryPageBean.setQueryString("%" + queryPageBean.getQueryString() + "%");
        }
        // 紧接着的查询语句会被分页
        Page<CheckItem> page = checkItemDao.findByCondition(queryPageBean.getQueryString());
        PageResult<CheckItem>  pageResult=new PageResult<CheckItem>(page.getTotal(), page.getResult());
        return pageResult;
    }
        /*
        * 根据id查询
        *
        * */
    @Override
    public CheckItem findByID(int id) {
      return   checkItemDao.findByID(id);
    }

    /*
    * 更新
    * */
    @Override
    public void update(CheckItem checkItem) {
        checkItemDao.update(checkItem);
    }
    /*
    *
    *  根据id删除数据
    * */
    @Override
    public void deleteById(int id) {
        //统计使用的次数
        int cnt = checkItemDao.findCountByCheckItemId(id);
        //判断是否为0
        if(cnt >0){
            throw new MyException("被使用");
        }
        checkItemDao.deleteById(id);
    }

}
