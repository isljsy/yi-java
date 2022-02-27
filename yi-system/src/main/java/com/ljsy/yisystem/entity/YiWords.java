package com.ljsy.yisystem.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author ljsy
 * @since 2022-01-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("yi_words")
public class YiWords implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String word;

    private Integer page;

    private String radical;

    private Integer count;

    private String code;

    private String hanyu;

    private String hanyukey;

    private String weining;

    private String dafang;

    private String luquan;

    private String bijie;

    private String panxian;

    private String longlin;

    private String honghe;

    private String shiping;

    private String weishan;

    private String xide;

    private String chuxiong;

    private String yongren;

    private String shilin;

    public String[] hanyuList() {
        return hanyu != null ? hanyu.split("/") : new String[]{"暂无汉语释义"};
    }

    /**
     * @return 东部方言拼音集合
     */
    public List<Map<String, String>> eastPinyinList() {
        List <Map<String, String>> list = new ArrayList<>();
        if (weining != null && !weining.isBlank()) {
            Map<String,String> map = new HashMap<>();
            map.put("local", "威宁");
            map.put("pinyin", weining);
            list.add(map);
        }
        if (luquan != null && !luquan.isBlank()) {
            Map<String,String> map = new HashMap<>();
            map.put("local", "禄劝");
            map.put("pinyin", luquan);
            list.add(map);
        }
        if (dafang != null && !dafang.isBlank()) {
            Map<String,String> map = new HashMap<>();
            map.put("local", "大方");
            map.put("pinyin", dafang);
            list.add(map);
        }
        if (bijie != null && !bijie.isBlank()) {
            Map<String,String> map = new HashMap<>();
            map.put("local", "毕节");
            map.put("pinyin", bijie);
            list.add(map);
        }
        if (panxian != null && !panxian.isBlank()) {
            Map<String,String> map = new HashMap<>();
            map.put("local", "盘县");
            map.put("pinyin", panxian);
            list.add(map);
        }
        if (longlin != null && !longlin.isBlank()) {
            Map<String,String> map = new HashMap<>();
            map.put("local", "隆林");
            map.put("pinyin", longlin);
            list.add(map);
        }
        return list;
    }

    /**
     * @return 南部方言拼音集合
     */
    public List<Map<String, String>> southPinyinList() {
        List <Map<String, String>> list = new ArrayList<>();
        if (honghe != null && !honghe.isBlank()) {
            Map<String,String> map = new HashMap<>();
            map.put("local", "红河");
            map.put("pinyin", honghe);
            list.add(map);
        }
        if (shiping != null && !shiping.isBlank()) {
            Map<String,String> map = new HashMap<>();
            map.put("local", "石屏");
            map.put("pinyin", shiping);
            list.add(map);
        }
        return list;
    }

    /**
     * @return 西部方言拼音集合
     */
    public List<Map<String, String>> westPinyinList() {
        List <Map<String, String>> list = new ArrayList<>();
        if (weishan != null && !weishan.isBlank()) {
            Map<String,String> map = new HashMap<>();
            map.put("local", "巍山");
            map.put("pinyin", weishan);
            list.add(map);
        }
        return list;
    }

    /**
     * @return 北部方言拼音集合
     */
    public List<Map<String, String>> northPinyinList() {
        List <Map<String, String>> list = new ArrayList<>();
        if (xide != null && !xide.isBlank()) {
            Map<String,String> map = new HashMap<>();
            map.put("local", "喜德");
            map.put("pinyin", xide);
            list.add(map);
        }
        return list;
    }

    /**
     * @return 中部方言拼音集合
     */
    public List<Map<String, String>> middlePinyinList() {
        List <Map<String, String>> list = new ArrayList<>();
        if (chuxiong != null && !chuxiong.isBlank()) {
            Map<String,String> map = new HashMap<>();
            map.put("local", "楚雄");
            map.put("pinyin", chuxiong);
            list.add(map);
        }
        if (yongren != null && !yongren.isBlank()) {
            Map<String,String> map = new HashMap<>();
            map.put("local", "永仁");
            map.put("pinyin", yongren);
            list.add(map);
        }
        return list;
    }

    /**
     * @return 东南方言拼音集合
     */
    public List<Map<String, String>> southeastPinyinList() {
        List <Map<String, String>> list = new ArrayList<>();
        if (shilin != null && !shilin.isBlank()) {
            Map<String,String> map = new HashMap<>();
            map.put("local", "石林");
            map.put("pinyin", shilin);
            list.add(map);
        }
        return list;
    }

    /**
     * @return 所有方言拼音集合
     */
    public List <Map<String, Object>> pinyinList() {
        List<Map<String, Object>> list = new ArrayList<>();


        List<Map<String, String>> eastPinyinList = eastPinyinList();
        if (eastPinyinList.size() > 0) {
            Map<String, Object> map = new HashMap<>();
            map.put("dialect", "东部");
            map.put("pinyinList", eastPinyinList);
            list.add(map);

        }
        List<Map<String, String>> southPinyinList = southPinyinList();
        if (southPinyinList.size() > 0) {
            Map<String, Object> map = new HashMap<>();
            map.put("dialect", "南部");
            map.put("pinyinList", southPinyinList);
            list.add(map);
        }

        List<Map<String, String>> westPinyinList = westPinyinList();
        if (westPinyinList.size() > 0) {
            Map<String, Object> map = new HashMap<>();
            map.put("dialect", "西部");
            map.put("pinyinList", westPinyinList);
            list.add(map);
        }

        List<Map<String, String>> northPinyinList = northPinyinList();
        if (northPinyinList.size() > 0) {
            Map<String, Object> map = new HashMap<>();
            map.put("dialect", "北部");
            map.put("pinyinList", northPinyinList);
            list.add(map);
        }

        List<Map<String, String>> middlePinyinList = middlePinyinList();
        if (middlePinyinList.size() > 0) {
            Map<String, Object> map = new HashMap<>();
            map.put("dialect", "中部");
            map.put("pinyinList", middlePinyinList);
            list.add(map);
        }

        List<Map<String, String>> southeastPinyinList = southeastPinyinList();
        if (southeastPinyinList.size() > 0) {
            Map<String, Object> map = new HashMap<>();
            map.put("东南", southeastPinyinList);
            list.add(map);
        }
       return list;
    }

}
