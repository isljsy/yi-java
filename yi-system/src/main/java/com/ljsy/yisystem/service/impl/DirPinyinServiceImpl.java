package com.ljsy.yisystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ljsy.yisystem.entity.DirPinyin;
import com.ljsy.yisystem.mapper.DirPinyinMapper;
import com.ljsy.yisystem.service.IDirPinyinService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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
    //TODO 解决双音节拼音和带音素拼音问题
}
