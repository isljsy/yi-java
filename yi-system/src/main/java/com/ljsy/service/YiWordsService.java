package com.ljsy.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.ljsy.entity.YiWords;

import java.sql.Blob;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ljsy
 * @since 2022-01-20
 */
public interface YiWordsService extends IService<YiWords> {

    /**
     *
     * @return 小程序后台数据库数据
     */
    String getJSON();

    /**
     *
     * @return 小程序关键词搜索和拼音搜索的列表， String格式
     */
    String getSearchJS();


    /**
     *
     * @return 小程序各地区方言拼音分类检索的列表， String格式
     */
    String getPinyinJS();

    /**
     *
     * @return 汉语翻译的数据库数据，id , hanyu
     */
    String getHanyuCsv();
}
