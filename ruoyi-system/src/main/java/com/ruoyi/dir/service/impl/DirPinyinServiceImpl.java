package com.ruoyi.dir.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.dir.mapper.DirPinyinMapper;
import com.ruoyi.dir.domain.DirPinyin;
import com.ruoyi.dir.service.IDirPinyinService;

/**
 * 拼音Service业务层处理
 * 
 * @author ljsy
 * @date 2023-03-11
 */
@Service
public class DirPinyinServiceImpl implements IDirPinyinService 
{
    @Autowired
    private DirPinyinMapper dirPinyinMapper;

    /**
     * 查询拼音
     * 
     * @param id 拼音主键
     * @return 拼音
     */
    @Override
    public DirPinyin selectDirPinyinById(Long id)
    {
        return dirPinyinMapper.selectDirPinyinById(id);
    }

    /**
     * 查询拼音列表
     * 
     * @param dirPinyin 拼音
     * @return 拼音
     */
    @Override
    public List<DirPinyin> selectDirPinyinList(DirPinyin dirPinyin)
    {
        return dirPinyinMapper.selectDirPinyinList(dirPinyin);
    }

    /**
     * 新增拼音
     * 
     * @param dirPinyin 拼音
     * @return 结果
     */
    @Override
    public int insertDirPinyin(DirPinyin dirPinyin)
    {
        return dirPinyinMapper.insertDirPinyin(dirPinyin);
    }

    /**
     * 修改拼音
     * 
     * @param dirPinyin 拼音
     * @return 结果
     */
    @Override
    public int updateDirPinyin(DirPinyin dirPinyin)
    {
        return dirPinyinMapper.updateDirPinyin(dirPinyin);
    }

    /**
     * 批量删除拼音
     * 
     * @param ids 需要删除的拼音主键
     * @return 结果
     */
    @Override
    public int deleteDirPinyinByIds(Long[] ids)
    {
        return dirPinyinMapper.deleteDirPinyinByIds(ids);
    }

    /**
     * 删除拼音信息
     * 
     * @param id 拼音主键
     * @return 结果
     */
    @Override
    public int deleteDirPinyinById(Long id)
    {
        return dirPinyinMapper.deleteDirPinyinById(id);
    }
}
