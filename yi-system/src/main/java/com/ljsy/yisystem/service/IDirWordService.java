package com.ljsy.yisystem.service;

import com.ljsy.yisystem.entity.DirWord;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ljsy.yisystem.entity.WordVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ljsy
 * @since 2022-03-19
 */
public interface IDirWordService extends IService<DirWord> {


    /**
     *
     * @param id id
     * @return 带全信息的彝字
     */
    WordVo getWordVoById(Integer id);
}
