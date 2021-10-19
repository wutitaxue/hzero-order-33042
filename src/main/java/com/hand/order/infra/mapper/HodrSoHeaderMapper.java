package com.hand.order.infra.mapper;

import com.hand.order.domain.entity.HodrSoHeader;
import io.choerodon.mybatis.common.BaseMapper;
import org.springframework.context.annotation.Bean;

import java.util.*;

/**
 * Mapper
 *
 * @author jiaxing.huang@hand-china.com 2021-07-31 14:26:56
 */

public interface HodrSoHeaderMapper extends BaseMapper<HodrSoHeader> {
    /**
     * 查询订单头
     *
     * @return Task
     */
    HodrSoHeader selectHeader(Long soHeaderId);

    List<HodrSoHeader> getHeadList(Long soHeaderId, Long companyId, Long customerId, String orderNumber, String orderStatus);

    Long getHeaderCount();

    Long insertHeaderOrder(Long soHeaderId, String orderNumber, Long companyId, Date orderDate, String orderStatus, Long customerId);

    Long updateStatus();
}
