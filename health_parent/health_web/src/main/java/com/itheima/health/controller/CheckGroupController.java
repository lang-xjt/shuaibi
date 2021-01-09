package com.itheima.health.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.health.constant.MessageConstant;
import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.entity.Result;
import com.itheima.health.pojo.CheckGroup;
import com.itheima.health.service.CheckGroupService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Date 2021/1/7
 */
@RestController
@RequestMapping("/checkgroup")
public class CheckGroupController {
    @Reference
    private CheckGroupService checkGroupService;

    /*
    * 添加
    * */
    @PostMapping("/add")
    public Result add(@RequestBody CheckGroup checkGroup,Integer[] checkitemIds ){
        //调用服务层
        checkGroupService.add(checkGroup,checkitemIds);
        return new Result(true, MessageConstant.ADD_CHECKGROUP_SUCCESS);
    }
    /*
    * 分页条件查询
    *
    * */
    @PostMapping("/findPage")
    public Result findPage(@RequestBody QueryPageBean queryPageBean){
        //调用服务层  返回结果用
       PageResult<CheckGroup> checkGroupPageResult= checkGroupService.findPage(queryPageBean);
       return new Result(true,MessageConstant.QUERY_CHECKGROUP_SUCCESS,checkGroupPageResult);
    }
    /*
    *根据id查询检查组
    *
    * */
    @GetMapping("/findById")
    public Result findById(int id){
        //调用服务
        CheckGroup checkGroup= checkGroupService.findById(id);
        //返回结果
        return new Result(true, MessageConstant.QUERY_CHECKGROUP_SUCCESS,checkGroup);
    }
    /*
    根据检查组的id查询出检查项的id
    */
    @GetMapping("/findCheckItemIdsByCheckGroupId")
    public Result findCheckItemIdsByCheckGroupId(int id){
        //调用服务
        List<Integer> checkItemIds= checkGroupService.findCheckItemIdsByCheckGroupId(id);
        //返回结果
        return new Result(true, MessageConstant.QUERY_CHECKGROUP_SUCCESS,checkItemIds);
    }
    /*
    * 更新检查组和检查项的关系
    * */
    @PostMapping("/update")
    public Result update(@RequestBody CheckGroup checkGroup,Integer[] checkitemIds ){
        //调用服务层
        checkGroupService.update(checkGroup,checkitemIds);
        return new Result(true, MessageConstant.ADD_CHECKGROUP_SUCCESS);
    }
    /*
    * 删除检查组
    * */
    @PostMapping("/deleteById")
    public Result deleteById(int id){
        checkGroupService.deleteById(id);
        return new Result(true, MessageConstant.DELETE_CHECKGROUP_SUCCESS);
    }
    /*
    * 查询所有检查组
    * */
    @GetMapping("/findAll")
    public Result findAll(){
        //调用服务层
        List<CheckGroup> list =checkGroupService.findAll();
        //返回结果
        return new Result(true, MessageConstant.QUERY_CHECKGROUP_SUCCESS,list);
    }
}
