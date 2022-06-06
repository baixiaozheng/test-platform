package com.czn.testplatform.service.impl;

import com.czn.testplatform.entity.Interface;
import com.czn.testplatform.entity.Project;
import com.czn.testplatform.repository.InterfaceRepository;
import com.czn.testplatform.repository.ProjectRepository;
import com.czn.testplatform.service.InterfaceService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class InterfaceServiceImpl implements InterfaceService {

    @Autowired
    private InterfaceRepository interfaceRepository;
    @Autowired
    private ProjectRepository projectRepository;

    @Override
    @Transactional
    public Interface save(Interface iface){
        return interfaceRepository.save(iface);
    }

    @Override
    public List<Interface> listByProjectId(Long projectId){
        return interfaceRepository.findAllByProjectId(projectId);
    }

    @Override
    public Interface getById(Long id) {
        Optional<Interface> optional = interfaceRepository.findById(id);
        Interface anInterface = optional.orElse(null);
        if(anInterface!=null){
            Optional<Project> project = projectRepository.findById(anInterface.getProjectId());
            project.ifPresent(value -> anInterface.setProjectName(value.getProjectName()));
        }
        return anInterface;
    }

    @Override
    public Page<Interface> list(Interface iface, Integer currentPage, Integer pageSize) {
        Pageable pageable = PageRequest.of(currentPage, pageSize, Sort.by("createTime").descending());

        Specification<Interface> specification = new Specification<Interface>() {
            @Override
            public Predicate toPredicate(Root<Interface> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<Predicate>();
                if(iface.getProjectId()!=null){
                    list.add(criteriaBuilder.equal(root.get("projectId").as(Long.class), iface.getProjectId()));
                }
                if(StringUtils.isNotBlank(iface.getInterfaceName())){
                    list.add(criteriaBuilder.equal(root.get("interfaceName()").as(String.class), "%" + iface.getInterfaceName() + "%"));
                }
                return criteriaBuilder.and(list.toArray(new Predicate[list.size()]));
            }
        };
        Page<Interface> interfaces =interfaceRepository.findAll(specification, pageable);
        return interfaces;
    }
}
