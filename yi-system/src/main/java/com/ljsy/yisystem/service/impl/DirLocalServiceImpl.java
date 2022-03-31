package com.ljsy.yisystem.service.impl;

import com.ljsy.yisystem.entity.DirLocal;
import com.ljsy.yisystem.entity.LocalVo;
import com.ljsy.yisystem.mapper.DirLocalMapper;
import com.ljsy.yisystem.service.IDirLocalService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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
    public List<LocalVo> getLocalVoList() {
        return localMapper.getLocalVoList();
    }
}
