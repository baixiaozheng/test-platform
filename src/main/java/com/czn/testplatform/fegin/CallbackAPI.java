package com.czn.testplatform.fegin;

import feign.Headers;
import feign.Param;
import feign.QueryMap;
import feign.RequestLine;
import org.springframework.web.bind.annotation.RequestBody;

import java.net.URI;
import java.util.Map;

public interface CallbackAPI {
    /**
     * 统一回调接口方法，请求消息体格式为JSON，响应消息体格式也为JSON
     * @param host 接口主机地址，如：http://localhost:8080，该参数是实现动态URL的关键
     * @param path 接口路径，如：/test/hello
     * @param queryMap 动态URL参数集合
     * @return
     */
    @RequestLine("POST {path}")
    @Headers({
            "Content-Type: application/json",
            "Accept: application/json"
    })
    Object callback(URI host, @Param("path") String path, @QueryMap Map<String, Object> queryMap);
    /**
     * 统一回调接口方法，请求消息体格式为JSON，响应消息体格式也为JSON
     * @param host 接口主机地址，如：http://localhost:8080，该参数是实现动态URL的关键
     * @param path 接口路径，如：/test/hello
     * @param queryMap 动态URL参数集合
     * @param body 请求消息体对象
     * @return
     */
    @RequestLine("POST {path}")
    @Headers({
            "Content-Type: application/json",
            "Accept: application/json"
    })
    Object callback(URI host, @Param("path") String path, @QueryMap Map<String, Object> queryMap, @RequestBody Object body);
    /**
     * 统一回调接口方法，请求消息体格式为JSON，响应消息体格式也为JSON
     * @param uri 完整的请求路径地址，如：http://localhost:8080/test/hello
     * @param queryMap 动态URL参数集合
     * @return
     */
    @RequestLine("POST")
    @Headers({
            "Content-Type: application/json",
            "Accept: application/json"
    })
    Object callback(URI uri, @QueryMap Map<String, Object> queryMap);
}

