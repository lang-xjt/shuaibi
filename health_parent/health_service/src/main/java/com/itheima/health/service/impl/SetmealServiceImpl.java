package com.itheima.health.service.impl;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.dubbo.container.page.PageHandler;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.health.dao.SetmealDao;
import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.pojo.Setmeal;
import com.itheima.health.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Date 2021/1/8
 */
@Service(interfaceClass = SetmealService.class)
public class SetmealServiceImpl implements SetmealService {
    @Autowired
    private SetmealDao setmealDao;
    /*
     * 添加套餐
     * setmeal 添加套餐的内容
     * checkgroupIds 选中的检查组
     * */
    @Override
    @Transactional
    public void add(Setmeal setmeal, Integer[] checkgroupIds) {
        // 调用Dao层进行添加
        setmealDao.add(setmeal);
        //获取添加后的id
        Integer setmealId = setmeal.getId();
        //判断checkgroupIds数组是否为空
        if(null!=checkgroupIds){
            //不为空
            for (Integer checkgroupId : checkgroupIds) {
                //往套餐和检查组的中间表添加关系
                setmealDao.addSetmealIdCheckgroupId(setmealId,checkgroupId);
            }
        }
    }
    /*
     *
     * 分页查询
     * */
    @Override
    public PageResult<Setmeal> findPage(QueryPageBean queryPageBean) {
        //使用分页插件  先获取当前页数和当前页数大小
        PageHelper.startPage(queryPageBean.getCurrentPage(),queryPageBean.getPageSize());
        //判断是否有查询条件
        if(StringUtils.isNotEmpty(queryPageBean.getQueryString())){
            //有查询条件进行拼接
            queryPageBean.setQueryString("%"+queryPageBean.getQueryString()+"%");
        }
        //进行分页查询
        Page<Setmeal> page =setmealDao.findByCondition(queryPageBean.getQueryString());
        return new PageResult<Setmeal>(page.getTotal(),page.getResult()) ;
    }
    /*
     * 根据id查询
     * */
    @Override
    public Setmeal findById(int id) {
        return setmealDao.findById(id);
    }
    /*
     * 回显钩选中的检查组，发送请求，通过套餐id查询选中的检查组id，绑定到checkgroupIds
     * */
    @Override
    public List<Integer> findCheckGroupIdsBySetmealId(int id) {
        return setmealDao.findCheckGroupIdsBySetmealId(id);
    }
    /*
     * 更新数据
     *
     * */
    @Override
    public void update(Setmeal setmeal, Integer[] checkgroupIds) {
        //先继续更新套餐
        setmealDao.update(setmeal);
        //删除原有关系表数据
        setmealDao.deleteSetmealCheckGroup(setmeal.getId());
        //判断是否有checkgroupIds值
        if(null !=checkgroupIds){
            //添加新关系
            for (Integer checkgroupId : checkgroupIds) {
                setmealDao.addSetmealIdCheckgroupId(setmeal.getId(),checkgroupId);
            }
        }
    }

}
