package com.ljsy.yisystem.mapper;

import com.ljsy.yisystem.entity.DirPinyin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ljsy.yisystem.entity.PinyinVo;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ljsy
 * @since 2022-03-22
 */
public interface DirPinyinMapper extends BaseMapper<DirPinyin> {
    List<PinyinVo> getPinyinVoByWordId(Integer wordId);
}
