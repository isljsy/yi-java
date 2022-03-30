package com.ljsy.yisystem.entity;

import lombok.Data;

@Data
public class PinyinVo {

    public PinyinVo(){};

    public PinyinVo(DirPinyin dirPinyin){
        id = dirPinyin.getId();
        wordId = dirPinyin.getWordId();
        pinyin = dirPinyin.getPinyin();
    }

    private Integer id;

    private Integer wordId;

    private String pinyin;

    private String local;

    private String dialect;

}
