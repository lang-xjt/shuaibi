package com.itheima.health.service.impl;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.health.dao.CheckGroupDao;
import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.exception.MyException;
import com.itheima.health.pojo.CheckGroup;
import com.itheima.health.service.CheckGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Date 2021/1/7
 */
@Service(interfaceClass = CheckGroupService.class)
public class CheckGroupServiceImpl implements CheckGroupService {
    @Autowired
    private CheckGroupDao checkGroupDao;
    @Override
    @Transactional
    public void add(CheckGroup checkGroup, Integer[] checkitemIds) {
        //添加检查组
        checkGroupDao.add(checkGroup);
        //获取刚添加检查组的id
        Integer checkGroupId = checkGroup.getId();
        //判断数组是否为空  就是判断前端是否有勾选检查项
        if(checkitemIds!=null){
            //遍历数组中的id
            for (Integer checkitemId : checkitemIds) {
                //进行对中间表添加数据  添加检查组id和检查项id
                checkGroupDao.addCheckGroupCheckItem(checkGroupId,checkitemId);
            }
        }

    }
    /*
    * 分页查询
    * */
    @Override
    public PageResult<CheckGroup> findPage(QueryPageBean queryPageBean) {
        //获取当前页数和当前页面大小
        PageHelper.startPage(queryPageBean.getCurrentPage(),queryPageBean.getPageSize());
        //判断条件查询是否为空
        if(StringUtils.isNotEmpty(queryPageBean.getQueryString())){
            //不为空 就行拼接
            queryPageBean.setQueryString("%"+queryPageBean.getQueryString()+"%");
        }
        //进行分页查询
        Page<CheckGroup> page = checkGroupDao.findByCondition(queryPageBean.getQueryString());
        return new PageResult<CheckGroup>(page.getTotal(),page.getResult());

    }
    /*
    根据id查询数据
    * */
    @Override
    public CheckGroup findById(int id) {
       return checkGroupDao.findById(id);
    }
    /*
    * 根据检查组id去中间表查询检查项id
    * */
    @Override
    public List<Integer> findCheckItemIdsByCheckGroupId(int id) {
        return checkGroupDao.findCheckItemIdsByCheckGroupId(id);
    }
    /*
    * 数据更新
    * */
    @Transactional
    @Override
    public void update(CheckGroup checkGroup, Integer[] checkitemIds) {
        //更新检查组
        checkGroupDao.update(checkGroup);
        //删除之前检查组原有的关系
        checkGroupDao.deleteCheckGroupCheckItem(checkGroup.getId());
        //建立新表关系
        if(null != checkitemIds) {
            for (Integer checkitemId : checkitemIds) {
                checkGroupDao.addCheckGroupCheckItem(checkGroup.getId(), checkitemId);
            }
        }
    }
    /*
    *  根据id删除
    * */
    @Override
    @Transactional
    public void deleteById(int id) {
        //先进行查询是否背套餐使用
       int count = checkGroupDao.findSetmealCountByCheckGroupId(id);
       //进行判断是否被使用
        if(count>0){
            //说明被使用不能删除
            throw new MyException("被使用不能删除");
        }
        //先进行删除检查表和检查项的关系
        checkGroupDao.deleteCheckGroupCheckItem(id);
        //进行删除
        checkGroupDao.deleteById(id);
    }

    @Override
    public List<CheckGroup> findAll() {
        //调用dao查询所有
        return checkGroupDao.findAll();
    }

}
