package com.hand.order.api.controller.v1;

import org.hzero.core.util.Results;
import org.hzero.core.base.BaseController;
import com.hand.order.app.service.HodrCompanyService;
import com.hand.order.domain.entity.HodrCompany;
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
@RestController("hodrCompanyController.v1" )
@RequestMapping("/v1/{organizationId}/hodr-companys" )
public class HodrCompanyController extends BaseController {

    private final HodrCompanyService hodrCompanyService;
    @Autowired
    public HodrCompanyController(HodrCompanyService hodrCompanyService) {
        this.hodrCompanyService = hodrCompanyService;
    }

    @ApiOperation(value = "列表")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @GetMapping
    public ResponseEntity<Page<HodrCompany>> list(@PathVariable("organizationId") Long organizationId, HodrCompany hodrCompany, @ApiIgnore @SortDefault(value = HodrCompany.FIELD_COMPANY_ID,
            direction = Sort.Direction.DESC) PageRequest pageRequest) {
        Page<HodrCompany> list = hodrCompanyService.list(organizationId, hodrCompany, pageRequest);
        return Results.success(list);
    }

    @ApiOperation(value = "明细")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @GetMapping("/{companyId}")
    public ResponseEntity<HodrCompany> detail(@PathVariable("organizationId") Long organizationId, @PathVariable Long companyId) {
        HodrCompany hodrCompany =hodrCompanyService.detail(organizationId, companyId);
        return Results.success(hodrCompany);
    }

    @ApiOperation(value = "创建")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @PostMapping
    public ResponseEntity<HodrCompany> create(@PathVariable("organizationId") Long organizationId, @RequestBody HodrCompany hodrCompany) {
            hodrCompanyService.create(organizationId, hodrCompany);
        return Results.success(hodrCompany);
    }

    @ApiOperation(value = "修改")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @PutMapping
    public ResponseEntity<HodrCompany> update(@PathVariable("organizationId") Long organizationId, @RequestBody HodrCompany hodrCompany) {
        hodrCompanyService.update(organizationId, hodrCompany);
        return Results.success(hodrCompany);
    }

    @ApiOperation(value = "删除")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @DeleteMapping
    public ResponseEntity<?> remove(@RequestBody HodrCompany hodrCompany) {
        hodrCompanyService.remove(hodrCompany);
        return Results.success();
    }

}
