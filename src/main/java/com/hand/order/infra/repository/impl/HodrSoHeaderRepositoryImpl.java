package com.hand.order.infra.repository.impl;

import com.hand.order.domain.entity.HodrSoHeader;
import com.hand.order.domain.repository.HodrSoHeaderRepository;
import com.hand.order.infra.mapper.HodrSoHeaderMapper;
import org.hzero.mybatis.base.impl.BaseRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;


/**
 *  资源库实现
 *
 * @author jiaxing.huang@hand-china.com 2021-07-31 14:26:56
 */
@Component
public class HodrSoHeaderRepositoryImpl extends BaseRepositoryImpl<HodrSoHeader> implements HodrSoHeaderRepository {
    private final HodrSoHeaderMapper hodrSoHeaderMapper;

    @Autowired
    public HodrSoHeaderRepositoryImpl(HodrSoHeaderMapper hodrSoHeaderMapper) {
        this.hodrSoHeaderMapper = hodrSoHeaderMapper;
    }

    @Override
    public HodrSoHeader selectHeaderById(Long soHeaderId) {
        return hodrSoHeaderMapper.selectHeader(soHeaderId);
    }

    @Override
    public List<HodrSoHeader> headerList(Long soHeaderId, Long companyId, Long customerId, String orderNumber, String orderStatus){
        return hodrSoHeaderMapper.getHeadList(soHeaderId, companyId, customerId, orderNumber, orderStatus);
    }

    @Override
    public Long getHeaderCount() {
        return hodrSoHeaderMapper.getHeaderCount();
    }

    @Override
    public Long insertHeaderOrder(Long soHeaderId, String orderNumber, Long companyId, Date orderDate, String orderStatus, Long customerId) {
        return hodrSoHeaderMapper.insertHeaderOrder(soHeaderId, orderNumber, companyId, orderDate, orderStatus, customerId);
    }


}
