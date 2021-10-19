package com.hand.order.app.service.impl;

import com.hand.order.domain.entity.HodrCompany;
import io.choerodon.mybatis.pagehelper.PageHelper;
import org.hzero.core.base.BaseAppService;

import org.hzero.mybatis.helper.SecurityTokenHelper;
import com.hand.order.app.service.HodrCustomerService;
import com.hand.order.domain.entity.HodrCustomer;
import com.hand.order.domain.repository.HodrCustomerRepository;
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
public class HodrCustomerServiceImpl extends BaseAppService implements HodrCustomerService {

    private final HodrCustomerRepository hodrCustomerRepository;

    @Autowired
    public HodrCustomerServiceImpl(HodrCustomerRepository hodrCustomerRepository) {
        this.hodrCustomerRepository = hodrCustomerRepository;
    }

    @Override
    public Page<HodrCustomer> list(Long tenantId, HodrCustomer hodrCustomer, PageRequest pageRequest) {
        return hodrCustomerRepository.pageAndSort(pageRequest, hodrCustomer);
    }

    @Override
    public HodrCustomer detail(Long tenantId, Long customerId) {
        return hodrCustomerRepository.selectByPrimaryKey(customerId);
    }

    @Override
    public HodrCustomer create(Long tenantId, HodrCustomer hodrCustomer) {
        validObject(hodrCustomer);
        hodrCustomerRepository.insertSelective(hodrCustomer);
        return hodrCustomer;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public HodrCustomer update(Long tenantId, HodrCustomer hodrCustomer) {
        SecurityTokenHelper.validToken(hodrCustomer);
        hodrCustomerRepository.updateByPrimaryKeySelective(hodrCustomer);
        return hodrCustomer;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void remove(HodrCustomer hodrCustomer) {
        SecurityTokenHelper.validToken(hodrCustomer);
        hodrCustomerRepository.deleteByPrimaryKey(hodrCustomer);
    }
}
