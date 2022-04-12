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
 * @since 2022-03-30
 */
@TableName("dir_radical")
@ApiModel(value = "DirRadical对象", description = "")
public class DirRadical implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String radical;

    @ApiModelProperty("起笔笔画")
    private Integer firstStrokeId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getRadical() {
        return radical;
    }

    public void setRadical(String radical) {
        this.radical = radical;
    }
    public Integer getFirstStrokeId() {
        return firstStrokeId;
    }

    public void setFirstStrokeId(Integer firstStrokeId) {
        this.firstStrokeId = firstStrokeId;
    }

    @Override
    public String toString() {
        return "DirRadical{" +
            "id=" + id +
            ", radical=" + radical +
            ", firstStrokeId=" + firstStrokeId +
        "}";
    }
}
