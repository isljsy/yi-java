package com.ljsy.yisystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ljsy.yisystem.entity.*;
import com.ljsy.yisystem.mapper.DirExplainMapper;
import com.ljsy.yisystem.mapper.DirPinyinMapper;
import com.ljsy.yisystem.mapper.DirRadicalMapper;
import com.ljsy.yisystem.mapper.DirWordMapper;
import com.ljsy.yisystem.service.IDirWordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
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

    /**
     * 通过无单调拼音和方言大区，找出此拼音对应的所有彝字
     *
     * @param pinyin  无单调拼音，如ta
     * @param dialect 方言大区
     * @return 拼音对应的所有彝字， 没有返回null
     */
    @Override
    public List<String> getWordListBySimplePinyin(String pinyin, Integer dialect) {
        List<String> list = wordMapper.getWordListBySimplePinyin(pinyin, dialect);
        if (list.size() == 0) {
            return null;
        }
        return list;
    }

    /**
     * 通过部首id获得,
     *
     * @param radical id
     * @return
     */
    @Override
    public Map<Integer, List<String>> getWordListByRadical(String radical) {
        List<DirWord> list = wordMapper.selectList(new QueryWrapper<DirWord>().eq("radical_id", radical).orderByAsc("radical_id"));
        Map<Integer, List<String>> map = new HashMap<>();
        // 按彝字笔画分组
        for(DirWord word: list){
            // 彝字笔画
            Integer count = word.getCount();
            // 是否已有此笔画分组
            if(!map.containsKey(count)){
                map.put(count, new ArrayList<>());
            }
            map.get(count).add(word.getWord());
        }
        return map;
    }

    /**
     * @return 所有部首
     */
    @Override
    public Map<String, List<DirRadical>> getRadicalMap() {
        List<RadicalVo> list = radicalMapper.getRadicalList();
        Map<String, List<DirRadical>> map = new HashMap<>();
        for(RadicalVo radicalVo: list){
            String firstStroke = radicalVo.getFirstStroke();
            // 新的笔画
            if(!map.containsKey(firstStroke)){
                map.put(firstStroke,new ArrayList<>());
            }
            // 新DirRadical, 加入到对应的起笔笔画组
            DirRadical radical = new DirRadical();
            radical.setId(radicalVo.getId());
            radical.setRadical(radicalVo.getRadical());
            map.get(firstStroke).add(radical);
        }
        return map;
    }
}
