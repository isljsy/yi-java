package com.ruoyi.dir.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 拼音对象 dir_pinyin
 * 
 * @author ljsy
 * @date 2023-03-11
 */
public class DirPinyin extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 彝字 */
    private String word;

    /** 拼音 */
    @Excel(name = "拼音")
    private String pinyin;

    /** 地区 */
    @Excel(name = "地区")
    private String local;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setWord(String word) 
    {
        this.word = word;
    }

    public String getWord() 
    {
        return word;
    }
    public void setPinyin(String pinyin) 
    {
        this.pinyin = pinyin;
    }

    public String getPinyin() 
    {
        return pinyin;
    }
    public void setLocal(String local) 
    {
        this.local = local;
    }

    public String getLocal() 
    {
        return local;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("word", getWord())
            .append("pinyin", getPinyin())
            .append("local", getLocal())
            .toString();
    }
}
