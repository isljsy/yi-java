package com.ljsy.yisystem.controller;


import com.ljsy.yisystem.entity.DirExplain;
import com.ljsy.yisystem.service.IDirExplainService;
import com.ljsy.yisystem.service.impl.DirExplainServiceImpl;
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
 * @since 2022-03-22
 */
@RestController
@RequestMapping("/yi-word/explain")
public class DirExplainController {

    @Autowired
    IDirExplainService explainService;

    @GetMapping("/{wordId}")
    public DirExplain getExplainValue(@PathVariable int wordId){
        return explainService.getExplainValue(wordId);
    }

}
