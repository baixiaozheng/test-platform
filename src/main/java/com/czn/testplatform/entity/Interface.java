package com.czn.testplatform.entity;

import com.czn.testplatform.common.enums.InterfaceType;
import com.czn.testplatform.common.enums.ParamType;
import com.czn.testplatform.common.enums.RequestMethodType;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "interface")
public class Interface implements Serializable {
    private static final long serialVersionUID = -4071406265550408094L;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 接口名
     */
    @Column(name = "interface_name")
    private String interfaceName;

    /**
     * 所属项目id
     */
    @Column(name = "project_id")
    private Long projectId;

    /**
     * 提交类型，GET，POST等
     */
    @Column(name = "method_type")
    @Enumerated(EnumType.STRING)
    private RequestMethodType methodType;

    /**
     * 接口类型，dubbo,http,springCloud等
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "interface_type")
    private InterfaceType interfaceType;
    /**
     * 服务名
     */
    @Column(name = "application_name")
    private String applicationName;
    /**
     * 接口类
     */
    @Column(name = "interface_class")
    private String interfaceClass;
    /**
     * 注册中心地址
     */
    @Column(name = "registry_address")
    private String registryAddress;
    /**
     * 方法名
     */
    @Column(name = "method_name")
    private String methodName;

    /**
     * 接口版本号
     * dubbo用
     */
    @Column(name = "interface_version")
    private String interfaceVersion;

    /**
     * 参数类型，url传参还是body传参
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "param_type")
    private ParamType paramType;

    @Column(name = "create_time")
    @CreationTimestamp
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @Column(name = "update_time")
    @UpdateTimestamp
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
}
