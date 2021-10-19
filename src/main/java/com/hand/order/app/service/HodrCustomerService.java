package com.hand.order.app.service;

import com.hand.order.domain.entity.HodrCustomer;

import io.choerodon.core.domain.Page;
import io.choerodon.mybatis.pagehelper.domain.PageRequest;
/**
 * 应用服务
 *
 * @author jiaxing.huang@hand-china.com 2021-07-31 14:26:56
 */
public interface HodrCustomerService {

    /**
    * 查询参数
    *
    * @param tenantId 租户ID
    * @param hodrCustomer
    * @param pageRequest 分页
    * @return 列表
    */
    Page<HodrCustomer> list(Long tenantId, HodrCustomer hodrCustomer, PageRequest pageRequest);

    /**
     * 详情
     *
     * @param tenantId 租户ID
     * @param customerId 主键
     * @return 列表
     */
    HodrCustomer detail(Long tenantId, Long customerId);

    /**
     * 创建
     *
     * @param tenantId 租户ID
     * @param hodrCustomer 
     * @return 
     */
    HodrCustomer create(Long tenantId, HodrCustomer hodrCustomer);

    /**
     * 更新
     *
     * @param tenantId 租户ID
     * @param hodrCustomer 
     * @return 
     */
    HodrCustomer update(Long tenantId, HodrCustomer hodrCustomer);

    /**
     * 删除
     *
     * @param hodrCustomer 
     */
    void remove(HodrCustomer hodrCustomer);
}
