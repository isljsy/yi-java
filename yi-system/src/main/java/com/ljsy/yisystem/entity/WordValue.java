package com.ljsy.yisystem.entity;


import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class WordValue extends DirWord {

    private String radic;

    private List<DirExplain> explainList;

    private List<DirPinyin> pinyinList;
}
