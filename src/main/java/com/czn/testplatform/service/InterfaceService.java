package com.czn.testplatform.service;

import com.czn.testplatform.entity.Interface;
import org.springframework.data.domain.Page;

import java.util.List;

public interface InterfaceService {
    Interface save(Interface iface);

    List<Interface> listByProjectId(Long projectId);

    Interface getById(Long id);

    Page<Interface> list(Interface iface, Integer currentPage, Integer pageSize);
}
