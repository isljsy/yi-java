package com.ruoyi.dir.service;

import java.util.List;
import com.ruoyi.dir.domain.DirWord;
import com.ruoyi.dir.domain.dto.DirWordDto;

/**
 * 彝文Service接口
 * 
 * @author ljsy
 * @date 2023-03-11
 */
public interface DirWordService
{
    /**
     * 查询彝文
     * 
     * @param id 彝文主键
     * @return 彝文
     */
    public DirWord selectDirWordById(Long id);

    /**
     * 查询彝文列表
     * 
     * @param dirWord 彝文
     * @return 彝文集合
     */
    public List<DirWordDto> selectDirWordList(DirWord dirWord);

    /**
     * 新增彝文
     * 
     * @param dirWord 彝文
     * @return 结果
     */
    public int insertDirWord(DirWord dirWord);

    /**
     * 修改彝文
     * 
     * @param dirWord 彝文
     * @return 结果
     */
    public int updateDirWord(DirWord dirWord);

    /**
     * 批量删除彝文
     * 
     * @param ids 需要删除的彝文主键集合
     * @return 结果
     */
    public int deleteDirWordByIds(Long[] ids);

    /**
     * 删除彝文信息
     * 
     * @param id 彝文主键
     * @return 结果
     */
    public int deleteDirWordById(Long id);
}
