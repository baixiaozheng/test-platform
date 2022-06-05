package com.czn.testplatform.repository;

import com.czn.testplatform.entity.Interface;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InterfaceRepository extends JpaRepository<Interface, Long>, JpaSpecificationExecutor<Interface> {

    List<Interface> findAllByProjectId(Long projectId);

    @Override
    Interface getById(Long id);

    @Override
    Page<Interface> findAll(Pageable pageable);
}
