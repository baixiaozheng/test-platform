package com.czn.testplatform.service.impl;

import com.czn.testplatform.entity.Project;
import com.czn.testplatform.repository.ProjectRepository;
import com.czn.testplatform.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public List<Project> list(){
        return projectRepository.findAll();
    }
}
