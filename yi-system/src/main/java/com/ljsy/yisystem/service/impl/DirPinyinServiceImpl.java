package com.ljsy.yisystem.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ljsy.yisystem.entity.DirPinyin;
import com.ljsy.yisystem.mapper.DirPinyinMapper;
import com.ljsy.yisystem.service.IDirPinyinService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ljsy.yisystem.util.PinyinHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ljsy
 * @since 2022-03-22
 */
@Service
public class DirPinyinServiceImpl extends ServiceImpl<DirPinyinMapper, DirPinyin> implements IDirPinyinService {

    @Resource
    DirPinyinMapper pinyinMapper;


    public Map<String, List<String>> simplePinyinMapByDialect(Integer dialect){
        PinyinHelper helper = new PinyinHelper();
        List<String> list = pinyinMapper.pinyinListByDialect(dialect);
        // 用来判断是否有新的辅音添加
        HashSet<String> set = new HashSet<>();
        HashMap<String, List<String>> map = new HashMap<>();
        int setSize = 0;
        for(int i =0;i<list.size();i++){
            list.set(i, helper.removeTone(list.get(i)));
            if(list.get(i) == null){
                continue;
            }
            String consonant = list.get(i).substring(0,1);
            set.add(consonant);
            // 若有新加的辅音
            if(set.size() > setSize){
                setSize = set.size();
                map.put(consonant,new ArrayList<>());
            }
            map.get(consonant).add(list.get(i));
        }
        return map;
    }

    /**
     * 分割有多个拼音的拼音，分开保存
     */
    @Override
    public int splitPinyin() {
        List<DirPinyin> pinyinList = pinyinMapper.selectList(new QueryWrapper<DirPinyin>().like("pinyin","，"));
        int count = 0;
        for (DirPinyin dirPinyin : pinyinList) {
            // 删除原来的数据
            pinyinMapper.deleteById(dirPinyin);
            // 依次插入新分割出来的拼音
            for (String pinyinSplit:dirPinyin.getPinyin().split("，")) {
                dirPinyin.setId(null);
                dirPinyin.setPinyin(pinyinSplit);
                pinyinMapper.insert(dirPinyin);
                count++;
            }
        }
        return count;
    }

}
