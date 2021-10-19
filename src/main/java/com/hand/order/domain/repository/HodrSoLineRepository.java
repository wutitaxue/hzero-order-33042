package com.hand.order.domain.repository;

import com.hand.order.domain.entity.HodrSoHeader;
import io.choerodon.mybatis.pagehelper.domain.PageRequest;
import org.hzero.mybatis.base.BaseRepository;
import com.hand.order.domain.entity.HodrSoLine;

import java.util.Date;
import java.util.List;

/**
 * 资源库
 *
 * @author jiaxing.huang@hand-china.com 2021-07-31 14:26:57
 */
public interface HodrSoLineRepository extends BaseRepository<HodrSoLine> {

    List lineList(String orderNumber, Date orderDate, String companyName, String customerName);

    Long getLineCount(Long soHeaderId);

    void deleteBySoLineId(Long soLineId);

    void deleteBySoHeaderId(Long soHeaderId);

    List<HodrSoHeader> getItemList(Long soHeaderId);
}
