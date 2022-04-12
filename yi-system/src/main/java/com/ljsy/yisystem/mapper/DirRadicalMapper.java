package com.ljsy.yisystem.mapper;

import com.ljsy.yisystem.entity.DirRadical;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ljsy.yisystem.entity.RadicalVo;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ljsy
 * @since 2022-03-30
 */
public interface DirRadicalMapper extends BaseMapper<DirRadical> {

    /**
     *
     * @return 部首带起笔笔画
     */
    List<RadicalVo> getRadicalList();
}
