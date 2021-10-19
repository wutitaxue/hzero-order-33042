package com.hand.order.api.controller.v1;

import io.choerodon.core.iam.ResourceLevel;
import io.choerodon.swagger.annotation.Permission;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.hzero.boot.interfaces.sdk.dto.RequestPayloadDTO;
import org.hzero.boot.interfaces.sdk.dto.ResponsePayloadDTO;
import org.hzero.boot.interfaces.sdk.invoke.InterfaceInvokeSdk;
import org.hzero.core.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("orderInterfaceController.v1")
@RequestMapping("/v1/{organizationId}/hand/interface")
public class OrderInterfaceController extends BaseController {
    @Autowired   private InterfaceInvokeSdk interfaceInvokeSdk;
    @ApiOperation(value = "调用一个接口")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @PostMapping("/invoke")
    public ResponsePayloadDTO invoke(@PathVariable long organizationId) {
        String namespace = "HZERO";
        String interfaceCode = "hzero-order-33042.hodr-so-header.searchList";
        String serverCode = "ORDER33042";
        RequestPayloadDTO payload = new RequestPayloadDTO();
        payload.setMediaType("application/json");

        ResponsePayloadDTO responsePayloadDTO = interfaceInvokeSdk.invoke(namespace,
                serverCode,
                interfaceCode,
                payload);
        return responsePayloadDTO;
    }
}