package com.ljsy.yisystem.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ljsy.yisystem.entity.YiWords;

import java.util.*;

public class PinyinHelper {

    /**
     * 去拼音单调和音素
     *
     * @param pinyin
     * @return
     */
    public String removeTone(String pinyin) {
        String str = pinyin.replaceAll("（.*?）", "");
        str = str.replaceAll("[¹²³⁴⁵r].*", "");
        if(str.isBlank()){
            System.out.println("拼音错误:"+pinyin);
            return null;
        }
        return str;
    }

    /**
     * 该字的所有拼音，形如"红河a/石屏ai"
     *
     * @param words
     * @return 带地区的拼音的字符串
     */
    public String pinyinByWord(YiWords words) {
        StringBuffer sb = new StringBuffer();
        if (words.getWeining() != null) {
            sb.append(pinyinWithLocal("威宁", words.getWeining()));
        }
        if (words.getLuquan() != null) {
            sb.append(pinyinWithLocal("禄劝", words.getLuquan()));
        }
        if (words.getDafang() != null) {
            sb.append(pinyinWithLocal("大方", words.getDafang()));
        }
        if (words.getBijie() != null) {
            sb.append(pinyinWithLocal("毕节", words.getBijie()));
        }
        if (words.getPanxian() != null) {
            sb.append(pinyinWithLocal("盘县", words.getPanxian()));
        }
        if (words.getLonglin() != null) {
            sb.append(pinyinWithLocal("隆林", words.getLonglin()));
        }
        if (words.getHonghe() != null) {
            sb.append(pinyinWithLocal("红河", words.getHonghe()));
        }
        if (words.getShiping() != null) {
            sb.append(pinyinWithLocal("石屏", words.getShiping()));
        }
        if (words.getWeishan() != null) {
            sb.append(pinyinWithLocal("巍山", words.getWeishan()));
        }
        if (words.getXide() != null) {
            sb.append(pinyinWithLocal("袁德", words.getXide()));
        }
        if (words.getChuxiong() != null) {
            sb.append(pinyinWithLocal("楚雄", words.getChuxiong()));
        }
        if (words.getYongren() != null) {
            sb.append(pinyinWithLocal("永仁", words.getYongren()));
        }
        if (words.getShilin() != null) {
            sb.append(pinyinWithLocal("石林", words.getShilin()));
        }
        return sb.toString();
    }

    /**
     * 组合拼音和地区，
     *
     * @param local  地区
     * @param pinyin 拼音字符串
     * @return ”地区pinyin1/地区pinyin2“
     */
    public String pinyinWithLocal(String local, String pinyin) {
        StringBuffer sb = new StringBuffer();
        // 如果是多音
        if (pinyin.contains("、")) {
            for (String p : pinyin.split("、")) {
                sb.append(local).append(removeTone(p)).append("/");
            }
        } else {
            // 不是多音
            sb.append(local).append(removeTone(pinyin)).append("/");
        }
        return sb.toString();
    }

    /**
     * 用于拼音检索表，第一步
     *
     * @param pinyinList
     * @return 去拼音单调，并分离多音
     */
    public List<String> removeListTone(List<String> pinyinList) {
        List<String> list = new ArrayList<>();
        for (String pinyin : pinyinList) {
            // 如果是多音
            if (pinyin.contains("、")) {
                // 分割多音，逐个加入新List
                for (String split : pinyin.split("、")) {
                    list.add(removeTone(split));
                }
            } else {
                // 不是多音
                list.add(removeTone(pinyin));
            }
        }
        return list;
    }

    /**
     * 将一个地区的拼音集合转化为辅音分类的Json集合， 拼音检索表第二步
     *
     * @param pinyinList 去单调的一个地区的拼音List
     * @return 以辅音分类的json集合
     */
    public JSONArray getSortList(List<String> pinyinList) {
        // 去拼声调
        List<String> list = removeListTone(pinyinList);
        // 辅音：此辅音开头的拼音
        Map<String, Set<String>> map = new HashMap<>();
        for (String pinyin : list) {
            // 拼音是否为空
            if(pinyin.isBlank()){
                continue;
            }
            String consonant;
            // 是否有辅音上标
            if (pinyin.contains("ʼ")) {
                consonant = pinyin.substring(0, pinyin.indexOf('ʼ')+1);
            } else {
                // 取第一个字母
                consonant = pinyin.substring(0, 1);
            }
            // 辅音是否已存在
            if (!map.containsKey(consonant)){
                // 不存在则新加辅音
                map.put(consonant,new HashSet<>());
            }
            // 加入拼音到对应辅音的集合
            map.get(consonant).add(pinyin);
        }
        // map转为json集合
        JSONArray jsonArray = new JSONArray();
        for (String key : map.keySet()){
            // 一个辅音
            JSONObject json = new JSONObject();
            json.put("pinyin",map.get(key));
            json.put("consonant",key);
            // 加入到json集合
            jsonArray.add(json);
        }
        return jsonArray;
    }

}
