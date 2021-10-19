package com.hand.order.app.service.impl;

import org.hzero.core.base.BaseAppService;

import org.hzero.mybatis.helper.SecurityTokenHelper;
import com.hand.order.app.service.HodrItemService;
import com.hand.order.domain.entity.HodrItem;
import com.hand.order.domain.repository.HodrItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.choerodon.core.domain.Page;
import io.choerodon.mybatis.pagehelper.domain.PageRequest;

/**
 * 应用服务默认实现
 *
 * @author jiaxing.huang@hand-china.com 2021-07-31 14:26:56
 */
@Service
public class HodrItemServiceImpl extends BaseAppService implements HodrItemService {

    private final HodrItemRepository hodrItemRepository;

    @Autowired
    public HodrItemServiceImpl(HodrItemRepository hodrItemRepository) {
        this.hodrItemRepository = hodrItemRepository;
    }

    @Override
    public Page<HodrItem> list(Long tenantId, HodrItem hodrItem, PageRequest pageRequest) {
        return hodrItemRepository.pageAndSort(pageRequest, hodrItem);
    }

    @Override
    public HodrItem detail(Long tenantId, Long itemId) {
        return hodrItemRepository.selectByPrimaryKey(itemId);
    }

    @Override
    public HodrItem create(Long tenantId, HodrItem hodrItem) {
        validObject(hodrItem);
        hodrItemRepository.insertSelective(hodrItem);
        return hodrItem;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public HodrItem update(Long tenantId, HodrItem hodrItem) {
        SecurityTokenHelper.validToken(hodrItem);
        hodrItemRepository.updateByPrimaryKeySelective(hodrItem);
        return hodrItem;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void remove(HodrItem hodrItem) {
        SecurityTokenHelper.validToken(hodrItem);
        hodrItemRepository.deleteByPrimaryKey(hodrItem);
    }
}
