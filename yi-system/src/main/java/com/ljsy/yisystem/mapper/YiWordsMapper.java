package com.ljsy.yisystem.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ljsy.yisystem.entity.YiWords;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author ljsy
 * @since 2022-01-20
 */
public interface YiWordsMapper extends BaseMapper<YiWords> {
    /**
     * @return 威宁所有拼音
     */
    List<String> weiningList();

    List<String> dafangList();

    List<String> luquanList();

    List<String> bijieList();

    List<String> panxianList();

    List<String> longlinList();

    List<String> hongheList();

    List<String> shipingList();

    List<String> weishanList();

    List<String> xideList();

    List<String> chuxiongList();

    List<String> yongrenList();

    List<String> shilinList();
}
