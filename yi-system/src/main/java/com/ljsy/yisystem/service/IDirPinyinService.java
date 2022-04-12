package com.ljsy.yisystem.service;

import com.ljsy.yisystem.entity.DirPinyin;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

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

    /**
     *
     * @param dialect 方言大区id
     * @return 指定方言的所有无单调无音素的拼音
     */
    Map<String, List<String>> simplePinyinMapByDialect(Integer dialect);
}
