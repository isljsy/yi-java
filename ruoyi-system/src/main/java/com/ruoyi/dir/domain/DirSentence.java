package com.ruoyi.dir.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 彝语例句对象 dir_sentence
 * 
 * @author ljsy
 * @date 2023-03-11
 */
public class DirSentence extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private String id;

    /** 释义id */
    private String explain;

    /** 彝文例句 */
    @Excel(name = "彝文例句")
    private String sentence;

    /** 汉语翻译 */
    @Excel(name = "汉语翻译")
    private String hanyuSentence;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setExplain(String explain) 
    {
        this.explain = explain;
    }

    public String getExplain() 
    {
        return explain;
    }
    public void setSentence(String sentence) 
    {
        this.sentence = sentence;
    }

    public String getSentence() 
    {
        return sentence;
    }
    public void setHanyuSentence(String hanyuSentence) 
    {
        this.hanyuSentence = hanyuSentence;
    }

    public String getHanyuSentence() 
    {
        return hanyuSentence;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("explain", getExplain())
            .append("sentence", getSentence())
            .append("hanyuSentence", getHanyuSentence())
            .toString();
    }
}
