package com.ruoyi.dir.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.dir.domain.DirSentence;
import com.ruoyi.dir.mapper.DirExplainMapper;
import com.ruoyi.dir.domain.DirExplain;
import com.ruoyi.dir.service.IDirExplainService;

/**
 * 汉语释义Service业务层处理
 * 
 * @author ljsy
 * @date 2023-03-11
 */
@Service
public class DirExplainServiceImpl implements IDirExplainService 
{
    @Autowired
    private DirExplainMapper dirExplainMapper;

    /**
     * 查询汉语释义
     * 
     * @param id 汉语释义主键
     * @return 汉语释义
     */
    @Override
    public DirExplain selectDirExplainById(Long id)
    {
        return dirExplainMapper.selectDirExplainById(id);
    }

    /**
     * 查询汉语释义列表
     * 
     * @param dirExplain 汉语释义
     * @return 汉语释义
     */
    @Override
    public List<DirExplain> selectDirExplainList(DirExplain dirExplain)
    {
        return dirExplainMapper.selectDirExplainList(dirExplain);
    }

    /**
     * 新增汉语释义
     * 
     * @param dirExplain 汉语释义
     * @return 结果
     */
    @Transactional
    @Override
    public int insertDirExplain(DirExplain dirExplain)
    {
        int rows = dirExplainMapper.insertDirExplain(dirExplain);
        return rows;
    }

    /**
     * 修改汉语释义
     * 
     * @param dirExplain 汉语释义
     * @return 结果
     */
    @Transactional
    @Override
    public int updateDirExplain(DirExplain dirExplain)
    {
        dirExplainMapper.deleteDirSentenceByExplain(dirExplain.getId());
        insertDirSentence(dirExplain);
        return dirExplainMapper.updateDirExplain(dirExplain);
    }

    /**
     * 批量删除汉语释义
     * 
     * @param ids 需要删除的汉语释义主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteDirExplainByIds(Long[] ids)
    {
        dirExplainMapper.deleteDirSentenceByExplains(ids);
        return dirExplainMapper.deleteDirExplainByIds(ids);
    }

    /**
     * 删除汉语释义信息
     * 
     * @param id 汉语释义主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteDirExplainById(Long id)
    {
        dirExplainMapper.deleteDirSentenceByExplain(id);
        return dirExplainMapper.deleteDirExplainById(id);
    }

    /**
     * 新增彝语例句信息
     * 
     * @param dirExplain 汉语释义对象
     */
    public void insertDirSentence(DirExplain dirExplain)
    {
        List<DirSentence> dirSentenceList = dirExplain.getDirSentenceList();
        Long id = dirExplain.getId();
        if (StringUtils.isNotNull(dirSentenceList))
        {
            List<DirSentence> list = new ArrayList<DirSentence>();
            for (DirSentence dirSentence : dirSentenceList)
            {
                dirSentence.setExplain(id.toString());
                list.add(dirSentence);
            }
            if (list.size() > 0)
            {
                dirExplainMapper.batchDirSentence(list);
            }
        }
    }
}
