package com.ljsy.yisystem.mapper;

import com.ljsy.yisystem.entity.DirLocal;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ljsy.yisystem.entity.LocalVo;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ljsy
 * @since 2022-03-31
 */
public interface DirLocalMapper extends BaseMapper<DirLocal> {
    /**
     *
     * @return 所有地区带方言大区
     */
    List<LocalVo> getLocalVoList();
}
