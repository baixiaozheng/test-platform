package com.czn.testplatform.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.czn.testplatform.common.enums.RequestMethodType;
import com.czn.testplatform.entity.Interface;
import com.czn.testplatform.repository.InterfaceRepository;
import com.czn.testplatform.service.HttpGenericService;
import com.czn.testplatform.util.HttpClientUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class HttpGenericServiceImpl implements HttpGenericService {
    @Autowired
    private InterfaceRepository interfaceRepository;
    @Override
    public String execute(Long interfaceId, Map<String, Object> params) {
        String result = "";
        Optional<Interface> optionalInterface = interfaceRepository.findById(interfaceId);
        if (optionalInterface.isPresent()) {
            Interface iface = optionalInterface.get();

            if(RequestMethodType.GET.equals(iface.getMethodType())){
                String url = iface.getRegistryAddress()+"/"+iface.getApplicationName()+"/"+iface.getMethodName();
                String param = getUrlParamsByMap(params);
                if(StringUtils.isNotBlank(param)){
                    url = url+"?"+param;
                }
                result = HttpClientUtils.sendHttpGet(url);
            } else if(RequestMethodType.POST.equals(iface.getMethodType())){
                String url = iface.getRegistryAddress()+"/"+iface.getApplicationName()+"/"+iface.getMethodName();
                result = HttpClientUtils.sendHttpPostJson(url, JSONObject.toJSONString(params));
            }
        }
        return result;
    }

    public static String getUrlParamsByMap(Map<String, Object> map) {
        if (map == null) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            sb.append(entry.getKey() + "=" + entry.getValue());
            sb.append("&");
        }
        String s = sb.toString();
        if (s.endsWith("&")) {
            s = StringUtils.substringBeforeLast(s, "&");
        }
        return s;
    }
}
