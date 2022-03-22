package com.ljsy.yisystem.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
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
@TableName("dir_pinyin")
@ApiModel(value = "DirPinyin对象", description = "")
public class DirPinyin implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer wordId;

    private String pinyin;

    private Integer localId;


    @Override
    public String toString() {
        return "DirPinyin{" +
            "id=" + id +
            ", wordId=" + wordId +
            ", pinyin=" + pinyin +
            ", localId=" + localId +
        "}";
    }
}
