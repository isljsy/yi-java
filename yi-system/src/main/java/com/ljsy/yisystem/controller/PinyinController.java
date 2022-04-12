package com.ljsy.yisystem.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ljsy.yisystem.entity.DirPinyin;
import com.ljsy.yisystem.mapper.DirPinyinMapper;
import com.ljsy.yisystem.service.IDirPinyinService;
import com.ljsy.yisystem.util.PinyinHelper;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ljsy
 * @since 2022-03-22
 */
@Api(tags = "拼音接口")
@RestController
@RequestMapping("/pinyin")
public class PinyinController {

    @Autowired
    IDirPinyinService pinyinService;
//    @Autowired
//    DirPinyinMapper pinyinMapper;

    /**
     * 分割有逗号的拼音, 调数据库专用，非正式接口
     * @return
     */
//    @GetMapping("")
//    public int splitPinyin(){
////        PinyinHelper helper = new PinyinHelper();
////        List<DirPinyin> list = pinyinMapper.selectList(new QueryWrapper<DirPinyin>().like("pinyin","（"));
////        for(DirPinyin pinyin: list){
////            String newPinyin = helper.removeTone(pinyin.getPinyin());
////            if(newPinyin != null){
////                pinyin.setPinyin(newPinyin);
////                pinyinMapper.updateById(pinyin);
////            }
////        }
//        //return pinyinService.splitPinyin();
//        return 200;
//    }

    @GetMapping("/{dialect}")
    public ResponseEntity<Object> getPinyinListByDialect(@PathVariable Integer dialect){
        return new ResponseEntity<>(pinyinService.simplePinyinMapByDialect(dialect),HttpStatus.OK);
    }
}
