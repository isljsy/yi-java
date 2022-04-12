package com.ljsy.yisystem.service;

import com.ljsy.yisystem.entity.DirRadical;
import com.ljsy.yisystem.entity.DirWord;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ljsy.yisystem.entity.WordVo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ljsy
 * @since 2022-03-19
 */
public interface IDirWordService extends IService<DirWord> {


    /**
     *
     * @param id id
     * @return 带全信息的彝字
     */
    WordVo getWordVoById(Integer id);

    /**
     * 通过无单调拼音和方言大区，找出此拼音对应的所有彝字
     * @param pinyin 无单调拼音，如ta
     * @param dialect 方言大区
     * @return 拼音对应的所有彝字， 没有返回null
     */
    List<String> getWordListBySimplePinyin(String pinyin, Integer dialect);

    /**
     * 通过部首id获得,
     * @param radical
     * @return
     */
    Map<Integer, List<String>> getWordListByRadical(String radical);

    /**
     * @return 所有部首
     */
    Map<String, List<DirRadical>> getRadicalMap();
}
