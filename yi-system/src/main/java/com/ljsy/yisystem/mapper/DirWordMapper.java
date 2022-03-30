package com.ljsy.yisystem.mapper;

import com.ljsy.yisystem.entity.DirWord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ljsy.yisystem.entity.WordVo;

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
}
