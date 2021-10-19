package com.hand.order.api.controller.v1;

import com.hand.order.app.service.HodrSoHeaderService;
import com.hand.order.domain.entity.HodrSoHeader;
import com.sun.deploy.appcontext.AppContext;
import org.hzero.core.util.Results;
import org.hzero.core.base.BaseController;
import com.hand.order.app.service.HodrSoLineService;
import com.hand.order.domain.entity.HodrSoLine;
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

import java.util.Date;
import java.util.List;

/**
 *  管理 API
 *
 * @author jiaxing.huang@hand-china.com 2021-07-31 14:26:57
 */
@RestController("hodrSoLineController.v1" )
@RequestMapping("/v1/{organizationId}/hodr-so-lines" )
public class HodrSoLineController extends BaseController {

    private final HodrSoLineService hodrSoLineService;
    private final HodrSoHeaderService hodrSoHeaderService;
    @Autowired
    public HodrSoLineController(HodrSoLineService hodrSoLineService, HodrSoHeaderService hodrSoHeaderService) {
        this.hodrSoLineService = hodrSoLineService;
        this.hodrSoHeaderService = hodrSoHeaderService;
    }

    @ApiOperation(value = "行订单列表")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @GetMapping("/lineList")
    public ResponseEntity<Page<HodrSoLine>> list(@PathVariable("organizationId") Long organizationId, HodrSoLine hodrSoLine, @ApiIgnore @SortDefault(value = HodrSoLine.FIELD_SO_LINE_ID,
            direction = Sort.Direction.DESC) PageRequest pageRequest) {
        Page<HodrSoLine> list = hodrSoLineService.list(organizationId, hodrSoLine, pageRequest);
        return Results.success(list);
    }

    @ApiOperation(value = "明细")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @GetMapping("/{soLineId}")
    public ResponseEntity<HodrSoLine> detail(@PathVariable Long soLineId) {
        HodrSoLine hodrSoLine =hodrSoLineService.detail(soLineId);
        return Results.success(hodrSoLine);
    }

    @ApiOperation(value = "创建")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @PostMapping("/createLine")
    public ResponseEntity<HodrSoLine> create(@PathVariable("organizationId") Long organizationId, @RequestBody HodrSoLine hodrSoLine) {
            hodrSoLineService.create(organizationId, hodrSoLine);
        return Results.success(hodrSoLine);
    }

    @ApiOperation(value = "修改")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @PutMapping("/updateLine")
    public ResponseEntity<HodrSoLine> update(@PathVariable("organizationId") Long organizationId, @RequestBody HodrSoLine hodrSoLine) {
        hodrSoLineService.update(organizationId, hodrSoLine);
        return Results.success(hodrSoLine);
    }

    @ApiOperation(value = "根据行ID对单据进行单据删除")
    @Permission(level = ResourceLevel.SITE, permissionLogin = true)
    @GetMapping("/deleteLine")
    public ResponseEntity<?> remove(@PathVariable("organizationId") Long organizationId, Long soLineId) {

        HodrSoLine HodrSoLineCheck =hodrSoLineService.detail(soLineId);

        //获取头数据
        /**
        Long hodrSoHeaderId = HodrSoLineCheck.getSoHeaderId();
        HodrSoHeader hodrSoHeader = hodrSoHeaderService.detail(hodrSoHeaderId);

        //权限检查
        if (hodrSoHeader == null) {
            throw new CommonException("error.NoSuchOrder");
        }

        if (hodrSoHeader.getOrderStatus().equals("NEW") || hodrSoHeader.getOrderStatus().equals("REJECTED")) {
            System.out.println("Success");
        } else {
            throw new CommonException("error.ErrorStatus");
        }
         */
        hodrSoLineService.remove(soLineId);
        return Results.success();
    }

    /**
     *
     * @param organizationId
     * @param orderNumber
     * @param orderDate
     * @param companyName
     * @param customerName
     * @param pageRequest
     * @return
     */
    @ApiOperation(value = "根据输入字段查询订单行信息")
    @Permission(level = ResourceLevel.SITE, permissionLogin = true)
    @GetMapping("/searchLineList")
    public ResponseEntity<Page<HodrSoHeader>> searchList(@PathVariable("organizationId") Long organizationId, String orderNumber, Date orderDate, String companyName, String customerName, @ApiIgnore @SortDefault(value = HodrSoHeader.FIELD_SO_HEADER_ID,
            direction = Sort.Direction.DESC) PageRequest pageRequest)  {
        Page<HodrSoHeader> list = hodrSoLineService.getLineList(orderNumber, orderDate, companyName, customerName, pageRequest);
        return Results.success(list);
    }

    @ApiOperation(value = "根据输头ID查询物料信息")
    @Permission(level = ResourceLevel.SITE, permissionLogin = true)
    @GetMapping("/searchItemList")
    public List<HodrSoHeader> searchItemList(@PathVariable("organizationId") Long organizationId, Long soHeaderId)  {
        List<HodrSoHeader> list = hodrSoLineService.getItemList(soHeaderId);
        return list;
    }

}
