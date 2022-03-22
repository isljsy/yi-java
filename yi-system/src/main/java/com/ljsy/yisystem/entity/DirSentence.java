package com.ljsy.yisystem.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 
 * </p>
 *
 * @author ljsy
 * @since 2022-03-22
 */
@TableName("dir_sentence")
@ApiModel(value = "DirSentence对象", description = "")
public class DirSentence implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("释义id")
    private Integer explainId;

    @ApiModelProperty("彝文例句")
    private String sentence;

    @ApiModelProperty("汉语翻译")
    private String hanyuSentence;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getExplainId() {
        return explainId;
    }

    public void setExplainId(Integer explainId) {
        this.explainId = explainId;
    }
    public String getSentence() {
        return sentence;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }
    public String getHanyuSentence() {
        return hanyuSentence;
    }

    public void setHanyuSentence(String hanyuSentence) {
        this.hanyuSentence = hanyuSentence;
    }

    @Override
    public String toString() {
        return "DirSentence{" +
            "id=" + id +
            ", explainId=" + explainId +
            ", sentence=" + sentence +
            ", hanyuSentence=" + hanyuSentence +
        "}";
    }
}
