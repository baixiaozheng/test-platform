package com.czn.testplatform.controller;

import com.czn.testplatform.service.DubboGenericService;
import com.czn.testplatform.service.SpringCloudGenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;

@RestController
public class ExecuteController {

    @Autowired
    private DubboGenericService dubboGenericService;
    @Autowired
    private SpringCloudGenericService springCloudGenericService;

    @GetMapping("execute")
    public void executeTest(Long interfaceId){
        LinkedHashMap<String, Object> params = new LinkedHashMap<>();
        params.put("name", "张三");
        params.put("id", 1L);
        params.put("age", 12);


        dubboGenericService.execute(interfaceId,params);
    }

    @GetMapping("execute2")
    public void execute2Test(Long interfaceId){
        LinkedHashMap<String, Object> params = new LinkedHashMap<>();
        params.put("name", "张三");
        params.put("id", 2L);
        params.put("age", 14);

        springCloudGenericService.execute(interfaceId,params);
    }
    @GetMapping("execute3")
    public void execute3Test(Long interfaceId){
        LinkedHashMap<String, Object> params = new LinkedHashMap<>();
        params.put("name", "张三");
        params.put("gender", "男");
        params.put("age", 14);

        springCloudGenericService.execute(interfaceId,params);
    }
}
