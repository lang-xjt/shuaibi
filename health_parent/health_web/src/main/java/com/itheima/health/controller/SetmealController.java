package com.itheima.health.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.health.constant.MessageConstant;
import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.entity.Result;
import com.itheima.health.pojo.Setmeal;
import com.itheima.health.service.SetmealService;
import com.itheima.health.utils.QiNiuUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @Date 2021/1/8
 */
@RestController
@RequestMapping("/setmeal")
public class SetmealController {

    @Reference
    private SetmealService setmealService;
    /*
    * 图片上传
    * */
    @PostMapping("/upload")
    public Result upload(MultipartFile imgFile){
        //获取文件名
        String originalFilename = imgFile.getOriginalFilename();
        //截取文件名后缀
        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        //- 生成唯一文件名，拼接后缀名
        String filename= UUID.randomUUID()+extension;
        //- 调用七牛上传文件
        try {
            QiNiuUtils.uploadViaByte(imgFile.getBytes(),filename);
            //- 返回数据给页面
            //{
            //    flag:
            //    message:
            //    data:{
            //        imgName: 图片名,
            //        domain: QiNiuUtils.DOMAIN
            //    }
            //}
            Map<String, Object> map =new HashMap<>();
            map.put("imgName",filename);
            map.put("domain",QiNiuUtils.DOMAIN);
            //返回结果
            return new Result(true, MessageConstant.PIC_UPLOAD_SUCCESS,map);
        } catch (IOException e) {
            e.printStackTrace();
            //失败
            return new Result(false, MessageConstant.PIC_UPLOAD_FAIL);
        }
    }
    /*
    * 添加套餐
    * setmeal 添加套餐的内容
    * checkgroupIds 选中的检查组
    * */
    @PostMapping("/add")
    public Result add(@RequestBody Setmeal setmeal, Integer[] checkgroupIds){
        //调用服务进行添加
        setmealService.add(setmeal,checkgroupIds);
        //响应结果
        return new Result(true, MessageConstant.ADD_SETMEAL_SUCCESS);
    }
    /*
    *
    * 分页查询
    * */
    @PostMapping("/findPage")
    public Result findPage(@RequestBody QueryPageBean queryPageBean){
        //调用服务层进行查询
        PageResult<Setmeal> setmealPageResult= setmealService.findPage(queryPageBean);
        return new Result(true, MessageConstant.QUERY_SETMEAL_SUCCESS,setmealPageResult);
    }
    /*
    * 根据id查询
    * */
    @GetMapping("/findById")
    public Result findById(int id){
        //调用服务层进行查询
        Setmeal setmeal =setmealService.findById(id);
        // 前端要显示图片需要全路径
        // 封装到map中，解决图片路径问题
        Map<String, Object> map =new HashMap<>();
        map.put("setmeal",setmeal);
        map.put("domain",QiNiuUtils.DOMAIN);
        return new Result(true, MessageConstant.QUERY_SETMEAL_SUCCESS,map);
    }

    /*
     * 回显钩选中的检查组，发送请求，通过套餐id查询选中的检查组id，绑定到checkgroupIds
     * */
    @GetMapping("/findCheckGroupIdsBySetmealId")
    public Result findCheckGroupIdsBySetmealId(int id){
        //调用服务层进行查询
        List<Integer> list=setmealService.findCheckGroupIdsBySetmealId(id);
        // 前端要显示图片需要全路径
        return new Result(true, MessageConstant.QUERY_CHECKGROUP_SUCCESS,list);
    }
    /*
    * 更新数据
    *
    * */
    @PostMapping("/update")
    public Result update(@RequestBody Setmeal setmeal, Integer[] checkgroupIds) {
        //调用服务进行添加
        setmealService.update(setmeal, checkgroupIds);
        //响应结果
        return new Result(true, MessageConstant.ADD_SETMEAL_SUCCESS);
    }
}
