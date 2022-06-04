package com.czn.testplatform.service;

import java.util.Map;

public interface HttpGenericService {
    void execute(Long interfaceId, Map<String, Object> params);
}
