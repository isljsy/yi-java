package com.ruoyi.dir.mapper;

import java.util.List;
import com.ruoyi.dir.domain.DirExplain;
import com.ruoyi.dir.domain.DirSentence;

/**
 * 汉语释义Mapper接口
 * 
 * @author ljsy
 * @date 2023-03-11
 */
public interface DirExplainMapper 
{
    /**
     * 查询汉语释义
     * 
     * @param id 汉语释义主键
     * @return 汉语释义
     */
    public DirExplain selectDirExplainById(Long id);

    /**
     * 查询汉语释义列表
     * 
     * @param dirExplain 汉语释义
     * @return 汉语释义集合
     */
    public List<DirExplain> selectDirExplainList(DirExplain dirExplain);

    /**
     * 新增汉语释义
     * 
     * @param dirExplain 汉语释义
     * @return 结果
     */
    public int insertDirExplain(DirExplain dirExplain);

    /**
     * 修改汉语释义
     * 
     * @param dirExplain 汉语释义
     * @return 结果
     */
    public int updateDirExplain(DirExplain dirExplain);

    /**
     * 删除汉语释义
     * 
     * @param id 汉语释义主键
     * @return 结果
     */
    public int deleteDirExplainById(Long id);

    /**
     * 批量删除汉语释义
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteDirExplainByIds(Long[] ids);

    /**
     * 批量删除彝语例句
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteDirSentenceByExplains(Long[] ids);
    
    /**
     * 批量新增彝语例句
     * 
     * @param dirSentenceList 彝语例句列表
     * @return 结果
     */
    public int batchDirSentence(List<DirSentence> dirSentenceList);
    

    /**
     * 通过汉语释义主键删除彝语例句信息
     * 
     * @param id 汉语释义ID
     * @return 结果
     */
    public int deleteDirSentenceByExplain(Long id);
}
