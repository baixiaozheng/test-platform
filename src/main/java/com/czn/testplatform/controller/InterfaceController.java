package com.czn.testplatform.controller;

import com.czn.testplatform.common.vo.ResultData;
import com.czn.testplatform.entity.Interface;
import com.czn.testplatform.entity.Project;
import com.czn.testplatform.service.InterfaceService;
import com.czn.testplatform.service.ProjectService;
import com.czn.testplatform.vo.ToAddInterfaceVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/admin/interface")
@Slf4j
public class InterfaceController {

    @Autowired
    private InterfaceService interfaceService;
    @Autowired
    private ProjectService projectService;

    @RequestMapping(value = "list",method = RequestMethod.GET)
    public ModelAndView waitingAuditList(@RequestParam(value = "pageNum", defaultValue = "0") int pageNum, @RequestParam(value = "pageSize", defaultValue = "2") int pageSize) {
        ModelAndView modelAndView = new ModelAndView("interface/list");
        Page<Interface> interfaces = interfaceService.list(new Interface(), pageNum, pageSize);
        modelAndView.addObject("interfaces", interfaces);
        return modelAndView;
    }

    @RequestMapping(value = "toAdd",method = RequestMethod.GET)
    @ResponseBody
    public ResultData toAdd(){
        List<Project> projectList = projectService.list();
        ToAddInterfaceVo vo = new ToAddInterfaceVo();
        vo.setProjects(projectList);
        return ResultData.success(vo);
    }

    @RequestMapping(value = "add",method = RequestMethod.POST)
    @ResponseBody
    public ResultData add(@RequestBody Interface iface){
        interfaceService.save(iface);
        return ResultData.success();
    }

    @RequestMapping("toExecute")
    public ResultData toExecute(Long interfaceId){
        return ResultData.success();
    }

}
