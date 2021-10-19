package com.hand.order.api.controller.v1;

import org.hzero.core.util.Results;
import org.hzero.core.base.BaseController;
import com.hand.order.app.service.HodrCustomerService;
import com.hand.order.domain.entity.HodrCustomer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.choerodon.core.domain.Page;
import io.choerodon.core.iam.ResourceLevel;
import io.choerodon.mybatis.pagehelper.annotation.SortDefault;
import io.choerodon.mybatis.pagehelper.domain.PageRequest;
import io.choerodon.mybatis.pagehelper.domain.Sort;
import io.choerodon.swagger.annotation.Permission;

import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

/**
 *  管理 API
 *
 * @author jiaxing.huang@hand-china.com 2021-07-31 14:26:56
 */
@RestController("hodrCustomerController.v1" )
@RequestMapping("/v1/{organizationId}/hodr-customers" )
public class HodrCustomerController extends BaseController {

    private final HodrCustomerService hodrCustomerService;
    @Autowired
    public HodrCustomerController(HodrCustomerService hodrCustomerService) {
        this.hodrCustomerService = hodrCustomerService;
    }

    @ApiOperation(value = "列表")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @GetMapping
    public ResponseEntity<Page<HodrCustomer>> list(@PathVariable("organizationId") Long organizationId, HodrCustomer hodrCustomer, @ApiIgnore @SortDefault(value = HodrCustomer.FIELD_CUSTOMER_ID,
            direction = Sort.Direction.DESC) PageRequest pageRequest) {
        Page<HodrCustomer> list = hodrCustomerService.list(organizationId, hodrCustomer, pageRequest);
        return Results.success(list);
    }

    @ApiOperation(value = "明细")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @GetMapping("/{customerId}")
    public ResponseEntity<HodrCustomer> detail(@PathVariable("organizationId") Long organizationId, @PathVariable Long customerId) {
        HodrCustomer hodrCustomer =hodrCustomerService.detail(organizationId, customerId);
        return Results.success(hodrCustomer);
    }

    @ApiOperation(value = "创建")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @PostMapping
    public ResponseEntity<HodrCustomer> create(@PathVariable("organizationId") Long organizationId, @RequestBody HodrCustomer hodrCustomer) {
            hodrCustomerService.create(organizationId, hodrCustomer);
        return Results.success(hodrCustomer);
    }

    @ApiOperation(value = "修改")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @PutMapping
    public ResponseEntity<HodrCustomer> update(@PathVariable("organizationId") Long organizationId, @RequestBody HodrCustomer hodrCustomer) {
        hodrCustomerService.update(organizationId, hodrCustomer);
        return Results.success(hodrCustomer);
    }

    @ApiOperation(value = "删除")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @DeleteMapping
    public ResponseEntity<?> remove(@RequestBody HodrCustomer hodrCustomer) {
        hodrCustomerService.remove(hodrCustomer);
        return Results.success();
    }

}
