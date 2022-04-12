package com.ljsy.yisystem.service;

import com.ljsy.yisystem.entity.DirLocal;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ljsy.yisystem.entity.LocalVo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ljsy
 * @since 2022-03-31
 */
public interface IDirLocalService extends IService<DirLocal> {
    /**
     *
     * @return 所有地区带方言大区
     */
    Map<String, List<DirLocal>> getLocalVoList();
}
