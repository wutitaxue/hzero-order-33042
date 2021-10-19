package com.hand.order.app.service;

import com.hand.order.domain.entity.HodrItem;

import io.choerodon.core.domain.Page;
import io.choerodon.mybatis.pagehelper.domain.PageRequest;
/**
 * 应用服务
 *
 * @author jiaxing.huang@hand-china.com 2021-07-31 14:26:56
 */
public interface HodrItemService {

    /**
    * 查询参数
    *
    * @param tenantId 租户ID
    * @param hodrItem 
    * @param pageRequest 分页
    * @return 列表
    */
    Page<HodrItem> list(Long tenantId, HodrItem hodrItem, PageRequest pageRequest);

    /**
     * 详情
     *
     * @param tenantId 租户ID
     * @param itemId 主键
     * @return 列表
     */
    HodrItem detail(Long tenantId, Long itemId);

    /**
     * 创建
     *
     * @param tenantId 租户ID
     * @param hodrItem 
     * @return 
     */
    HodrItem create(Long tenantId, HodrItem hodrItem);

    /**
     * 更新
     *
     * @param tenantId 租户ID
     * @param hodrItem 
     * @return 
     */
    HodrItem update(Long tenantId, HodrItem hodrItem);

    /**
     * 删除
     *
     * @param hodrItem 
     */
    void remove(HodrItem hodrItem);
}
