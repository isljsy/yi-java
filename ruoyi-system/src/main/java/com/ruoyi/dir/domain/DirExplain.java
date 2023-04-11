package com.ruoyi.dir.domain;

import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 汉语释义对象 dir_explain
 * 
 * @author ljsy
 * @date 2023-03-11
 */
public class DirExplain extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** $column.columnComment */
    private String wordId;

    /** 汉语释义 */
    @Excel(name = "汉语释义")
    private String hanyu;

    /** 彝语例句信息 */
    private List<DirSentence> dirSentenceList;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setWordId(String wordId) 
    {
        this.wordId = wordId;
    }

    public String getWordId() 
    {
        return wordId;
    }
    public void setHanyu(String hanyu) 
    {
        this.hanyu = hanyu;
    }

    public String getHanyu() 
    {
        return hanyu;
    }

    public List<DirSentence> getDirSentenceList()
    {
        return dirSentenceList;
    }

    public void setDirSentenceList(List<DirSentence> dirSentenceList)
    {
        this.dirSentenceList = dirSentenceList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("wordId", getWordId())
            .append("hanyu", getHanyu())
            .append("dirSentenceList", getDirSentenceList())
            .toString();
    }
}
