package com.hand.order.domain.service.impl;

import com.hand.order.domain.service.OrderMessage;
import org.hzero.boot.message.MessageClient;
import org.hzero.boot.message.entity.Receiver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class OrderMessageImpl extends OrderMessage {

    @Autowired
    private MessageClient messageClient;

    @Override
    public void orderMessage() {
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
        String context = null;
        args.put("parmA", context);
        messageClient.sendEmail(tenantId, serverCode, messageTemplateCode, receiverList, args, null);
    }
}