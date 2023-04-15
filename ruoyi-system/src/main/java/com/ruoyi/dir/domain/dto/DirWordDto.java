package com.ruoyi.dir.domain.dto;

import lombok.Data;

import java.util.List;

@Data
public class DirWordDto {

    private Long id;

    private String word;

    private Long page;

    private String radical;

    private Long count;

    private String code;

    private List<String> hanyuList;
}
