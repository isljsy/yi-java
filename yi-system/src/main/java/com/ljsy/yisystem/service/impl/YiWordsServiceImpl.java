package com.ljsy.yisystem.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ljsy.yisystem.entity.YiWords;
import com.ljsy.yisystem.service.YiWordsService;
import com.ljsy.yisystem.util.PinyinHelper;
import com.ljsy.yisystem.mapper.YiWordsMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author ljsy
 * @since 2022-01-20
 */
@Service
public class YiWordsServiceImpl extends ServiceImpl<YiWordsMapper, YiWords> implements YiWordsService {

    @Resource
    private YiWordsMapper mapper;

    /**
     * 小程序数据库
     *
     * @return
     */
    @Override
    public String getJSON() {
        JSONArray array = new JSONArray();
        List<YiWords> list = mapper.selectList(new QueryWrapper<YiWords>());

        for (YiWords word : list) {
            JSONObject json = new JSONObject();
            json.put("word", word.getWord());
            json.put("page", word.getPage());
            json.put("radical", word.getRadical());
            json.put("count", word.getCount());
            json.put("code", word.getCode());
            json.put("hanyuList", word.hanyuList());
            json.put("pinyinsList", word.pinyinList());
            array.add(json);
        }

        return JSONObject.toJSONString(array);
    }

    /**
     * @return 搜索词和拼音搜索
     */
    @Override
    public String getSearchJS() {
        PinyinHelper pinyinHelper = new PinyinHelper();
        JSONArray jsonArray = new JSONArray();
        // 取所有彝字， 循环添加
        for (YiWords words : mapper.selectList(new QueryWrapper<YiWords>())) {
            JSONObject json = new JSONObject();
            json.put("word", words.getWord());
            json.put("pinyins", pinyinHelper.pinyinByWord(words));
            json.put("hanyukey", "/" + words.getHanyukey());
            jsonArray.add(json);
        }
        return "module.exports = { getList: getList }\nfunction getList() {return list;}\n" +
                "var list =" + jsonArray.toJSONString();
    }

    /**
     * @return 所有方言拼音辅音分类拼音集合的jsonString
     */
    @Override
    public String getPinyinJS() {
        PinyinHelper pinyinHelper = new PinyinHelper();
        JSONArray jsonArray = new JSONArray();
        JSONObject weiningJson = new JSONObject();
        weiningJson.put("local", "威宁");
        weiningJson.put("sortList", pinyinHelper.getSortList(mapper.weiningList()));
        jsonArray.add(weiningJson);
        JSONObject dafangJson = new JSONObject();
        dafangJson.put("local", "大方");
        dafangJson.put("sortList", pinyinHelper.getSortList(mapper.dafangList()));
        jsonArray.add(dafangJson);
        JSONObject luquanJson = new JSONObject();
        luquanJson.put("local", "禄劝");
        luquanJson.put("sortList", pinyinHelper.getSortList(mapper.luquanList()));
        jsonArray.add(luquanJson);
        JSONObject bijieJson = new JSONObject();
        bijieJson.put("local", "毕节");
        bijieJson.put("sortList", pinyinHelper.getSortList(mapper.bijieList()));
        jsonArray.add(bijieJson);
        JSONObject panxianJson = new JSONObject();
        panxianJson.put("local", "盘县");
        panxianJson.put("sortList", pinyinHelper.getSortList(mapper.panxianList()));
        jsonArray.add(panxianJson);
        JSONObject longlinJson = new JSONObject();
        longlinJson.put("local", "隆林");
        longlinJson.put("sortList", pinyinHelper.getSortList(mapper.longlinList()));
        jsonArray.add(longlinJson);
        JSONObject hongheJson = new JSONObject();
        hongheJson.put("local", "红河");
        hongheJson.put("sortList", pinyinHelper.getSortList(mapper.hongheList()));
        jsonArray.add(hongheJson);
        JSONObject shipingJson = new JSONObject();
        shipingJson.put("local", "石屏");
        shipingJson.put("sortList", pinyinHelper.getSortList(mapper.shipingList()));
        jsonArray.add(shipingJson);
        JSONObject weishanJson = new JSONObject();
        weishanJson.put("local", "巍山");
        weishanJson.put("sortList", pinyinHelper.getSortList(mapper.weishanList()));
        jsonArray.add(weishanJson);
        JSONObject xideJson = new JSONObject();
        xideJson.put("local", "喜德");
        xideJson.put("sortList", pinyinHelper.getSortList(mapper.xideList()));
        jsonArray.add(xideJson);
        JSONObject chuxiongJson = new JSONObject();
        chuxiongJson.put("local", "楚雄");
        chuxiongJson.put("sortList", pinyinHelper.getSortList(mapper.chuxiongList()));
        jsonArray.add(chuxiongJson);
        JSONObject yongrenJson = new JSONObject();
        yongrenJson.put("local", "永仁");
        yongrenJson.put("sortList", pinyinHelper.getSortList(mapper.yongrenList()));
        jsonArray.add(yongrenJson);
        JSONObject shilinJson = new JSONObject();
        shilinJson.put("local", "石林");
        shilinJson.put("sortList", pinyinHelper.getSortList(mapper.shilinList()));
        jsonArray.add(shilinJson);

        return "module.exports = { getList: getList }\n" +
                "function getList() {return list;}\n" +
                "var list ="+jsonArray.toJSONString();
    }

    /**
     * @return 汉语翻译的数据库数据，id , hanyu
     */
    @Override
    public String getHanyuCsv() {
        // 取出彝字集合
        List<YiWords> list = mapper.selectList(new QueryWrapper<YiWords>());

        StringBuffer stringBuffer = new StringBuffer("word_id,hanyu_key\n");

        for (YiWords word : list) {
            if(word.getHanyukey()!=null) {
                for (String s : word.getHanyukey().split("/")) {
                    if (!s.isBlank()) {
                        stringBuffer.append(word.getId()).append(",").append(s).append("\n");
                    }
                }
            }
        }

        return stringBuffer.toString();
    }
}
