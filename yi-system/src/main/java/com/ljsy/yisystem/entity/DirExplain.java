package com.ljsy.yisystem.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 
 * </p>
 *
 * @author ljsy
 * @since 2022-03-22
 */
@Data
@TableName("dir_explain")
@ApiModel(value = "DirExplain对象", description = "")
public class DirExplain implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer wordId;

    @ApiModelProperty("汉语翻译")
    private String hanyu;

    @TableField(exist = false)
    private List<DirSentence> sentenceList;
}
