package com.hand.order.infra.repository.impl;

import com.hand.order.infra.mapper.HodrCustomerMapper;
import org.hzero.mybatis.base.impl.BaseRepositoryImpl;
import com.hand.order.domain.entity.HodrCustomer;
import com.hand.order.domain.repository.HodrCustomerRepository;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 *  资源库实现
 *
 * @author jiaxing.huang@hand-china.com 2021-07-31 14:26:56
 */
@Component
public class HodrCustomerRepositoryImpl extends BaseRepositoryImpl<HodrCustomer> implements HodrCustomerRepository {

    public HodrCustomerMapper hodrCustomerMapper;

}
