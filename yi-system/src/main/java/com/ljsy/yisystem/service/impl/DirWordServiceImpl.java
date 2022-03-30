package com.ljsy.yisystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ljsy.yisystem.entity.DirExplain;
import com.ljsy.yisystem.entity.DirPinyin;
import com.ljsy.yisystem.entity.DirWord;
import com.ljsy.yisystem.entity.WordVo;
import com.ljsy.yisystem.mapper.DirExplainMapper;
import com.ljsy.yisystem.mapper.DirPinyinMapper;
import com.ljsy.yisystem.mapper.DirRadicalMapper;
import com.ljsy.yisystem.mapper.DirWordMapper;
import com.ljsy.yisystem.service.IDirWordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ljsy
 * @since 2022-03-19
 */
@Service
public class DirWordServiceImpl extends ServiceImpl<DirWordMapper, DirWord> implements IDirWordService {

    @Resource
    DirWordMapper wordMapper;

    @Resource
    DirRadicalMapper radicalMapper;

    @Resource
    DirPinyinMapper pinyinMapper;

    @Resource
    DirExplainMapper explainMapper;

    /**
     * @param id id
     * @return 带全信息的彝字
     */
    @Override
    public WordVo getWordVoById(Integer id) {
        WordVo wordVo = wordMapper.getWordVoById(id);
        // 根据字id找拼音列表
        wordVo.setPinyinList(pinyinMapper.getPinyinVoByWordId(wordVo.getId()));
        // 根据字id找释义列表
        wordVo.setExplainList(explainMapper.getExplainVoListByWordId(wordVo.getId()));
        return wordVo;
    }
}
