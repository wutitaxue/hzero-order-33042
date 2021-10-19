package com.hand.order.infra.mapper;

import com.hand.order.domain.entity.HodrSoHeader;
import com.hand.order.domain.entity.HodrSoLine;
import io.choerodon.mybatis.common.BaseMapper;

import java.util.Date;
import java.util.List;

/**
 * Mapper
 *
 * @author jiaxing.huang@hand-china.com 2021-07-31 14:26:57
 */
public interface HodrSoLineMapper extends BaseMapper<HodrSoLine> {

    List getLineList(String orderNumber, Date orderDate, String companyName, String customerName);

    Long getLineCount(Long soHeaderId);

    void deleteBySoLineId(Long soLineId);

    List getItemList(Long soHeaderId);

    void deleteBySoHeaderId(Long soHeaderId);
}
