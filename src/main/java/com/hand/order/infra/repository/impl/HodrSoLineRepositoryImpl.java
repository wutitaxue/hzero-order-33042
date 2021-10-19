package com.hand.order.infra.repository.impl;

import com.hand.order.domain.entity.HodrSoHeader;
import com.hand.order.infra.mapper.HodrSoLineMapper;
import org.hzero.mybatis.base.impl.BaseRepositoryImpl;
import com.hand.order.domain.entity.HodrSoLine;
import com.hand.order.domain.repository.HodrSoLineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 *  资源库实现
 *
 * @author jiaxing.huang@hand-china.com 2021-07-31 14:26:57
 */
@Component
public class HodrSoLineRepositoryImpl extends BaseRepositoryImpl<HodrSoLine> implements HodrSoLineRepository {

    private final HodrSoLineMapper hodrSoLineMapper;

    @Autowired
    public HodrSoLineRepositoryImpl(HodrSoLineMapper hodrSoLineMapper) {
        this.hodrSoLineMapper = hodrSoLineMapper;
    }

    @Override
    public List lineList(String orderNumber, Date orderDate, String companyName, String customerName) {
        return hodrSoLineMapper.getLineList(orderNumber, orderDate, companyName, customerName);
    }

    @Override
    public Long getLineCount(Long soHeaderId) {
        return hodrSoLineMapper.getLineCount(soHeaderId);
    }

    @Override
    public void deleteBySoLineId(Long soLineId) {
        hodrSoLineMapper.deleteBySoLineId(soLineId);
    }

    @Override
    public void deleteBySoHeaderId(Long soHeaderId) {
        hodrSoLineMapper.deleteBySoHeaderId(soHeaderId);
    }

    @Override
    public List<HodrSoHeader> getItemList(Long soHeaderId) {
        return hodrSoLineMapper.getItemList(soHeaderId);
    }

}
