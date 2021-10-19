package com.hand.order.app.service.impl;

import com.hand.order.domain.entity.HodrSoHeader;
import io.choerodon.mybatis.pagehelper.PageHelper;
import org.hzero.core.base.BaseAppService;

import org.hzero.mybatis.helper.SecurityTokenHelper;
import com.hand.order.app.service.HodrSoLineService;
import com.hand.order.domain.entity.HodrSoLine;
import com.hand.order.domain.repository.HodrSoLineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.choerodon.core.domain.Page;
import io.choerodon.mybatis.pagehelper.domain.PageRequest;

import java.util.Date;
import java.util.List;

/**
 * 应用服务默认实现
 *
 * @author jiaxing.huang@hand-china.com 2021-07-31 14:26:57
 */
@Service
public class HodrSoLineServiceImpl extends BaseAppService implements HodrSoLineService {

    private final HodrSoLineRepository hodrSoLineRepository;

    @Autowired
    public HodrSoLineServiceImpl(HodrSoLineRepository hodrSoLineRepository) {
        this.hodrSoLineRepository = hodrSoLineRepository;
    }

    @Override
    public Page<HodrSoLine> list(Long tenantId, HodrSoLine hodrSoLine, PageRequest pageRequest) {
        return hodrSoLineRepository.pageAndSort(pageRequest, hodrSoLine);
    }

    @Override
    public HodrSoLine detail(Long soLineId) {
        return hodrSoLineRepository.selectByPrimaryKey(soLineId);
    }

    @Override
    public HodrSoLine create(Long tenantId, HodrSoLine hodrSoLine) {
        //validObject(hodrSoLine);
        hodrSoLineRepository.insertSelective(hodrSoLine);
        return hodrSoLine;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public HodrSoLine update(Long tenantId, HodrSoLine hodrSoLine) {
        //SecurityTokenHelper.validToken(hodrSoLine);
        hodrSoLineRepository.updateByPrimaryKeySelective(hodrSoLine);
        return hodrSoLine;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void remove(Long soLineId) {
        //SecurityTokenHelper.validToken(hodrSoLine);
        //hodrSoLineRepository.deleteByPrimaryKey(hodrSoLine);
        hodrSoLineRepository.deleteBySoLineId(soLineId);
    }

    @Override
    public Page<HodrSoHeader> getLineList(String orderNumber, Date orderDate, String companyName, String customerName, PageRequest pageRequest) {
        return PageHelper.doPage(pageRequest, () -> hodrSoLineRepository.lineList(orderNumber, orderDate, companyName, customerName));
    }

    @Override
    public Long getCount(Long soHeaderId) {
        return hodrSoLineRepository.getLineCount(soHeaderId);
    }

    @Override
    public List<HodrSoHeader> getItemList(Long soHeaderId) {
        return hodrSoLineRepository.getItemList(soHeaderId);
    }

}
