package com.ljsy.yisystem.entity;


import com.alibaba.fastjson.JSONArray;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class WordValue extends DirWord {

    private String radic;

    private JSONArray explainList;

    private JSONArray pinyinList;
}
