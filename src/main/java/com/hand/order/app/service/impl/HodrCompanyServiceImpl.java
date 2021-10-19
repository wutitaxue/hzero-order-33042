package com.hand.order.app.service.impl;

import org.hzero.core.base.BaseAppService;

import org.hzero.mybatis.helper.SecurityTokenHelper;
import com.hand.order.app.service.HodrCompanyService;
import com.hand.order.domain.entity.HodrCompany;
import com.hand.order.domain.repository.HodrCompanyRepository;
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
public class HodrCompanyServiceImpl extends BaseAppService implements HodrCompanyService {

    private final HodrCompanyRepository hodrCompanyRepository;

    @Autowired
    public HodrCompanyServiceImpl(HodrCompanyRepository hodrCompanyRepository) {
        this.hodrCompanyRepository = hodrCompanyRepository;
    }

    @Override
    public Page<HodrCompany> list(Long tenantId, HodrCompany hodrCompany, PageRequest pageRequest) {
        return hodrCompanyRepository.pageAndSort(pageRequest, hodrCompany);
    }

    @Override
    public HodrCompany detail(Long tenantId, Long companyId) {
        return hodrCompanyRepository.selectByPrimaryKey(companyId);
    }

    @Override
    public HodrCompany create(Long tenantId, HodrCompany hodrCompany) {
        validObject(hodrCompany);
        hodrCompanyRepository.insertSelective(hodrCompany);
        return hodrCompany;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public HodrCompany update(Long tenantId, HodrCompany hodrCompany) {
        SecurityTokenHelper.validToken(hodrCompany);
        hodrCompanyRepository.updateByPrimaryKeySelective(hodrCompany);
        return hodrCompany;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void remove(HodrCompany hodrCompany) {
        SecurityTokenHelper.validToken(hodrCompany);
        hodrCompanyRepository.deleteByPrimaryKey(hodrCompany);
    }
}
