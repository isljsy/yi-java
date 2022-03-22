package com.ljsy.yisystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ljsy.yisystem.entity.DirExplain;
import com.ljsy.yisystem.entity.DirSentence;
import com.ljsy.yisystem.mapper.DirExplainMapper;
import com.ljsy.yisystem.mapper.DirSentenceMapper;
import com.ljsy.yisystem.service.IDirExplainService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ljsy
 * @since 2022-03-22
 */
@Service
public class DirExplainServiceImpl extends ServiceImpl<DirExplainMapper, DirExplain> implements IDirExplainService {

    @Resource
    private DirExplainMapper explainMapper;

    @Resource
    private DirSentenceMapper sentenceMapper;

    /**
     * @param wordId 彝字id
     * @return 带例句数组的释义
     */
    @Override
    public DirExplain getExplainValue(int wordId) {
        DirExplain explain = explainMapper.selectOne(new QueryWrapper<DirExplain>().eq("word_id",wordId));
        explain.setSentenceList(sentenceMapper.selectList(new QueryWrapper<DirSentence>().eq("explain_id",explain.getId())));
        return explain;
    }
}
