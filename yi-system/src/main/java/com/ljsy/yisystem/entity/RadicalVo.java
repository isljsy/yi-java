package com.ljsy.yisystem.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class RadicalVo {

    private Integer id;

    private String radical;

    @ApiModelProperty("起笔笔画")
    private String firstStroke;

}
