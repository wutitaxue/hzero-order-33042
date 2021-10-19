package com.hand.order;

import io.choerodon.core.iam.ResourceLevel;
import io.choerodon.swagger.annotation.Permission;
import io.swagger.annotations.ApiOperation;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import io.choerodon.resource.annoation.EnableChoerodonResourceServer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@EnableChoerodonResourceServer
@EnableDiscoveryClient
@SpringBootApplication
public class Hzeroorder33042Application {

    public static void main(String[] args) {
        SpringApplication.run(Hzeroorder33042Application.class, args);
    }
}



