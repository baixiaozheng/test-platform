package com.czn.testplatform.service.impl;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.rpc.service.GenericService;
import com.alibaba.fastjson.JSONObject;
import com.czn.testplatform.entity.Interface;
import com.czn.testplatform.repository.InterfaceRepository;
import com.czn.testplatform.service.DubboGenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.Optional;

@Service
public class DubboGenericServiceImpl implements DubboGenericService {
    @Autowired
    private InterfaceRepository interfaceRepository;
    @Override
    public void execute(Long interfaceId, Map<String, Object> params) {
        Optional<Interface> optionalInterface = interfaceRepository.findById(interfaceId);
        if(optionalInterface.isPresent()){
            Interface iface = optionalInterface.get();
            // 引用远程服务
            // 该实例很重量，里面封装了所有与注册中心及服务提供方连接，请缓存
            ReferenceConfig<GenericService> reference = new ReferenceConfig<GenericService>();
            // 弱类型接口名
            reference.setApplication(new ApplicationConfig(iface.getApplicationName()));
            reference.setInterface(iface.getInterfaceClass());
            reference.setRegistry(new RegistryConfig(iface.getRegistryAddress()));
            reference.setCheck(false);
            reference.setCluster("failfast");
            reference.setVersion(StringUtils.isEmpty(iface.getInterfaceVersion())?"1.0.0":iface.getInterfaceVersion());

            // 声明为泛化接口
            reference.setGeneric(true);

            // 用org.apache.dubbo.rpc.service.GenericService可以替代所有接口引用
            GenericService genericService = reference.get();


            String[] paramTypes = null;
            Object[] paramValues = null;
            if(!CollectionUtils.isEmpty(params)){
                paramTypes = new String[params.size()];
                paramValues = new Object[params.size()];
                int i = 0;
                for (Map.Entry<String, Object> entry : params.entrySet()) {
                    paramTypes[i] = entry.getValue().getClass().getTypeName();
                    paramValues[i] = entry.getValue();
                    i++;
                }
            }
            Object result = genericService.$invoke(iface.getMethodName(), paramTypes, paramValues);
            System.out.println(JSONObject.toJSONString(result));
            reference.destroy();//这里一定要用，否则dubbo会一直连接，没几十次调用系统就会挂掉

        }
    }
}
