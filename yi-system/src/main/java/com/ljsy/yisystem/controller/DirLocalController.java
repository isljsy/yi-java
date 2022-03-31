package com.ljsy.yisystem.controller;


import com.ljsy.yisystem.entity.DirLocal;
import com.ljsy.yisystem.entity.LocalVo;
import com.ljsy.yisystem.mapper.DirLocalMapper;
import com.ljsy.yisystem.service.IDirLocalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ljsy
 * @since 2022-03-31
 */
@RestController
@RequestMapping("/local")
public class DirLocalController {

    @Autowired
    IDirLocalService localService;

    @GetMapping("")
    public List<LocalVo> getLocalList(){
        return localService.getLocalVoList();
    }
}
