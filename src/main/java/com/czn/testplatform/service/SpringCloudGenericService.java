package com.czn.testplatform.service;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public interface SpringCloudGenericService {
    void execute(Long interfaceId, Map<String, Object> params);

}
