package com.hand.order.app.service;

import com.hand.order.domain.entity.HodrCompany;

import io.choerodon.core.domain.Page;
import io.choerodon.mybatis.pagehelper.domain.PageRequest;
/**
 * 应用服务
 *
 * @author jiaxing.huang@hand-china.com 2021-07-31 14:26:56
 */
public interface HodrCompanyService {

    /**
    * 查询参数
    *
    * @param tenantId 租户ID
    * @param hodrCompany 
    * @param pageRequest 分页
    * @return 列表
    */
    Page<HodrCompany> list(Long tenantId, HodrCompany hodrCompany, PageRequest pageRequest);

    /**
     * 详情
     *
     * @param tenantId 租户ID
     * @param companyId 主键
     * @return 列表
     */
    HodrCompany detail(Long tenantId, Long companyId);

    /**
     * 创建
     *
     * @param tenantId 租户ID
     * @param hodrCompany 
     * @return 
     */
    HodrCompany create(Long tenantId, HodrCompany hodrCompany);

    /**
     * 更新
     *
     * @param tenantId 租户ID
     * @param hodrCompany 
     * @return 
     */
    HodrCompany update(Long tenantId, HodrCompany hodrCompany);

    /**
     * 删除
     *
     * @param hodrCompany 
     */
    void remove(HodrCompany hodrCompany);
}
