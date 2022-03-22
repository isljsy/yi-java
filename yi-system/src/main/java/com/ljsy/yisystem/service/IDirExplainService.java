package com.ljsy.yisystem.service;

import com.ljsy.yisystem.entity.DirExplain;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ljsy
 * @since 2022-03-22
 */
public interface IDirExplainService extends IService<DirExplain> {

    /**
     *
     * @param wordId 彝字id
     * @return 带例句数组的释义
     */
    DirExplain getExplainValue(int wordId);
}
