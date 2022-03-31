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
 * @since 2022-03-31
 */
@TableName("dir_local")
@ApiModel(value = "DirLocal对象", description = "")
public class DirLocal implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String local;

    private Integer dialectId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }
    public Integer getDialectId() {
        return dialectId;
    }

    public void setDialectId(Integer dialectId) {
        this.dialectId = dialectId;
    }

    @Override
    public String toString() {
        return "DirLocal{" +
            "id=" + id +
            ", local=" + local +
            ", dialectId=" + dialectId +
        "}";
    }
}
