package com.ljsy.yisystem.mapper;

import com.ljsy.yisystem.entity.DirExplain;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ljsy
 * @since 2022-03-22
 */
public interface DirExplainMapper extends BaseMapper<DirExplain> {
    List<DirExplain> getExplainVoListByWordId(Integer wordId);
}
