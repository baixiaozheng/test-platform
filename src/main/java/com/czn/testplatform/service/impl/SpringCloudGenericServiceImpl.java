package com.czn.testplatform.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.alibaba.fastjson.support.springfox.SwaggerJsonSerializer;
import com.czn.testplatform.common.enums.ParamType;
import com.czn.testplatform.entity.Interface;
import com.czn.testplatform.fegin.CallbackAPI;
import com.czn.testplatform.repository.InterfaceRepository;
import com.czn.testplatform.service.SpringCloudGenericService;
import feign.Feign;
import feign.Logger;
import feign.slf4j.Slf4jLogger;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URI;
import java.util.*;

@Service
public class SpringCloudGenericServiceImpl implements SpringCloudGenericService {

    @Autowired
    private InterfaceRepository interfaceRepository;

    @Override
    public void execute(Long interfaceId, Map<String, Object> params) {
        Optional<Interface> optionalInterface = interfaceRepository.findById(interfaceId);
        if (optionalInterface.isPresent()) {
            Interface iface = optionalInterface.get();

            CallbackAPI callbackAPI = Feign.builder()
                    .encoder(new SpringEncoder(feignHttpMessageConverter()))
                    .decoder(new SpringDecoder(feignHttpMessageConverter()))
                    .logger(new Slf4jLogger())
                    .logLevel(Logger.Level.FULL)
                    .target(CallbackAPI.class, "EMPTY"); // 注意：这里的url参数不能为空字符串，但是可以设置为任意字符串值，在这里设置为“EMPTY”



            if(ParamType.BODY_PARAM.equals(iface.getParamType())){
                Object object = JSONObject.parseObject(JSONObject.toJSONString(params));
                callbackAPI.callback(URI.create(iface.getRegistryAddress()), iface.getApplicationName()+"/"+iface.getMethodName(), params, object);
            } else {
                callbackAPI.callback(URI.create(iface.getRegistryAddress()), iface.getApplicationName()+"/"+iface.getMethodName(), params);
            }
        }
    }


    private ObjectFactory<HttpMessageConverters> feignHttpMessageConverter() {
        final HttpMessageConverters httpMessageConverters = new HttpMessageConverters(this.getFastJsonConverter());
        return () -> httpMessageConverters;
    }

    private FastJsonHttpMessageConverter getFastJsonConverter() {
        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();

        List<MediaType> supportedMediaTypes = new ArrayList<>();
        MediaType mediaTypeJson = MediaType.valueOf(MediaType.APPLICATION_JSON_UTF8_VALUE);
        supportedMediaTypes.add(mediaTypeJson);
        converter.setSupportedMediaTypes(supportedMediaTypes);
        FastJsonConfig config = new FastJsonConfig();
        config.getSerializeConfig().put(JSON.class, new SwaggerJsonSerializer());
        config.setSerializerFeatures(SerializerFeature.DisableCircularReferenceDetect);
        converter.setFastJsonConfig(config);
        return converter;
    }
}
