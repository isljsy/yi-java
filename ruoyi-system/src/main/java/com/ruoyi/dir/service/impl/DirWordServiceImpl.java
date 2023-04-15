package com.ruoyi.dir.service.impl;

import java.util.List;

import com.ruoyi.dir.domain.dto.DirWordDto;
import com.ruoyi.dir.service.DirWordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.dir.mapper.DirWordMapper;
import com.ruoyi.dir.domain.DirWord;

/**
 * 彝文Service业务层处理
 * 
 * @author ljsy
 * @date 2023-03-11
 */
@Service
public class DirWordServiceImpl implements DirWordService
{
    @Autowired
    private DirWordMapper dirWordMapper;

    /**
     * 查询彝文
     * 
     * @param id 彝文主键
     * @return 彝文
     */
    @Override
    public DirWord selectDirWordById(Long id)
    {
        return dirWordMapper.selectDirWordById(id);
    }

    /**
     * 查询彝文列表
     * 
     * @param dirWord 彝文
     * @return 彝文
     */
    @Override
    public List<DirWordDto> selectDirWordList(DirWord dirWord)
    {
        return dirWordMapper.selectDirWordList(dirWord);
    }

    /**
     * 新增彝文
     * 
     * @param dirWord 彝文
     * @return 结果
     */
    @Override
    public int insertDirWord(DirWord dirWord)
    {
        return dirWordMapper.insertDirWord(dirWord);
    }

    /**
     * 修改彝文
     * 
     * @param dirWord 彝文
     * @return 结果
     */
    @Override
    public int updateDirWord(DirWord dirWord)
    {
        return dirWordMapper.updateDirWord(dirWord);
    }

    /**
     * 批量删除彝文
     * 
     * @param ids 需要删除的彝文主键
     * @return 结果
     */
    @Override
    public int deleteDirWordByIds(Long[] ids)
    {
        return dirWordMapper.deleteDirWordByIds(ids);
    }

    /**
     * 删除彝文信息
     * 
     * @param id 彝文主键
     * @return 结果
     */
    @Override
    public int deleteDirWordById(Long id)
    {
        return dirWordMapper.deleteDirWordById(id);
    }
}
