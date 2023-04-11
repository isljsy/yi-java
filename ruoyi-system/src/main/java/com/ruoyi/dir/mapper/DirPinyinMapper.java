package com.ruoyi.dir.mapper;

import java.util.List;
import com.ruoyi.dir.domain.DirPinyin;

/**
 * 拼音Mapper接口
 * 
 * @author ljsy
 * @date 2023-03-11
 */
public interface DirPinyinMapper 
{
    /**
     * 查询拼音
     * 
     * @param id 拼音主键
     * @return 拼音
     */
    public DirPinyin selectDirPinyinById(Long id);

    /**
     * 查询拼音列表
     * 
     * @param dirPinyin 拼音
     * @return 拼音集合
     */
    public List<DirPinyin> selectDirPinyinList(DirPinyin dirPinyin);

    /**
     * 新增拼音
     * 
     * @param dirPinyin 拼音
     * @return 结果
     */
    public int insertDirPinyin(DirPinyin dirPinyin);

    /**
     * 修改拼音
     * 
     * @param dirPinyin 拼音
     * @return 结果
     */
    public int updateDirPinyin(DirPinyin dirPinyin);

    /**
     * 删除拼音
     * 
     * @param id 拼音主键
     * @return 结果
     */
    public int deleteDirPinyinById(Long id);

    /**
     * 批量删除拼音
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteDirPinyinByIds(Long[] ids);
}
