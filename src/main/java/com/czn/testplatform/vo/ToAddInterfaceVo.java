package com.czn.testplatform.vo;

import com.czn.testplatform.entity.Project;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

@Getter @Setter
@Accessors(chain = true)
public class ToAddInterfaceVo implements Serializable {
    private static final long serialVersionUID = -6206293591257988070L;

    private List<Project> projects;
}
