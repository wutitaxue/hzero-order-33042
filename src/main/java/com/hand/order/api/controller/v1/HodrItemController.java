package com.hand.order.api.controller.v1;

import org.hzero.core.util.Results;
import org.hzero.core.base.BaseController;
import com.hand.order.app.service.HodrItemService;
import com.hand.order.domain.entity.HodrItem;
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
@RestController("hodrItemController.v1" )
@RequestMapping("/v1/{organizationId}/hodr-items" )
public class HodrItemController extends BaseController {

    private final HodrItemService hodrItemService;
    @Autowired
    public HodrItemController(HodrItemService hodrItemService) {
        this.hodrItemService = hodrItemService;
    }

    @ApiOperation(value = "列表")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @GetMapping
    public ResponseEntity<Page<HodrItem>> list(@PathVariable("organizationId") Long organizationId, HodrItem hodrItem, @ApiIgnore @SortDefault(value = HodrItem.FIELD_ITEM_ID,
            direction = Sort.Direction.DESC) PageRequest pageRequest) {
        Page<HodrItem> list = hodrItemService.list(organizationId, hodrItem, pageRequest);
        return Results.success(list);
    }

    @ApiOperation(value = "明细")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @GetMapping("/{itemId}")
    public ResponseEntity<HodrItem> detail(@PathVariable("organizationId") Long organizationId, @PathVariable Long itemId) {
        HodrItem hodrItem =hodrItemService.detail(organizationId, itemId);
        return Results.success(hodrItem);
    }

    @ApiOperation(value = "创建")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @PostMapping
    public ResponseEntity<HodrItem> create(@PathVariable("organizationId") Long organizationId, @RequestBody HodrItem hodrItem) {
            hodrItemService.create(organizationId, hodrItem);
        return Results.success(hodrItem);
    }

    @ApiOperation(value = "修改")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @PutMapping
    public ResponseEntity<HodrItem> update(@PathVariable("organizationId") Long organizationId, @RequestBody HodrItem hodrItem) {
        hodrItemService.update(organizationId, hodrItem);
        return Results.success(hodrItem);
    }

    @ApiOperation(value = "删除")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @DeleteMapping
    public ResponseEntity<?> remove(@RequestBody HodrItem hodrItem) {
        hodrItemService.remove(hodrItem);
        return Results.success();
    }

}
