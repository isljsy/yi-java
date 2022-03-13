package com.ljsy.yisystem.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ljsy.yisystem.entity.YiWords;
import com.ljsy.yisystem.service.YiWordsService;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ljsy
 * @since 2022-01-20
 */
@RestController
@RequestMapping("/yi-words")
public class YiWordsController {

    @Resource
    private YiWordsService service;

    @ApiOperation("查询所有彝字")
    @GetMapping("")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public Object list(@RequestParam(value = "searchWord", defaultValue = "") String searchWord,
                       @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                       @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize){
        Map<String,Object> map = new HashMap<>();
        QueryWrapper<YiWords> wrapper;
        if("".equals(searchWord)){
            wrapper=new QueryWrapper<>();
        } else {
            wrapper=new QueryWrapper<YiWords>().like("hanyukey",searchWord+"/");
        }
        try {
            IPage<YiWords> iPage = service.page(new Page<>(pageNum,pageSize), wrapper);
            map.put("data",iPage);
            map.put("status","200");
        } catch (Exception e) {
            map.put("status","500");
            map.put("msg","查询所有彝字时出错");
            e.printStackTrace();
        }
        return map;
    }

    @ApiOperation("id查询彝字")
    @GetMapping("/{id}")
    public Object getById(@PathVariable String id){
        Map<String,Object> map = new HashMap<>();
        try {
            map.put("data",service.getById(id));
            map.put("status","200");
        } catch (Exception e) {
            map.put("status","500");
            map.put("msg","查询彝字(id:"+id+")时出错");
            e.printStackTrace();
        }
        return map;
    }

    @ApiOperation("修改彝文")
    @PutMapping("")
    public Object update(YiWords yiWords){
        Map<String,Object> map = new HashMap<>();
        try {
            map.put("data",service.updateById(yiWords));
            map.put("status","200");
        } catch (Exception e) {
            map.put("status","500");
            map.put("msg","修改彝字(id:"+yiWords.getWord()+")时出错");
            e.printStackTrace();
        }
        return map;
    }
}

