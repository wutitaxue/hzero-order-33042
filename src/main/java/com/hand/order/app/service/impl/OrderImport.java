package com.hand.order.app.service.impl;

 import com.fasterxml.jackson.databind.ObjectMapper;
 import com.hand.order.app.service.HodrSoHeaderService;
 import com.hand.order.domain.entity.HodrSoHeader;
 import com.hand.order.domain.repository.HodrSoHeaderRepository;
 import org.hzero.boot.imported.app.service.IDoImportService;
 import org.hzero.boot.imported.infra.validator.annotation.ImportService;
 import org.springframework.beans.factory.annotation.Autowired;
 import java.io.IOException;

 @ImportService(templateCode = "HEADERORDER")
 public class OrderImport implements IDoImportService {
     @Autowired
     private ObjectMapper objectMapper;

     @Autowired
     private HodrSoHeaderService hodrSoHeaderService;
     @Override
     public Boolean doImport(String data) {

         HodrSoHeader hodrSoHeader;
         try {
             hodrSoHeader = objectMapper.readValue(data, HodrSoHeader.class);
         } catch (IOException e) {
             return false;
         }
         Long organizationId = 1L;
         hodrSoHeaderService.saveOrder(hodrSoHeader, organizationId);

         return true;
     }
 }