package com.ljsy.yisystem.mapper;

import com.ljsy.yisystem.entity.DirWord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ljsy.yisystem.entity.WordVo;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ljsy
 * @since 2022-03-19
 */
public interface DirWordMapper extends BaseMapper<DirWord> {
    WordVo getWordVoById(Integer id);

    /**
     * 通过无单调拼音和方言大区，找出此拼音对应的所有彝字
     * @param pinyin 无单调拼音，如ta
     * @param dialect 方言大区
     * @return 拼音对应的所有彝字， 没有返回null
     */
    List<String> getWordListBySimplePinyin(String pinyin, Integer dialect);
}
