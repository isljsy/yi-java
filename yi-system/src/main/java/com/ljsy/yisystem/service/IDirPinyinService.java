package com.ljsy.yisystem.service;

import com.ljsy.yisystem.entity.DirPinyin;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ljsy
 * @since 2022-03-22
 */
public interface IDirPinyinService extends IService<DirPinyin> {
    /**
     * 分割有多个拼音的拼音，分开保存
     */
    int splitPinyin();
}
