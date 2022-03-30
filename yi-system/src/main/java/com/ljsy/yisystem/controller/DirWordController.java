package com.ljsy.yisystem.controller;


import com.ljsy.yisystem.entity.WordVo;
import com.ljsy.yisystem.service.IDirWordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ljsy
 * @since 2022-03-19
 */
@RestController
@RequestMapping("/yi-word/word")
public class DirWordController {

    @Autowired
    private IDirWordService wordService;

    @GetMapping("{id}")
    public WordVo getById(@PathVariable Integer id){
        return wordService.getWordVoById(id);
    }
}
