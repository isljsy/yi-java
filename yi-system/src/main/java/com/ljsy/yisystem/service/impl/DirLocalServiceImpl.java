package com.ljsy.yisystem.service.impl;

import com.ljsy.yisystem.entity.DirLocal;
import com.ljsy.yisystem.entity.LocalVo;
import com.ljsy.yisystem.mapper.DirLocalMapper;
import com.ljsy.yisystem.service.IDirLocalService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ljsy
 * @since 2022-03-31
 */
@Service
public class DirLocalServiceImpl extends ServiceImpl<DirLocalMapper, DirLocal> implements IDirLocalService {

    @Resource
    private DirLocalMapper localMapper;

    /**
     * @return 所有地区带方言大区
     */
    @Override
    public Map<String, List<DirLocal>> getLocalVoList() {
        List<LocalVo> list =  localMapper.getLocalVoList();
        Map<String, List<DirLocal>> map = new HashMap<>();
        for(LocalVo localVo: list){
            DirLocal local = new DirLocal();
            local.setId(localVo.getId());
            local.setLocal(localVo.getLocal());

            if(!map.containsKey(localVo.getDialect())){
                map.put(localVo.getDialect(), new ArrayList<>());
            }

            map.get(localVo.getDialect()).add(local);
        }
        return map;
    }
}
