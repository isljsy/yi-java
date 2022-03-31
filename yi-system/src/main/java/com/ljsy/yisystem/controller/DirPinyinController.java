package com.ljsy.yisystem.controller;


import com.ljsy.yisystem.service.IDirPinyinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ljsy
 * @since 2022-03-22
 */
@RestController
@RequestMapping("/pinyin")
public class DirPinyinController {

    @Autowired
    IDirPinyinService pinyinService;

    @GetMapping("")
    public int splitPinyin(){
        return pinyinService.splitPinyin();
    }
}
