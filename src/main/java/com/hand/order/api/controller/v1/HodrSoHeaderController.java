package com.hand.order.api.controller.v1;

import org.hzero.boot.platform.code.builder.CodeRuleBuilder;
import org.hzero.core.base.BaseController;
import org.hzero.core.util.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.hand.order.app.service.HodrSoHeaderService;
import com.hand.order.app.service.HodrSoLineService;
import com.hand.order.domain.entity.HodrSoHeader;
import com.sun.xml.internal.ws.api.model.CheckedException;

import io.choerodon.core.domain.Page;
import io.choerodon.core.exception.CommonException;
import io.choerodon.core.iam.ResourceLevel;
import io.choerodon.mybatis.pagehelper.annotation.SortDefault;
import io.choerodon.mybatis.pagehelper.domain.PageRequest;
import io.choerodon.mybatis.pagehelper.domain.Sort;
import io.choerodon.swagger.annotation.Permission;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;


/**
 * 管理 API
 *
 * @author jiaxing.huang@hand-china.com 2021-07-31 14:26:56
 */
@RestController("hodrSoHeaderController.v1")
@RequestMapping("/v1/{organizationId}/hodr-so-headers")
public class HodrSoHeaderController extends BaseController {

    private final HodrSoHeaderService hodrSoHeaderService;

    @Autowired
    public HodrSoHeaderController(HodrSoHeaderService hodrSoHeaderService, CodeRuleBuilder codeRuleBuilder,
                    HodrSoLineService hodrSoLineService) {
        this.hodrSoHeaderService = hodrSoHeaderService;
    }


    @ApiOperation(value = "明细")
    @Permission(level = ResourceLevel.SITE, permissionLogin = true)
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "ID", paramType = "path")})
    @GetMapping("/{soHeaderId}")
    public ResponseEntity<HodrSoHeader> detail(@PathVariable("organizationId") Long organizationId,
                    @PathVariable Long soHeaderId) {
        HodrSoHeader hodrSoHeader = hodrSoHeaderService.detail(soHeaderId);
        return Results.success(hodrSoHeader);
    }


    @ApiOperation(value = "创建头订单")
    @Permission(level = ResourceLevel.SITE)
    @PostMapping("/createHeader")
    public ResponseEntity<HodrSoHeader> create(@PathVariable("organizationId") Long organizationId,
                    @RequestBody HodrSoHeader hodrSoHeader) {
        hodrSoHeaderService.insertOrder(hodrSoHeader);
        return Results.success(hodrSoHeader);
    }

    @ApiOperation(value = "修改")
    @Permission(level = ResourceLevel.SITE, permissionLogin = true)
    @PutMapping("/updateHeader")
    public ResponseEntity<HodrSoHeader> update(@PathVariable("organizationId") Long organizationId,
                    @RequestBody HodrSoHeader hodrSoHeader) {
        hodrSoHeaderService.update(hodrSoHeader);
        return Results.success(hodrSoHeader);
    }


    @ApiOperation(value = "根据头ID对订单头进行删除")
    @Permission(level = ResourceLevel.SITE, permissionLogin = true)
    @GetMapping("/deleteHeader")
    public ResponseEntity<?> removeHeader(@PathVariable("organizationId") Long organizationId, Long soHeaderId) {

        HodrSoHeader hodrSoHeader = hodrSoHeaderService.detail(soHeaderId);
        hodrSoHeaderService.remove(hodrSoHeader);
        return Results.success();
    }

    @ApiOperation(value = "根据头ID对单据行和订单头进行单据删除")
    @Permission(level = ResourceLevel.SITE, permissionLogin = true)
    @DeleteMapping
    public ResponseEntity<?> removeHeaderAndLine(@PathVariable("organizationId") Long organizationId,
                    @RequestBody HodrSoHeader hodrSoHeader) {
        HodrSoHeader hodrSoHeaderCheck = hodrSoHeaderService.detail(hodrSoHeader.getSoHeaderId());
        hodrSoHeaderService.removeHeaderAndLine(hodrSoHeaderCheck);
        return Results.success();
    }

    @ApiOperation(value = "根据输入字段查询订单头信息")
    @Permission(level = ResourceLevel.SITE, permissionLogin = true)
    @GetMapping("/searchList")
    public ResponseEntity<Page<HodrSoHeader>> searchList(@PathVariable("organizationId") Long organizationId,
                    Long soHeaderId, Long companyId, Long customerId, String orderNumber, String orderStatus,
                    @ApiIgnore @SortDefault(value = HodrSoHeader.FIELD_SO_HEADER_ID,
                                    direction = Sort.Direction.DESC) PageRequest pageRequest) {
        Page<HodrSoHeader> list = hodrSoHeaderService.getHeaderList(soHeaderId, companyId, customerId, orderNumber,
                        orderStatus, pageRequest);
        return Results.success(list);
    }

    @ApiOperation(value = "头订单列表")
    @Permission(level = ResourceLevel.SITE)
    @GetMapping("/headerList")
    public ResponseEntity<Page<HodrSoHeader>> list(@PathVariable("organizationId") Long organizationId,
                    HodrSoHeader hodrSoHeader, PageRequest pageRequest) {
        // hodrSoHeader.setToken(null);
        Page<HodrSoHeader> list = hodrSoHeaderService.list(hodrSoHeader, pageRequest);
        return Results.success(list);
    }

    @ApiOperation(value = "检查订单头信息")
    @Permission(level = ResourceLevel.SITE, permissionLogin = true)
    @GetMapping("/checkStatus")
    public void check(@PathVariable("organizationId") Long organizationId, Long soHeaderId, String status) {
        HodrSoHeader hodrSoHeader = hodrSoHeaderService.detail(soHeaderId);
        hodrSoHeaderService.updateStatus(hodrSoHeader);
    }

    /**
     * @param organizationId
     * @return
     */
    @ApiOperation(value = "保存订单头行结构")
    @Permission(level = ResourceLevel.SITE, permissionLogin = true)
    @PostMapping("/saveOrder")
    public ResponseEntity<HodrSoHeader> updateHeaderAndLines(@PathVariable("organizationId") Long organizationId,
                    @RequestBody HodrSoHeader hodrSoHeader) {
        hodrSoHeaderService.saveOrder(hodrSoHeader, organizationId);
        return Results.success(hodrSoHeader);
    }

    /**
     * soHeaderId 订单头id
     * 
     * @return
     */
    @ApiOperation(value = "启动审批流")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @PutMapping("/workflow/{soHeaderId}")
    public ResponseEntity<?> orderStatusWorkflow(@PathVariable long organizationId, @PathVariable Long soHeaderId)
                    throws CommonException {
        hodrSoHeaderService.orderStatusWorkflow(organizationId, soHeaderId);
        return Results.success();
    }

    /**
     * 审批流结束后修改订单状态
     *
     * @param soHeaderId 订单头id
     * @param status 修改后的状态
     * @return
     * @throws CheckedException 状态校验异常
     */
    @ApiOperation(value = "审批结束后修改状态")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @PutMapping("/workflowChangeStatus/{soHeaderId}/{status}")
    public ResponseEntity<?> workflowChangeStatus(@PathVariable("organizationId") Long organizationId,
                    @PathVariable("soHeaderId") Long soHeaderId, @PathVariable("status") String status)
                    throws CommonException {
        hodrSoHeaderService.workflowChangeStatus(organizationId, soHeaderId, status);
        return Results.success();
    }


    /**
     * 下载报表文件
     * 
     * @param exportType 导出类型
     * @return response
     */
    @ApiOperation(value = "客户端报表下载")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @PutMapping("/downloadReport")
    public void downloadReport(@PathVariable("organizationId") Long organizationId, String exportType) {
        hodrSoHeaderService.downloadReport(organizationId, exportType);
    };


    /**
     * 审批通过
     *
     * @param organizationId 租户ID
     */
    @ApiOperation(value = "订单审批通过")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @PutMapping("/approveOrder")
    public void approveOrder(@PathVariable("organizationId") Long organizationId) {
        hodrSoHeaderService.approveOrder(organizationId);
    };
}
