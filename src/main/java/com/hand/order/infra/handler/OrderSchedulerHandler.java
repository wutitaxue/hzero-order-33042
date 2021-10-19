package com.hand.order.infra.handler;

import com.hand.order.domain.service.OrderMessage;
import com.hand.order.infra.mapper.HodrSoHeaderMapper;
import org.hzero.boot.message.entity.Receiver;
import org.hzero.boot.scheduler.infra.annotation.JobHandler;
import org.hzero.boot.scheduler.infra.enums.ReturnT;
import org.hzero.boot.scheduler.infra.handler.IJobHandler;
import org.hzero.boot.scheduler.infra.tool.SchedulerTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.hzero.boot.message.MessageClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 */


@JobHandler("OrderSchedulerHandler")
public class OrderSchedulerHandler implements IJobHandler {

    @Autowired
    private HodrSoHeaderMapper hodrSoHeaderMapper;

    @Autowired
    private MessageClient messageClient;

    @Override
    public ReturnT execute(Map<String, String> map, SchedulerTool tool) {
        tool.updateProgress(1, "Starting...");
        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Long num = hodrSoHeaderMapper.updateStatus();
        if(num > 0){
            long tenantId = 0L;
            //账户代码
            String serverCode = "HAND_DEMO_EMAIL";
            //消息模板代码
            String messageTemplateCode = "HAND_INFORMATION_DEMO";
            List<Receiver> receiverList = new ArrayList<>();
            Receiver receiver = new Receiver();
            //接受方
            receiver.setEmail("2738381962@qq.com");
            receiverList.add(receiver);
            Map<String, String> args = new HashMap<>();
            args.put("parmA", "订单状态为已审批");
            args.put("parmB", "已关闭");
            messageClient.sendEmail(tenantId, serverCode, messageTemplateCode, receiverList, args, null);
        }
        tool.updateProgress(100, "Done");
        tool.info("Done");
        return ReturnT.SUCCESS;
    }

    @Override
    public void onCreate(SchedulerTool tool) {
        tool.info("onCreate");
    }
    @Override
    public void onException(SchedulerTool tool) {
        tool.info("onException");
    }
    @Override
    public void onFinish(SchedulerTool tool, ReturnT returnT) {
        tool.info("onFinish");
    }
}