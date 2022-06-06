package com.czn.testplatform.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.czn.testplatform.common.enums.InterfaceType;
import com.czn.testplatform.common.enums.ParamType;
import com.czn.testplatform.common.vo.ResultData;
import com.czn.testplatform.entity.Interface;
import com.czn.testplatform.service.DubboGenericService;
import com.czn.testplatform.service.ExecuteService;
import com.czn.testplatform.service.HttpGenericService;
import com.czn.testplatform.service.InterfaceService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
@Slf4j
public class ExecuteServiceImpl implements ExecuteService {

    @Autowired
    private InterfaceService interfaceService;
    @Autowired
    private DubboGenericService dubboGenericService;
    @Autowired
    private  SpringCloudGenericServiceImpl springCloudGenericService;
    @Autowired
    private HttpGenericService httpGenericService;
    @Override
    public ResultData execute(Long interfaceId) {
        Interface iface = interfaceService.getById(interfaceId);
        String result = "";
        if(iface == null){
            return ResultData.fail("接口不存在");
        }
        Map<String, Object> params = new LinkedHashMap<>();
        if(ParamType.QUERY_PARAM.equals(iface.getParamType())){
            params = getUrlParams(iface.getParam());
        } else {
            params = JSONObject.parseObject(iface.getParam(), LinkedHashMap.class);
        }
        if (InterfaceType.HTTP.equals(iface.getInterfaceType())){
            result = httpGenericService.execute(interfaceId, params);
        } else if(InterfaceType.DUBBO.equals(iface.getInterfaceType())){
            result = dubboGenericService.execute(interfaceId, params);
        } else if(InterfaceType.SPRING_CLOUD.equals(iface.getInterfaceType())){
            result = springCloudGenericService.execute(interfaceId,params);
        }
        if(StringUtils.isBlank(result)){
            return ResultData.fail("执行失败");
        }
        return ResultData.success(result);
    }

    /**
     * 将url参数转换成map
     *
     * @param param aa=11&bb=22&cc=33
     * @return
     */
    public static Map<String, Object> getUrlParams(String param) {
        Map<String, Object> map = new LinkedHashMap<>();
        if (StringUtils.isBlank(param)) {
            return map;
        }
        String[] params = param.split("&");
        for (int i = 0; i < params.length; i++) {
            String[] p = params[i].split("=");
            if (p.length == 2) {
                map.put(p[0], p[1]);
            }
        }
        return map;
    }
}
