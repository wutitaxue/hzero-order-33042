package com.hand.order.app.service;

import com.hand.order.domain.entity.HodrSoHeader;
import com.hand.order.domain.entity.HodrSoLine;

import io.choerodon.core.domain.Page;
import io.choerodon.mybatis.pagehelper.domain.PageRequest;

import java.util.Date;
import java.util.List;

/**
 * 应用服务
 *
 * @author jiaxing.huang@hand-china.com 2021-07-31 14:26:57
 */
public interface HodrSoLineService {

    /**
    * 查询参数
    *
    * @param tenantId 租户ID
    * @param hodrSoLine 
    * @param pageRequest 分页
    * @return 列表
    */
    Page<HodrSoLine> list(Long tenantId, HodrSoLine hodrSoLine, PageRequest pageRequest);

    /**
     * 详情
     *
     * @param tenantId 租户ID
     * @param soLineId 主键
     * @return 列表
     */
    HodrSoLine detail(Long soLineId);

    /**
     * 创建
     *
     * @param tenantId 租户ID
     * @param hodrSoLine 
     * @return 
     */
    HodrSoLine create(Long tenantId, HodrSoLine hodrSoLine);

    /**
     * 更新
     *
     * @param tenantId 租户ID
     * @param hodrSoLine 
     * @return 
     */
    HodrSoLine update(Long tenantId, HodrSoLine hodrSoLine);

    /**
     * 删除
     *
     * @param hodrSoLine 
     */
    void remove(Long soLineId);

    Page<HodrSoHeader> getLineList(String orderNumber, Date orderDate, String companyName, String customerName, PageRequest pageRequest);

    Long getCount(Long soHeaderId);

    List<HodrSoHeader> getItemList(Long soHeaderId);
}
