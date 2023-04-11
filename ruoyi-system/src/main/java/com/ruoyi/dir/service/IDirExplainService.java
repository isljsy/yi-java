package com.ruoyi.dir.service;

import java.util.List;
import com.ruoyi.dir.domain.DirExplain;

/**
 * 汉语释义Service接口
 * 
 * @author ljsy
 * @date 2023-03-11
 */
public interface IDirExplainService 
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
     * 批量删除汉语释义
     * 
     * @param ids 需要删除的汉语释义主键集合
     * @return 结果
     */
    public int deleteDirExplainByIds(Long[] ids);

    /**
     * 删除汉语释义信息
     * 
     * @param id 汉语释义主键
     * @return 结果
     */
    public int deleteDirExplainById(Long id);
}
