package com.itheima.health.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.health.constant.MessageConstant;
import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.entity.Result;
import com.itheima.health.pojo.CheckItem;
import com.itheima.health.service.CheckItemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Date 2021/1/5
 */
@RestController
@RequestMapping("/checkitem")
public class CheckItemController {
    /*
        订阅服务
     */
    @Reference
    private CheckItemService checkItemService;

    /*
    *  查询所有
    * */
    @GetMapping("/findAll")
    public Result findAll(){
        //调用服务查询所有
      List<CheckItem> list =  checkItemService.findAll();
        //返回结果  Result类是用来储存返回数据的
        return new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS,list);
        }

        /*
        * 添加
        * */
    @PostMapping("/add")
    //@RequestBody 获取前端json字符串数据请求体  用实体类CheckItem进行存储
    public Result add(@RequestBody CheckItem checkItem){
        //调用服务添加
        checkItemService.add(checkItem);
        //返回结果
        return new Result(true,MessageConstant.QUERY_CHECKITEM_SUCCESS);
    }

    /*
    * 分页查询
    * 请求参数：currentPage 当前页数
    *           pageSize  每页记录数大小
    *           queryString  查询条件
    *  返回数据：当前页展示数据 ，数据总条数
    * */
    @PostMapping("/findPage")
    //@RequestBody 获取前端json字符串数据请求体  用实体类QueryPageBean进行存储
    public Result findPage(@RequestBody QueryPageBean queryPageBean){
        //调用服务进行查询
        PageResult<CheckItem> pageResult =checkItemService.findPage(queryPageBean);
        //返回查询结果
        return new Result(true,MessageConstant.QUERY_CHECKITEM_SUCCESS,pageResult);
    }
    /*
    * 根据id查询 单条数据的显示
    *
    * */
    @GetMapping("/findById")
    public Result findByID(int id){
        //调用服务查询
        CheckItem checkItem =checkItemService.findByID(id);
        return new Result(true,MessageConstant.QUERY_CHECKITEM_SUCCESS,checkItem);
    }
    /*
     * 更新数据
    */
    @PostMapping("/update")
    public Result update(@RequestBody CheckItem checkItem){
        //调用服务添加
        checkItemService.update(checkItem);
        //返回结果
        return new Result(true,MessageConstant.QUERY_CHECKITEM_SUCCESS);
    }
    /*
    * 删除数据
    * */
    @PostMapping("/deleteById")
    public Result deleteById(int id){
        //调用服务添加
        checkItemService.deleteById(id);
        //返回结果
        return new Result(true,MessageConstant.DELETE_CHECKITEM_SUCCESS);
    }
}
