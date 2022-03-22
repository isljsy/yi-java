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
 * @since 2022-03-19
 */
@Data
@TableName("dir_word")
@ApiModel(value = "DirWord对象", description = "")
public class DirWord implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String word;

    private Integer page;

    private Integer radicalId;

    private Integer count;

    private String code;

}
