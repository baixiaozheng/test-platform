package com.czn.testplatform.repository;

import com.czn.testplatform.entity.Interface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface InterfaceRepository extends JpaRepository<Interface, Long>, JpaSpecificationExecutor<Interface> {
}
