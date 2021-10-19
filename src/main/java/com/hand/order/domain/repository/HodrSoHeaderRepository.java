package com.hand.order.domain.repository;

import org.hzero.mybatis.base.BaseRepository;
import com.hand.order.domain.entity.HodrSoHeader;

import java.util.Date;
import java.util.List;

/**
 * 资源库
 *
 * @author jiaxing.huang@hand-china.com 2021-07-31 14:26:56
 */
public interface HodrSoHeaderRepository extends BaseRepository<HodrSoHeader> {


    HodrSoHeader selectHeaderById(Long soHeaderId);

    List<HodrSoHeader> headerList(Long soHeaderId, Long companyId, Long customerId, String orderNumber, String orderStatus);

    Long getHeaderCount();

    Long insertHeaderOrder(Long soHeaderId, String orderNumber, Long companyId, Date orderDate, String orderStatus, Long customerId);
}
