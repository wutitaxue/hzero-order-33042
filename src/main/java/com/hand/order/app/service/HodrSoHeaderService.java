package com.hand.order.app.service;

import com.hand.order.domain.entity.HodrSoHeader;

import io.choerodon.core.domain.Page;
import io.choerodon.mybatis.pagehelper.domain.PageRequest;
import org.hzero.export.constant.ExportType;

import java.util.List;

/**
 * 应用服务
 *
 * @author jiaxing.huang@hand-china.com 2021-07-31 14:26:56
 */
public interface HodrSoHeaderService {


    /**
     * 详情
     *
     * @param tenantId 租户ID
     * @param soHeaderId 主键
     * @return 列表
     */
    HodrSoHeader detail(Long soHeaderId);
    /**
     * 创建
     *
     * @param tenantId 租户ID
     * @param hodrSoHeader 
     * @return 
     */
    //HodrSoHeader create(Long tenantId, HodrSoHeader hodrSoHeader);
    HodrSoHeader create(HodrSoHeader hodrSoHeader);
    /**
     * 更新
     *
     * @param tenantId 租户ID
     * @param hodrSoHeader 
     * @return 
     */
    HodrSoHeader update(HodrSoHeader hodrSoHeader);

    /**
     * 删除
     *
     * @param hodrSoHeader 
     */
    void remove(HodrSoHeader hodrSoHeader);
    /**
     * 查询
     *
     * @param id 主键
     * @return 列表
     */
    HodrSoHeader select(Long id);

    Page<HodrSoHeader> getHeaderList(Long soHeaderId, Long companyId, Long customerId, String orderNumber, String orderStatus, PageRequest pageRequest);

    /**
     * 查询参数
     *
     * @param tenantId 租户ID
     * @param hodrSoHeader
     * @param pageRequest 分页
     * @return 列表
     */
    Page<HodrSoHeader> list(HodrSoHeader hodrSoHeader, PageRequest pageRequest);

    void updateStatus(HodrSoHeader hodrSoHeader);

    void saveOrder(HodrSoHeader hodrSoHeader, Long organizationId);

    void insertOrder(HodrSoHeader hodrSoHeader);

    void removeHeaderAndLine(HodrSoHeader hodrSoHeaderCheck);

    void workUpdateStatus(HodrSoHeader soHeader, String actionCode);

    void orderStatusWorkflow(long organizationId, Long soHeaderId);

    void workflowChangeStatus(long organizationId, Long soHeaderId, String status);

    void downloadReport(Long organizationId, String exportType);

    void approveOrder(Long organizationId);
}
