package com.ljsy.yisystem.entity;


import lombok.Data;

import java.util.List;

@Data
public class WordVo {

    public WordVo(){};

    public WordVo(DirWord word) {
        id = word.getId();
        this.word = word.getWord();
        page = word.getPage();
        count = word.getCount();
        code = word.getCode();
    }

    private Integer id;

    private String word;

    private Integer page;

    private Integer count;

    private String code;

    private String radical;

    private List<DirExplain> explainList;

    private List<PinyinVo> pinyinList;
}
